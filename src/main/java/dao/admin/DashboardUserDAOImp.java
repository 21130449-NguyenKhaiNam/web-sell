package dao.admin;

import dao.general.GeneralDAO;
import models.User;

public class DashboardUserDAOImp implements IDashboardUserDAO {

    @Override
    public int countUser() {
        String querry = "SELECT id FROM users";
        return  GeneralDAO.executeQueryWithSingleTable(querry, User.class).size();
    }
}
