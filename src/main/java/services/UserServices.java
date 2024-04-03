package services;

import dao.UserDAO;
import dao.UserDAOImplement;
import models.Address;
import models.User;
import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;
import properties.Map4dProperties;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
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

    public List<User> getUserById(String email) {
        return userDAO.selectByEmail(email, "1");
    }

    public User getUser(int userId) {
        return userDAO.getUserByID(userId).get(0);
    }

    public List<User> getUserByID(int id) {
        return userDAO.getUserByID(id);
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

    private boolean validateAddress(String address) throws URISyntaxException, IOException {
        URI uri = new URIBuilder(Map4dProperties.getINSTANCE().getUrl())
                .addParameter("address", address)
                .addParameter("key", Map4dProperties.getINSTANCE().getApiKey()).build();
        HttpResponse response = Request.Get(uri).execute().returnResponse();
        int statusCode = response.getStatusLine().getStatusCode();
        return statusCode == 200;
    }

    public boolean updateAddress(int userId, Address address) throws URISyntaxException, IOException {
        if (!validateAddress(address.exportAddressString()))
            return false;
        return true;
    }
}
