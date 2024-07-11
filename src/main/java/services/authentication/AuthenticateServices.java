package services.authentication;

import dao.UserDAO;
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
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class AuthenticateServices {
    private static final String STATE_VERIFY = "1";
    private static AuthenticateServices INSTANCE;
    UserDAO userDAO = new UserDAO();

    private AuthenticateServices() {
    }

    public static AuthenticateServices getINSTANCE() {
        if (INSTANCE == null) INSTANCE = new AuthenticateServices();
        return INSTANCE;
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

    public Validation checkEmailExists(String email) {
        Validation validation = new Validation();
        validation.setFieldEmail(email.isBlank() ?
                "Email không được để trống" :
                (
                        !userDAO.selectByEmail(email, null).isEmpty() ?
                                "Email đã tồn tại." :
                                ""
                )
        );
        return validation;
    }

    public Validation checkUsernameExists(String username) {
        Validation validation = new Validation();
        validation.setFieldUsername(username.isBlank() ?
                "Tên đăng nhập không được để trống" :
                (
                        !userDAO.selectByUsername(username, null).isEmpty() ?
                                "Tên đăng nhập đã tồn tại." :
                                ""
                )
        );
        return validation;
    }

    //    Kiểm tra đăng nhập bằng email (google, facebook)
    public User checkSignIn(String email) {
        List<User> users = userDAO.selectByEmail(email, STATE_VERIFY);
        if (users.size() != 1) return null;
        User user = users.get(0);
        User userValid = extractUser(user);
        return userValid;
    }

    //    Kiểm tra đăng nhập bằng username + password
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
        List<User> users = userDAO.selectByUsername(username, STATE_VERIFY);

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

    public Validation checkSignUp(User user, String confirmPassword) {
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
        if (user.getUsername().isBlank()) {
            validation.setFieldUsername(emptyField);
            countError++;
        }
        if (user.getEmail().isBlank()) {
            validation.setFieldEmail(emptyField);
            countError++;
        }

        if (user.getPasswordEncoding().isBlank()) {
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
        if (!userDAO.findUsername(user.getUsername()).isEmpty()) {
            validation.setFieldUsername(errorUsername);
            countError++;
        }

//        Check email regex format
        Pattern pattern = Pattern.compile(REGEX_EMAIL_VALID);
        Matcher matcher = pattern.matcher(user.getEmail());
        if (!matcher.find()) {
            validation.setFieldEmail(errorEmailRegex);
            countError++;
        } else {

//        Check Email Exist
            if (!userDAO.findEmail(user.getEmail()).isEmpty() && !isValidEmailAddress(user.getEmail())) {
                validation.setFieldEmail(errorEmail);
                countError++;
            }
        }

//        Check Pass
        ValidatePassword validatePassword = new ValidatePassword(user.getPasswordEncoding());
        if (!validatePassword.check()) {
            validation.setFieldPassword(errorPassword);
            countError++;
        }

//        Check confirmPassword != password
        if (!user.getPasswordEncoding().equals(confirmPassword)) {
            validation.setFieldConfirmPassword(errorPasswordConfirm);
            countError++;
        }

        if (countError == 0) {
            user.setPasswordEncoding(Encoding.getINSTANCE().toSHA1(user.getPasswordEncoding()));
            user.setRole(RoleProperties.getINSTANCE().getGuest());
            validation.setObjReturn(user);
        }
        return validation;
    }

    public static boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
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
        if (users.size() == 1 && isValidEmailAddress(email)) {
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

    //    Trích xuất ra các giá trị cần thiết của user
//    Ex: loại bỏ phần mật khẩu
    private User extractUser(User user) {
        User userValid = new User();
        userValid.setId(user.getId());
        userValid.setUsername(user.getUsername());
        userValid.setRole(user.getRole());
        userValid.setAvatar(user.getAvatar());
        userValid.setFullName(user.getFullName());
        userValid.setPhone(user.getPhone());
        userValid.setGender(user.getGender());
        userValid.setEmail(user.getEmail());
        userValid.setPhone(user.getPhone());
        userValid.setAddress(user.getAddress());
        userValid.setBirthDay(user.getBirthDay());
        return userValid;
    }

}