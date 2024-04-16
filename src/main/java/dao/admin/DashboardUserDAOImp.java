package dao.admin;

import dao.general.GeneralDAOImp;
import models.User;

public class DashboardUserDAOImp implements IDashboardUserDAO {

    @Override
    public int countUser() {
        String query = "SELECT id FROM users";
        return  GeneralDAOImp.executeQueryWithSingleTable(query, User.class).size();
    }
}
