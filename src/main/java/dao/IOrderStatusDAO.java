package dao;

import models.OrderStatus;

import java.util.List;

public interface IOrderStatusDAO {
//    Lấy ra danh sách chi tiết của tất cả các trạng thái đơn hàng có thể có
//    Ví dụ: chờ xác nhận, đã xác nhận, đang giao hàng, đã giao hàng, đã hủy
    List<OrderStatus> getListAllOrderStatus();

//    Lấy ra trạng thái đơn hàng dựa theo id trạng thái đơn hàng
    OrderStatus getOrderStatusById(int orderStatusId);
}
