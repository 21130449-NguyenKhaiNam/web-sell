package dao;

import models.OrderStatus;

import java.util.List;

public class OrderStatusDao {

    public List<OrderStatus> getListAllOrderStatus(){
        String sql = "SELECT id, typeStatus FROM order_statuses";
        return GeneralDAO.executeQueryWithSingleTable(sql, OrderStatus.class);
    }

    public OrderStatus getOrderStatusById(int orderStatusId){
        String sql = "SELECT id, typeStatus FROM order_statuses WHERE id = ?";
        return GeneralDAO.executeQueryWithSingleTable(sql, OrderStatus.class, orderStatusId).get(0);
    }

    public static void main(String[] args) {

    }
}
