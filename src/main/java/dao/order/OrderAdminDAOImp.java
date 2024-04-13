package dao.order;

import annotations.LogTable;
import dao.general.GeneralDAO;
import models.*;

import java.util.List;
import java.util.Map;

@LogTable(LogTable.ADMIN)
public class OrderAdminDAOImp implements IOrderAdminDAO {

    public static void updateStatusByOrderId(String orderId, int orderStatusId, int transactionStatusId) {
        String sql = "UPDATE orders SET orderStatusId = ?, transactionStatusId = ? WHERE id = ?";
        GeneralDAO.executeAllTypeUpdate(sql, orderStatusId, transactionStatusId, orderId);
    }

    public List<Order> getListAllOrders() {
        String sql = "SELECT id, userId, dateOrder, deliveryMethodId, paymentMethodId, fullName, email, phone, address, orderStatusId, transactionStatusId, voucherId FROM orders";
        return GeneralDAO.executeQueryWithSingleTable(sql, Order.class);
    }

    public List<PaymentMethod> getListAllPaymentMethodManage() {
        String sql = "SELECT id, typePayment FROM payment_methods";
        return GeneralDAO.executeQueryWithSingleTable(sql, PaymentMethod.class);
    }

    public List<DeliveryMethod> getListAllDeliveryMethodManage() {
        String sql = "SELECT id, typeShipping, description, shippingFee FROM delivery_methods";
        return GeneralDAO.executeQueryWithSingleTable(sql, DeliveryMethod.class);
    }

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

    public PaymentMethod getPaymentMethodMangeById(int id) {
        String sql = "SELECT id, typePayment FROM payment_methods WHERE id = ?";
        return GeneralDAO.executeQueryWithSingleTable(sql, PaymentMethod.class, id).get(0);
    }

    public DeliveryMethod getDeliveryMethodManageById(int id) {
        String sql = "SELECT id, typeShipping, description, shippingFee FROM delivery_methods WHERE id = ?";
        return GeneralDAO.executeQueryWithSingleTable(sql, DeliveryMethod.class, id).get(0);
    }

    public Order getOrderById(String id) {
        String query = "SELECT id, userId, dateOrder, deliveryMethodId, paymentMethodId, fullName, email, phone, address, orderStatusId, transactionStatusId, voucherId FROM orders WHERE id = ?";
        return GeneralDAO.executeQueryWithSingleTable(query, Order.class, id).get(0);
    }

    public void removeOrderByMultipleId(String[] multipleId) {
        String fillEntry = String.format("'%s'", String.join("','", multipleId));
        String query = "DELETE FROM orders" + " WHERE id IN(" + fillEntry + ")";
        GeneralDAO.executeAllTypeUpdate(query);
    }

    public void cancelOrderByArrayMultipleId(String[] multipleId) {
        String fillEntry = String.format("'%s'", String.join("','", multipleId));
        String sql = "UPDATE orders SET orderStatusId = 5 WHERE id IN (" + fillEntry + ")";
        GeneralDAO.executeAllTypeUpdate(sql);
    }

    public Voucher getVoucherById(int id) {
        String sql = "SELECT id, code, description, minimumPrice, discountPercent FROM vouchers WHERE id = ?";
        return GeneralDAO.executeQueryWithSingleTable(sql, Voucher.class, id).get(0);
    }

    public List<Order> getOrderByUserIdAndStatusOrder(int userId, int statusOrder) {
        String query = "SELECT id FROM orders WHERE userId = ? AND orderStatusId = ?";
        return GeneralDAO.executeQueryWithSingleTable(query, Order.class, userId, statusOrder);
    }

    public List<Order> getOrderByUserId(int userId) {
        String query = "SELECT id FROM orders WHERE userId = ? ";
        return GeneralDAO.executeQueryWithSingleTable(query, Order.class, userId);
    }

    public List<OrderDetail> getOrderDetailByOrderId(List<String> listId) {
        StringBuilder conditionBuilder = new StringBuilder();
        for (String id : listId)
            conditionBuilder.append("'").append(id).append("'").append(',');

        conditionBuilder.deleteCharAt(conditionBuilder.length() - 1);
        String condition = conditionBuilder.toString();

        String query = "SELECT id,  productId, quantityRequired, price " +
                "FROM order_details " +
                "WHERE orderId IN (" + condition + ")";
        return GeneralDAO.executeQueryWithSingleTable(query, OrderDetail.class);
    }

    public List<Product> getProductInOrderDetail(int id) {
        String query = "SELECT DISTINCT id, name, categoryId " +
                "FROM products " +
                "WHERE id =?";

        return GeneralDAO.executeQueryWithSingleTable(query, Product.class, id);
    }

    public List<Image> getNameImageByProductId(int id) {
        String query = "SELECT nameImage FROM images WHERE productId = ?";
        return GeneralDAO.executeQueryWithSingleTable(query, Image.class, id);
    }

    public List<OrderDetail> getOrderDetailWithReview(int userId, boolean hasReview) {
        String query = "SELECT id FROM order_details\n" +
                "WHERE orderId IN\n" +
                "(SELECT id FROM orders\n" +
                "WHERE userId = ? AND orderStatusId = 4) AND\n" +
                "id " + (hasReview ? "" : "NOT") + " IN (SELECT reviews.orderDetailId FROM reviews)";
        return GeneralDAO.executeQueryWithSingleTable(query, OrderDetail.class, userId);
    }

//    public List<OrderDetail> getOrderDetailNotReview(int userId) {
////        String query = "SELECT order_details.id " +
////                "FROM orders JOIN order_details ON orders.id = order_details.orderId " +
////                "WHERE orders.userId = ? AND orders.orderStatusId = 4 " +
////                "AND order_details.id NOT IN (SELECT reviews.orderDetailId FROM reviews) ";
//        String query = "SELECT id FROM order_details\n" +
//                "WHERE orderId IN\n" +
//                "(SELECT id FROM orders\n" +
//                "WHERE userId = ? AND orderStatusId = 4) AND\n" +
//                "id NOT IN (SELECT reviews.orderDetailId FROM reviews)";
//        return GeneralDAO.executeQueryWithSingleTable(query, OrderDetail.class, userId);
//    }
//
//    public List<OrderDetail> getOrderDetailHasReview(int userId) {
//        String querry = "SELECT order_details.id " +
//                "FROM orders JOIN order_details ON orders.id = order_details.orderId " +
//                "WHERE orders.userId = ? AND orders.orderStatusId = 4 " +
//                "AND order_details.id IN (SELECT reviews.orderDetailId FROM reviews) ";
//        return GeneralDAO.executeQueryWithSingleTable(querry, OrderDetail.class, userId);
//    }

    //    public List<Order> getListOrderByPartialId(String orderId){
//        StringBuilder sql = new StringBuilder("SELECT id, userId, dateOrder, deliveryMethodId, paymentMethodId, fullName, email, phone, address, orderStatusId, transactionStatusId, voucherId");
//        sql.append(" FROM orders WHERE id LIKE ?");
//        String surrOrderIdFmt = "%" + orderId + "%";
//        return GeneralDao.executeQueryWithSingleTable(sql.toString(), Order.class, surrOrderIdFmt);
//    }

//    public List<Order> getListOrderByCustomerName(String customerName){
//        StringBuilder sql = new StringBuilder("SELECT id, userId, dateOrder, deliveryMethodId, paymentMethodId, fullName, email, phone, address, orderStatusId, transactionStatusId, voucherId");
//        sql.append(" FROM orders WHERE fullName LIKE ?");
//        String surrCustomerNameFmt = "%" + customerName + "%";
//        return GeneralDao.executeQueryWithSingleTable(sql.toString(), Order.class, surrCustomerNameFmt);
//    }

//    public void updateOrderStatusIdByOrderId(int orderStatusId , String orderId){
//        String sql = "UPDATE orders SET orderStatusId ? WHERE id = ?";
//        GeneralDao.executeAllTypeUpdate(sql, orderStatusId, orderId);
//    }
//
//    public void updateTransactionStatusIdByOrderId(int transactionStatusId , String orderId){
//        String sql = "UPDATE orders SET transactionStatusId ? WHERE id = ?";
//        GeneralDao.executeAllTypeUpdate(sql, transactionStatusId, orderId);
//    }

    //    public static void main(String[] args) {
//        System.out.println(getVoucherById(1));
//        String[] list1 = {"1"};
//        String[] list2 = {"1", "2"};
//        Map<Object, List<String>> map = new HashMap<>();
//        map.put(new DeliveryMethod(), list1);
//        map.put(new PaymentMethod(), list2);
//
//        System.out.println(getListOrdersByFilterSection(map, "2022-10-01", "2022-10-30"));
//        System.out.println(getListAllOrders());
//        System.out.println(getListOrderByCustomerName("N"));
//        String sql = "SELECT id FROM orders";
//        List<Order> list = GeneralDao.executeQueryWithSingleTable(sql, Order.class);
//        for (Order order: list) {
//            System.out.println(order.getId());
//        }
//    }
}
