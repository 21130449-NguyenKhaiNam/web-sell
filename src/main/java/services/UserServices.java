package services;

import dao.UserDAO;
import dao.UserImplement;
import models.User;


public class UserServices {
    private static UserServices INSTANCE;

    private UserServices() {
    }

    public static UserServices getINSTANCE() {
        if (INSTANCE == null)
            INSTANCE = new UserServices();
        return INSTANCE;
    }

    public User checkSignIn(String username, String password) {
        UserDAO userDAO = new UserImplement();
        User user = userDAO.selectByUsername(username);
        if (user == null) return null;
        if (user.getUsername().equals(username))
            if (user.getPassword().equals(password))
                return user;
        return null;
    }
//    public User checkSignUp(String name, String password, String email) {
//
//    }
}
