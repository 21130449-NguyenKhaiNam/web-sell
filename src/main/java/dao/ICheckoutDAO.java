package dao;

import annotations.LogParam;
import annotations.WriteLog;
import models.DeliveryMethod;
import models.PaymentMethod;
import models.PaymentOwner;

import java.util.List;

public interface ICheckoutDAO extends IDAO {

    //    Thêm đơn hàng mới
    @WriteLog(WriteLog.INSERT)
    void addNewOrder(@LogParam("id-order") int orderId,@LogParam("id-user")  int userId,@LogParam("date-order") String dateOrder,@LogParam("full-name") String fullName,@LogParam("email") String email,@LogParam("phone") String phone,@LogParam("address") String address,@LogParam("id-delivery-method") Integer deliveryMethodId,@LogParam("id-payment-method") int paymentMethodId,@LogParam("id-voucher") Integer voucherId);

    //    Thêm chi tiết đơn hàng mới dựa theo orderId
    @WriteLog(WriteLog.INSERT)
    void addEachOrderDetail(@LogParam("id-order") int orderId,@LogParam("id-product") int productId,@LogParam("product-name") String productName,@LogParam("size-required") String sizeRequired,@LogParam("color-required") String colorRequired,@LogParam("quantity") int quantityRequired,@LogParam("price") double price);

    //    Lấy ra danh sách tất cả các phương thức giao hàng có trong bảng delivery_method
    List<DeliveryMethod> getAllInformationDeliveryMethod();

    //    Lấy ra danh sách tất cả các phương thức thanh toán có trong bảng payment_method
    List<PaymentMethod> getAllPaymentMethod();

    //    Lấy ra thông tin về phương thức thanh toán dựa vào id của phương thức thanh toán
    PaymentMethod getPaymentMethodById(int id);

    //      Lấy ra thông tin về phương thức thanh toán của cửa hàng dựa vào id của phương thức thanh toán
    PaymentOwner getPaymentOwnerByPaymentMethodId(int id);
}
