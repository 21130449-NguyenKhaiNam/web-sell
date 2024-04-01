
import dao.DashboadDAOImp;

import models.Order;
import models.OrderDetail;

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
        DashboadDAOImp dshb = new DashboadDAOImp();
        List<Order> listOrderByMonth;
        List<String> listOrderId = new ArrayList<>();
        List<Double> listTotalRevenueByMonth = new ArrayList<>();

        for (int i = 1; i <= 12; i++) {
            listOrderByMonth = dshb.getOrderByMonth(i);

            for (Order order : listOrderByMonth) {
                listOrderId.add(order.getId());
            }

            double totalRevenue = 0.0;
            for (String orderId : listOrderId) {
                List<OrderDetail> listQuantityByMonth = dshb.getOrderByOrderId(orderId);

                for (OrderDetail orderDetail : listQuantityByMonth) {
                    int quantity = orderDetail.getQuantityRequired();
                    double price = orderDetail.getPrice();
                    totalRevenue += quantity * price;
                    System.out.println(totalRevenue);
                }
            }

            listTotalRevenueByMonth.add(totalRevenue);
            System.out.println("Month " + i + ": Total Revenue = $" + totalRevenue);

            listOrderId.clear();
        }

// In ra doanh thu theo từng tháng
        System.out.println("Total Revenue by Month: " + listTotalRevenueByMonth);
    }
    public void checkUser(int id){

    }
    public void method1(){

    }
    public void method2(){

    }
}
