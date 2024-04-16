package dao.user;

import annotations.LogTable;
import dao.general.GeneralDAOImp;
import database.JDBIConnector;
import models.User;

import java.sql.*;
import java.util.List;

@LogTable(LogTable.USER)
public class UserDAOImp implements IUserDAO {
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
        return GeneralDAOImp.executeQueryWithSingleTable("SELECT id FROM users WHERE username = ?", User.class, username);
    }

    @Override
    public List<User> findEmail(String email) {
        return GeneralDAOImp.executeQueryWithSingleTable("SELECT id FROM users WHERE email = ?", User.class, email);
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
    public void updateVerify(int id, boolean status){
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
    public void updateTokenResetPassword(int id, String token, Timestamp timeTokenExpired){
        String query = "UPDATE users " +
                "SET tokenResetPassword = ?, tokenResetPasswordTime = ? " +
                "WHERE id = ?";
        GeneralDAOImp.executeAllTypeUpdate(query, token, timeTokenExpired, id);
    }

    @Override
    public int insert(User user) {
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
    public List<User> selectALl() {
        String querry ="Select id, username, email, fullname, gender, phone, address, birthDay, role from users ";
        return GeneralDAOImp.executeQueryWithSingleTable(querry, User.class);
    }

    @Override
    public List<User> searchUsersByName(String search) {
        String query = "SELECT id, username, fullName, gender, phone, email, address, birthday, isVerify, role, avatar FROM users WHERE LOWER(username) LIKE ? OR LOWER(email) LIKE ? ";
        return GeneralDAOImp.executeQueryWithSingleTable(query, User.class, "%" + search.toLowerCase() + "%", "%" + search.toLowerCase() + "%");
    }

    @Override
    public void insertUser(String username,String passwordEncoding, String fullname, String gender, String email, String phone, String address, Date birthDay, String role) {
        String querry = "INSERT INTO users(username, passwordEncoding, fullname, gender, email, phone, address, birthDay, role) VALUES(?,?,?,?,?,?,?,?,?)";
        GeneralDAOImp.executeAllTypeUpdate(querry, username, passwordEncoding, fullname, gender, email, phone, address, birthDay, role);
    }

    @Override
    public List<User> getUserByID(int id) {
        String querry = "SELECT id, username, email, fullName, gender, phone, address, birthDay, avatar, role FROM users WHERE id = ?";
        return GeneralDAOImp.executeQueryWithSingleTable(querry, User.class, id);
    }

    @Override
    public void updateUserByID(int id, String username, String fullName, String gender, String email, String phone, String address, Date birthDay) {
        String query = "UPDATE users SET username = ?, fullname = ?, gender = ?, email = ?, phone = ?, address = ?, birthDay = ? WHERE id = ?";
        GeneralDAOImp.executeAllTypeUpdate(query, username, fullName, gender, email, phone, address, birthDay, id);
    }

    @Override
    public void updateUserByIDWithRole(int id, String username, String fullname, String gender, String email, String phone, String address, Date birthDay, String role) {
        String query = "UPDATE users SET username = ?, fullname = ?, gender = ?, email = ?, phone = ?, address = ?, birthDay = ?, role = ? WHERE id = ?";
        GeneralDAOImp.executeAllTypeUpdate(query, username, fullname, gender, email, phone, address, birthDay, role, id);
    }


    @Override
    public void updateUserPassword(int userId, String password) {
        String querry = "UPDATE users SET passwordEncoding = ? WHERE id = ?";
        GeneralDAOImp.executeAllTypeUpdate(querry,password,userId);
    }

    @Override
    public int update(User user) {
        String statement = "UPDATE users " +
                "SET username = :username, passwordEncoding = :passwordEncoding, fullName = :fullName, " +
                "gender = :gender, email = :email, phone = :phone, address = :address, birthday = :birthday, " +
                "isVerify = :isVerify, role = :role, avatar = :avatar, tokenVerify = :tokenVerify, " +
                "tokenResetPassword = :tokenResetPassword " +
                "WHERE id = :id";
        int count = JDBIConnector.get().withHandle(handle -> handle.createUpdate(statement)
                .bind("id", user.getId())
                .bind("username", user.getUsername())
                .bind("passwordEncoding", user.getPasswordEncoding())
                .bind("email", user.getEmail())
                .bind("fullName", user.getFullName())
                .bind("phone", user.getPhone())
                .bind("address", user.getAddress())
                .bind("birthday", user.getBirthDay())
                .bind("isVerify", user.isVerify())
                .bind("role", user.getRole())
                .bind("avatar", user.getAvatar())
                .bind("tokenVerify", user.getTokenVerify())
                .bind("tokenResetPassword", user.getTokenResetPassword())
                .execute());
        return count;
    }

    @Override
    public void updateInfoUser(int id, String avatar) {
        String query = "UPDATE users SET avatar = ? WHERE id = ?";
        GeneralDAOImp.executeAllTypeUpdate(query, avatar, id);
    }

    @Override
    public List<User> getUserByIdProductDetail(int orderDetailId) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT DISTINCT users.id, users.fullName ")
                .append("FROM users JOIN (orders JOIN order_details ON orders.id = order_details.orderId) ON users.id = orders.userId ")
                .append("WHERE order_details.id = ?");
        return GeneralDAOImp.executeQueryWithSingleTable(sql.toString(), User.class, orderDetailId);
    }
}

