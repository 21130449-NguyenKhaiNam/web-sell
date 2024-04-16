package dao.user;

import models.User;

import java.util.List;

public class UserAdminDAOImp implements IUserAdminDAO {
    @Override
    public List<User> selectByEmail(String email, String isVerify) {
        return null;
    }

    @Override
    public List<User> findUsername(String username) {
        return null;
    }

    @Override
    public List<User> findEmail(String email) {
        return null;
    }

    @Override
    public List<User> selectALl() {
        return null;
    }

    @Override
    public List<User> selectAccount(String username, String isVerify) {
        return null;
    }

    @Override
    public List<User> searchUsersByName(String search) {
        return null;
    }

    @Override
    public List<User> getInfoUserByOrderDetail(int orderDetailId) {
        return null;
    }
}
