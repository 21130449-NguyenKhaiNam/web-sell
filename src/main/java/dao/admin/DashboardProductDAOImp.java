package dao.admin;

import dao.general.GeneralDAO;
import models.Product;
import models.Review;

public class DashboardProductDAOImp implements IDashboardProductDAO {

    @Override
    public int countProduct() {
        String query = "SELECT id FROM products";
        return  GeneralDAO.executeQueryWithSingleTable(query, Product.class).size();
    }

    @Override
    public int countReview() {
        String query = "SELECT id FROM reviews";
        return  GeneralDAO.executeQueryWithSingleTable(query, Review.class).size();
    }
}
