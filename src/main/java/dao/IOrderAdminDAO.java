package dao;

import models.*;

import java.util.List;
import java.util.Map;

public interface IOrderAdminDAO {
    List<Order> getListAllOrders();
    List<PaymentMethod> getListAllPaymentMethodManage();
    List<DeliveryMethod> getListAllDeliveryMethodManage();
    List<Order> getListOrdersBySearchFilter(Map<Object, List<String>> mapFilterSectionOrders, String contentSearch, String searchSelect, String startDate, String endDate);
    PaymentMethod getPaymentMethodMangeById(int id);
    DeliveryMethod getDeliveryMethodManageById(int id);
    Order getOrderById(String id);
    void removeOrderByMultipleId(String[] multipleId);
    void cancelOrderByArrayMultipleId(String[] multipleId);
    Voucher getVoucherById(int id);
    List<Order> getOrderByUserIdAndStatusOrder(int userId, int statusOrder);
    List<Order> getOrderByUserId(int userId);
    List<OrderDetail> getOrderDetailByOrderId(List<String> listId);
    List<Product> getProductInOrderDetail(int id);
    List<Image> getNameImageByProductId(int id);
    List<OrderDetail> getOrderDetailNotReview(int userId);
    List<OrderDetail> getOrderDetailHasReview(int userId);
}
