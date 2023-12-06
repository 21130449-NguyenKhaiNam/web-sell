package services;

import dao.UserDAO;
import dao.UserDAOImplement;
import models.User;
import utils.Encoding;
import utils.Token;
import utils.ValidationError;

import javax.mail.MessagingException;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class AuthenticateServices {
    private static AuthenticateServices INSTANCE;

    UserDAO userDAO = new UserDAOImplement();

    private AuthenticateServices() {
    }

    public static AuthenticateServices getINSTANCE() {
        if (INSTANCE == null) INSTANCE = new AuthenticateServices();
        return INSTANCE;
    }

    public ValidationError checkSignIn(String username, String password) {
        ValidationError validationError = new ValidationError();
//        Check username empty
        if (username.isEmpty()) {
            validationError.setFieldUsername("Tài khoản không được để trống");
        }
//          Check pass empty
        if (password.isEmpty()) {
            validationError.setFieldPassword("Mật khẩu không được để trống");
        }

//        Check user in db
        List<User> users = userDAO.selectAccount(username, "1");

//        Check username
        if (users.size() != 1) {
            validationError.setFieldUsername("Tên đăng nhập không tồn tại.");
            return validationError;
        }

//        Check password
        User user = users.get(0);
        String encode = Encoding.getINSTANCE().toSHA1(password);
        if (user.getPasswordEncoding().equals(encode)) {
            validationError.setObjReturn(user);
        } else {
            validationError.setFieldPassword("Mật khẩu sai");
        }
        return validationError;
    }

    public ValidationError checkSignUp(String username, String email, String password, String confirmPassword) {
        ValidationError validationError = new ValidationError();
        final String REGEX_EMAIL_VALID = "^(.+)@(.+)$";
        final int minLength = 6;
        String errorEmail = "Email đã tồn tại";
        String errorEmailRegex = "Email không đúng định dạng";
        String errorUsername = "Tên đăng nhập đã tồn tại";
        String errorPassword = "Mật khẩu có tối thiểu " + minLength + " kí tự";
        String errorPasswordConfirm = "Mật khẩu nhập lại không hợp lê";
        String emptyField = "Không được để trống trường này";

//        checkEmpty
        int countError = 0;
        if (username.isBlank()) {
            validationError.setFieldUsername(emptyField);
            countError++;
        }
        if (email.isBlank()) {
            validationError.setFieldEmail(emptyField);
            countError++;
        }

        if (password.isBlank()) {
            validationError.setFieldPassword(emptyField);
            countError++;
        }
        if (confirmPassword.isBlank()) {
            validationError.setFieldConfirmPassword(emptyField);
            countError++;
        }

//        Prevent check in db
        if (countError != 0) {
            return validationError;
        }

//        Check Username Exist
        if (!userDAO.findUsername(username).isEmpty()) {
            validationError.setFieldUsername(errorUsername);
            countError++;
        }

//        Check email regex format
        Pattern pattern = Pattern.compile(REGEX_EMAIL_VALID);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.find()) {
            validationError.setFieldEmail(errorEmailRegex);
            countError++;
        } else {
//        Check Email Exist
            if (!userDAO.findEmail(email).isEmpty()) {
                validationError.setFieldEmail(errorEmail);
                countError++;
            }
        }

//        Check Pass
        if (password.length() < minLength) {
            validationError.setFieldPassword(errorPassword);
            countError++;
        }

//        Check confirmPassword != password
        if (!password.equals(confirmPassword)) {
            validationError.setFieldConfirmPassword(errorPasswordConfirm);
            countError++;
        }

        if (countError == 0) {
            User user = new User();
            user.setUsername(username);
            user.setEmail(email);
            user.setPasswordEncoding(Encoding.getINSTANCE().toSHA1(password));
            validationError.setObjReturn(user);
        }
        return validationError;
    }


    public void createUser(User user) {
//        Create token
        String tokenVerify = Token.generateToken();
        user.setTokenVerify(tokenVerify);

//        Create Time Stamp
        Timestamp timestampExpiredToken = addTime(LocalDateTime.now(), MailProperties.getDurationTokenVerify());
        user.setTokenVerifyTime(timestampExpiredToken);

//        Insert user to db
        userDAO.insert(user);
        try {
            IMailServices mailServices = new MailVerifyServices(user.getEmail(), user.getUsername(), tokenVerify, timestampExpiredToken);
            mailServices.send();
            System.out.println("Send mail success");
        } catch (MessagingException ignored) {
            ignored.printStackTrace();
        }
    }

    public boolean verify(String username, String token) {
        List<User> users = userDAO.selectTokenVerify(username);
        if (users.size() != 1) return false;
        User user = users.get(0);
        Timestamp userTokenExpired = user.getTokenVerifyTime();
        Timestamp timestampCurrent = Timestamp.valueOf(LocalDateTime.now());
        if (timestampCurrent.compareTo(userTokenExpired) <= 0) {
            userDAO.updateTokenVerify(user.getId(), null, null);
            userDAO.updateVerify(user.getId(), true);
            return true;
        }
        return false;
    }

    public ValidationError checkForgetPassword(String email) {
        ValidationError validationError = new ValidationError();

        String errorEmail = "Tài khoản ứng với email này chưa đăng ký hoặc chưa xác thực";

        List<User> users = userDAO.selectByEmail(email, "1");
        if (users.size() == 1) {
            User user = users.get(0);
            validationError.setObjReturn(user);
        } else {
            validationError.setFieldEmail(errorEmail);
        }
        return validationError;
    }

    public void sendMailResetPassword(User user) {
//            Create token
        String token = Token.generateToken();
        Timestamp timestampExpiredToken = addTime(LocalDateTime.now(), MailProperties.getDurationTokenRestPassword());
        userDAO.updateTokenResetPassword(user.getId(), token, timestampExpiredToken);
//            SEND MAIL
        try {
            IMailServices mailServices = new MailResetPasswordServices(user.getEmail(), user.getEmail(), token);
            mailServices.send();
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean resetPassword(String email, String token) {
        List<User> users = userDAO.selectTokenResetPassword(email);
        if (users.size() != 1) return false;
        User user = users.get(0);
        String userToken = user.getTokenResetPassword();
        Timestamp userTokenExpired = user.getTokenResetPasswordTime();
        Timestamp timestampCurrent = Timestamp.valueOf(LocalDateTime.now());
        return timestampCurrent.compareTo(userTokenExpired) <= 0 && token.equals(userToken);
    }

    public boolean updatePassword(String email, String password) {
        String passwordEncoding = Encoding.getINSTANCE().toSHA1(password);
        List<User> users = userDAO.selectByEmail(email, "1");
        User user = users.get(0);
        if (user.getTokenResetPassword() != null) {
            userDAO.updatePasswordEncoding(user.getId(), passwordEncoding);
            userDAO.updateTokenResetPassword(user.getId(), null, null);
            return true;
        }
        return false;
    }

    private static Timestamp addTime(LocalDateTime dateTime, String duration) {
        // Format duration to Time
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime durationTime = LocalTime.parse(duration, formatter);

        // CurrentTime + durationTime
        LocalDateTime newDateTime = dateTime
                .plusHours(durationTime.getHour())
                .plusMinutes(durationTime.getMinute())
                .plusSeconds(durationTime.getSecond());

        Timestamp result = Timestamp.valueOf(newDateTime);
        return result;
    }
}
