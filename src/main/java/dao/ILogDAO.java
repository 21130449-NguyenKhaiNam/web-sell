package dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface ILogDAO {
    void writeLog(ObjectMapper mapper, String ip, int level, Map<String, String> params, LocalDate update, int table) throws JsonProcessingException;
}
