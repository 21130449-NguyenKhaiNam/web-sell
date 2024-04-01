package dao;

import models.OrderDetail;

import java.util.List;

public class OrderDetailDAO implements IOrderDetailDAO {
    public List<OrderDetail> getOrderDetailById(int id) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT productId, sizeRequired, colorRequired, quantityRequired, price ")
                .append("FROM order_details ")
                .append("WHERE id = ?");
        return GeneralDAOImp.executeQueryWithSingleTable(sql.toString(), OrderDetail.class, id);
    }

    public List<OrderDetail> getListOrderDetailByOrderId(String orderId){
        StringBuilder sql = new StringBuilder("SELECT id, orderId, productId, productName, sizeRequired, colorRequired, quantityRequired, price ");
        sql.append(" FROM order_details WHERE orderId = ?");
        return GeneralDAOImp.executeQueryWithSingleTable(sql.toString(), OrderDetail.class, orderId);
    }

    public void removeOrderDetailByMultipleOrderId(String[] multipleOrderId){
        String fillEntry = String.format("'%s'", String.join("','", multipleOrderId));
        StringBuilder sql = new StringBuilder("DELETE FROM order_details");
        sql.append(" WHERE orderId IN(" + fillEntry + ")");
        GeneralDAOImp.executeAllTypeUpdate(sql.toString());
    }
}
