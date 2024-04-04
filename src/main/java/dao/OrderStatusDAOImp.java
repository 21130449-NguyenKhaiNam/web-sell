package dao;

import annotations.LogTable;
import models.OrderStatus;

import java.util.List;

@LogTable(LogTable.ORDER)
public class OrderStatusDAOImp implements IOrderStatusDAO {

    public List<OrderStatus> getListAllOrderStatus(){
        String sql = "SELECT id, typeStatus FROM order_statuses";
        return GeneralDAOImp.executeQueryWithSingleTable(sql, OrderStatus.class);
    }

    public OrderStatus getOrderStatusById(int orderStatusId){
        String sql = "SELECT id, typeStatus FROM order_statuses WHERE id = ?";
        return GeneralDAOImp.executeQueryWithSingleTable(sql, OrderStatus.class, orderStatusId).get(0);
    }

    @Override
    public Object getModelById(Object id) {
        return null;
    }
}
