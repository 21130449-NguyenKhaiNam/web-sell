package dao;

import database.JDBIConnector;
import models.Category;
import models.Size;
import org.jdbi.v3.core.statement.Query;
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
    public static void executeAllTypeUpdate(String sql, Object... params){
//        jdbi.withHandle(handle -> {
//            Update update = handle.createUpdate(sql);
//            for(int i = 0; i < params.length; i++){
//                update.bind(i, params[i]);
//            }
//            return update.execute();
//        });
        JDBIConnector.get().withHandle(handle -> handle.execute(sql, params));
    }

}
