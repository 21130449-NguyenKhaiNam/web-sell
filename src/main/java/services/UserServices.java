package services;

import dao.AddressDAO;
import dao.IAddressDAO;
import dao.UserDAO;
import models.User;
import utils.Encoding;

import java.sql.Date;
import java.util.List;

public class UserServices {
    private static UserServices INSTANCE;
    private UserDAO userDAO;
    private IAddressDAO addressDAO;

    private UserServices() {
        userDAO = new UserDAO();
        addressDAO = new AddressDAO();
    }

    public static UserServices getINSTANCE() {
        if (INSTANCE == null)
            INSTANCE = new UserServices();
        return INSTANCE;
    }

    public User getUser(int userId) {
        return userDAO.selectById(userId);
    }

    public List<User> getUserByID(int id) {
        return userDAO.getUserByID(id);
    }

    public void updateUserPassword(int userId, String password) {
        userDAO.updateUserPassword(userId, password);
    }

    public void updateUserByID(int id, String fullname, String gender, String phone, Date birthDay) {
        userDAO.updateUserByID(id, fullname, gender, phone, birthDay);
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

    public void insertUser(User user, String password) {
        user.setPasswordEncoding(Encoding.getINSTANCE().toSHA1(password));
        userDAO.insertUser(user);
    }

    public void updateUser(User user) {
        userDAO.updateUser(user);
    }

    public long getQuantity() {
        return userDAO.getQuantity();
    }

    public List<User> getLimit(int limit, int offset) {
        return userDAO.getLimit(limit, offset);
    }

    public List<User> getUser(Integer start, Integer length, String searchValue, String orderBy, String orderDir) {
        return userDAO.selectWithCondition(start, length, searchValue, orderBy, orderDir);
    }

    public long getTotalWithCondition(String searchValue) {
        return userDAO.getSizeWithCondition(searchValue);
    }
}
