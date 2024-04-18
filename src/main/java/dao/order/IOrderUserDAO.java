package dao.order;

import dao.IDAO;
import models.Image;
import models.Order;
import models.OrderDetail;
import models.Product;

import java.util.List;

public interface IOrderUserDAO extends IDAO {
    //    Lấy ra danh sách tất cả các đơn hàng của người dùng dựa theo trạng thái đơn hàng
    List<Order> getOrderByUserIdAndStatusOrder(int userId, int statusOrder);

    //    Lấy ra danh sách tất cả các đơn hàng của người dùng
    List<Order> getOrderByUserId(int userId);

    //    Lấy ra danh sách chi tiết đơn hàng theo id đơn hàng
    List<OrderDetail> getOrderDetailByOrderId(List<String> listId);

    //    Lấy ra danh sách sản phẩm trong chi tiết đơn hàng theo id chi tiết đơn hàng
    List<Product> getProductInOrderDetail(int id);

    //    Lấy ra danh sách ảnh theo id sản phẩm
    List<Image> getNameImageByProductId(int id);

    List<OrderDetail> getOrderDetailWithReview(int userId, boolean hasReview);

}
