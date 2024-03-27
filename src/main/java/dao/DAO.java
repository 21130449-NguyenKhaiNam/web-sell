package dao;

import java.util.List;

<<<<<<< HEAD
public interface DAO<T> {
=======
public interface DAO<T> extends IDao{
>>>>>>> 21130449

    T selectById(int id);

    //CREATE
    int insert(T o);

    int insertAll(List<T> list);

    //DELETE
    int delete(T o);

    int deleteAll(List<T> list);

    //UPDATE
    int update(Object o);

}
