package dao.user;

import dao.general.GeneralDAOImp;
import models.User;

import java.util.List;

public class UserAdminDAOImp implements IUserAdminDAO {
    @Override
    public List<User> selectByEmail(String email, String isVerify) {
        String query;
        if (isVerify == null) {
            query = "SELECT id, username, email, passwordEncoding, tokenResetPassword FROM users WHERE email = ?";
            return GeneralDAOImp.executeQueryWithSingleTable(query, User.class, email);
        } else {
            query = "SELECT id, username, email, passwordEncoding, tokenResetPassword FROM users WHERE email = ? AND isVerify = ?";
            return GeneralDAOImp.executeQueryWithSingleTable(query, User.class, email, isVerify);
        }
    }

    @Override
    public List<User> findUsername(String username) {
        String query = "SELECT id FROM users WHERE username = ?";
        return GeneralDAOImp.executeQueryWithSingleTable(query, User.class, username);
    }

    @Override
    public List<User> findEmail(String email) {
        String query = "SELECT id FROM users WHERE email = ?";
        return GeneralDAOImp.executeQueryWithSingleTable(query, User.class, email);
    }

    @Override
    public List<User> selectALl() {
        String query ="Select id, username, email, fullname, gender, phone, address, birthDay, role from users ";
        return GeneralDAOImp.executeQueryWithSingleTable(query, User.class);
    }

    @Override
    public List<User> searchUsersByName(String search) {
        String query = "SELECT id, username, fullName, gender, phone, email, address, birthday, isVerify, role, avatar FROM users WHERE LOWER(username) LIKE ? OR LOWER(email) LIKE ? ";
        return GeneralDAOImp.executeQueryWithSingleTable(query, User.class, "%" + search.toLowerCase() + "%", "%" + search.toLowerCase() + "%");
    }
}
