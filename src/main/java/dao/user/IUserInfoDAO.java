package dao.user;

import dao.IDAO;
import models.User;

public interface IUserInfoDAO extends IDAO {
    int INFO = 1; // Các thông tin cơ bản:
    int PASSWORD = 2;
    int ADDRESS = 3;
    int ROLE = 4;
    int AVATAR = 5;


    // Cập nhật thông tin tài khoản
    int update(User user, int select);

    //    Cập nhập mật khẩu dựa vào id user
    int updatePasswordEncoding(int id, String pass);
}
