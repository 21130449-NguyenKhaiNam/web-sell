package dao;

import models.*;

import java.util.List;

public class DashboadDAOImp implements IDashboadDAO{
    public  int countUser(){
        String querry = "SELECT id FROM users";
        return  GeneralDAO.executeQueryWithSingleTable(querry, User.class).size();
    }
    public  int countProduct(){
        String querry = "SELECT id FROM products";
        return  GeneralDAO.executeQueryWithSingleTable(querry, Product.class).size();
    }
    public  int countOrder(){
        String querry = "SELECT id FROM orders";
        return  GeneralDAO.executeQueryWithSingleTable(querry, Order.class).size();
    }
    public  int countReview(){
        String querry = "SELECT id FROM reviews";
        return  GeneralDAO.executeQueryWithSingleTable(querry, Review.class).size();
    }
    public List<OrderDetail> getTop5Product(){
        String querry = "SELECT products.id, products.name, SUM(quantityRequired)\n" +
                "FROM order_details  INNER JOIN products ON products.id = order_details.productId\n" +
                "GROUP BY products.id, products.name\n" +
                "ORDER BY SUM(quantityRequired) DESC\n" +
                "LIMIT 5";
        return GeneralDAO.executeQueryWithSingleTable(querry, OrderDetail.class);
    }
    public List<Product> getTop5ProductName(int productId){
        String querry="SELECT name FROM products WHERE id=?";
        return GeneralDAO.executeQueryWithSingleTable(querry, Product.class, productId);
    }
    public List<OrderDetail> getTop5ProductQuantity(int productId){
        String querry="SELECT quantityRequired FROM order_details WHERE productId=?";
        return GeneralDAO.executeQueryWithSingleTable(querry, OrderDetail.class, productId);
    }

    public List<Order> getOrderByMonth(int month){
        String querry ="SELECT id FROM orders WHERE MONTH(dateOrder)=?";
        return GeneralDAO.executeQueryWithSingleTable(querry,Order.class, month);
    }

    public List<OrderDetail> getOrderByOrderId(String orderId){
        String querry = "SELECT quantityRequired, price FROM order_details WHERE orderId = ?";
        return GeneralDAO.executeQueryWithSingleTable(querry,OrderDetail.class,orderId);
    }

    public List<OrderDetail> getOrderDetailByOrderId(String orderId){
        String querry="SELECT quantityRequired FROM order_details WHERE orderId=?";
        return GeneralDAO.executeQueryWithSingleTable(querry, OrderDetail.class, orderId);
    }

}
