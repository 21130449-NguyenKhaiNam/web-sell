package dao.order;

import annotations.LogTable;
import dao.general.GeneralDAOImp;
import models.*;

import java.util.List;
import java.util.Map;

@LogTable(LogTable.ADMIN)
public class OrderAdminDAOImp implements IOrderAdminDAO {
    @Override
    public void updateStatusByOrderId(String orderId, int orderStatusId, int transactionStatusId) {
        String sql = "UPDATE orders SET orderStatusId = ?, transactionStatusId = ? WHERE id = ?";
        GeneralDAOImp.executeAllTypeUpdate(sql, orderStatusId, transactionStatusId, orderId);
    }

    @Override
    public List<Order> getListAllOrders() {
        String sql = "SELECT id, userId, dateOrder, deliveryMethodId, paymentMethodId, fullName, email, phone, address, orderStatusId, transactionStatusId, voucherId FROM orders";
        return GeneralDAOImp.executeQueryWithSingleTable(sql, Order.class);
    }

    @Override
    public List<PaymentMethod> getListAllPaymentMethodManage() {
        String sql = "SELECT id, typePayment FROM payment_methods";
        return GeneralDAOImp.executeQueryWithSingleTable(sql, PaymentMethod.class);
    }

    @Override
    public List<DeliveryMethod> getListAllDeliveryMethodManage() {
        String sql = "SELECT id, typeShipping, description, shippingFee FROM delivery_methods";
        return GeneralDAOImp.executeQueryWithSingleTable(sql, DeliveryMethod.class);
    }

    @Override
    public List<Order> getListOrdersBySearchFilter(Map<Object, List<String>> mapFilterSectionOrders, String contentSearch, String searchSelect, String startDate, String endDate) {
        StringBuilder sql = new StringBuilder("SELECT id, userId, dateOrder, deliveryMethodId, paymentMethodId, fullName, email, phone, address, orderStatusId, transactionStatusId, voucherId FROM orders");
        if (searchSelect.equals("orderId")) {
            sql.append(" WHERE id LIKE ?");
        } else if (searchSelect.equals("customerName")) {
            sql.append(" WHERE fullName LIKE ?");
        }

        for (Object objectRepresent : mapFilterSectionOrders.keySet()) {
            List<String> arrayIdSectionFilter = mapFilterSectionOrders.get(objectRepresent);
            String fillEntry = String.join(",", arrayIdSectionFilter);
            if (objectRepresent instanceof DeliveryMethod) {
                sql.append(" AND deliveryMethodId");
            } else if (objectRepresent instanceof PaymentMethod) {
                sql.append(" AND paymentMethodId");
            } else if (objectRepresent instanceof OrderStatus) {
                sql.append(" AND orderStatusId");
            } else if (objectRepresent instanceof TransactionStatus) {
                sql.append(" AND transactionStatusId");
            }
            sql.append(" IN(").append(fillEntry).append(")");
        }

        String surrStartDateFmt = String.format("'%s'", startDate);
        String surrEndDateFmt = String.format("'%s'", endDate);

        if (!startDate.isEmpty() && !endDate.isEmpty()) {
            sql.append(" AND dateOrder BETWEEN ").append(surrStartDateFmt).append(" AND ").append(surrEndDateFmt);
        } else if (!startDate.isEmpty()) {
            sql.append(" AND dateOrder >= ").append(surrStartDateFmt);
        } else if (!endDate.isEmpty()) {
            sql.append(" AND dateOrder <= ").append(surrEndDateFmt);
        }

        return GeneralDAOImp.executeQueryWithSingleTable(sql.toString(), Order.class, "%" + contentSearch + "%");
    }

    @Override
    public PaymentMethod getPaymentMethodMangeById(int id) {
        String sql = "SELECT id, typePayment FROM payment_methods WHERE id = ?";
        return GeneralDAOImp.executeQueryWithSingleTable(sql, PaymentMethod.class, id).get(0);
    }

    @Override
    public DeliveryMethod getDeliveryMethodManageById(int id) {
        String sql = "SELECT id, typeShipping, description, shippingFee FROM delivery_methods WHERE id = ?";
        return GeneralDAOImp.executeQueryWithSingleTable(sql, DeliveryMethod.class, id).get(0);
    }

    @Override
    public Order getOrderById(String id) {
        String query = "SELECT id, userId, dateOrder, deliveryMethodId, paymentMethodId, fullName, email, phone, address, orderStatusId, transactionStatusId, voucherId FROM orders WHERE id = ?";
        return GeneralDAOImp.executeQueryWithSingleTable(query, Order.class, id).get(0);
    }

    @Override
    public void removeOrderByMultipleId(String[] multipleId) {
        String fillEntry = String.format("'%s'", String.join("','", multipleId));
        String query = "DELETE FROM orders" + " WHERE id IN(" + fillEntry + ")";
        GeneralDAOImp.executeAllTypeUpdate(query);
    }

    @Override
    public void cancelOrderByArrayMultipleId(String[] multipleId) {
        String fillEntry = String.format("'%s'", String.join("','", multipleId));
        String sql = "UPDATE orders SET orderStatusId = 5 WHERE id IN (" + fillEntry + ")";
        GeneralDAOImp.executeAllTypeUpdate(sql);
    }

    @Override
    public Voucher getVoucherById(int id) {
        String sql = "SELECT id, code, description, minimumPrice, discountPercent FROM vouchers WHERE id = ?";
        return GeneralDAOImp.executeQueryWithSingleTable(sql, Voucher.class, id).get(0);
    }

    @Override
    public List<Order> getOrderByUserIdAndStatusOrder(int userId, int statusOrder) {
        String query = "SELECT id FROM orders WHERE userId = ? AND orderStatusId = ?";
        return GeneralDAOImp.executeQueryWithSingleTable(query, Order.class, userId, statusOrder);
    }

    @Override
    public List<Order> getOrderByUserId(int userId) {
        String query = "SELECT id FROM orders WHERE userId = ? ";
        return GeneralDAOImp.executeQueryWithSingleTable(query, Order.class, userId);
    }

    @Override
    public List<OrderDetail> getOrderDetailByOrderId(List<String> listId) {
        StringBuilder conditionBuilder = new StringBuilder();
        for (String id : listId)
            conditionBuilder.append("'").append(id).append("'").append(',');

        conditionBuilder.deleteCharAt(conditionBuilder.length() - 1);
        String condition = conditionBuilder.toString();

        String query = "SELECT id,  productId, quantityRequired, price " +
                "FROM order_details " +
                "WHERE orderId IN (" + condition + ")";
        return GeneralDAOImp.executeQueryWithSingleTable(query, OrderDetail.class);
    }

    @Override
    public List<Product> getProductInOrderDetail(int id) {
        String query = "SELECT DISTINCT id, name, categoryId " +
                "FROM products " +
                "WHERE id =?";
        return GeneralDAOImp.executeQueryWithSingleTable(query, Product.class, id);
    }

    @Override
    public List<Image> getNameImageByProductId(int id) {
        String query = "SELECT nameImage FROM images WHERE productId = ?";
        return GeneralDAOImp.executeQueryWithSingleTable(query, Image.class, id);
    }

    @Override
    public List<OrderDetail> getOrderDetailWithReview(int userId, boolean hasReview) {
        String query = "SELECT id FROM order_details\n" +
                "WHERE orderId IN\n" +
                "(SELECT id FROM orders\n" +
                "WHERE userId = ? AND orderStatusId = 4) AND\n" +
                "id " + (hasReview ? "" : "NOT") + " IN (SELECT reviews.orderDetailId FROM reviews)";
        return GeneralDAOImp.executeQueryWithSingleTable(query, OrderDetail.class, userId);
    }
}
