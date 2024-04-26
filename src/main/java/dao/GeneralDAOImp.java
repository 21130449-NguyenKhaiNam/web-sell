package dao;

import database.ConnectionPool;
import database.JDBIConnector;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.statement.Query;
import services.LogService;

import java.util.List;
import java.util.Map;

public class GeneralDAOImp implements IGeneralDAO {

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
            LogService.getINSTANCE().insertLog(query);
            return query.mapToBean(type).list();
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
                    LogService.getINSTANCE().insertLog(query);
                    return query.mapToMap().list();
                }
        );
    }

    //Use for delete, insert and update statements
    public static void executeAllTypeUpdate(String sql, Object... params) {
        Handle handle = ConnectionPool.getINSTANCE().getHandle();
        try {
            handle.useTransaction(handleInner -> {
                try {
                    handleInner.getConnection().setAutoCommit(false);
                    handleInner.execute(sql, params);
                    handleInner.getConnection().commit();
                    LogService.getINSTANCE().insertLog(sql, params);
                } catch (Exception exception) {
                    handle.rollback();
                }

            });
        } catch (Exception exception) {
            handle.rollback();
        } finally {
            ConnectionPool.getINSTANCE().releaseHandle(handle);
        }
    }
}
