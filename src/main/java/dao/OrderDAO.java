package dao;

import models.*;

import java.util.ArrayList;
import java.util.List;

public class OrderDAO {

    public List<Order> getOrderByUserIdAndStatusOrder(int userId, String statusOrder){
        String querry = "SELECT id FROM orders WHERE userId = ? AND statusOrder = ?";
        return GeneralDao.executeQueryWithSingleTable(querry,Order.class, userId,statusOrder);
    }

    public List<Order> getOrderByUserId(int userId){
        String querry = "SELECT id FROM orders WHERE userId = ? ";
        return GeneralDao.executeQueryWithSingleTable(querry,Order.class, userId);
    }

    public List<OrderDetail> getOrderDetailByOrderId(List<Integer> listId) {
        if (listId.isEmpty()) return new ArrayList<>();

        StringBuilder conditionBuilder = new StringBuilder();
        for (int id : listId) conditionBuilder.append(id).append(',');

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


}
