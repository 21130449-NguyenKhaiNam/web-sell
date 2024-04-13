package dao.order;

import annotations.LogTable;
import dao.general.GeneralDAO;
import models.OrderStatus;

import java.util.List;

@LogTable(LogTable.ORDER)
public class OrderStatusDAOImp implements IOrderStatusDAO {

    @Override
    public OrderStatus selectById(Object id) {
        if(id instanceof Integer) {
            String sql = "SELECT id, typeStatus FROM order_statuses WHERE id = ?";
            return GeneralDAO.executeQueryWithSingleTable(sql, OrderStatus.class, id).get(0);
        } else {
            throw new UnsupportedOperationException("OrderStatusDAOImp >> Không hỗ trợ tham số SELECT kiểu khác");
        }
    }

    public List<OrderStatus> getListAllOrderStatus(){
        String sql = "SELECT id, typeStatus FROM order_statuses";
        return GeneralDAO.executeQueryWithSingleTable(sql, OrderStatus.class);
    }

}
