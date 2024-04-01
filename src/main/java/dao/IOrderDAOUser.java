package dao;

import models.Image;
import models.Order;
import models.OrderDetail;
import models.Product;

import java.util.List;

public interface IOrderDAOUser {
    List<Order> getOrderByUserIdAndStatusOrder(int userId, int statusOrder);

    List<Order> getOrderByUserId(int userId);

    List<OrderDetail> getOrderDetailByOrderId(List<String> listId);

    List<Product> getProductInOrderDetail(int id);

    List<Image> getNameImageByProductId(int id);

    List<OrderDetail> getOrderDetailNotReview(int userId);

    List<OrderDetail> getOrderDetailHasReview(int userId);
}
