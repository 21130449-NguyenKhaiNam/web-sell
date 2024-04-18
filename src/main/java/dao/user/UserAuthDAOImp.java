package dao.user;

import dao.general.GeneralDAOImp;
import database.JDBIConnector;
import models.User;

import java.sql.Timestamp;
import java.util.List;

public class UserAuthDAOImp implements IUserAuthDAO {
    @Override
    public User selectById(Object id) {
        if(id instanceof Integer) {
            String query = "SELECT id, username, fullName, gender, phone, email, address, birthday, isVerify, role, avatar FROM users WHERE id = ?";
            return GeneralDAOImp.executeQueryWithSingleTable(query, User.class, id).get(0);
        } else {
            throw new UnsupportedOperationException("UserDAOImp >> Phương thức thêm không hỗ trợ tham số kiểu khác");
        }
    }

    @Override
    public int insert(Object u) {
        User user = (User) u;
        String statement = "INSERT INTO users (username, passwordEncoding, fullName, gender, email, phone, address, birthDay, isVerify, role, avatar, tokenVerifyTime, tokenVerify, tokenResetPasswordTime, tokenResetPassword) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        int count = JDBIConnector.get().withHandle(handle -> handle.createUpdate(statement)
                .bind(0, user.getUsername())
                .bind(1, user.getPasswordEncoding())
                .bind(2, user.getFullName())
                .bind(3, user.getGender())
                .bind(4, user.getEmail())
                .bind(5, user.getPhone())
                .bind(6, user.getAddress())
                .bind(7, user.getBirthDay())
                .bind(8, user.isVerify())
                .bind(9, user.getRole())
                .bind(10, user.getAvatar())
                .bind(11, user.getTokenVerifyTime())
                .bind(12, user.getTokenVerify())
                .bind(13, user.getTokenResetPasswordTime())
                .bind(14, user.getTokenResetPassword())
                .execute());
        return count;
    }

    @Override
    public List<User> selectTokenVerify(String username) {
        String query = "SELECT id, tokenVerifyTime, tokenVerify FROM users WHERE username = ? AND isVerify = 0";
        return GeneralDAOImp.executeQueryWithSingleTable(query, User.class, username);
    }

    @Override
    public void updateTokenVerify(int id, String token, Timestamp timeTokenExpired) {
        String statement = "UPDATE users " +
                "SET tokenVerify = ?, tokenVerifyTime = ? " +
                "WHERE id = ?";
        GeneralDAOImp.executeAllTypeUpdate(statement, token, timeTokenExpired, id);
    }

    @Override
    public void updateVerify(int id, boolean status) {
        String query = "UPDATE users " +
                "SET isVerify = ? " +
                "WHERE id = ?";
        GeneralDAOImp.executeAllTypeUpdate(query, status, id);
    }

    @Override
    public List<User> selectTokenResetPassword(String email) {
        String query = "SELECT id, tokenResetPassword, tokenResetPasswordTime FROM users WHERE email = ?";
        return GeneralDAOImp.executeQueryWithSingleTable(query, User.class, email);
    }

    @Override
    public void updateTokenResetPassword(int id, String token, Timestamp timeTokenExpired) {
        String query = "UPDATE users " +
                "SET tokenResetPassword = ?, tokenResetPasswordTime = ? " +
                "WHERE id = ?";
        GeneralDAOImp.executeAllTypeUpdate(query, token, timeTokenExpired, id);
    }

    @Override
    public List<User> selectAccount(String username, String isVerify) {
        String query;
        if (isVerify == null) {
            query = "SELECT id, username, passwordEncoding, fullName, email, gender, phone, address, birthDay, role, isVerify FROM users WHERE username = ?";
            return GeneralDAOImp.executeQueryWithSingleTable(query, User.class, username);
        } else {
            query = "SELECT id, username, passwordEncoding,  fullName, email, gender, phone, address, birthDay, role, isVerify FROM users WHERE username = ? AND isVerify = ?";
            return GeneralDAOImp.executeQueryWithSingleTable(query, User.class, username, isVerify);
        }
    }
}
