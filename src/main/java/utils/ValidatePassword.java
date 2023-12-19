package utils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidatePassword {
    String password;
    static final int MIN_LENGTH = 8;
    static final String REGEX_LEAST_1_CHAR_UPPER = "[A-Z]{1,}";
    static final String REGEX_LEAST_1_CHAR_LOWER = "[a-z]{1,}";
    static final String REGEX_LEAST_1_CHAR_NUMBER = "[\\d]{1,}";
    static final String REGEX_LEAST_1_CHAR_SPECIAL = "[^\\s\\w]{1,}";
    static final String REGEX_NO_SPACE = "\\s";

    Map<String, String> errorMap;

    public ValidatePassword(String password) {
        this.password = password;
        this.errorMap = new HashMap<>();
    }

    public boolean check() {
        boolean isLowerCharValid = checkLowerChar();
        boolean isNumberCharValid = checkNumberChar();
        boolean isUpperCharValid = checkUpperChar();
        boolean isMinLengthValid = checkMinLength();
        boolean isSpecialCharValid = checkSpecialChar();
        boolean isNoSpaceValid = checkNoSpace();

        return isLowerCharValid && isNumberCharValid && isUpperCharValid && isMinLengthValid && isSpecialCharValid && isNoSpaceValid;
    }

    private boolean checkMinLength() {
        if (password.length() >= MIN_LENGTH)
            return true;
        else {
            errorMap.put("char-min-length", "Có ít nhất 8 kí tự");
            return false;
        }
    }

    private boolean checkUpperChar() {
        Pattern pattern = Pattern.compile(REGEX_LEAST_1_CHAR_UPPER);
        Matcher matcher = pattern.matcher(password);
        if (matcher.find())
            return true;
        else {
            errorMap.put("char-upper", "Có ít nhất 1 chữ hoa như: A, B, C,..");
            return false;
        }
    }

    private boolean checkLowerChar() {
        Pattern pattern = Pattern.compile(REGEX_LEAST_1_CHAR_LOWER);
        Matcher matcher = pattern.matcher(password);
        if (matcher.find())
            return true;
        else {
            errorMap.put("char-lower", "Có ít nhất 1 chữ thường như a, b, c,...");
            return false;
        }
    }

    private boolean checkNumberChar() {
        Pattern pattern = Pattern.compile(REGEX_LEAST_1_CHAR_NUMBER);
        Matcher matcher = pattern.matcher(password);
        if (matcher.find())
            return true;
        else {
            errorMap.put("char-number", "Có ít nhất 1 chữ số như 1, 2, 3,...");
            return false;
        }

    }

    private boolean checkSpecialChar() {
        Pattern pattern = Pattern.compile(REGEX_LEAST_1_CHAR_SPECIAL);
        Matcher matcher = pattern.matcher(password);
        if (matcher.find())
            return true;
        else {
            errorMap.put("char-special", "Có ít nhất 1 kí tự đặc biệt như \"@\", \".\", \":\",....");
            return false;
        }
    }

    private boolean checkNoSpace() {
        Pattern pattern = Pattern.compile(REGEX_NO_SPACE);
        Matcher matcher = pattern.matcher(password);
        if (matcher.find()) {
            errorMap.put("no-space", "Mật khẩu không được có khoảng trắng.");
            return false;
        } else
            return true;
    }

    public String getPassword() {
        return password;
    }

    public Map<String, String> getErrorMap() {
        return errorMap;
    }
}
