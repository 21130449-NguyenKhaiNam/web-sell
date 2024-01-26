
import dao.DashboadDAO;
import dao.UserDAO;
import dao.UserDAOImplement;
import models.OrderDetail;
import models.Product;
import models.User;
import services.AuthenticateServices;

import java.sql.Date;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class TestDB {
    public static void main(String[] args) throws IOException {
//        UserDAO userDAO = new UserDAOImplement();
//        System.out.println(userDAO.selectByEmail("ducvui2003@gmail.com", "1"));
////        System.out.println(userDAO.selectById(24));
////        userDAO.updatePassword(23, "123456");
////        User user = AuthenticateServices.getINSTANCE().checkSignIn("ducvui2003", "123456");
////        System.out.println(user.isRole());
////        System.out.println(user);
////        System.out.println(user);
////        String str = "ducvui2003";
////        String encoding = Encoding.getINSTANCE().toSHA1(str);
////        System.out.println(encoding);
////        UserDAO userDAO = new UserImplement();
//////        System.out.println(userDAO.insert(new User("ducvui20", "20012001", "ducvui@gmail.com", false, false)));
////        List<User> userList = userDAO.selectALl();
////        for (User item : userList) {
////            String passEncoding = Encoding.getINSTANCE().toSHA1(item.getPasswordEncoding());
//////            System.out.println( userDAO.updatePasswordEncoding(item.getId(), passEncoding));
////        }
////        System.out.println(  userDAO.updateTokenVerify(23, null));
//
//        UserDAO getUserByID = new UserDAOImplement();
//        System.out.println(getUserByID.getUserByID(1));
//
//        UserDAO testUpdateUser = new UserDAOImplement();
//        testUpdateUser.updateUserByID(1,"HieuNguyen","Nguyễn Chí Hai","Nam","HieuNguyen@gmail.com","0703637448","164 Ngô Tất Tố, Bình Định", Date.valueOf("1990-01-01"));
        DashboadDAO dshb = new DashboadDAO();
        List<OrderDetail> top5Product= dshb.getTop5Product();
        List<Product> listTop5Product = new ArrayList<>();
        List<Integer> listProducId = new ArrayList<>();

        List<String> listtop5NameProduct = new ArrayList<>();
        List<Integer> listtop5Quantity = new ArrayList<>();

        for (OrderDetail top5OrderDetail:top5Product) {
            listProducId.add(top5OrderDetail.getId());
        }
        for (Integer productId:listProducId) {
//            listTop5Product = dshb.getTop5ProductName(productId);
//            listtop5NameProduct.add(listTop5Product.get(0).getName());
            listTop5Product = dshb.getTop5ProductQuantity(productId);
            System.out.println(listTop5Product);
//            listtop5Quantity.add(listTop5Product);
        }
//        for (Product product:listTop5Product) {
//            listtop5NameProduct.add(product.getName());
//        }
//        System.out.println(listtop5NameProduct);
//        System.out.println(listtop5NameProduct);

    }
    public void checkUser(int id){

    }
    public void method1(){

    }
    public void method2(){

    }
}
