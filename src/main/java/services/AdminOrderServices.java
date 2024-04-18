package services;

import dao.order.*;
import models.*;
import utils.FormatCurrency;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class AdminOrderServices {

    private static AdminOrderServices INSTANCE;
    private IOrderAdminDAO orderDao;
    private IOrderStatusDAO orderStatusDao;
    private ITransactionStatusDAO transactionStatusDao;
    private IOrderDetailDAO orderDetailDAO;
    private IOrderUserDAO orderUserDAO;
    private IOrderAdminDAO orderAdminDAO;

    public AdminOrderServices() {
        orderDao = LogService.getINSTANCE().createProxy(new OrderAdminDAOImp());
        orderStatusDao = LogService.getINSTANCE().createProxy(new OrderStatusDAOImp());
        transactionStatusDao = LogService.getINSTANCE().createProxy(new TransactionStatusDAOImp());
        orderDetailDAO = LogService.getINSTANCE().createProxy(new OrderDetailDAOImp());
        orderUserDAO = LogService.getINSTANCE().createProxy(new OrderUserDAOImp());
        orderAdminDAO = LogService.getINSTANCE().createProxy(new OrderAdminDAOImp());
    }

    public static AdminOrderServices getINSTANCE() {
        if (INSTANCE == null)
            INSTANCE = new AdminOrderServices();
        return INSTANCE;
    }

    public List<OrderStatus> getListAllOrderStatus() {
        return orderStatusDao.getListAllOrderStatus();
    }

    public List<TransactionStatus> getListAllTransactionStatus() {
        return transactionStatusDao.getListAllTransactionStatus();
    }

    public OrderStatus getOrderStatusById(int orderStatusId) {
        return orderStatusDao.selectById(orderStatusId);
    }

    public TransactionStatus getTransactionStatusById(int transactionStatusId) {
        return transactionStatusDao.selectById(transactionStatusId);
    }

    public List<Order> getListAllOrders() {
        return orderDao.getListAllOrders();
    }

    public List<Order> getListOrdersBySearchFilter(Map<Object, List<String>> mapFilterSectionOrders, String contentSearch, String searchSelect, String startDate, String endDate) {
        return orderDao.getListOrdersBySearchFilter(mapFilterSectionOrders, contentSearch, searchSelect, startDate, endDate);
    }

    public List<PaymentMethod> getListAllPaymentMethodManage() {
        return orderDao.getListAllPaymentMethodManage();
    }

    public List<DeliveryMethod> getListAllDeliveryMethodManage() {
        return orderDao.getListAllDeliveryMethodManage();
    }

    public PaymentMethod getPaymentMethodMangeById(int id) {
        return orderDao.getPaymentMethodMangeById(id);
    }

    public DeliveryMethod getDeliveryMethodManageById(int id) {
        return orderDao.getDeliveryMethodManageById(id);
    }

    public Order getOrderById(String id) {
        return orderDao.getOrderById(id);
    }

    public void cancelOrderByMultipleId(String[] multipleId) {
        orderDao.cancelOrderByArrayMultipleId(multipleId);
    }

    public List<OrderDetail> getListOrderDetailByOrderId(String orderId) {
        return orderUserDAO.getOrderDetailByOrderId(Collections.singletonList(orderId));
    }

    public void updateStatusByOrderId(String orderId, int orderStatusId, int transactionStatusId) {
        orderAdminDAO.updateStatusByOrderId(orderId, orderStatusId, transactionStatusId);
    }

    public Voucher getVoucherById(int id) {
        return orderDao.getVoucherById(id);
    }

    public String getTotalPriceFormatByOrderId(String orderId) {
        Order order = orderDao.getOrderById(orderId);
        List<OrderDetail> listOrderDetail = orderUserDAO.getOrderDetailByOrderId(Collections.singletonList(orderId));
        double totalPrice = 0;
        for (OrderDetail orderDetail : listOrderDetail) {
            totalPrice += orderDetail.getPrice();
        }

        if (order.getVoucherId() != 0) {
            Voucher voucher = getVoucherById(order.getVoucherId());
            totalPrice *= (1 - voucher.getDiscountPercent());
        }
        return FormatCurrency.vietNamCurrency(totalPrice);
    }

}
