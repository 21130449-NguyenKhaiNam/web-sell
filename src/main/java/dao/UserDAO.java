package dao;

import models.User;
import org.w3c.dom.ls.LSException;

import java.util.ArrayList;
import java.util.List;

public class UserDAO implements DAO<User> {
    private List<User> data = new ArrayList<>();

    @Override
    public List<User> selectALl() {
        return null;
    }

    @Override
    public User select(int id) {
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
