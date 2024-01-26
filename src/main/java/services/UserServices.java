package services;

import dao.UserDAO;
import dao.UserDAOImplement;
import models.User;

import java.util.List;

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

    public User getUserByIdProductDetail(int orderDetailId) {
        List<User> listUser = userDAO.getUserByIdProductDetail(orderDetailId);
        if (listUser.isEmpty())
            return null;
        return listUser.get(0);
    }
}
