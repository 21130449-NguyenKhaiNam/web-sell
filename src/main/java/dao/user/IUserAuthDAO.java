package dao.user;

import dao.IDAO;
import models.User;

import java.sql.Timestamp;
import java.util.List;

public interface IUserAuthDAO extends IDAO {
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
}
