package database;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class ConnectionPool implements ObjectPool {
    private static ConnectionPool INSTANCE;
    private final int INITIAL_SIZE = 12;
    private final int TIME_OUT = 10000;
    private String url;
    private String username;
    private String password;
    private LinkedBlockingQueue<Connection> available;
    private List<Connection> inUse;

    private ConnectionPool() {
        this.available = new LinkedBlockingQueue<>(INITIAL_SIZE);
        this.inUse = new ArrayList<>();
        setupConnection();
    }

    private void setupConnection() {
        for (int i = 0; i < INITIAL_SIZE; i++) {
            this.available.offer(createConnection(url, username, password));
        }
    }

    public static ConnectionPool getINSTANCE() {
        if (INSTANCE == null)
            INSTANCE = new ConnectionPool();
        return INSTANCE;
    }

    private Connection createConnection(String url, String username, String password) {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setURL(url);
        dataSource.setUser(username);
        dataSource.setPassword(password);
        Connection connection;
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    @Override
    public synchronized Connection getConnection() {
//        Nếu available rỗng -> Đợi cho đến khi có một connection trả về
//        Nếu available ko rỗng -> Lấy ra connection
        while (this.available.peek() == null) {
            System.out.println("No connection available, wait...");
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        Connection connection = this.available.remove();
        this.inUse.add(connection);
        return connection;
    }

    @Override
    public synchronized boolean releaseConnection(Connection connection) {
//        Nếu connection tồn tại -> Trả nó về available
        if (this.inUse.remove(connection)) {
//            Remove thành công
            try {
                this.available.put(connection);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return true;
        }
        return false;
    }
}
