package dao;

import models.User;

public interface UserDAO extends DAO<User> {
    User selectByUsername(String username);
    User selectByUsernamePassword (String username, String password);
}
