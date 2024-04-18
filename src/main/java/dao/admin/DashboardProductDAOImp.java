package dao.admin;

import dao.general.GeneralDAO;
import models.Product;

public class DashboardProductDAOImp implements IDashboardProductDAO {

    @Override
    public long total() {
        String query = "SELECT id FROM products";
        return  GeneralDAO.executeQueryWithSingleTable(query, Product.class).size();
    }
}
