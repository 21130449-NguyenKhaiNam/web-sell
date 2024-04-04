package dao;

import annotations.LogTable;
import models.Image;
import models.Order;
import models.OrderDetail;
import models.Product;

import java.util.List;

@LogTable(LogTable.ORDER)
public class OrderUserDAOImp implements IOrderUserDAO {

    public List<Order> getOrderByUserIdAndStatusOrder(int userId, int statusOrder){
        String querry = "SELECT id FROM orders WHERE userId = ? AND orderStatusId = ?";
        return GeneralDAOImp.executeQueryWithSingleTable(querry,Order.class, userId,statusOrder);
    }

    public List<Order> getOrderByUserId(int userId){
        String querry = "SELECT id FROM orders WHERE userId = ? ";
        return GeneralDAOImp.executeQueryWithSingleTable(querry,Order.class, userId);
    }

    public List<OrderDetail> getOrderDetailByOrderId(List<String> listId) {
        StringBuilder conditionBuilder = new StringBuilder();
        for (String id : listId) conditionBuilder.append("'").append(id).append("'").append(',');

        conditionBuilder.deleteCharAt(conditionBuilder.length() - 1);
        String condition = conditionBuilder.toString();

        String query = "SELECT id,  productId, quantityRequired, price " +
                "FROM order_details " +
                "WHERE orderId IN (" + condition + ")";
        return GeneralDAOImp.executeQueryWithSingleTable(query, OrderDetail.class);
    }

    public List<Product> getProductInOrderDetail(int id){
        String query = "SELECT DISTINCT id, name, categoryId " +
                "FROM products " +
                "WHERE id =?";

        return  GeneralDAOImp.executeQueryWithSingleTable(query, Product.class,id);
    }

    public List<Image> getNameImageByProductId(int id) {
        String querry = "SELECT nameImage FROM images WHERE productId = ?";
         return GeneralDAOImp.executeQueryWithSingleTable(querry, Image.class, id);
    }


    public List<OrderDetail> getOrderDetailNotReview(int userId) {
        String querry = "SELECT order_details.id " +
                "FROM orders JOIN order_details ON orders.id = order_details.orderId " +
                "WHERE orders.userId = ? AND orders.orderStatusId = 4 " +
                "AND order_details.id NOT IN (SELECT reviews.orderDetailId FROM reviews) ";
        return GeneralDAOImp.executeQueryWithSingleTable(querry, OrderDetail.class,userId);
    }

    public List<OrderDetail> getOrderDetailHasReview(int userId) {
        String querry = "SELECT order_details.id " +
                "FROM orders JOIN order_details ON orders.id = order_details.orderId " +
                "WHERE orders.userId = ? AND orders.orderStatusId = 4 " +
                "AND order_details.id IN (SELECT reviews.orderDetailId FROM reviews) ";
        return GeneralDAOImp.executeQueryWithSingleTable(querry, OrderDetail.class, userId);
    }
}
