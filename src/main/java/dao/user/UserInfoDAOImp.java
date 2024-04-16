package dao.user;

import models.User;

public class UserInfoDAOImp implements IUserInfoDAO {
    @Override
    public int update(Object o) {
        return IUserInfoDAO.super.update(o);
    }

    @Override
    public int update(User user, int select) {
        return 0;
    }

    @Override
    public int updatePasswordEncoding(int id, String pass) {
        return 0;
    }
}
