package dao;

import database.JDBIConnector;
import models.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserImplement implements UserDAO {

    @Override
    public List<User> selectALl() {
        List<User> users = JDBIConnector.get().withHandle(handle ->
                handle.createQuery("SELECT * FROM users")
                        .mapToBean(User.class).stream().collect(Collectors.toList())
        );
        return users;
    }

    @Override
    public User select(int id) {
        List<User> users = JDBIConnector.get().withHandle(handle ->
                handle.createQuery("SELECT * FROM users WHERE id = ?").bind(0, id)
                        .mapToBean(User.class).stream().collect(Collectors.toList())
        );
        if (users.size() == 1) {
            return users.get(0);
        }
        return null;
    }

    @Override
    public User selectByUsername(String username) {
        List<User> users = JDBIConnector.get().withHandle(handle ->
                handle.createQuery("SELECT username, password FROM users WHERE username = ? AND isVerify = 1").bind(0, username)
                        .mapToBean(User.class).list()
        );
        if (users.size() == 1) {
            return users.get(0);
        }
        return null;
    }

    @Override
    public User selectByUsernamePassword(String username, String password) {
        List<User> users = JDBIConnector.get().withHandle(handle ->
                handle.createQuery("SELECT * FROM users WHERE username = ? AND password = ?").bind(0, username).bind(1, password)
                        .mapToBean(User.class).stream().collect(Collectors.toList())
        );
        if (users.size() == 1) {
            return users.get(0);
        }
        return null;
    }

    @Override
    public int insert(User o) {
        return 0;
    }

    @Override
    public int insertAll(List<User> list) {
        return 0;
    }

    @Override
    public int delete(User o) {
        return 0;
    }

    @Override
    public int deleteAll(List<User> list) {
        return 0;
    }

    @Override
    public int update(Object o) {
        return 0;
    }
}
