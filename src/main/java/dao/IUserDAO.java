package dao;

import annotations.LogParam;
import annotations.WriteLog;
import models.User;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public interface IUserDAO extends IDAO {
//    Lấy ra user (tất cả các thông tin) theo id user
    User selectById(int id);

//    Lấy ra user (tất cả các thông tin) theo username và trạng thái xác nhận tài khoản (isVerify)
    @WriteLog(WriteLog.SELECT)
    List<User> selectAccount(@LogParam("username") String username, @LogParam("isVerify") String isVerify);

//    Lấy ra user (id, username, email, passwordEncoding, tokenResetPassword)
//    theo email và trạng thái xác nhận tài khoản (isVerify)
//    Phục vụ cho việc kiểm tra email tồn tại hay không?
    @WriteLog(WriteLog.SELECT)
    List<User> selectByEmail(String email, String isVerify);

//    Lấy ra danh sách id user dựa theo username
    List<User> findUsername(String username);

//    Lấy ra danh sách id user dựa theo email
    List<User> findEmail(String email);

//    Cập nhập mật khẩu dựa vào id user
    int updatePasswordEncoding(int id, String pass);

//    Lấy ra token và hạn sử dụng token dựa vào username
//    Tạo link để xác thực tài khoản
    List<User> selectTokenVerify(String username);

//    Cập nhập token và hạn sử dụng token dựa vào id user
    void updateTokenVerify(int id, String token, Timestamp timeTokenExpired);

//    Cập nhập trạng thái xác nhận tài khoản dựa vào id user
    void updateVerify(int id, boolean status);

//    Lấy ra token và hạn sử dụng token dựa vào email (quên mật khẩu)
    List<User> selectTokenResetPassword(String email);

//    Cập nhập token và hạn sử dụng token dựa vào id user (quên mật khẩu)
    void updateTokenResetPassword(int id, String token, Timestamp timeTokenExpired);

//    Thêm mới user
    int insert(User user);

//    Lấy ra danh sách user
    List<User> selectALl();

//    Tìm kiếm user dựa vào tên
    List<User> searchUsersByName(String search);

    void insertUser(String username, String passwordEncoding, String fullname, String gender, String email, String phone, String address, Date birthDay, String role);

//    Lấy ra danh sách user dựa vào id user
    List<User> getUserByID(int id);

//    Cập nhập thông tin user (ko cập nhập role) dựa vào id user
    void updateUserByID(int id, String username, String fullName, String gender, String email, String phone, String address, Date birthDay);

//    Cập nhập thông tin user + role dựa vào id user
    void updateUserByIDWithRole(int id, String username, String fullname, String gender, String email, String phone, String address, Date birthDay, String role);

//    Cập nhập mật khẩu dựa vào id user
    void updateUserPassword(int userId, String password);

    @WriteLog(WriteLog.UPDATE)
    int update(User user);

//    Cập nhập avatar dựa vào id user
    void updateInfoUser(int id, String avatar);

//    Lấy ra id và fullName user dựa vào id user từ bảng orderDetail
//    Cần rename lại tên hàm
    List<User> getUserByIdProductDetail(int orderDetailId);
}
