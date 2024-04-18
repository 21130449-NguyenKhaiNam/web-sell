package dao.general;

import database.ConnectionPool;
import database.JDBIConnector;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.statement.Query;

import java.util.List;
import java.util.stream.Collectors;

public class GeneralDAO {

    // Sử dụng cho câu select mà trả về một đối tượng có cấu trúc 'type' trước
    public static <T> List<T> executeQueryWithSingleTable(String sql, Class<T> type, Object... params) {
        Handle handle = ConnectionPool.getINSTANCE().getHandle();
        try {
            Query query = handle.createQuery(sql);
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    query.bind(i, params[i]);
                }
            }
            return query.mapToBean(type).list();
        } finally {
            ConnectionPool.getINSTANCE().releaseHandle(handle);
        }
    }

    // Sử dụng cho câu select mà trả về kiểu không xác định cấu trúc
    public static <T> List<T> executeQueryWithSingleTable(String sql, Object... params) {
        return (List<T>) JDBIConnector.get().withHandle(handle -> {
                    Query query = handle.createQuery(sql);
                    if(params != null){
                        for (int i = 0; i < params.length; i++) {
                            query.bind(i, params[i]);
                        }
                    }
                    return query.mapToMap().list().stream()
                            .flatMap(item -> item.values().stream())
                            .collect(Collectors.toList());
                }
        );
    }

    // Dùng cho các câu: Update, Insert
    public static void executeAllTypeUpdate(String sql, Object... params) {
        Handle handle = ConnectionPool.getINSTANCE().getHandle();
        try {
            handle.useTransaction(handleInner -> {
                try{
                    handleInner.getConnection().setAutoCommit(false);
                    handleInner.execute(sql, params);
                    handleInner.getConnection().commit();
                }catch (Exception exception) {
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
