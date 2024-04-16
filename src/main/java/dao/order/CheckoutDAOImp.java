package dao.order;

import dao.general.GeneralDAOImp;
import models.Order;
import models.PaymentMethod;
import models.DeliveryMethod;
import models.PaymentOwner;

import java.util.List;
public class CheckoutDAOImp implements ICheckoutDAO {
    // Thêm một đơn hàng mới
    @Override
    public <T> int insert(T o) {
        if(o instanceof Order) {
            Order order = (Order) o;
            String query = "INSERT INTO orders (id, userId, dateOrder, fullName, email, phone, address, deliveryMethodId, paymentMethodId, orderStatusId, transactionStatusId, voucherId)" + " VALUES (? , ?, ?, ?, ?, ?, ?, ?, ?, 1, 1, ?)";
            GeneralDAOImp.executeAllTypeUpdate(query, order.getId(), order.getUserId(), order.getDateOrder(), order.getFullName(), order.getEmail(), order.getPhone(), order.getAddress(), order.getDeliveryMethodId(), order.getPaymentMethodId(), order.getVoucherId());
            return 1;
        } else {
            throw new UnsupportedOperationException("CheckoutDAOImp >> Phương thức thêm không hỗ trợ tham số kiểu khác");
        }
    }

    @Override
    public List<DeliveryMethod> getAllInformationDeliveryMethod(){
        String sql = "SELECT id, typeShipping, description, shippingFee FROM delivery_methods";
        return GeneralDAOImp.executeQueryWithSingleTable(sql, DeliveryMethod.class);
    }

    @Override
    public List<PaymentMethod> getAllPaymentMethod(){
        String sql = "SELECT id, typePayment FROM payment_methods";
        return GeneralDAOImp.executeQueryWithSingleTable(sql, PaymentMethod.class);
    }

    @Override
    public static DeliveryMethod getDeliveryMethodById(int id){
        String sql = "SELECT id, typeShipping, description, shippingFee FROM delivery_methods WHERE id = ?";
        return GeneralDAOImp.executeQueryWithSingleTable(sql, DeliveryMethod.class, id).get(0);
    }

    @Override
    public PaymentMethod getPaymentMethodById(int id){
        String sql = "SELECT id, typePayment FROM payment_methods WHERE id = ?";
        return GeneralDAOImp.executeQueryWithSingleTable(sql, PaymentMethod.class, id).get(0);
    }

    @Override
    public PaymentOwner getPaymentOwnerByPaymentMethodId(int id){
        String sql = "SELECT id, paymentMethodId, ownerName, paymentPlatform, accountNumber FROM payment_owner WHERE paymentMethodId = ?";
        return GeneralDAOImp.executeQueryWithSingleTable(sql, PaymentOwner.class, id).get(0);
    }

    @Override
    public void addEachOrderDetail(int orderId, int productId, String productName, String sizeRequired, String colorRequired, int quantityRequired, double price){
        String query = "INSERT INTO order_details(orderId, productId, productName, sizeRequired, colorRequired, quantityRequired, price) VALUES (?, ?, ?, ?, ?, ?, ?)";
        GeneralDAOImp.executeAllTypeUpdate(query, orderId, productId, productName, sizeRequired, colorRequired, quantityRequired, price);
    }
}
