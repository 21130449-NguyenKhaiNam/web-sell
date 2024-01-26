package dao;

import models.OrderDetail;

import java.util.List;

public class OrderDetailDAO {
    public List<OrderDetail> getOrderDetailById(int id) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT productId, sizeRequired, colorRequired, quantityRequired, price ")
                .append("FROM order_details ")
                .append("WHERE id = ?");
        return GeneralDao.executeQueryWithSingleTable(sql.toString(), OrderDetail.class, id);
    }


}
