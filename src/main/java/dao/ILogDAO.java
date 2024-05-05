package dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.IModel;
import models.Log;
import org.jdbi.v3.core.statement.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface ILogDAO extends IDAO {
    // Giúp bắt ip tác động
    void setIp(String ip);

    // Phiên bản câu
    void insertLog(String sql, Object... params);

    void insertLogForSelect(String sql, List<?> list);

    List<Log> findAll();

    List<Log> getLog(int start, int limit);

    long getSize();
}
