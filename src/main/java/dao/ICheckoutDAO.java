package dao;

import models.DeliveryMethod;
import models.PaymentMethod;
import models.PaymentOwner;

import java.util.List;

public interface ICheckoutDAO extends IDAO {

    //    Thêm đơn hàng mới
    void addNewOrder(int orderId,int userId, String dateOrder, String fullName, String email,String phone, String address, Integer deliveryMethodId, int paymentMethodId, Integer voucherId);

    //    Thêm chi tiết đơn hàng mới dựa theo orderId
    void addEachOrderDetail(int orderId, int productId, String productName,String sizeRequired,String colorRequired, int quantityRequired, double price);

    //    Lấy ra danh sách tất cả các phương thức giao hàng có trong bảng delivery_method
    List<DeliveryMethod> getAllInformationDeliveryMethod();

    //    Lấy ra danh sách tất cả các phương thức thanh toán có trong bảng payment_method
    List<PaymentMethod> getAllPaymentMethod();

    //    Lấy ra thông tin về phương thức thanh toán dựa vào id của phương thức thanh toán
    PaymentMethod getPaymentMethodById(int id);

    //      Lấy ra thông tin về phương thức thanh toán của cửa hàng dựa vào id của phương thức thanh toán
    PaymentOwner getPaymentOwnerByPaymentMethodId(int id);
}
