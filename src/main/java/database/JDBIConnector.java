package database;

import com.mysql.cj.jdbc.MysqlDataSource;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.jdbi.v3.core.Jdbi;
import properties.DBProperties;

import javax.sql.DataSource;
import java.sql.SQLException;

public class JDBIConnector {

    private static Jdbi jdbi;

    private static void makeConnect() {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setURL("jdbc:mysql://" + DBProperties.getHost() + ":" + DBProperties.getPort() + "/"
                + DBProperties.getName());
        dataSource.setUser(DBProperties.getUsername());
        dataSource.setPassword(DBProperties.getPassword());

        try {
            dataSource.setUseCompression(true);
            dataSource.setAutoReconnect(true);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        jdbi = Jdbi.create(dataSource);
    }
    public static Jdbi get(){
        if (jdbi == null)
            makeConnect();
        return jdbi;
    }

    private static DataSource configConnectionPooling() {
        HikariConfig config = new HikariConfig();
        String url = "jdbc:mysql://" + DBProperties.getHost() + ":" + DBProperties.getPort() + "/"
                + DBProperties.getName();
        config.setJdbcUrl(url);
        config.setUsername(DBProperties.getUsername());
        config.setPassword(DBProperties.getPassword());

        config.setMaximumPoolSize(15);
        config.setMinimumIdle(6);

        return new HikariDataSource(config);
    }
}
