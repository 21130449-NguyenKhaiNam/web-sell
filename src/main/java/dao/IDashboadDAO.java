package dao;

import models.Order;
import models.OrderDetail;
import models.Product;

import java.util.List;

public interface IDashboadDAO {
    int countUser();
    int countProduct();
    int countOrder();
    int countReview();
    List<OrderDetail> getTop5Product();
    List<Product> getTop5ProductName(int productId);
    List<OrderDetail> getTop5ProductQuantity(int productId);
    List<Order> getOrderByMonth(int month);
    List<OrderDetail> getOrderByOrderId(String orderId);
    List<OrderDetail> getOrderDetailByOrderId(String orderId);
}
