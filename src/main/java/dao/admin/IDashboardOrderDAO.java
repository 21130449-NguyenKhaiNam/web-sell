package dao.admin;

import models.Order;
import models.OrderDetail;

import java.util.List;

public interface IDashboardOrderDAO extends IDashboadDAO {
    //    Lấy số đơn hàng đã đặt (tất cả các trạng thái của đơn hàng) (table Orders)
    int countOrder();

    //    Lấy ra số lượng và giá của sản phẩm trong Order_details theo orderID
    List<OrderDetail> getOrderByOrderId(String orderId);

    //    Lấy ra số lượng của sản phẩm trong Order_details theo orderID
    List<OrderDetail> getOrderDetailByOrderId(String orderId);

    //    Lấy đơn hàng theo tháng
    List<Order> getOrderByMonth(int month);
}
