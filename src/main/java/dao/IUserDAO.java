package dao;

import models.User;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public interface IUserDAO {
    @Log(Log.SELECT)
    List<User> selectAccount(String username, String isVerify);

    @Log(Log.SELECT)
    List<User> selectByEmail(String email, String isVerify);

    @Log(Log.SELECT)
    List<User> findUsername(String username);

    @Log(Log.SELECT)
    List<User> findEmail(String email);

    int updatePasswordEncoding(int id, String pass);

    List<User> selectTokenVerify(String username);

    void updateTokenVerify(int id, String token, Timestamp timeTokenExpired);

    void updateVerify(int id, boolean status);

    List<User> selectTokenResetPassword(String email);

    void updateTokenResetPassword(int id, String token, Timestamp timeTokenExpired);

    int insert(User user);

    List<User> selectALl();

    @Log(Log.SELECT)
    List<User> searchUsersByName(String search);
    void insertUser(String username, String passwordEncoding, String fullname, String gender, String email, String phone, String address, Date birthDay, String role);

    @Log(Log.SELECT)
    List<User> getUserByID(int id);
    void updateUserByID(int id, String username, String fullName, String gender, String email, String phone, String address, Date birthDay);
    void updateUserByIDWithRole(int id, String username, String fullname, String gender, String email, String phone, String address, Date birthDay, String role);
    void updateUserPassword(int userId, String password);
    int update(Object o);
    void updateInfoUser(int id, String avatar);
    List<User> getUserByIdProductDetail(int orderDetailId);
}
