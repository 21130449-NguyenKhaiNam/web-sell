package dao;

import database.JDBIConnector;
import models.Category;
import models.Size;
import org.jdbi.v3.core.statement.Query;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class GeneralDao {

    //Use for select statement
    public static <T>List<T> executeQueryWithSingleTable(String sql, Class<T> type, Object... params) {
        return JDBIConnector.get().withHandle(handle -> {
                    Query query = handle.createQuery(sql);
                    if(params != null){
                        for (int i = 0; i < params.length; i++) {
                            query.bind(i, params[i]);
                        }
                    }
                    return query.mapToBean(type).list();
                }
        );
    }

    public static List<Map<String, Object>> executeQueryWithJoinTables(String sql, Object... params) {
        return JDBIConnector.get().withHandle(handle -> {
                    Query query = handle.createQuery(sql);
                    if(params != null){
                        for (int i = 0; i < params.length; i++) {
                            query.bind(i, params[i]);
                        }
                    }
                    return query.mapToMap().list();
                }
        );
    }

    //Use for delete, insert and update statements
    public static void executeAllTypeUpdate(String sql, Object... params) {
        try {
            JDBIConnector.get().useHandle(handle -> {
                handle.getConnection().setAutoCommit(false);
                try {
                    handle.execute(sql, params);
                    handle.commit();
                } catch (Exception exception) {
                    handle.rollback();
                } finally {
                    handle.close();
                }
            });
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
