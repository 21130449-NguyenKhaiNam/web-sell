package dao;

import models.AbstractModel;
import models.Log;

public class LogDAO {
    private static LogDAO INSTANCE;

    private LogDAO() {
    }

    public static LogDAO getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new LogDAO();
        }
        return INSTANCE;
    }

    public void insert(AbstractModel model) {
//        GeneralDao.executeAllTypeUpdate("insert into logs (ip, level, location, resource, previousValue, currentValue) values (?, ?, ?, ?, ?, ?)",
//                ((Log) model).getIp(), ((Log) model).getLevel(), ((Log) model).getLocation(), ((Log) model).getResource(), ((Log) model).getPreviousValue(), ((Log) model).getCurrentValue());
    }
}
