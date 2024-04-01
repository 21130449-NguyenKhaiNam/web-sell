package dao;

import models.OrderDetail;

import java.util.List;

public interface IOrderDetailDAO {
    List<OrderDetail> getOrderDetailById(int id);
    List<OrderDetail> getListOrderDetailByOrderId(String orderId);
    void removeOrderDetailByMultipleOrderId(String[] multipleOrderId);
}
