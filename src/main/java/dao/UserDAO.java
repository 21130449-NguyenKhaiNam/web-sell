package dao;

import database.JDBIConnector;
import models.User;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserDAO {

    public User selectById(int id) {
        String query = "SELECT id, username, fullName, gender, phone, email, birthday, isVerify, role, avatar FROM users WHERE id = ?";
        return GeneralDao.executeQueryWithSingleTable(query, User.class, id).get(0);
    }


    public List<User> selectByUsername(String username, String isVerify) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT id, username, fullName, passwordEncoding, gender, phone, email, address, birthday, isVerify, role, avatar FROM users WHERE username = ?")
                .append(isVerify == null ? "" : " AND isVerify = ?");
        return GeneralDao.executeQueryWithSingleTable(query.toString(), User.class, username, isVerify);
    }

    public List<User> selectByEmail(String email, String isVerify) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT id, username, fullName, passwordEncoding, gender, phone, email, address, birthday, isVerify, role, avatar FROM users WHERE email = ?")
                .append(isVerify == null ? "" : " AND isVerify = ?");
        return GeneralDao.executeQueryWithSingleTable(query.toString(), User.class, email, isVerify);
    }


    public List<User> findUsername(String username) {
        return GeneralDao.executeQueryWithSingleTable("SELECT id FROM users WHERE username = ?", User.class, username);
    }


    public List<User> findEmail(String email) {
        return GeneralDao.executeQueryWithSingleTable("SELECT id FROM users WHERE email = ?", User.class, email);
    }


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


    public List<User> selectTokenVerify(String username) {
        String query = "SELECT id, tokenVerifyTime, tokenVerify FROM users WHERE username = ? AND isVerify = 0";
        return GeneralDao.executeQueryWithSingleTable(query, User.class, username);
    }

    public void updateTokenVerify(int id, String token, Timestamp timeTokenExpired) {
        String statement = "UPDATE users " +
                "SET tokenVerify = ?, tokenVerifyTime = ? " +
                "WHERE id = ?";
        GeneralDao.executeAllTypeUpdate(statement, token, timeTokenExpired, id);
    }


    public void updateVerify(int id, boolean status) {
        String query = "UPDATE users " +
                "SET isVerify = ? " +
                "WHERE id = ?";
        GeneralDao.executeAllTypeUpdate(query, status, id);
    }


    public List<User> selectTokenResetPassword(String email) {
        String query = "SELECT id, tokenResetPassword, tokenResetPasswordTime FROM users WHERE email = ?";
        return GeneralDao.executeQueryWithSingleTable(query, User.class, email);
    }


    public void updateTokenResetPassword(int id, String token, Timestamp timeTokenExpired) {
        String query = "UPDATE users " +
                "SET tokenResetPassword = ?, tokenResetPasswordTime = ? " +
                "WHERE id = ?";
        GeneralDao.executeAllTypeUpdate(query, token, timeTokenExpired, id);
    }


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


    public int insertAll(List<User> list) {
        int count = 0;
        for (User item : list) {
            insert(item);
            count++;
        }
        return count;
    }


    public List<User> selectALl() {
        String querry = "Select id, username, email, fullname, gender, phone, address, birthDay, role from users ";
        return GeneralDao.executeQueryWithSingleTable(querry, User.class);
    }


    public void deleteUserById(int id) {
        String query = "DELETE FROM users WHERE id = ?";
        GeneralDao.executeAllTypeUpdate(query, id);
    }


    public List<User> searchUsersByName(String search) {
        String query = "SELECT id, username, fullName, gender, phone, email, address, birthday, isVerify, role, avatar FROM users WHERE LOWER(username) LIKE ? OR LOWER(email) LIKE ? ";
        return GeneralDao.executeQueryWithSingleTable(query, User.class, "%" + search.toLowerCase() + "%", "%" + search.toLowerCase() + "%");
    }


    public void insertUser(User user) {
        String query = "INSERT INTO users(username, passwordEncoding, fullname, gender, email, phone, birthDay, role) VALUES(?,?,?,?,?,?,?,?,?)";
        GeneralDao.executeAllTypeUpdate(query,
                user.getUsername(),
                user.getPasswordEncoding(),
                user.getFullName(),
                user.getGender(),
                user.getEmail(),
                user.getPhone(),
                user.getBirthDay(),
                user.getRole());
    }


    public List<User> getUserByID(int id) {
        String querry = "SELECT id, username, email, fullName, gender, phone, address, birthDay, avatar, role FROM users WHERE id = ?";
        return GeneralDao.executeQueryWithSingleTable(querry, User.class, id);
    }


    public void updateUserByID(int id, String fullName, String gender, String phone, Date birthDay) {
        String query = "UPDATE users SET fullname = ?, gender = ?, phone = ?, birthDay = ? WHERE id = ?";
        GeneralDao.executeAllTypeUpdate(query, fullName, gender, phone, birthDay, id);
    }


    public void updateUser(User user) {
        String query = "UPDATE users SET username = ?, fullname = ?, gender = ?, email = ?, phone = ?, birthDay = ?, role = ? WHERE id = ?";
        GeneralDao.executeAllTypeUpdate(query, user.getUsername(), user.getFullName(), user.getGender(), user.getEmail(), user.getPhone(), user.getBirthDay(), user.getRole(), user.getId());
    }


    public void updateUserPassword(int userId, String password) {
        String querry = "UPDATE users SET passwordEncoding = ? WHERE id = ?";
        GeneralDao.executeAllTypeUpdate(querry, password, userId);
    }


    public int deleteAll(List<User> list) {
        return 0;
    }


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
                .bind("role", user.getRole())
                .bind("avatar", user.getAvatar())
                .bind("tokenVerify", user.getTokenVerify())
                .bind("tokenResetPassword", user.getTokenResetPassword())
                .execute());
        return count;
    }


    public List<User> getAvatar(int id) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT avatar FROM users WHERE id = ?");
        return GeneralDao.executeQueryWithSingleTable(sql.toString(), User.class, id);
    }


    public void updateInfoUser(int id, String avatar) {
        String query = "UPDATE users SET avatar = ? WHERE id = ?";
        GeneralDao.executeAllTypeUpdate(query, avatar, id);
    }


    public List<User> getUserByIdProductDetail(int orderDetailId) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT DISTINCT users.id, users.fullName ")
                .append("FROM users JOIN (orders JOIN order_details ON orders.id = order_details.orderId) ON users.id = orders.userId ")
                .append("WHERE order_details.id = ?");
        return GeneralDao.executeQueryWithSingleTable(sql.toString(), User.class, orderDetailId);
    }

    public long getQuantity() {
        String sql = "SELECT COUNT(*) count FROM users";
        LogDAOImp.CountResult result = new LogDAOImp.CountResult();
        GeneralDao.customExecute(handle -> {
            result.setCount(handle.createQuery(sql)
                    .mapToBean(LogDAOImp.CountResult.class)
                    .list().get(0).getCount());
        });
        return result.getCount();
    }

    public List<User> getLimit(int limit, int offset) {
        String querry = "Select id, username, email, fullname, gender, phone, address, birthDay, role from users limit ? offset ?";
        return GeneralDao.executeQueryWithSingleTable(querry, User.class, limit, offset);
    }

    public List<User> selectWithCondition(Integer start, Integer length, String searchValue, String orderBy, String orderDir) {
        String sql = "SELECT id, username, fullName, gender, email, phone, birthday, role " + "FROM users " +
                "WHERE username LIKE :search OR fullName LIKE :search OR email LIKE :search OR phone LIKE :search " +
                "ORDER BY :orderBy :orderDir LIMIT :limit OFFSET :start";
        List<User> users = new ArrayList<>();
        GeneralDao.customExecute(handle -> {
            users.addAll(handle.createQuery(sql)
                    .bind("search", "%" + searchValue + "%")
                    .bind("limit", length)
                    .bind("start", start)
                    .bind("orderBy", orderBy)
                    .bind("orderDir", orderDir)
                    .mapToBean(User.class)
                    .list());
        });
        if (orderDir.equals("desc")) {
            Collections.reverse(users);
        }
        return users;
    }

    public long getSizeWithCondition(String searchValue) {
        String sql = "SELECT COUNT(*) count FROM users WHERE username LIKE :search OR fullName LIKE :search OR email LIKE :search OR phone LIKE :search ";
        LogDAOImp.CountResult result = new LogDAOImp.CountResult();
        GeneralDao.customExecute(handle -> {
            result.setCount(handle.createQuery(sql)
                    .bind("search", "%" + searchValue + "%")
                    .mapToBean(LogDAOImp.CountResult.class)
                    .list().get(0).getCount());
        });
        return result.getCount();
    }
}

