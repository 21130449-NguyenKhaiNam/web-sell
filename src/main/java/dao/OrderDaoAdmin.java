package dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import models.*;
import services.admin.AdminOrderServices;

import java.util.List;
import java.util.Map;

public class OrderDaoAdmin {

    public List<Order> getListAllOrders() {
        String sql = "SELECT id, userId, dateOrder, deliveryMethodId, paymentMethodId, fullName, email, phone, address, orderStatusId, transactionStatusId, voucherId FROM orders";
        return GeneralDao.executeQueryWithSingleTable(sql, Order.class);
    }

    public List<PaymentMethod> getListAllPaymentMethodManage(){
        String sql = "SELECT id, typePayment FROM payment_methods";
        return GeneralDao.executeQueryWithSingleTable(sql, PaymentMethod.class);
    }

    public List<DeliveryMethod> getListAllDeliveryMethodManage(){
        String sql = "SELECT id, typeShipping, description, shippingFee FROM delivery_methods";
        return GeneralDao.executeQueryWithSingleTable(sql, DeliveryMethod.class);
    }

    public List<Order> getListOrdersBySearchFilter(Map<Object, List<String>> mapFilterSectionOrders, String contentSearch, String searchSelect, String startDate, String endDate) {
        StringBuilder sql = new StringBuilder("SELECT id, userId, dateOrder, deliveryMethodId, paymentMethodId, fullName, email, phone, address, orderStatusId, transactionStatusId, voucherId FROM orders WHERE 1=1");
        if(searchSelect.equals("orderId")){
            sql.append(" AND id LIKE ?");
        }else if(searchSelect.equals("customerName")){
            sql.append(" AND fullName LIKE ?");
        }

        for(Object objectRepresent : mapFilterSectionOrders.keySet()){
            List<String> arrayIdSectionFilter = mapFilterSectionOrders.get(objectRepresent);
            String fillEntry = String.join(",", arrayIdSectionFilter);
            if(objectRepresent instanceof DeliveryMethod){
                sql.append(" AND deliveryMethodId");
            }else if(objectRepresent instanceof PaymentMethod){
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

        if(!startDate.isEmpty() && !endDate.isEmpty()) {
            sql.append(" AND dateOrder BETWEEN ").append(surrStartDateFmt).append(" AND ").append(surrEndDateFmt);
        }else if(!startDate.isEmpty()){
            sql.append(" AND dateOrder >= ").append(surrStartDateFmt);
        }else if(!endDate.isEmpty()){
            sql.append(" AND dateOrder <= ").append(surrEndDateFmt);
        }

        return GeneralDao.executeQueryWithSingleTable(sql.toString(), Order.class, "%" + contentSearch + "%");
    }

    public PaymentMethod getPaymentMethodMangeById(int id){
        String sql = "SELECT id, typePayment FROM payment_methods WHERE id = ?";
        return GeneralDao.executeQueryWithSingleTable(sql, PaymentMethod.class, id).get(0);
    }

    public DeliveryMethod getDeliveryMethodManageById(int id){
        String sql = "SELECT id, typeShipping, description, shippingFee FROM delivery_methods WHERE id = ?";
        return GeneralDao.executeQueryWithSingleTable(sql, DeliveryMethod.class, id).get(0);
    }

    public Order getOrderById(String id){
        StringBuilder sql = new StringBuilder("SELECT id, userId, dateOrder, deliveryMethodId, paymentMethodId, fullName, email, phone, address, orderStatusId, transactionStatusId, voucherId, province, district, ward, detail");
        sql.append(" FROM orders WHERE id = ?");
        return GeneralDao.executeQueryWithSingleTable(sql.toString(), Order.class, id).get(0);
    }

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

    public void removeOrderByMultipleId(String[] multipleId){
        String fillEntry = String.format("'%s'", String.join("','", multipleId));
        StringBuilder sql = new StringBuilder("DELETE FROM orders");
        sql.append(" WHERE id IN(" + fillEntry + ")");
        GeneralDao.executeAllTypeUpdate(sql.toString());
    }

    public void cancelOrderByArrayMultipleId(String[] multipleId){
        String fillEntry = String.format("'%s'", String.join("','", multipleId));
        String sql = "UPDATE orders SET orderStatusId = 5 WHERE id IN (" + fillEntry + ")";
        GeneralDao.executeAllTypeUpdate(sql);
    }

    public static void updateStatusByOrderId(String orderId, int orderStatusId, int transactionStatusId){
        String sql = "UPDATE orders SET orderStatusId = ?, transactionStatusId = ? WHERE id = ?";
        GeneralDao.executeAllTypeUpdate(sql, orderStatusId, transactionStatusId, orderId);
    }

    public Voucher getVoucherById(int id){
        String sql = "SELECT id, code, description, minimumPrice, discountPercent FROM vouchers WHERE id = ?";
        return GeneralDao.executeQueryWithSingleTable(sql, Voucher.class, id).get(0);
    }

    public List<Order> getOrderByUserIdAndStatusOrder(int userId, int statusOrder){
        String querry = "SELECT id FROM orders WHERE userId = ? AND orderStatusId = ?";
        return GeneralDao.executeQueryWithSingleTable(querry,Order.class, userId,statusOrder);
    }

    public List<Order> getOrderByUserId(int userId){
        String querry = "SELECT id FROM orders WHERE userId = ? ";
        return GeneralDao.executeQueryWithSingleTable(querry,Order.class, userId);
    }

    public List<OrderDetail> getOrderDetailByOrderId(List<String> listId) {
        StringBuilder conditionBuilder = new StringBuilder();
        for (String id : listId) conditionBuilder.append("'").append(id).append("'").append(',');

        conditionBuilder.deleteCharAt(conditionBuilder.length() - 1);
        String condition = conditionBuilder.toString();

        String query = "SELECT id,  productId, quantityRequired, price " +
                "FROM order_details " +
                "WHERE orderId IN (" + condition + ")";
        return GeneralDao.executeQueryWithSingleTable(query, OrderDetail.class);
    }

    public List<Product> getProductInOrderDetail(int id){
        String query = "SELECT DISTINCT id, name, categoryId " +
                "FROM products " +
                "WHERE id =?";

        return  GeneralDao.executeQueryWithSingleTable(query, Product.class,id);
    }

    public List<Image> getNameImageByProductId(int id) {
        String querry = "SELECT nameImage FROM images WHERE productId = ?";
        return GeneralDao.executeQueryWithSingleTable(querry, Image.class, id);
    }


    public List<OrderDetail> getOrderDetailNotReview(int userId) {
        String querry = "SELECT order_details.id " +
                "FROM orders JOIN order_details ON orders.id = order_details.orderId " +
                "WHERE orders.userId = ? AND orders.orderStatusId = 4 " +
                "AND order_details.id NOT IN (SELECT reviews.orderDetailId FROM reviews) ";
        return GeneralDao.executeQueryWithSingleTable(querry, OrderDetail.class,userId);
    }

    public List<OrderDetail> getOrderDetailHasReview(int userId) {
        String querry = "SELECT order_details.id " +
                "FROM orders JOIN order_details ON orders.id = order_details.orderId " +
                "WHERE orders.userId = ? AND orders.orderStatusId = 4 " +
                "AND order_details.id IN (SELECT reviews.orderDetailId FROM reviews) ";
        return GeneralDao.executeQueryWithSingleTable(querry, OrderDetail.class, userId);
    }

    public long getQuantity() {
        String sql = "SELECT COUNT(*) count FROM orders";
        LogDAOImp.CountResult result = new LogDAOImp.CountResult();
        GeneralDao.customExecute(handle -> {
            result.setCount(handle.createQuery(sql)
                    .mapToBean(LogDAOImp.CountResult.class)
                    .list().get(0).getCount());
        });
        return result.getCount();
    }

    public List<Order> getLimit(int limit, int offset) {
        String sql = "select id, userId, dateOrder, deliveryMethodId, paymentMethodId, fullName, email, phone, address, orderStatusId, transactionStatusId, voucherId from orders limit ? offset ?";
        return GeneralDao.executeQueryWithSingleTable(sql, Order.class, limit, offset);
    }


    public static void main(String[] args) {
        System.out.println(AdminOrderServices.getINSTANCE().getQuantity());
    }
}
