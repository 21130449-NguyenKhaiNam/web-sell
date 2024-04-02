package dao;

import annotations.WriteLog;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.util.Map;

public class LogDAOImp implements ILogDAO {
    @Override
    public void writeLog(ObjectMapper mapper, String ip, int level, Map<String, String> params, LocalDate updateDate, int table) throws JsonProcessingException {
        String changeData = mapper.writeValueAsString(params);
        if(level == WriteLog.UPDATE) {
            // Dang xu ly
        } else {
            String query = "INSERT INTO logs (ip, id_level, data_change, update_date, id_table) VALUES (?, ?, ?, ?, ?)";
            GeneralDAOImp.executeQueryWithSingleTable(query, LogForOther.class, ip, level, changeData, updateDate, table);
        }
    }

    class Log {
        private String ip;
        private int level;
        private String changeData;
        private LocalDate updateDate;
        private int table;

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public String getChangeData() {
            return changeData;
        }

        public void setChangeData(String changeData) {
            this.changeData = changeData;
        }

        public LocalDate getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(LocalDate updateDate) {
            this.updateDate = updateDate;
        }

        public int getTable() {
            return table;
        }

        public void setTable(int table) {
            this.table = table;
        }
    }

    class LogForUpdate extends Log {
        private String prevData;

        public String getPrevData() {
            return prevData;
        }

        public void setPrevData(String prevData) {
            this.prevData = prevData;
        }
    }

    class LogForOther extends Log {

    }


}
