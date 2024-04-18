package dao.order;

import annotations.LogParam;
import annotations.WriteLog;
import dao.IDAO;
import models.DeliveryMethod;
import models.PaymentMethod;
import models.PaymentOwner;

import java.util.List;

public interface ICheckoutDAO extends IDAO {
    //    Thêm chi tiết đơn hàng mới dựa theo orderId
    @WriteLog(WriteLog.INSERT)
    void addEachOrderDetail(@LogParam("id-order") int orderId, @LogParam("id-product") int productId, @LogParam("product-name") String productName, @LogParam("size-required") String sizeRequired,
                            @LogParam("color-required") String colorRequired, @LogParam("quantity") int quantityRequired, @LogParam("price") double price);

    //    Lấy ra danh sách tất cả các phương thức giao hàng có trong bảng delivery_method
    List<DeliveryMethod> getAllInformationDeliveryMethod();

    //    Lấy ra danh sách tất cả các phương thức thanh toán có trong bảng payment_method
    List<PaymentMethod> getAllPaymentMethod();

    // Lấy ra phương thức thanh toán dựa vào
    DeliveryMethod getDeliveryMethodById(int id);

    //    Lấy ra thông tin về phương thức thanh toán dựa vào id của phương thức thanh toán
    PaymentMethod getPaymentMethodById(int id);

    //      Lấy ra thông tin về phương thức thanh toán của cửa hàng dựa vào id của phương thức thanh toán
    PaymentOwner getPaymentOwnerByPaymentMethodId(int id);
}
