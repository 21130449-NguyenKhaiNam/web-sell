package dao;

import models.Log;

import java.util.List;

public interface ILogDAO {
    // Giúp bắt ip tác động
    void setIp(String ip);

    // Phiên bản câu
    void insertLog(String sql, Object... params);

    void insertLogForSelect(String sql, List<?> list);

    List<Log> getLog(int start, int limit, String search, String orderBy, String orderDir);

    long getSize();

    long getSizeWithCondition(String search);

    void save(Log log);
}
