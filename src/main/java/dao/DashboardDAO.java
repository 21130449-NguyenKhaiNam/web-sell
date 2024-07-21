package dao;

import database.JDBIConnector;
import models.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DashboardDAO {
    public int countUser() {
        String querry = "SELECT id FROM users";
        return GeneralDao.executeQueryWithSingleTable(querry, User.class).size();
    }

    public int countProduct() {
        String querry = "SELECT id FROM products";
        return GeneralDao.executeQueryWithSingleTable(querry, Product.class).size();
    }

    public int countOrder() {
        String querry = "SELECT id FROM orders";
        return GeneralDao.executeQueryWithSingleTable(querry, Order.class).size();
    }

    public int countReview() {
        String querry = "SELECT id FROM reviews";
        return GeneralDao.executeQueryWithSingleTable(querry, Review.class).size();
    }

    public List<OrderDetail> getTop5Product() {
        String querry = "SELECT products.id, products.name, SUM(quantityRequired)\n" +
                "FROM order_details  INNER JOIN products ON products.id = order_details.productId\n" +
                "GROUP BY products.id, products.name\n" +
                "ORDER BY SUM(quantityRequired) DESC\n" +
                "LIMIT 5";
        return GeneralDao.executeQueryWithSingleTable(querry, OrderDetail.class);
    }

    public List<Product> getTop5ProductName(int productId) {
        String querry = "SELECT name FROM products WHERE id=?";
        return GeneralDao.executeQueryWithSingleTable(querry, Product.class, productId);
    }

    public List<OrderDetail> getTop5ProductQuantity(int productId) {
        String querry = "SELECT quantityRequired FROM order_details WHERE productId=?";
        return GeneralDao.executeQueryWithSingleTable(querry, OrderDetail.class, productId);
    }

    public List<Order> getOrderByMonth(int month) {
        String querry = "SELECT * FROM orders WHERE MONTH(dateOrder)=?";
        return GeneralDao.executeQueryWithSingleTable(querry, Order.class, month);
    }

    public List<OrderDetail> getOrderByOrderId(String orderId) {
        String querry = "SELECT quantityRequired, price FROM order_details WHERE orderId = ?";
        return GeneralDao.executeQueryWithSingleTable(querry, OrderDetail.class, orderId);
    }

    public List<OrderDetail> getOrderDetailByOrderId(String orderId) {
        String querry = "SELECT quantityRequired FROM order_details WHERE orderId=?";
        return GeneralDao.executeQueryWithSingleTable(querry, OrderDetail.class, orderId);
    }

    public Long quantityOrderSuccess(String month, String year) {
        LogDAOImp.CountResult result = new LogDAOImp.CountResult();
        String query = "SELECT COUNT(*) count FROM orders WHERE MONTH(dateOrder) = ? AND YEAR(dateOrder) = ? AND orderStatusId = 4";
        GeneralDao.customExecute(handle -> {
            result.setCount(handle.createQuery(query)
                    .bind(0, month)
                    .bind(1, year)
                    .mapTo(Long.class)
                    .one());
        });
        return result.getCount();
    }

    public Long quantityOrderFailed(String month, String year) {
        LogDAOImp.CountResult result = new LogDAOImp.CountResult();
        String query = "SELECT COUNT(*) count FROM orders WHERE MONTH(dateOrder) = ? AND YEAR(dateOrder) = ? AND orderStatusId = 5";
        GeneralDao.customExecute(handle -> {
            result.setCount(handle.createQuery(query)
                    .bind(0, month)
                    .bind(1, year)
                    .mapTo(Long.class)
                    .one());
        });
        return result.getCount();
    }

    public Map<Product, Integer> getListProductPopular(String month, String year) {
        String query = "SELECT products.id, products.name, SUM(quantityRequired) as total " +
                "FROM orders JOIN (order_details JOIN products ON products.id = order_details.productId) ON orders.id = order_details.orderId " +
                "WHERE YEAR(orders.dateOrder) = ? AND MONTH(orders.dateOrder) = ? AND orders.orderStatusId = 4 " +
                "GROUP BY products.id, products.name " +
                "ORDER BY total DESC " +
                "LIMIT 5 ";
        List<Map<String, Object>> resultDB = GeneralDao.executeQueryWithJoinTables(query, year, month);
        Map<Product, Integer> result = new HashMap<>();
        for (Map<String, Object> map : resultDB) {
            Product product = new Product();
            product.setId((Integer) map.get("id"));
            product.setName((String) map.get("name"));
            result.put(product, ((BigDecimal) map.get("total")).intValue());
        }
        return result;
    }

    public Map<Product, Integer> getListProductNotPopular(String month, String year) {
        String query = "SELECT products.id, products.name, SUM(quantityRequired) as total " +
                "FROM orders JOIN (order_details JOIN products ON products.id = order_details.productId) ON orders.id = order_details.orderId " +
                "WHERE YEAR(orders.dateOrder) = ? AND MONTH(orders.dateOrder) = ? AND orders.orderStatusId = 4 " +
                "GROUP BY products.id, products.name " +
                "ORDER BY total ASC " +
                "LIMIT 5 ";
        List<Map<String, Object>> resultDB = GeneralDao.executeQueryWithJoinTables(query, year, month);
        Map<Product, Integer> result = new HashMap<>();
        for (Map<String, Object> map : resultDB) {
            Product product = new Product();
            product.setId((Integer) map.get("id"));
            product.setName((String) map.get("name"));
            result.put(product, ((BigDecimal) map.get("total")).intValue());
        }
        return result;
    }

    public Double getRevenueByMonth(String month, String year) {
        String query = "SELECT SUM(order_details.quantityRequired * order_details.price) FROM `order_details` join orders on order_details.orderId = orders.id " +
                "WHERE MONTH(orders.dateOrder) = ? AND YEAR(orders.dateOrder) = ? AND orders.orderStatusId = 4 ";
        return JDBIConnector.get().withHandle(handle -> handle.createQuery(query)
                .bind(0, month)
                .bind(1, year)
                .mapTo(Double.class)
                .one());
    }
}
