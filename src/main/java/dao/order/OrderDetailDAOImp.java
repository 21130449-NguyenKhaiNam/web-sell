package dao.order;

import annotations.LogTable;
import dao.general.GeneralDAO;
import models.OrderDetail;

import java.util.List;

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

//    public void removeOrderDetailByMultipleOrderId(String[] multipleOrderId){
//        String fillEntry = String.format("'%s'", String.join("','", multipleOrderId));
//        StringBuilder sql = new StringBuilder("DELETE FROM order_details");
//        sql.append(" WHERE orderId IN(" + fillEntry + ")");
//        GeneralDAO.executeAllTypeUpdate(sql.toString());
//    }

//    public List<OrderDetail> getListOrderDetailByOrderId(String orderId){
//        StringBuilder sql = new StringBuilder("SELECT id, orderId, productId, productName, sizeRequired, colorRequired, quantityRequired, price ");
//        sql.append(" FROM order_details WHERE orderId = ?");
//        return GeneralDAO.executeQueryWithSingleTable(sql.toString(), OrderDetail.class, orderId);
//    }
}
