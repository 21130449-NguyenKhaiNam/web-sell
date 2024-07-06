package services;

import dao.ILogDAO;
import dao.LogDAOImp;
import models.Log;

import java.util.List;

public class LogService {
    private static LogService logService;
    private ILogDAO logDAO;
    private String ip = "128.0.0.1";

    private LogService() {
        logDAO = new LogDAOImp();
        logDAO.setIp(ip);
    }

    public static LogService getINSTANCE() {
        return logService == null ? (logService = new LogService()) : logService;
    }

    public void setIp(String ip) {
        logDAO.setIp(ip == null ? this.ip : ip);
    }

    public void insertLog(String sql, Object... params) {
        logDAO.insertLog(sql, params);
    }

    public void insertLogForSelect(String sql, List<?> list) {
        logDAO.insertLogForSelect(sql, list);
    }

    public List<Log> getLog(int start, int length, String search, String orderBy, String orderDir) {
        return logDAO.getLog(start, length, search, orderBy, orderDir);
    }

    public long getTotal() {
        return logDAO.getSize();
    }

    public List<Log> getLimit(int limit, int offset) {
        return logDAO.getLimit(limit, offset);
    }

    public long getQuantity() {
        return logDAO.getQuantity();
    }

    public long getTotalWithCondition(String search) {
        return logDAO.getSizeWithCondition(search);
    }

    public void save(Log log) {
        logDAO.save(log);
    }

    public void deleteAll() {
        logDAO.deleteAll();
    }
}
