package dao.order;

import dao.general.GeneralDAOImp;
import dao.order.ICheckoutDAO;
import models.PaymentMethod;
import models.DeliveryMethod;
import models.PaymentOwner;

import java.util.List;
public class CheckoutDAOImp implements ICheckoutDAO {

    public List<DeliveryMethod> getAllInformationDeliveryMethod(){
        String sql = "SELECT id, typeShipping, description, shippingFee FROM delivery_methods";
        return GeneralDAOImp.executeQueryWithSingleTable(sql, DeliveryMethod.class);
    }

    public List<PaymentMethod> getAllPaymentMethod(){
        String sql = "SELECT id, typePayment FROM payment_methods";
        return GeneralDAOImp.executeQueryWithSingleTable(sql, PaymentMethod.class);
    }

    public static DeliveryMethod getDeliveryMethodById(int id){
        String sql = "SELECT id, typeShipping, description, shippingFee FROM delivery_methods WHERE id = ?";
        return GeneralDAOImp.executeQueryWithSingleTable(sql, DeliveryMethod.class, id).get(0);
    }

    public PaymentMethod getPaymentMethodById(int id){
        String sql = "SELECT id, typePayment FROM payment_methods WHERE id = ?";
        return GeneralDAOImp.executeQueryWithSingleTable(sql, PaymentMethod.class, id).get(0);
    }

    public PaymentOwner getPaymentOwnerByPaymentMethodId(int id){
        String sql = "SELECT id, paymentMethodId, ownerName, paymentPlatform, accountNumber FROM payment_owner WHERE paymentMethodId = ?";
        return GeneralDAOImp.executeQueryWithSingleTable(sql, PaymentOwner.class, id).get(0);
    }

    public void addNewOrder(int orderId, int userId, String dateOrder, String fullName, String email, String phone, String address, Integer deliveryMethodId, int paymentMethodId, Integer voucherId){
        StringBuilder sql = new StringBuilder("INSERT INTO orders (id, userId, dateOrder, fullName, email, phone, address, deliveryMethodId, paymentMethodId, orderStatusId, transactionStatusId, voucherId)");
        sql.append(" VALUES (? , ?, ?, ?, ?, ?, ?, ?, ?, 1, 1, ?)");
        GeneralDAOImp.executeAllTypeUpdate(sql.toString(), orderId, userId, dateOrder, fullName, email, phone, address, deliveryMethodId, paymentMethodId, voucherId);
    }

    public void addEachOrderDetail(int orderId, int productId, String productName, String sizeRequired, String colorRequired, int quantityRequired, double price){
        StringBuilder sql = new StringBuilder("INSERT INTO order_details(orderId, productId, productName, sizeRequired, colorRequired, quantityRequired, price)");
        sql.append(" VALUES (?, ?, ?, ?, ?, ?, ?)");
        GeneralDAOImp.executeAllTypeUpdate(sql.toString(), orderId, productId, productName, sizeRequired, colorRequired, quantityRequired, price);
    }

    @Override
    public Object getModelById(Object id) {
        return null;
    }
}
