package dao.order;

import annotations.LogTable;
import dao.general.GeneralDAOImp;
import models.Image;
import models.Order;
import models.OrderDetail;
import models.Product;

import java.util.List;

@LogTable(LogTable.ORDER)
public class OrderUserDAOImp implements IOrderUserDAO {
    @Override
    public List<Order> getOrderByUserIdAndStatusOrder(int userId, int statusOrder) {
        String query = "SELECT id FROM orders WHERE userId = ? AND orderStatusId = ?";
        return GeneralDAOImp.executeQueryWithSingleTable(query, Order.class, userId, statusOrder);
    }

    @Override
    public List<Order> getOrderByUserId(int userId) {
        String query = "SELECT id FROM orders WHERE userId = ? ";
        return GeneralDAOImp.executeQueryWithSingleTable(query, Order.class, userId);
    }

    @Override
    public List<OrderDetail> getOrderDetailByOrderId(List<String> listId) {
        StringBuilder conditionBuilder = new StringBuilder();
        for (String id : listId)
            conditionBuilder.append("'").append(id).append("'").append(',');

        conditionBuilder.deleteCharAt(conditionBuilder.length() - 1);
        String condition = conditionBuilder.toString();

        String query = "SELECT id,  productId, quantityRequired, price " +
                "FROM order_details " +
                "WHERE orderId IN (" + condition + ")";
        return GeneralDAOImp.executeQueryWithSingleTable(query, OrderDetail.class);
    }

    @Override
    public List<Product> getProductInOrderDetail(int idOrderDetail) {
        String query = "SELECT id, name, categoriesId, description, originalPrice, salePrice, visibility, createAt FROM products\n" +
                "WHERE id IN\n" +
                "(SELECT productId FROM order_details WHERE id = ?)";
        return GeneralDAOImp.executeQueryWithSingleTable(query, Product.class, idOrderDetail);
    }

    @Override
    public List<Image> getNameImageByProductId(int id) {
        String query = "SELECT nameImage FROM images WHERE productId = ?";
        return GeneralDAOImp.executeQueryWithSingleTable(query, Image.class, id);
    }

    @Override
    public List<OrderDetail> getOrderDetailWithReview(int userId, boolean hasReview) {         "AND order_details.id NOT IN (SELECT reviews.orderDetailId FROM reviews) ";
        String query = "SELECT id, orderId, productName, sizeRequired, colorRequired, quantityRequired, price FROM order_details\n" +
                "WHERE orderId IN\n" +
                "(SELECT id FROM orders WHERE orders.userId = ? AND orders.orderStatusId = 4) AND\n" +
                "id " + (hasReview ? "" : "NOT") + " IN\n" +
                "(SELECT orderDetailId FROM reviews)";
        return GeneralDAOImp.executeQueryWithSingleTable(query, OrderDetail.class, userId);
    }
}
