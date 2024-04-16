package dao.general;

import java.util.List;
import java.util.Map;

public interface IGeneralDao {
    <T> List<T> executeQueryWithSingleTable(String sql, Class<T> type, Object... params);

    List<Map<String, Object>> executeQueryWithJoinTables(String sql, Object... params);

    void executeAllTypeUpdate(String sql, Object... params);
}
