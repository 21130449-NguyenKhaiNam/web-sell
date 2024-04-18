package dao.order;

import annotations.LogTable;
import dao.general.GeneralDAO;
import models.*;

import java.util.List;
import java.util.Map;

@LogTable(LogTable.ORDER)
public class OrderAdminDAOImp implements IOrderAdminDAO {
    @Override
    public void updateStatusByOrderId(String orderId, int orderStatusId, int transactionStatusId) {
        String sql = "UPDATE orders SET orderStatusId = ?, transactionStatusId = ? WHERE id = ?";
        GeneralDAO.executeAllTypeUpdate(sql, orderStatusId, transactionStatusId, orderId);
    }

    @Override
    public List<Order> getListAllOrders() {
        String sql = "SELECT id, userId, dateOrder, deliveryMethodId, paymentMethodId, fullName, email, phone, address, orderStatusId, transactionStatusId, voucherId FROM orders";
        return GeneralDAO.executeQueryWithSingleTable(sql, Order.class);
    }

    @Override
    public List<PaymentMethod> getListAllPaymentMethodManage() {
        String sql = "SELECT id, typePayment FROM payment_methods";
        return GeneralDAO.executeQueryWithSingleTable(sql, PaymentMethod.class);
    }

    @Override
    public List<DeliveryMethod> getListAllDeliveryMethodManage() {
        String sql = "SELECT id, typeShipping, description, shippingFee FROM delivery_methods";
        return GeneralDAO.executeQueryWithSingleTable(sql, DeliveryMethod.class);
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

        return GeneralDAO.executeQueryWithSingleTable(sql.toString(), Order.class, "%" + contentSearch + "%");
    }

    @Override
    public PaymentMethod getPaymentMethodMangeById(int id) {
        String sql = "SELECT id, typePayment FROM payment_methods WHERE id = ?";
        return GeneralDAO.executeQueryWithSingleTable(sql, PaymentMethod.class, id).get(0);
    }

    @Override
    public DeliveryMethod getDeliveryMethodManageById(int id) {
        String sql = "SELECT id, typeShipping, description, shippingFee FROM delivery_methods WHERE id = ?";
        return GeneralDAO.executeQueryWithSingleTable(sql, DeliveryMethod.class, id).get(0);
    }

    @Override
    public Order getOrderById(String id) {
        String query = "SELECT id, userId, dateOrder, deliveryMethodId, paymentMethodId, fullName, email, phone, address, orderStatusId, transactionStatusId, voucherId FROM orders WHERE id = ?";
        return GeneralDAO.executeQueryWithSingleTable(query, Order.class, id).get(0);
    }

    @Override
    public void cancelOrderByArrayMultipleId(String[] multipleId) {
        String fillEntry = String.format("'%s'", String.join("','", multipleId));
        String sql = "UPDATE orders SET orderStatusId = 5 WHERE id IN (" + fillEntry + ")";
        GeneralDAO.executeAllTypeUpdate(sql);
    }

    @Override
    public Voucher getVoucherById(int id) {
        String sql = "SELECT id, code, description, minimumPrice, discountPercent FROM vouchers WHERE id = ?";
        return GeneralDAO.executeQueryWithSingleTable(sql, Voucher.class, id).get(0);
    }
}
