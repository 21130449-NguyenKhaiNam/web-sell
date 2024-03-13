package services;

import dao.UserDAO;
import dao.UserDAOImplement;
import models.User;

import java.sql.Date;
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

    public List<User> getUserByID(int id) {
        return userDAO.getUserByID(id);
    }

    public List<User> getUserById(String email) {
        return userDAO.selectByEmail(email, "1");
    }

    public void updateUserPassword(int userId, String password) {
        userDAO.updateUserPassword(userId, password);
    }

    public void updateUserByID(int id, String username, String fullname, String gender, String email, String phone, String address, Date birthDay) {
        userDAO.updateUserByID(id, username, fullname, gender, email, phone, address, birthDay);
    }

    public void updateInfoUser(int id, String avatar) {
        userDAO.updateInfoUser(id, avatar);
    }

    public User getUserByIdProductDetail(int orderDetailId) {
        List<User> listUser = userDAO.getUserByIdProductDetail(orderDetailId);
        if (listUser.isEmpty())
            return null;
        return listUser.get(0);
    }

    public List<User> searchUsersByName(String search) {
        return userDAO.searchUsersByName(search);
    }

    public List<User> selectALl() {
        return userDAO.selectALl();
    }

    public void insertUser(String username, String passwordEncoding, String fullname, String gender, String email, String phone, String address, Date birthDay, String role) {
        userDAO.insertUser(username, passwordEncoding, fullname, gender, email, phone, address, birthDay, role);
    }

    public void updateUserByIDWithRole(int id, String username, String fullname, String gender, String email, String phone, String address, Date birthDay, String role) {
        userDAO.updateUserByIDWithRole(id, username, fullname, gender, email, phone, address, birthDay, role);
    }
}
