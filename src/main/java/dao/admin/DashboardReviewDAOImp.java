package dao.admin;

import dao.general.GeneralDAO;
import models.Review;

public class DashboardReviewDAOImp implements IDashboardReviewDAO {
    @Override
    public long total() {
        String query = "SELECT id FROM reviews";
        return  GeneralDAO.executeQueryWithSingleTable(query, Review.class).size();
    }
}
