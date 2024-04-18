package dao.order;

import annotations.LogTable;
import dao.general.GeneralDAO;
import models.OrderDetail;

@LogTable(LogTable.ORDER)
public class OrderDetailDAOImp implements IOrderDetailDAO {
    @Override
    public OrderDetail selectById(Object id) {
        if(id instanceof Integer) {
            String sql = "SELECT orderId, productId, sizeRequired, colorRequired, quantityRequired, price " +
                    "FROM order_details " +
                    "WHERE id = ?";
            return GeneralDAO.executeQueryWithSingleTable(sql, OrderDetail.class, id).get(0);
        } else {
            throw new UnsupportedOperationException("OrderDetailDAOImp >> Không hỗ trợ tham số SELECT kiểu khác");
        }
    }
}
