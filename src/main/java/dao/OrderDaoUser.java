package dao;

import models.Image;
import models.Order;
import models.OrderDetail;
import models.Product;

import java.util.List;

public class OrderDaoUser {

    public List<Order> getOrderByUserIdAndStatusOrder(int userId, int statusOrder){
        String querry = "SELECT id FROM orders WHERE userId = ? AND orderStatusId = ?";
        return GeneralDao.executeQueryWithSingleTable(querry,Order.class, userId,statusOrder);
    }

    public List<Order> getOrderByUserId(int userId){
        String querry = "SELECT id FROM orders WHERE userId = ? ";
        return GeneralDao.executeQueryWithSingleTable(querry,Order.class, userId);
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

    public List<Product> getProductInOrderDetail(int id){
        String query = "SELECT DISTINCT id, name, categoryId " +
                "FROM products " +
                "WHERE id =?";

        return  GeneralDao.executeQueryWithSingleTable(query, Product.class,id);
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
        return GeneralDao.executeQueryWithSingleTable(querry, OrderDetail.class,userId);
    }

    public List<OrderDetail> getOrderDetailHasReview(int userId) {
        String querry = "SELECT order_details.id " +
                "FROM orders JOIN order_details ON orders.id = order_details.orderId " +
                "WHERE orders.userId = ? AND orders.orderStatusId = 4 " +
                "AND order_details.id IN (SELECT reviews.orderDetailId FROM reviews) ";
        return GeneralDao.executeQueryWithSingleTable(querry, OrderDetail.class, userId);
    }
}
