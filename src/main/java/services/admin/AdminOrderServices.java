package services.admin;

import dao.OrderDetailDAO;
import dao.OrderDaoAdmin;
import dao.OrderStatusDao;
import dao.TransactionStatusDao;
import models.*;
import services.state.OrderState;
import services.state.TransactionState;
import utils.FormatCurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AdminOrderServices {

    private final OrderDaoAdmin orderDao;
    private final OrderStatusDao orderStatusDao;
    private final TransactionStatusDao transactionStatusDao;

    private final OrderDetailDAO orderDetailDAO;

    private static AdminOrderServices INSTANCE;

    public AdminOrderServices() {
        orderDao = new OrderDaoAdmin();
        orderStatusDao = new OrderStatusDao();
        transactionStatusDao = new TransactionStatusDao();
        orderDetailDAO = new OrderDetailDAO();
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
        return orderStatusDao.getOrderStatusById(orderStatusId);
    }

    public TransactionStatus getTransactionStatusById(int transactionStatusId) {
        return transactionStatusDao.getTransactionStatusById(transactionStatusId);
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

//    public List<Order> getListOrderById(String orderId){
//        return orderDao.getListOrderByPartialId(orderId);
//    }

//    public List<Order> getListOrderByCustomerName(String customerName){
//        return orderDao.getListOrderByCustomerName(customerName);
//    }

    public Order getOrderById(String id) {
        return orderDao.getOrderById(id);
    }

//    public void updateOrderStatusIdByOrderId(int orderStatusId , String orderId){
//        orderDao.updateOrderStatusIdByOrderId(orderStatusId, orderId);
//    }
//
//    public void updateTransactionStatusIdByOrderId(int transactionStatusId , String orderId){
//        orderDao.updateTransactionStatusIdByOrderId(transactionStatusId,orderId);
//    }

    public void removeOrderByMultipleOrderId(String[] multipleOrderId) {
        orderDetailDAO.removeOrderDetailByMultipleOrderId(multipleOrderId);
        orderDao.removeOrderByMultipleId(multipleOrderId);
    }

    public void cancelOrderByMultipleId(String[] multipleId) {
        orderDao.cancelOrderByArrayMultipleId(multipleId);
    }

    public List<OrderDetail> getListOrderDetailByOrderId(String orderId) {
        return orderDetailDAO.getListOrderDetailByOrderId(orderId);
    }

    public boolean updateOrder(String orderId, Integer orderStatusId, Integer transactionStatusId) {
        Order order = orderDao.getOrderById(orderId);
        if (orderStatusId != null && transactionStatusId != null) {
            return updateStatusAndOrderTransaction(order, orderStatusId, transactionStatusId);
        }
        if (orderStatusId != null) {
            return updateOrderStatus(order, orderStatusId);
        }
        if (transactionStatusId != null) {
            return updateOrderTransaction(order, transactionStatusId);
        }
        return false;
    }

    private boolean updateStatusAndOrderTransaction(Order order, Integer orderStatusId, Integer transactionStatusId) {
        if (canUpdateStatusByOrderId(order, orderStatusId) && canUpdateTransactionByOrderId(order, transactionStatusId)) {
            orderDao.updateStatusByOrderId(order.getId(), orderStatusId, transactionStatusId);
            return true;
        }
        return false;
    }

    private boolean canUpdateStatusByOrderId(Order order, int orderStatusId) {
        OrderState orderState = OrderState.getByValue(orderStatusId);
        if (orderState == null) return false;
        List<OrderState> orderStateCanChange = new ArrayList<>();
        switch (OrderState.getByValue(order.getOrderStatusId())) {
            case PENDING:
                orderStateCanChange.add(OrderState.PENDING);
                orderStateCanChange.add(OrderState.CONFIRMED);
                orderStateCanChange.add(OrderState.CANCELLED);
                break;
            case CONFIRMED:
                orderStateCanChange.add(OrderState.DELIVERY);
                orderStateCanChange.add(OrderState.CONFIRMED);
                break;
            case DELIVERY:
                orderStateCanChange.add(OrderState.DELIVERY);
                orderStateCanChange.add(OrderState.CANCELLED);
                orderStateCanChange.add(OrderState.COMPLETED);
                break;
        }
        return orderStateCanChange.contains(orderState);
    }

    private boolean canUpdateTransactionByOrderId(Order order, int transactionStatusId) {
        TransactionState transactionState = TransactionState.getByValue(transactionStatusId);
        if (transactionState == null) return false;
        List<TransactionState> transactionStateCanChange = new ArrayList<>();
        switch (TransactionState.getByValue(order.getTransactionStatusId())) {
            case UN_PAID, PROCESSING:
                transactionStateCanChange.add(TransactionState.UN_PAID);
                transactionStateCanChange.add(TransactionState.PROCESSING);
                transactionStateCanChange.add(TransactionState.PAID);
                break;
        }
        return transactionStateCanChange.contains(transactionState);
    }

    public boolean updateOrderTransaction(Order order, int transactionStatusId) {
        if (canUpdateTransactionByOrderId(order, transactionStatusId)) {
            orderDao.updateOrderTransaction(order.getId(), transactionStatusId);
            return true;
        }
        return false;
    }

    public boolean updateOrderStatus(Order order, int statusId) {
        if (canUpdateStatusByOrderId(order, statusId)) {
            orderDao.updateOrderStatus(order.getId(), statusId);
            return true;
        }
        return false;
    }

    public Voucher getVoucherById(int id) {
        return orderDao.getVoucherById(id);
    }

    public String getTotalPriceFormatByOrderId(String orderId) {
        Order order = orderDao.getOrderById(orderId);
        List<OrderDetail> listOrderDetail = orderDetailDAO.getListOrderDetailByOrderId(orderId);
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

    public long getQuantity() {
        return orderDao.getQuantity();
    }

    public List<Order> getLimit(int limit, int offset) {
        return orderDao.getLimit(limit, offset);
    }
}
