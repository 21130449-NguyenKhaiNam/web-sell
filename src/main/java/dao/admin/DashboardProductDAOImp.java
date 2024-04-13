package dao.admin;

import dao.general.GeneralDAOImp;
import models.OrderDetail;
import models.Product;
import models.Review;

import java.util.List;

public class DashboardProductDAOImp implements IDashboardProductDAO {

    @Override
    public int countProduct() {
        String querry = "SELECT id FROM products";
        return  GeneralDAOImp.executeQueryWithSingleTable(querry, Product.class).size();
    }

    @Override
    public int countReview() {
        String querry = "SELECT id FROM reviews";
        return  GeneralDAOImp.executeQueryWithSingleTable(querry, Review.class).size();
    }

    @Override
    public List<Product> getTop5ProductName(int productId) {
        String querry="SELECT name FROM products WHERE id=?";
        return GeneralDAOImp.executeQueryWithSingleTable(querry, Product.class, productId);
    }

    @Override
    public List<OrderDetail> getTop5Product() {
        String querry = "SELECT products.id, products.name, SUM(quantityRequired)\n" +
                "FROM order_details INNER JOIN products ON products.id = order_details.productId\n" +
                "GROUP BY products.id, products.name\n" +
                "ORDER BY SUM(quantityRequired) DESC\n" +
                "LIMIT 5";
        return GeneralDAOImp.executeQueryWithSingleTable(querry, OrderDetail.class);
    }

    @Override
    public List<OrderDetail> getTop5ProductQuantity(int productId) {
        String querry="SELECT quantityRequired FROM order_details WHERE productId=?";
        return GeneralDAOImp.executeQueryWithSingleTable(querry, OrderDetail.class, productId);
    }
}
