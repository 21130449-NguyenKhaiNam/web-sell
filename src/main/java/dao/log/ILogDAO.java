package dao.log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.IDAO;
import models.IModel;

import java.time.LocalDate;
import java.util.Map;

public interface ILogDAO extends IDAO {
    <T> void writeLog(ObjectMapper mapper, IDAO dao, Object model, String ip, int level, Map<String, String> params, LocalDate updateDate, int table) throws JsonProcessingException;
}
