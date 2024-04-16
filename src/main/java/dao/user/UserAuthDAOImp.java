package dao.user;

import models.User;

import java.sql.Timestamp;
import java.util.List;

public class UserAuthDAOImp implements IUserAuthDAO {
    @Override
    public List<User> selectTokenVerify(String username) {
        return null;
    }

    @Override
    public void updateTokenVerify(int id, String token, Timestamp timeTokenExpired) {

    }

    @Override
    public void updateVerify(int id, boolean status) {

    }

    @Override
    public List<User> selectTokenResetPassword(String email) {
        return null;
    }

    @Override
    public void updateTokenResetPassword(int id, String token, Timestamp timeTokenExpired) {

    }
}
