package dao;

import models.OrderStatus;

import java.util.List;

public interface IOrderStatusDAO {
    List<OrderStatus> getListAllOrderStatus();
    OrderStatus getOrderStatusById(int orderStatusId);
}
