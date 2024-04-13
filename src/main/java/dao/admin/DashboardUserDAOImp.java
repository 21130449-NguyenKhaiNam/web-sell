package dao.admin;

import dao.general.GeneralDAOImp;
import models.OrderDetail;
import models.Product;
import models.User;

import java.util.List;

public class DashboardUserDAOImp implements IDashboardUserDAO {

    @Override
    public int countUser() {
        String querry = "SELECT id FROM users";
        return  GeneralDAOImp.executeQueryWithSingleTable(querry, User.class).size();
    }
}
