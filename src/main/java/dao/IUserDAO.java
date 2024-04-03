package dao;

import annotations.LogParam;
import annotations.WriteLog;
import models.User;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public interface IUserDAO extends IDAO {
    User selectById(int id);

    @WriteLog(WriteLog.SELECT)
    List<User> selectAccount(@LogParam("username") String username, @LogParam("isVerify") String isVerify);

    @WriteLog(WriteLog.SELECT)
    List<User> selectByEmail(String email, String isVerify);

    List<User> findUsername(String username);

    List<User> findEmail(String email);

    int updatePasswordEncoding(int id, String pass);

    List<User> selectTokenVerify(String username);

    void updateTokenVerify(int id, String token, Timestamp timeTokenExpired);

    void updateVerify(int id, boolean status);

    List<User> selectTokenResetPassword(String email);

    void updateTokenResetPassword(int id, String token, Timestamp timeTokenExpired);

    int insert(User user);

    List<User> selectALl();

    List<User> searchUsersByName(String search);

    void insertUser(String username, String passwordEncoding, String fullname, String gender, String email, String phone, String address, Date birthDay, String role);

    List<User> getUserByID(int id);

    void updateUserByID(int id, String username, String fullName, String gender, String email, String phone, String address, Date birthDay);

    void updateUserByIDWithRole(int id, String username, String fullname, String gender, String email, String phone, String address, Date birthDay, String role);

    void updateUserPassword(int userId, String password);

    @WriteLog(WriteLog.UPDATE)
    int update(User user);

    void updateInfoUser(int id, String avatar);

    List<User> getUserByIdProductDetail(int orderDetailId);
}