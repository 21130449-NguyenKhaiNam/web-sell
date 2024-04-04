package dao;

import models.OrderDetail;

import java.util.List;

public interface IOrderDetailDAO {
    //    Lấy thông tin chi tiết đơn hàng trong đơn hàng theo id chi tiết đơn hàng
    List<OrderDetail> getOrderDetailById(int id);

    //      Lấy thông tin chi tiết đơn hàng trong đơn hàng theo id đơn hàng
//    Câu này với trên giống nhau chỉ khác là lấy nhiều trường dữ liệu hơn
    List<OrderDetail> getListOrderDetailByOrderId(String orderId);

    //    Xóa thông tin chi tiết đơn hàng trong đơn hàng theo nhiều id đơn hàng
    void removeOrderDetailByMultipleOrderId(String[] multipleOrderId);
}
