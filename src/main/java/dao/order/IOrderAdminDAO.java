package dao.order;

import annotations.LogParam;
import annotations.WriteLog;
import dao.IDAO;
import models.*;

import java.util.List;
import java.util.Map;

public interface IOrderAdminDAO extends IDAO {

    @WriteLog(WriteLog.UPDATE)
    void updateStatusByOrderId(@LogParam("order-id") String orderId, @LogParam("order-status-id") int orderStatusId, @LogParam("transaction-status-id") int transactionStatusId);

    //    Các hàm dưới phục vụ việc lọc trong admin order page
    //    Lấy danh sách tất cả đơn hàng
    List<Order> getListAllOrders();

    //    Lấy danh sách tất cả các phương thức thanh toán
    List<PaymentMethod> getListAllPaymentMethodManage();

    //    Lấy danh sách tất cả các phương thức giao hàng
    List<DeliveryMethod> getListAllDeliveryMethodManage();

    //    Lấy danh sách tất cả  đơn hàng theo các tiêu chí lọc
    List<Order> getListOrdersBySearchFilter(Map<Object, List<String>> mapFilterSectionOrders, String contentSearch, String searchSelect, String startDate, String endDate);

    //    Lấy chi tiết Phương thức thanh toán theo id phương thức thanh toán
    PaymentMethod getPaymentMethodMangeById(int id);

    //    Lấy chi tiết Phương thức giao hàng theo id phương thức giao hàng
    DeliveryMethod getDeliveryMethodManageById(int id);

    //    Lấy chi tiết đơn hàng theo id đơn hàng
    Order getOrderById(String id);

    //    Hủy các đơn hàng dựa theo id đơn hàng
    @WriteLog(WriteLog.UPDATE)
    void cancelOrderByArrayMultipleId(@LogParam("cancel-order-id") String[] multipleId);

    //    Lấy ra voucher theo id voucher
    Voucher getVoucherById(int id);
}
