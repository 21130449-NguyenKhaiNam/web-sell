package services;

import dao.ILogDAO;
import dao.LogDAOImp;
import models.Log;

import java.util.List;

/**
 * Sử dụng để ghi Log, lớp muốn ghi log thì sử dụng phương thức createProxy
 * để tạo các phiên bản:
 * I?Dao dao = LogService.getINSTANCE().createProxy(new ?DaoImp());
 */
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

    public long getTotalWithCondition(String search) {
        return logDAO.getSizeWithCondition(search);
    }

    public void save(Log log) {
        logDAO.save(log);
    }
}
