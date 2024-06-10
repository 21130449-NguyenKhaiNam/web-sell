package dao;

import dto.OrderDetailResponseDTO;
import dto.OrderItemResponseDTO;
import dto.OrderResponseDTO;
import models.Image;
import models.OrderDetail;
import models.Product;

import java.util.List;
import java.util.Optional;

public class OrderDaoUser {

    public List<OrderResponseDTO> getOrder(int userId, int statusOrder) {
        StringBuilder query = new StringBuilder();
        query.append(" SELECT orders.id AS id, orders.dateOrder AS dateOrder, COUNT(order_details.orderId) AS quantity ")
                .append("FROM orders JOIN order_details ON orders.id = order_details.orderId ")
                .append(" WHERE orders.orderStatusId = ?")
                .append(" AND orders.userId = ?")
                .append(" GROUP BY order_details.orderId");
        return GeneralDao.executeQueryWithSingleTable(query.toString(), OrderResponseDTO.class, statusOrder, userId);
    }

    public List<OrderDetail> getOrderDetailByOrderId(List<String> listId) {
        StringBuilder conditionBuilder = new StringBuilder();
        for (String id : listId) conditionBuilder.append("'").append(id).append("'").append(',');

        conditionBuilder.deleteCharAt(conditionBuilder.length() - 1);
        String condition = conditionBuilder.toString();

        String query = "SELECT id,  productId, quantityRequired, price " +
                "FROM order_details " +
                "WHERE orderId IN (" + condition + ")";
        return GeneralDao.executeQueryWithSingleTable(query, OrderDetail.class);
    }

    public List<Product> getProductInOrderDetail(int id) {
        String query = "SELECT DISTINCT id, name, categoryId " +
                "FROM products " +
                "WHERE id =?";

        return GeneralDao.executeQueryWithSingleTable(query, Product.class, id);
    }

    public List<Image> getNameImageByProductId(int id) {
        String querry = "SELECT nameImage FROM images WHERE productId = ?";
        return GeneralDao.executeQueryWithSingleTable(querry, Image.class, id);
    }


    public List<OrderDetail> getOrderDetailNotReview(int userId) {
        String querry = "SELECT order_details.id " +
                "FROM orders JOIN order_details ON orders.id = order_details.orderId " +
                "WHERE orders.userId = ? AND orders.orderStatusId = 4 " +
                "AND order_details.id NOT IN (SELECT reviews.orderDetailId FROM reviews) ";
        return GeneralDao.executeQueryWithSingleTable(querry, OrderDetail.class, userId);
    }

    public List<OrderDetail> getOrderDetailHasReview(int userId) {
        String querry = "SELECT order_details.id " +
                "FROM orders JOIN order_details ON orders.id = order_details.orderId " +
                "WHERE orders.userId = ? AND orders.orderStatusId = 4 " +
                "AND order_details.id IN (SELECT reviews.orderDetailId FROM reviews) ";
        return GeneralDao.executeQueryWithSingleTable(querry, OrderDetail.class, userId);
    }

    public Optional<OrderDetailResponseDTO> getOrderByOrderDetailId(String orderId) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT orders.dateOrder, order_statuses.typeStatus, orders.fullName, orders.phone, orders.voucherId, orders.province, orders.district, orders.ward, orders.detail, orders.dateOrder AS orderDate\t\n" +
                "FROM orders JOIN order_statuses ON orders.orderStatusId = order_statuses.id\n" +
                "WHERE orders.id = ?");
        List<OrderDetailResponseDTO> orderDetail = GeneralDao.executeQueryWithSingleTable(query.toString(), OrderDetailResponseDTO.class, orderId);
        if (orderDetail.isEmpty()) {
            return Optional.empty(); // Return an empty Optional
        } else {
            return Optional.ofNullable(orderDetail.get(0)); // Wrap the first address in an Optional
        }
    }

    public List<OrderItemResponseDTO> getOrderDetailsByOrderId(String orderId) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT order_details.productName AS name, order_details.quantityRequired AS quantity, order_details.sizeRequired AS size, order_details.colorRequired AS color, order_details.price AS price, images.nameImage AS thumbnail \n" +
                "FROM (\n" +
                "    SELECT productId, MIN(images.id) AS minImageId \n" +
                "    FROM images\n" +
                "    GROUP BY productId\n" +
                ") AS minImages\n" +
                "JOIN images ON images.productId = minImages.productId AND images.id = minImages.minImageId\n" +
                "JOIN order_details ON order_details.productId = images.productId\n" +
                "WHERE order_details.orderId = ?");
        return GeneralDao.executeQueryWithSingleTable(query.toString(), OrderItemResponseDTO.class, orderId);
    }
}
