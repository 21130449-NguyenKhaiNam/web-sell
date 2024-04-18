package dao.user;

import dao.general.GeneralDAO;
import database.JDBIConnector;
import models.User;

import java.sql.Timestamp;

public class UserAuthDAOImp implements IUserAuthDAO {
    @Override
    public User selectById(Object id) {
        if(id instanceof Integer) {
            String query = "SELECT id, username, fullName, gender, phone, email, address, birthday, isVerify, role, avatar FROM users WHERE id = ? AND isVerify = ?";
            return GeneralDAO.executeQueryWithSingleTable(query, User.class, id, 1).get(0);
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
    public User selectTokenVerify(String username) {
        String query = "SELECT id, tokenVerifyTime, tokenVerify FROM users WHERE username = ? AND isVerify = 0";
        return GeneralDAO.executeQueryWithSingleTable(query, User.class, username).get(0);
    }

    @Override
    public void updateTokenVerify(int id, String token, Timestamp timeTokenExpired) {
        String statement = "UPDATE users " +
                "SET tokenVerify = ?, tokenVerifyTime = ? " +
                "WHERE id = ?";
        GeneralDAO.executeAllTypeUpdate(statement, token, timeTokenExpired, id);
    }

    @Override
    public void updateVerify(int id, boolean status) {
        String query = "UPDATE users " +
                "SET isVerify = ? " +
                "WHERE id = ?";
        GeneralDAO.executeAllTypeUpdate(query, status, id);
    }

    @Override
    public User selectTokenResetPassword(String email) {
        String query = "SELECT id, tokenResetPassword, tokenResetPasswordTime FROM users WHERE email = ?";
        return GeneralDAO.executeQueryWithSingleTable(query, User.class, email).get(0);
    }

    @Override
    public void updateTokenResetPassword(int id, String token, Timestamp timeTokenExpired) {
        String query = "UPDATE users " +
                "SET tokenResetPassword = ?, tokenResetPasswordTime = ? " +
                "WHERE id = ?";
        GeneralDAO.executeAllTypeUpdate(query, token, timeTokenExpired, id);
    }

    @Override
    public User findEmail(String email, boolean isVerify) {
        String verify = isVerify ? "1" : "0";
        String query = "SELECT id FROM users WHERE email = ?";
        return GeneralDAO.executeQueryWithSingleTable(query, User.class, email).get(0);
    }
    @Override
    public int updatePasswordEncoding(int id, String pass) {
        String statement = "UPDATE users " +
                "SET passwordEncoding = :passwordEncoding "
                + "WHERE id = :id";
        int count = JDBIConnector.get().withHandle(handle -> handle.createUpdate(statement)
                .bind("id", id)
                .bind("passwordEncoding", pass)
                .execute());
        return count;
    }

}
