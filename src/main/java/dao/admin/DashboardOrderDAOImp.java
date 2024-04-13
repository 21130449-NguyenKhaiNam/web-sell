package dao.admin;

import dao.general.GeneralDAO;
import models.Order;
import models.OrderDetail;
import models.Product;

import java.time.LocalDate;
import java.util.List;

public class DashboardOrderDAOImp implements IDashboardOrderDAO {
    // Lấy ra danh sách order_details theo mã id của orders
    @Override
    public List<OrderDetail> selectById(Object orderId) {
        if (orderId instanceof String) {
            String query = "SELECT id, orderId, productId, productName, sizeRequired, colorRequired, quantityRequired, price FROM order_details WHERE orderId=?";
            return GeneralDAO.executeQueryWithSingleTable(query, OrderDetail.class, orderId);
        } else {
            throw new UnsupportedOperationException("DashboardOrderDAOImp >> Không hỗ trợ tham số dữ liệu khác");
        }
    }

    @Override
    public List<Product> getTopNProduct(int n) {
        // Giúp dữ liệu không bị tràn
        if (n < 1 || n > 50) n = 5;
        String query = "SELECT id, name, categoriesId, description, originalPrice, salePrice, visibility, createAt\n" +
                "FROM products WHERE id IN\n" +
                "(SELECT productId FROM\n" +
                "(SELECT productId, SUM(quantityRequired) total\n" +
                "FROM order_details\n" +
                "GROUP BY productId ORDER BY SUM(quantityRequired) DESC LIMIT ?) AS temp)";
        //        String querry = "SELECT products.id, products.name, SUM(quantityRequired)\n" +
//                "FROM order_details INNER JOIN products ON products.id = order_details.productId\n" +
//                "GROUP BY products.id, products.name\n" +
//                "ORDER BY SUM(quantityRequired) DESC\n" +
//                "LIMIT ?";
        return GeneralDAO.executeQueryWithSingleTable(query, Product.class, n);
    }

    @Override
    public int countOrder() {
        String query = "SELECT id FROM orders";
        return GeneralDAO.executeQueryWithSingleTable(query, Order.class).size();
    }

    @Override
    public List<Order> getOrderByDate(LocalDate date) {
        String query = "SELECT id, userId, dateOrder, paymentMethodId, fullName, email, phone, address, transactionStatusId, orderStatusId, voucherId, deliveryMethodId FROM orders WHERE MONTH(dateOrder) = ?";
        return GeneralDAO.executeQueryWithSingleTable(query, Order.class, date);
    }

    //    @Override
//    public List<Product> getTop5ProductName(int productId) {
//        String querry="SELECT name FROM products WHERE id=?";
//        return GeneralDAOImp.executeQueryWithSingleTable(querry, Product.class, productId);
//    }

//    @Override
//    public List<OrderDetail> getTop5Product() {
//        // Có thể tối ưu bằng sub
//        String querry = "SELECT products.id, products.name, SUM(quantityRequired)\n" +
//                "FROM order_details INNER JOIN products ON products.id = order_details.productId\n" +
//                "GROUP BY products.id, products.name\n" +
//                "ORDER BY SUM(quantityRequired) DESC\n" +
//                "LIMIT 5";
//        return GeneralDAOImp.executeQueryWithSingleTable(querry, OrderDetail.class);
//    }

//    @Override
//    public List<OrderDetail> getTop5ProductQuantity(int productId) {
//        String querry="SELECT quantityRequired FROM order_details WHERE productId=?";
//        return GeneralDAOImp.executeQueryWithSingleTable(querry, OrderDetail.class, productId);
//    }
}
