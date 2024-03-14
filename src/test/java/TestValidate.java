import utils.ValidatePassword;

public class TestValidate {
    public static void main(String[] args) {
        testValidate();
    }
    public static void testValidate(){
        ValidatePassword validatePassword = new ValidatePassword("    ");
        validatePassword.check();
        System.out.println( validatePassword.getErrorMap());
    }
}
