package services;

import dao.OrderDaoUser;
import dto.OrderDetailResponseDTO;
import dto.OrderItemResponseDTO;
import dto.OrderResponseDTO;
import models.Image;
import models.OrderDetail;
import models.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public List<OrderResponseDTO> getOrder(int userId, int statusOrder) {
        return orderDAO.getOrder(userId, statusOrder);
    }

    public List<OrderDetail> getOrderDetailNotReview(int userId) {
        return orderDAO.getOrderDetailNotReview(userId);
    }

    public List<OrderDetail> getOrderDetailHasReview(int userId) {
        return orderDAO.getOrderDetailHasReview(userId);
    }

    public List<Product> getProductInOrderDetail(int id) {
        return orderDAO.getProductInOrderDetail(id);
    }

    public List<Image> getNameImageByProductId(int id) {
        return orderDAO.getNameImageByProductId(id);
    }

    public List<OrderItemResponseDTO> getOrderDetailByOrderId(String orderId) {
        return orderDAO.getOrderDetailsByOrderId(orderId);
    }

    public OrderDetailResponseDTO getOrderByOrderId(String orderId, int userId) {
        Optional<OrderDetailResponseDTO> orderDetailResponseDTO = orderDAO.getOrderByOrderDetailId(orderId);
        if (orderDetailResponseDTO.isPresent()) {
            OrderDetailResponseDTO order = orderDetailResponseDTO.get();
            List<OrderItemResponseDTO> orderDetails = getOrderDetailByOrderId(orderId);
            if (orderDetails == null) return null;
            order.setOrderItems(orderDetails);
            return order;
        }
        return null;
    }
}
