package dao;

import database.ConnectionPool;
import database.JDBIConnector;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.statement.Query;
import services.LogService;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class GeneralDao {

    public static void customExecute(Consumer<Handle> function) {
        Handle handle = ConnectionPool.getINSTANCE().getHandle();
        try {
            function.accept(handle);
        } finally {
            ConnectionPool.getINSTANCE().releaseHandle(handle);
        }
    }

    //Use for select statement
    public static <T> List<T> executeQueryWithSingleTable(String sql, Class<T> type, Object... params) {
        Handle handle = ConnectionPool.getINSTANCE().getHandle();
        try {
            Query query = handle.createQuery(sql);
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    query.bind(i, params[i]);
                }
            }
            List<T> list = query.mapToBean(type).list();
//            LogService.getINSTANCE().insertLogForSelect(sql, list);
            return list;
        } finally {
            ConnectionPool.getINSTANCE().releaseHandle(handle);
        }
    }

    public static List<Map<String, Object>> executeQueryWithJoinTables(String sql, Object... params) {
        return JDBIConnector.get().withHandle(handle -> {
            Query query = handle.createQuery(sql);
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    query.bind(i, params[i]);
                }
            }
            List<Map<String, Object>> list = query.mapToMap().list();
//            LogService.getINSTANCE().insertLogForSelect(sql, list);
            return list;
        });
    }

    //Use for delete, insert and update statements
    public static void executeAllTypeUpdate(String sql, Object... params) {
        Handle handle = ConnectionPool.getINSTANCE().getHandle();
        try {
//            LogService.getINSTANCE().insertLog(sql, params);
            handle.useTransaction(handleInner -> {
                try {
                    handleInner.getConnection().setAutoCommit(false);
                    handleInner.execute(sql, params);
                    handleInner.getConnection().commit();
                } catch (Exception exception) {
                    handle.rollback();
                }

            });
        } catch (Exception exception) {
            handle.rollback();
            exception.printStackTrace();
        } finally {
            ConnectionPool.getINSTANCE().releaseHandle(handle);
        }
    }

}
