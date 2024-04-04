package dao;

import models.Order;
import models.OrderDetail;
import models.Product;

import java.util.List;

public interface IDashboadDAO {
//   Lấy số lượng User có trong hệ thống (table Users)
    int countUser();
//    Lấy số sản phẩm có trong hệ thống (table Products)
    int countProduct();
//    Lấy số đơn hàng đã đặt (tất cả các trạng thái của đơn hàng) (table Orders)
    int countOrder();
//    Lấy số lượng review có trong hệ thống (table Reviews)
    int countReview();
//    Lấy 5 sản phẩm bán chạy nhất (số lượng sản phẩm đã bán ra nhiều nhất)
    List<OrderDetail> getTop5Product();
//    Lấy tên 5 sản phẩm bán chạy nhất
    List<Product> getTop5ProductName(int productId);
//    Lấy số lượng 5 sản phẩm bán chạy nhất
    List<OrderDetail> getTop5ProductQuantity(int productId);
//    Lấy đơn hàng theo tháng
    List<Order> getOrderByMonth(int month);
//    Lấy ra số lượng và giá của sản phẩm trong Order_details theo orderID
    List<OrderDetail> getOrderByOrderId(String orderId);
//    Lấy ra số lượng của sản phẩm trong Order_details theo orderID
    List<OrderDetail> getOrderDetailByOrderId(String orderId);
}
