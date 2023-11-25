package dao;

import java.util.List;

public interface DAO<T> {
    //READ
    List<T> selectALl();

    T select(int id);

    //CREATE
    int insert(T o);

    int insertAll(List<T> list);

    //DELETE
    int delete(T o);

    int deleteAll(List<T> list);

    //UPDATE
    int update(Object o);

}
