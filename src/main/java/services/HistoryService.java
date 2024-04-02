package services;

import dao.OrderDaoUser;
import dto.OrderDTO;
import models.*;

import java.util.ArrayList;
import java.util.List;

public class HistoryService {
    private static HistoryService INSTANCE;
    private OrderDaoUser orderDAO;

    private HistoryService() {
        this.orderDAO = new OrderDaoUser();
    }

    public static HistoryService getINSTANCE() {
        if (INSTANCE == null)
            INSTANCE = new HistoryService();
        return INSTANCE;
    }

    public List<OrderDetail> getOrderDetailByOrderId(List<String> listId) {
        if (listId.isEmpty()) return new ArrayList<>();
        return orderDAO.getOrderDetailByOrderId(listId);
    }

    public List<OrderDTO> getOrderByStatusId(String statusId) {
        return orderDAO.getOrderByStatusId(statusId);
    }

    public List<Order> getOrderByUserIdAndStatusOrder(int userId, int statusOrder) {
        return orderDAO.getOrderByUserIdAndStatusOrder(userId, statusOrder);
    }

    public List<OrderDetail> getOrderDetailNotReview(int userId) {
        return orderDAO.getOrderDetailNotReview(userId);
    }

    public List<OrderDetail> getOrderDetailHasReview(int userId) {
        return orderDAO.getOrderDetailHasReview(userId);
    }

    public List<Order> getOrderByUserId(int userId) {
        return orderDAO.getOrderByUserId(userId);
    }

    public List<Product> getProductInOrderDetail(int id) {
        return orderDAO.getProductInOrderDetail(id);
    }

    public List<Image> getNameImageByProductId(int id) {
        return orderDAO.getNameImageByProductId(id);
    }

    public Paging<OrderDTO> getOrderByStatusId(int start, int length) {
        Paging<OrderDTO> paging = new Paging<>();
        paging.setStart(start);
        paging.setLength(length);
        paging.setList(orderDAO.getOrderList(start, length));
        return paging;
    }
}
