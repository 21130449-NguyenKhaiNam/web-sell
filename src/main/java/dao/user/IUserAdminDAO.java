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

    //    Lấy ra danh sách user
    List<User> selectALl();

    //    Tìm kiếm user dựa vào tên
    List<User> searchUsersByName(String search);

    List<User> getUserByIdProductDetail(int orderDetailId);
}
