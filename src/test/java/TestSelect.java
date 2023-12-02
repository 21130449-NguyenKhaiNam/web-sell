import dao.GeneralDao;
import dao.UserDAO;
import dao.UserImplement;
import database.DBProperties;
import database.JDBIConnector;
import models.User;

import java.util.ArrayList;
import java.util.List;

public class TestSelect {
    public static void main(String[] args) {
        System.out.println(GeneralDao.executeQueryWithSingleTable("SELECT id, fullname, username FROM users", User.class));
    }
}
