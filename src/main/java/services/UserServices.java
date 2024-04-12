package services;

import dao.AddressDAO;
import dao.IAddressDAO;
import dao.UserDAO;
import models.Address;
import models.User;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

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

    public List<User> searchUsersByName(String search) {
        return userDAO.searchUsersByName(search);
    }

    public List<User> selectAll() {
        return userDAO.selectALl();
    }

    public void insertUser(String username, String passwordEncoding, String fullname, String gender, String email, String phone, String address, Date birthDay, String role) {
        userDAO.insertUser(username, passwordEncoding, fullname, gender, email, phone, address, birthDay, role);
    }

    public void updateUserByIDWithRole(int id, String username, String fullname, String gender, String email, String phone, String address, Date birthDay, String role) {
        userDAO.updateUserByIDWithRole(id, username, fullname, gender, email, phone, address, birthDay, role);
    }

    public boolean updateAddress(Address address) throws URISyntaxException, IOException {
        if (!AddressServices.getINSTANCE().validateAddress(address.exportAddressString())) {
            return false;
        }
        userDAO.updateAddress(address);
        return true;
    }

    public Address getAddress(int userId) {
        Optional<Address> optionalAddress = addressDAO.getAddress(userId);
        return optionalAddress.orElse(null);
    }
}
