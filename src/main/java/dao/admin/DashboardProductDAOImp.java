package dao.admin;

import dao.general.GeneralDAOImp;
import models.Product;
import models.Review;

public class DashboardProductDAOImp implements IDashboardProductDAO {

    @Override
    public int countProduct() {
        String query = "SELECT id FROM products";
        return  GeneralDAOImp.executeQueryWithSingleTable(query, Product.class).size();
    }

    @Override
    public int countReview() {
        String query = "SELECT id FROM reviews";
        return  GeneralDAOImp.executeQueryWithSingleTable(query, Review.class).size();
    }
}
