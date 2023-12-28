
import dao.UserDAO;
import dao.UserDAOImplement;
import models.User;
import services.AuthenticateServices;

import java.io.IOException;

public class TestDB {
    public static void main(String[] args) throws IOException {
        UserDAO userDAO = new UserDAOImplement();
        System.out.println(userDAO.selectByEmail("ducvui2003@gmail.com", "1"));
//        System.out.println(userDAO.selectById(24));
//        userDAO.updatePassword(23, "123456");
//        User user = AuthenticateServices.getINSTANCE().checkSignIn("ducvui2003", "123456");
//        System.out.println(user.isRole());
//        System.out.println(user);
//        System.out.println(user);
//        String str = "ducvui2003";
//        String encoding = Encoding.getINSTANCE().toSHA1(str);
//        System.out.println(encoding);
//        UserDAO userDAO = new UserImplement();
////        System.out.println(userDAO.insert(new User("ducvui20", "20012001", "ducvui@gmail.com", false, false)));
//        List<User> userList = userDAO.selectALl();
//        for (User item : userList) {
//            String passEncoding = Encoding.getINSTANCE().toSHA1(item.getPasswordEncoding());
////            System.out.println( userDAO.updatePasswordEncoding(item.getId(), passEncoding));
//        }
//        System.out.println(  userDAO.updateTokenVerify(23, null));
    }
    public void checkUser(int id){

    }
    public void method1(){

    }
    public void method2(){

    }
}
