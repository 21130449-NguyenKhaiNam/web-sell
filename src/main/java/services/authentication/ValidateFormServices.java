package services.authentication;

import dao.UserDAO;
import dao.UserDAOImplement;
import models.User;
import properties.MailProperties;
import properties.RoleProperties;
import services.mail.IMailServices;
import services.mail.MailResetPasswordServices;
import services.mail.MailVerifyServices;
import utils.Encoding;
import utils.Token;
import utils.ValidatePassword;
import utils.Validation;

import javax.mail.MessagingException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ValidateFormServices {
    private static ValidateFormServices INSTANCE;

    UserDAO userDAO = new UserDAOImplement();

    private ValidateFormServices() {
    }

    public static ValidateFormServices getINSTANCE() {
        if (INSTANCE == null) INSTANCE = new ValidateFormServices();
        return INSTANCE;
    }

    public Validation checkSignIn(String username, String password) {
        Validation validation = new Validation();
//        Check username empty
        if (username.isEmpty()) {
            validation.setFieldUsername("Tài khoản không được để trống");
        }
//          Check pass empty
        if (password.isEmpty()) {
            validation.setFieldPassword("Mật khẩu không được để trống");
        }

//        Check user in db
        List<User> users = userDAO.selectAccount(username, "1");

//        Check username
        if (users.size() != 1) {
            validation.setFieldUsername("Tên đăng nhập không tồn tại.");
            return validation;
        }

//        Check password
        User user = users.get(0);
        String encode = Encoding.getINSTANCE().toSHA1(password);
        if (user.getPasswordEncoding().equals(encode)) {
            validation.setObjReturn(user);
        } else {
            validation.setFieldPassword("Mật khẩu sai");
        }
        return validation;
    }

    public Validation checkSignUp(String username, String email, String password, String confirmPassword) {
        Validation validation = new Validation();
        final String REGEX_EMAIL_VALID = "^(.+)@(.+)$";
        String errorEmail = "Email đã tồn tại";
        String errorEmailRegex = "Email không đúng định dạng";
        String errorUsername = "Tên đăng nhập đã tồn tại";
        String errorPassword = "Mật khẩu có không thỏa điều kiện";
        String errorPasswordConfirm = "Mật khẩu nhập lại không hợp lệ";

        String emptyField = "Không được để trống trường này";

//        checkEmpty
        int countError = 0;
        if (username.isBlank()) {
            validation.setFieldUsername(emptyField);
            countError++;
        }
        if (email.isBlank()) {
            validation.setFieldEmail(emptyField);
            countError++;
        }

        if (password.isBlank()) {
            validation.setFieldPassword(emptyField);
            countError++;
        }
        if (confirmPassword.isBlank()) {
            validation.setFieldConfirmPassword(emptyField);
            countError++;
        }

//        Prevent check in db
        if (countError != 0) {
            return validation;
        }

//        Check Username Exist
        if (!userDAO.findUsername(username).isEmpty()) {
            validation.setFieldUsername(errorUsername);
            countError++;
        }

//        Check email regex format
        Pattern pattern = Pattern.compile(REGEX_EMAIL_VALID);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.find()) {
            validation.setFieldEmail(errorEmailRegex);
            countError++;
        } else {

//        Check Email Exist
            if (!userDAO.findEmail(email).isEmpty()) {
                validation.setFieldEmail(errorEmail);
                countError++;
            }
        }

//        Check Pass
        ValidatePassword validatePassword = new ValidatePassword(password);
        if (!validatePassword.check()) {
            validation.setFieldPassword(errorPassword);
            countError++;
        }

//        Check confirmPassword != password
        if (!password.equals(confirmPassword)) {
            validation.setFieldConfirmPassword(errorPasswordConfirm);
            countError++;
        }

        if (countError == 0) {
            User user = new User();
            user.setUsername(username);
            user.setEmail(email);
            user.setPasswordEncoding(Encoding.getINSTANCE().toSHA1(password));
            user.setRole(RoleProperties.getINSTANCE().getGuest());
            validation.setObjReturn(user);
        }
        return validation;
    }

    public Map<String, String> checkPasswordTemplate(String password) {
        ValidatePassword validatePassword = new ValidatePassword(password);
        validatePassword.check();
        if (validatePassword.getErrorMap().isEmpty())
            return null;
        return validatePassword.getErrorMap();
    }

    public void createUser(User user) {
//        Create token
        String tokenVerify = Token.generateToken();

//        Create Time Stamp
        Timestamp timestampExpiredToken = addTime(LocalDateTime.now(), MailProperties.getDurationTokenVerify());

//        Check user exist in db: resend email verify for user
        List<User> userList = userDAO.findUsername(user.getUsername());
        if (!userList.isEmpty()) {
            userDAO.updateTokenVerify(user.getId(), tokenVerify, timestampExpiredToken);
        } else {
//        Insert user to db
            user.setTokenVerify(tokenVerify);
            user.setTokenVerifyTime(timestampExpiredToken);
            userDAO.insert(user);
        }
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

    public Validation checkForgetPassword(String email) {
        Validation validation = new Validation();

        String errorEmail = "Tài khoản ứng với email này chưa đăng ký hoặc chưa xác thực";

        List<User> users = userDAO.selectByEmail(email, "1");
        if (users.size() == 1) {
            User user = users.get(0);
            validation.setObjReturn(user);
        } else {
            validation.setFieldEmail(errorEmail);
        }
        return validation;
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
        if (userTokenExpired == null) return false;
        Timestamp timestampCurrent = Timestamp.valueOf(LocalDateTime.now());
        return timestampCurrent.compareTo(userTokenExpired) <= 0 && token.equals(userToken);
    }

    public boolean updatePassword(String email, String password) {
        String passwordEncoding = Encoding.getINSTANCE().toSHA1(password);
        List<User> users = userDAO.selectByEmail(email, "1");
        if (users.isEmpty()) return false;
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
