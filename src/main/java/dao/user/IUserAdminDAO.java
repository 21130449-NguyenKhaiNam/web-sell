package dao.user;

import annotations.LogParam;
import annotations.WriteLog;
import dao.IDAO;
import models.User;

import java.util.List;

public interface IUserAdminDAO extends IDAO {
    //    Lấy ra user (id, username, email, passwordEncoding, tokenResetPassword)
    //    theo email và trạng thái xác nhận tài khoản (isVerify)
    //    Phục vụ cho việc kiểm tra email tồn tại hay không?
    List<User> selectByEmail(String email, String isVerify);

    //    Lấy ra danh sách id user dựa theo username
    List<User> findUsername(String username);

    //    Lấy ra danh sách id user dựa theo email
    List<User> findEmail(String email);

    //    Lấy ra danh sách user
    List<User> selectALl();

    //    Lấy ra user (tất cả các thông tin) theo username và trạng thái xác nhận tài khoản (isVerify)
    @WriteLog(WriteLog.SELECT)
    List<User> selectAccount(@LogParam("username") String username, @LogParam("isVerify") String isVerify);

    //    Tìm kiếm user dựa vào tên
    List<User> searchUsersByName(String search);

    //    Lấy ra id và fullName user dựa vào id user từ bảng orderDetail
    List<User> getInfoUserByOrderDetail(int orderDetailId);
}
