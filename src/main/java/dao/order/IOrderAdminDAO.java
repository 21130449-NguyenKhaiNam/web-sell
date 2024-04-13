package dao.order;

import dao.IDAO;
import models.*;

import java.util.List;
import java.util.Map;

public interface IOrderAdminDAO extends IDAO {
    //    Các ham dưới phục vụ việc lọc trong admin order page
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

    //    Xóa các đơn hàng dựa theo id đơn hàng
    void removeOrderByMultipleId(String[] multipleId);

    //    Hủy các đơn hàng dựa theo id đơn hàng
    void cancelOrderByArrayMultipleId(String[] multipleId);

    //    Lấy ra voucher theo id voucher
    Voucher getVoucherById(int id);

    //    Lấy ra danh sách các đơn hàng theo id user và trạng thái đơn hàng
    List<Order> getOrderByUserIdAndStatusOrder(int userId, int statusOrder);

    //    Lấy ra danh sách các đơn hàng theo id user
    List<Order> getOrderByUserId(int userId);

    //    Lấy ra danh sách các chi tiết đơn hàng theo id chi tiết đơn hàng
    List<OrderDetail> getOrderDetailByOrderId(List<String> listId);

    //    Lấy ra danh sách các sản phẩm đơn hàng theo id sản phẩm nằm trong bảng chi tiết đơn hàng
    List<Product> getProductInOrderDetail(int id);

    //    Lấy ra danh sách tên ảnh dựa theo id sản phẩm
    List<Image> getNameImageByProductId(int id);

    //    2 method sau bị lặp lại với Interface OrderUserDAO
    //    Lấy ra danh sách chi tiết đơn hàng chưa review
    //    Đơn hàng thuộc chi tiết đơn hàng đó đã giao thành công nhưng chưa xuất hiện trong bảng review
    List<OrderDetail> getOrderDetailNotReview(int userId);

    //       Lấy ra danh sách chi tiết đơn hàng đã review
    //   Đơn hàng thuộc chi tiết đơn hàng đó đã giao thành công và xuất hiện trong bảng review
    List<OrderDetail> getOrderDetailHasReview(int userId);
}
