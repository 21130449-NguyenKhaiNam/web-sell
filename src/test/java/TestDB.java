import models.User;
import services.UserServices;

public class TestDB {
    public static void main(String[] args) {
        User user = UserServices.getINSTANCE().checkSignIn("ducvui2003", "123456");
        System.out.println(user);
    }
}
