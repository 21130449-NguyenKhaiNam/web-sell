package database;

import java.sql.Connection;

public interface ObjectPool {
    public Connection getConnection();

    public boolean releaseConnection(Connection connection);
}
