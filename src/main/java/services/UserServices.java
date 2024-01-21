package services;

import dao.UserDAO;
import dao.UserDAOImplement;
import models.User;

public class UserServices {
    private static UserServices INSTANCE;
    private UserDAO userDAO;

    private UserServices() {
        userDAO = new UserDAOImplement();
    }

    public static UserServices getINSTANCE() {
        if (INSTANCE == null)
            INSTANCE = new UserServices();
        return INSTANCE;
    }

    public User getUser(int userId) {
        return userDAO.getUserByID(userId).get(0);
    }
}
