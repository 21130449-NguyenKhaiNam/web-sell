package dao.admin;

import dao.general.GeneralDAO;
import models.User;

public class DashboardUserDAOImp implements IDashboardUserDAO {

    @Override
    public int countUser() {
        String query = "SELECT id FROM users";
        return  GeneralDAO.executeQueryWithSingleTable(query, User.class).size();
    }
}
