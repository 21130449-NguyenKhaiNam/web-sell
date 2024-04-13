package dao.admin;

import dao.general.GeneralDAOImp;
import models.Order;
import models.OrderDetail;

import java.util.List;

public class DashboardOrderDAOImp implements IDashboardOrderDAO {

    @Override
    public int countOrder() {
        String querry = "SELECT id FROM orders";
        return  GeneralDAOImp.executeQueryWithSingleTable(querry, Order.class).size();
    }

    @Override
    public List<OrderDetail> getOrderByOrderId(String orderId) {
        String querry="SELECT quantityRequired FROM order_details WHERE orderId=?";
        return GeneralDAOImp.executeQueryWithSingleTable(querry, OrderDetail.class, orderId);
    }

    @Override
    public List<OrderDetail> getOrderDetailByOrderId(String orderId) {
        String querry = "SELECT quantityRequired, price FROM order_details WHERE orderId = ?";
        return GeneralDAOImp.executeQueryWithSingleTable(querry,OrderDetail.class,orderId);
    }

    @Override
    public List<Order> getOrderByMonth(int month) {
        String querry ="SELECT id FROM orders WHERE MONTH(dateOrder)=?";
        return GeneralDAOImp.executeQueryWithSingleTable(querry,Order.class, month);
    }
}
