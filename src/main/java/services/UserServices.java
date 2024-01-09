package services;

import dao.UserDAO;
import dao.UserDAOImplement;

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


    public String getAvatar(int id) {
        return userDAO.getAvatar(id).get(0).getAvatar();
    }

}
