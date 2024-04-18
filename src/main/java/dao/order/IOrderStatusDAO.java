package dao.order;

import annotations.WriteLog;
import dao.IDAO;
import models.OrderStatus;

import java.util.List;

public interface IOrderStatusDAO extends IDAO {
    //    Lấy ra danh sách chi tiết của tất cả các trạng thái đơn hàng có thể có
    //    Ví dụ: chờ xác nhận, đã xác nhận, đang giao hàng, đã giao hàng, đã hủy
    @WriteLog(WriteLog.SELECT)
    List<OrderStatus> getListAllOrderStatus();
}
