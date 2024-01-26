package services;

import dao.OrderDAO;
import models.Image;
import models.Order;
import models.OrderDetail;
import models.Product;

import java.util.ArrayList;
import java.util.List;

public class HistoryService {
    private static HistoryService INSTANCE;
    private OrderDAO orderDAO;

    private HistoryService() {
        this.orderDAO= new OrderDAO();
    }

    public static HistoryService getINSTANCE() {
        if (INSTANCE == null)
            INSTANCE = new HistoryService();
        return INSTANCE;
    }

    public List<OrderDetail> getOrderDetailByOrderId(List<Integer> listId){
        if (listId.isEmpty()) return new ArrayList<>();
        return orderDAO.getOrderDetailByOrderId(listId);
    }
    public List<Order> getOrderByUserIdAndStatusOrder(int userId, String statusOrder){
        return orderDAO.getOrderByUserIdAndStatusOrder(userId, statusOrder);
    }
    public List<OrderDetail> getOrderDetailNotReview(int userId){
        return orderDAO.getOrderDetailNotReview(userId);
    }

    public List<OrderDetail> getOrderDetailHasReview(int userId) {
        return orderDAO.getOrderDetailHasReview(userId);
    }
    public List<Order> getOrderByUserId(int userId){
        return orderDAO.getOrderByUserId(userId);
    }

    public List<Product> getProductInOrderDetail(int id){
        return orderDAO.getProductInOrderDetail(id);
    }
    public List<Image> getNameImageByProductId(int id){
        return orderDAO.getNameImageByProductId(id);
    }
}
