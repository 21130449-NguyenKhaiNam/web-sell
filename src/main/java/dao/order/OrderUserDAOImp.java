package dao.order;

import annotations.LogTable;
import dao.general.GeneralDAO;
import models.Image;
import models.Order;
import models.OrderDetail;
import models.Product;

import java.util.List;

@LogTable(LogTable.ORDER)
public class OrderUserDAOImp implements IOrderUserDAO {
    public List<Order> getOrderByUserIdAndStatusOrder(int userId, int statusOrder) {
        String query = "SELECT id FROM orders WHERE userId = ? AND orderStatusId = ?";
        return GeneralDAO.executeQueryWithSingleTable(query, Order.class, userId, statusOrder);
    }

    public List<Order> getOrderByUserId(int userId) {
        String query = "SELECT id FROM orders WHERE userId = ? ";
        return GeneralDAO.executeQueryWithSingleTable(query, Order.class, userId);
    }

    public List<OrderDetail> getOrderDetailByOrderId(List<String> listId) {
        StringBuilder conditionBuilder = new StringBuilder();
        for (String id : listId)
            conditionBuilder.append("'").append(id).append("'").append(',');

        conditionBuilder.deleteCharAt(conditionBuilder.length() - 1);
        String condition = conditionBuilder.toString();

        String query = "SELECT id,  productId, quantityRequired, price " +
                "FROM order_details " +
                "WHERE orderId IN (" + condition + ")";
        return GeneralDAO.executeQueryWithSingleTable(query, OrderDetail.class);
    }

    public List<Product> getProductInOrderDetail(int idOrderDetail) {
//        String query = "SELECT DISTINCT id, name, categoryId " +
//                "FROM products " +
//                "WHERE id =?";
        String query = "SELECT id, name, categoriesId, description, originalPrice, salePrice, visibility, createAt FROM products\n" +
                "WHERE id IN\n" +
                "(SELECT productId FROM order_details WHERE id = ?)";
        return GeneralDAO.executeQueryWithSingleTable(query, Product.class, idOrderDetail);
    }

    public List<Image> getNameImageByProductId(int id) {
        String query = "SELECT nameImage FROM images WHERE productId = ?";
        return GeneralDAO.executeQueryWithSingleTable(query, Image.class, id);
    }

    @Override
    public List<OrderDetail> getOrderDetailWithReview(int userId, boolean hasReview) {
//        String querry = "SELECT order_details.id " +
//                "FROM orders JOIN order_details ON orders.id = order_details.orderId " +
//                "WHERE orders.userId = ? AND orders.orderStatusId = 4 " +
//                "AND order_details.id NOT IN (SELECT reviews.orderDetailId FROM reviews) ";
        String query = "SELECT id, orderId, productName, sizeRequired, colorRequired, quantityRequired, price FROM order_details\n" +
                "WHERE orderId IN\n" +
                "(SELECT id FROM orders WHERE orders.userId = ? AND orders.orderStatusId = 4) AND\n" +
                "id " + (hasReview ? "" : "NOT") + " IN\n" +
                "(SELECT orderDetailId FROM reviews)";
        return GeneralDAO.executeQueryWithSingleTable(query, OrderDetail.class, userId);
    }


//    public List<OrderDetail> getOrderDetailNotReview(int userId) {
//        String querry = "SELECT order_details.id " +
//                "FROM orders JOIN order_details ON orders.id = order_details.orderId " +
//                "WHERE orders.userId = ? AND orders.orderStatusId = 4 " +
//                "AND order_details.id NOT IN (SELECT reviews.orderDetailId FROM reviews) ";
//        return GeneralDAO.executeQueryWithSingleTable(querry, OrderDetail.class, userId);
//    }
//
//    public List<OrderDetail> getOrderDetailHasReview(int userId) {
//        String querry = "SELECT order_details.id " +
//                "FROM orders JOIN order_details ON orders.id = order_details.orderId " +
//                "WHERE orders.userId = ? AND orders.orderStatusId = 4 " +
//                "AND order_details.id IN (SELECT reviews.orderDetailId FROM reviews) ";
//        return GeneralDAO.executeQueryWithSingleTable(querry, OrderDetail.class, userId);
//    }

}
