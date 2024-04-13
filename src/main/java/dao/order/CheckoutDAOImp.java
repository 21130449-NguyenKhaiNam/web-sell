package dao.order;

import dao.general.GeneralDAO;
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
            GeneralDAO.executeAllTypeUpdate(query, order.getId(), order.getUserId(), order.getDateOrder(), order.getFullName(), order.getEmail(), order.getPhone(), order.getAddress(), order.getDeliveryMethodId(), order.getPaymentMethodId(), order.getVoucherId());
            return 1;
        } else {
            throw new UnsupportedOperationException("CheckoutDAOImp >> Phương thức thêm không hỗ trợ tham số kiểu khác");
        }
    }

    public List<DeliveryMethod> getAllInformationDeliveryMethod(){
        String sql = "SELECT id, typeShipping, description, shippingFee FROM delivery_methods";
        return GeneralDAO.executeQueryWithSingleTable(sql, DeliveryMethod.class);
    }

    public List<PaymentMethod> getAllPaymentMethod(){
        String sql = "SELECT id, typePayment FROM payment_methods";
        return GeneralDAO.executeQueryWithSingleTable(sql, PaymentMethod.class);
    }

    public static DeliveryMethod getDeliveryMethodById(int id){
        String sql = "SELECT id, typeShipping, description, shippingFee FROM delivery_methods WHERE id = ?";
        return GeneralDAO.executeQueryWithSingleTable(sql, DeliveryMethod.class, id).get(0);
    }

    public PaymentMethod getPaymentMethodById(int id){
        String sql = "SELECT id, typePayment FROM payment_methods WHERE id = ?";
        return GeneralDAO.executeQueryWithSingleTable(sql, PaymentMethod.class, id).get(0);
    }

    public PaymentOwner getPaymentOwnerByPaymentMethodId(int id){
        String sql = "SELECT id, paymentMethodId, ownerName, paymentPlatform, accountNumber FROM payment_owner WHERE paymentMethodId = ?";
        return GeneralDAO.executeQueryWithSingleTable(sql, PaymentOwner.class, id).get(0);
    }

    public void addEachOrderDetail(int orderId, int productId, String productName, String sizeRequired, String colorRequired, int quantityRequired, double price){
        String query = "INSERT INTO order_details(orderId, productId, productName, sizeRequired, colorRequired, quantityRequired, price) VALUES (?, ?, ?, ?, ?, ?, ?)";
        GeneralDAO.executeAllTypeUpdate(query, orderId, productId, productName, sizeRequired, colorRequired, quantityRequired, price);
    }
}
