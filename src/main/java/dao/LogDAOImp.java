package dao;

import org.jdbi.v3.core.statement.Query;

import java.util.Arrays;

public class LogDAOImp implements ILogDAO {

    private String ip;

    public void setIp(String ip) {
        this.ip = ip;
    }

    // Câu insert vào db dành cho câu lệnh select
    @Override
    public void insertLog(Query query) {
        System.out.println("Log >> " + query);
    }

    @Override
    public void insertLog(String sql, Object... params) {
        System.out.println("Log >> " + sql + " - " + Arrays.toString(params));
    }

}
