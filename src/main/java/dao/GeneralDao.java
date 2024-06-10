package dao;

import database.ConnectionPool;
import database.JDBIConnector;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.statement.Query;
import org.jdbi.v3.core.statement.Update;
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
            query.setFetchSize(Integer.MIN_VALUE);

            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    query.bind(i, params[i]);
                }
            }
            List<T> list = query.mapToBean(type).list();
            try {
                LogService.getINSTANCE().insertLogForSelect(sql, list);
            } catch (Exception e) {
                System.out.println("Lỗi bởi ghi log trong general dao [executeQueryWithSingleTable] >> " + e.getMessage());
            }
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
            try {
                LogService.getINSTANCE().insertLogForSelect(sql, list);
            } catch (Exception e) {
                System.out.println("Lỗi bởi ghi log trong general dao [executeQueryWithJoinTables]>> " + e.getMessage());
            }
            return list;
        });
    }

    //Use for delete, insert and update statements
    public static void executeAllTypeUpdate(String sql, Object... params) {
        Handle handle = ConnectionPool.getINSTANCE().getHandle();
        try {
            LogService.getINSTANCE().insertLog(sql, params);
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
            System.out.println("Lỗi bởi ghi log trong general dao [executeAllTypeUpdate]>> " + exception.getMessage());
            handle.rollback();
        } finally {
            ConnectionPool.getINSTANCE().releaseHandle(handle);
        }
    }

    public static int executeInsert(String sql, Object... params) {
        Handle handle = ConnectionPool.getINSTANCE().getHandle();
        Update insert = handle.createUpdate(sql);
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                insert.bind(i, params[i]);
            }
        }
        try {
            LogService.getINSTANCE().insertLog(sql, params);
        } catch (Exception e) {
            System.out.println("Lỗi bởi ghi log trong general dao [executeInsert]>> " + e.getMessage());
        }
        return insert.executeAndReturnGeneratedKeys("id") // "id" is the column name of the generated key
                .mapTo(Integer.class)
                .one();
    }
}
