package dao.log;

import annotations.WriteLog;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.general.GeneralDAO;
import dao.IDAO;

import java.time.LocalDate;
import java.util.Map;

public class LogDAOImp implements ILogDAO {
    @Override
    public <T> void writeLog(ObjectMapper mapper, IDAO dao, Object model, String ip, int level, Map<String, String> params, LocalDate updateDate, int table) throws JsonProcessingException {
        String changeData = mapper.writeValueAsString(params);
        if(level == WriteLog.UPDATE) {
            String query = "INSERT INTO logs (ip, id_level, data, data_change, update_date, id_table) VALUES (?, ?, ?, ?, ?, ?)";
//            System.out.println("Write Log >> id-dao: " + dao.selectById(iModel.getMainId()));
            String jsonDataPrev = mapper.writeValueAsString(model);
            GeneralDAO.executeAllTypeUpdate(query, ip, level, jsonDataPrev, changeData, updateDate, table);
        } else {
            String query = "INSERT INTO logs (ip, id_level, data_change, update_date, id_table) VALUES (?, ?, ?, ?, ?)";
            GeneralDAO.executeAllTypeUpdate(query, ip, level, changeData, updateDate, table);
        }
    }

    @Override
    public Object getModelById(Object id) {
        return null;
    }
}
