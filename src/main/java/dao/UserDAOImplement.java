package dao;

import database.JDBIConnector;
import models.User;

import java.sql.Timestamp;
import java.util.List;

public class UserDAOImplement implements UserDAO {
    @Override
    public User selectById(int id) {
        String query = "SELECT id, username, fullName, gender, phone, email, address, birthday, isVerify, role, avatar FROM users WHERE id = ?";
        return GeneralDao.executeQueryWithSingleTable(query, User.class, id).get(0);
    }

    @Override
    public List<User> selectAccount(String username, String isVerify) {
        String query;
        if (isVerify == null) {
            query = "SELECT id, username, passwordEncoding, role, isVerify FROM users WHERE username = ?";
            return GeneralDao.executeQueryWithSingleTable(query, User.class, username);
        } else {
            query = "SELECT id, username, passwordEncoding, role, isVerify FROM users WHERE username = ? AND isVerify = ?";
            return GeneralDao.executeQueryWithSingleTable(query, User.class, username, isVerify);
        }
    }

    @Override
    public List<User> selectByEmail(String email, String isVerify) {
        String query;
        if (isVerify == null) {
            query = "SELECT id, username, email, passwordEncoding, tokenResetPassword FROM users WHERE email = ?";
            return GeneralDao.executeQueryWithSingleTable(query, User.class, email);
        } else {
            query = "SELECT id, username, email, passwordEncoding, tokenResetPassword FROM users WHERE email = ? AND isVerify = ?";
            return GeneralDao.executeQueryWithSingleTable(query, User.class, email, isVerify);
        }
    }

    @Override
    public List<User> findUsername(String username) {
        return GeneralDao.executeQueryWithSingleTable("SELECT id FROM users WHERE username = ?", User.class, username);
    }

    @Override
    public List<User> findEmail(String email) {
        return GeneralDao.executeQueryWithSingleTable("SELECT id FROM users WHERE email = ?", User.class, email);
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
        return GeneralDao.executeQueryWithSingleTable(query, User.class, username);
    }

    @Override
    public void updateTokenVerify(int id, String token, Timestamp timeTokenExpired) {
        String statement = "UPDATE users " +
                "SET tokenVerify = ?, tokenVerifyTime = ? " +
                "WHERE id = ?";
        GeneralDao.executeAllTypeUpdate(statement, token, timeTokenExpired, id);
    }

    @Override
    public void updateVerify(int id, boolean status) {
        String query = "UPDATE users " +
                "SET isVerify = ? " +
                "WHERE id = ?";
        GeneralDao.executeAllTypeUpdate(query, status, id);
    }

    @Override
    public List<User> selectTokenResetPassword(String email) {
        String query = "SELECT id, tokenResetPassword, tokenResetPasswordTime FROM users WHERE email = ?";
        return GeneralDao.executeQueryWithSingleTable(query, User.class, email);
    }

    @Override
    public void updateTokenResetPassword(int id, String token, Timestamp timeTokenExpired) {
        String query = "UPDATE users " +
                "SET tokenResetPassword = ?, tokenResetPasswordTime = ? " +
                "WHERE id = ?";
        GeneralDao.executeAllTypeUpdate(query, token, timeTokenExpired, id);
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
                .bind(9, user.isRole())
                .bind(10, user.getAvatar())
                .bind(11, user.getTokenVerifyTime())
                .bind(12, user.getTokenVerify())
                .bind(13, user.getTokenResetPasswordTime())
                .bind(14, user.getTokenResetPassword())
                .execute());
        return count;
    }

    @Override
    public int insertAll(List<User> list) {
        int count = 0;
        for (User item : list) {
            insert(item);
            count++;
        }
        return count;
    }

    @Override
    public int delete(User o) {
        return 0;
    }

    @Override
    public List<User> selectALl() {
        String querry ="Select * from users ";
        return GeneralDao.executeQueryWithSingleTable(querry, User.class);
    }

    @Override
    public void deleteUserById(int id) {
        String query = "DELETE FROM users WHERE id = ?";
        GeneralDao.executeAllTypeUpdate(query, id);
    }


    @Override
    public int deleteAll(List<User> list) {
        return 0;
    }

    @Override
    public int update(Object o) {
        User user = (User) o;
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
                .bind("role", user.isRole())
                .bind("avatar", user.getAvatar())
                .bind("tokenVerify", user.getTokenVerify())
                .bind("tokenResetPassword", user.getTokenResetPassword())
                .execute());
        return count;
    }
}
