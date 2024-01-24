import dao.GeneralDao;
import dao.OrderDAO;
import dao.UserDAO;
import dao.UserDAOImplement;

import database.JDBIConnector;
import models.Order;
import models.OrderDetail;
import models.User;
import services.AuthenticateServices;
import services.ReviewServices;
import utils.ProductFactory;

import java.util.ArrayList;
import java.util.List;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestSelect {
    public static void main(String[] args) {
//        System.out.println(ProductFactory.getListSizesByProductId(3));
//        System.out.println(ReviewServices.getINSTANCE().getListReview(43));
//        System.out.println(AuthenticateServices.getINSTANCE().checkSignIn("Ozus", "Qq@12345").getObjReturn());
//        String status = request.getParameter("status");

        OrderDAO order = new OrderDAO();
        List<Order> listOrder = order.getOrderByUserIdAndStatusOrder(1,"ĐÃ XÁC NHẬN");

        List<Integer> listOrderId = new ArrayList<>();
        for (Order orders:listOrder) {
            listOrderId.add(orders.getId());
        }
        List<OrderDetail> listOrderDetail = order.getOrderDetailByOrderId(listOrderId);
        System.out.println(listOrderDetail);

        for(OrderDetail orderdetails:listOrderDetail) {
            System.out.println(order.getNameImageByProductId(order.getProductInOrderDetail(orderdetails.getProductId()).get(0).getId()).get(0).getNameImage());
        }
    }
}
