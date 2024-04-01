package services;

import dao.CheckoutDAOImp;
import models.PaymentMethod;
import models.DeliveryMethod;
import models.PaymentOwner;

import java.util.List;

public class CheckoutServices {
    private CheckoutDAOImp checkoutDao;
    private static CheckoutServices INSTANCE;

    public CheckoutServices() {
        checkoutDao = new CheckoutDAOImp();
    }

    public static CheckoutServices getINSTANCE() {
        if (INSTANCE == null)
            INSTANCE = new CheckoutServices();
        return INSTANCE;
    }

    public List<DeliveryMethod> getAllInformationDeliveryMethod(){
        return checkoutDao.getAllInformationDeliveryMethod();
    }

    public List<PaymentMethod> getAllPaymentMethod(){
        return checkoutDao.getAllPaymentMethod();
    }

    public DeliveryMethod getDeliveryMethodById(int id){
        return checkoutDao.getDeliveryMethodById(id);
    }

    public PaymentMethod getPaymentMethodById(int id){
        return checkoutDao.getPaymentMethodById(id);
    }

    public PaymentOwner getPaymentOwnerByPaymentMethodId(int id){
        return checkoutDao.getPaymentOwnerByPaymentMethodId(id);
    }

    public void addNewOrder(int orderId, int userId, String dateOrder, String fullName, String email, String phone, String address, Integer deliveryMethodId, int paymentMethodId, Integer voucherId){
        checkoutDao.addNewOrder(orderId, userId, dateOrder, fullName, email, phone, address, deliveryMethodId, paymentMethodId, voucherId);
    }

    public void addEachOrderDetail(int orderId, int productId, String productName, String sizeRequired, String colorRequired, int quantityRequired, double price){
        checkoutDao.addEachOrderDetail(orderId, productId, productName, sizeRequired, colorRequired, quantityRequired, price);
    }
}
