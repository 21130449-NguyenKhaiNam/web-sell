package dao;

import annotations.WriteLog;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.IModel;

import java.time.LocalDate;
import java.util.Map;

public class LogDAOImp implements ILogDAO {
    @Override
    public <T> void writeLog(ObjectMapper mapper, IDAO dao, Object model, String ip, int level, Map<String, String> params, LocalDate updateDate, int table) throws JsonProcessingException {
        String changeData = mapper.writeValueAsString(params);
        if(level == WriteLog.UPDATE) {
            IModel iModel = (IModel) model;
            String query = "INSERT INTO logs (ip, id_level, data_prev, data_change, update_date, id_table) VALUES (?, ?, ?, ?, ?, ?)";
            System.out.println("fdasdf: " + dao.getModelById(iModel.getMainId()));
            String jsonDataPrev = mapper.writeValueAsString(iModel);
            GeneralDAOImp.executeAllTypeUpdate(query, ip, level, jsonDataPrev, changeData, updateDate, table);
        } else {
            String query = "INSERT INTO logs (ip, id_level, data_change, update_date, id_table) VALUES (?, ?, ?, ?, ?)";
            GeneralDAOImp.executeAllTypeUpdate(query, ip, level, changeData, updateDate, table);
        }
    }
}