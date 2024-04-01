package dao;

import models.DeliveryMethod;
import models.PaymentMethod;
import models.PaymentOwner;

import java.util.List;

public interface ICheckoutDAO {
    void addNewOrder(int orderId, int userId, String dateOrder, String fullName, String email, String phone, String address, Integer deliveryMethodId, int paymentMethodId, Integer voucherId);

    void addEachOrderDetail(int orderId, int productId, String productName, String sizeRequired, String colorRequired, int quantityRequired, double price);

    List<DeliveryMethod> getAllInformationDeliveryMethod();

    List<PaymentMethod> getAllPaymentMethod();

    PaymentMethod getPaymentMethodById(int id);

    PaymentOwner getPaymentOwnerByPaymentMethodId(int id);
}
