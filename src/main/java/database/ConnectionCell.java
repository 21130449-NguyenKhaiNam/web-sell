package database;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionCell {
    private String username;
    private String password;
    private String url;
    private long relaxTime;
    private long timeOut;

    private Connection connection;

    public ConnectionCell(String username, String password, String url, long relaxTime) {
        this.username = username;
        this.password = password;
        this.url = url;
        this.relaxTime = relaxTime;
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setURL(url);
        dataSource.setUser(username);
        dataSource.setPassword(password);
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isTimeOut() {
        return System.currentTimeMillis() - relaxTime > timeOut;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getRelaxTime() {
        return relaxTime;
    }

    public void setRelaxTime(long relaxTime) {
        this.relaxTime = relaxTime;
    }

    public long getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(long timeOut) {
        this.timeOut = timeOut;
    }

}
