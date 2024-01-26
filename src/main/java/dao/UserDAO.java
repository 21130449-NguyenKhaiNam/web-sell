package dao;

import models.User;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public interface UserDAO extends DAO<User> {

    List<User> selectByEmail(String email, String isVerify);

    List<User> selectAccount(String username, String isVerify);

    List<User> findUsername(String username);

    List<User> findEmail(String email);

    public int updatePasswordEncoding(int id, String pass);

    public List<User> selectTokenVerify(String username);

    public void updateTokenVerify(int id, String token, Timestamp timeTokenExpired);

    public void updateVerify(int id, boolean status) ;

    public List<User> selectTokenResetPassword(String email);

    public void updateTokenResetPassword(int id, String token, Timestamp timeTokenExpired);

    @Override
    int delete(User o);
    public List<User> selectALl();

    public void deleteUserById(int id);

    public List<User> searchUsersByName(String search);

    public void insertUser(String username,String passwordEncoding, String fullname, String gender, String email, String phone, String address, Date birthDay);

    public List<User> getUserByID(int id);

    public void updateUserByID(int id, String username, String fullname, String gender, String email, String phone, String address, Date birthDay);

    public void deleteContactsFromUserByUserId(int userId);

    public void deleteReviewsFromUserByUserId(int userId);

    public void deleteOrderdetailsFromUserByUserId(int userId);

    public void deleteOrderFromUserByUserId(int userId);

    public void updateUserPassword(int userId, String password);

    public List<User> getAvatar(int id);

   public void updateInfoUser(int id,  String avatar);


    List<User> getUserByIdProductDetail(int orderDetailId);
}

