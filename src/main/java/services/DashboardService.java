package services;

import dao.DashboadDAO;
import models.*;

import java.util.List;

public class DashboardService {
    private static DashboardService INSTANCE;
    private DashboadDAO dashboadDAO;

    private DashboardService() {
        this.dashboadDAO = new DashboadDAO();
    }

    public static DashboardService getINSTANCE() {
        if (INSTANCE == null)
            INSTANCE = new DashboardService();
        return INSTANCE;
    }
    public  int countUser(){
        return dashboadDAO.countUser();
    }
    public  int countProduct(){
        return dashboadDAO.countProduct();
    }
    public  int countOrder(){
        return dashboadDAO.countOrder();
    }
    public  int countReview(){
       return  dashboadDAO.countReview();
    }
    public List<OrderDetail> getTop5Product(){
        return dashboadDAO.getTop5Product();
    }
    public List<Product> getTop5ProductName(int productId){
        return dashboadDAO.getTop5ProductName(productId);
    }
    public List<OrderDetail> getTop5ProductQuantity(int productId){
        return dashboadDAO.getTop5ProductQuantity(productId);
    }

    public List<Order> getOrderByMonth(int month){
        return dashboadDAO.getOrderByMonth(month);
    }

    public List<OrderDetail> getOrderByOrderId(String orderId){
       return dashboadDAO.getOrderByOrderId(orderId);
    }

    public List<OrderDetail> getOrderDetailByOrderId(String orderId){
       return dashboadDAO.getOrderDetailByOrderId(orderId);
    }
}
