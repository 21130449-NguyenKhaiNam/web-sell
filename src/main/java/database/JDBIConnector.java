package database;

import com.mysql.cj.jdbc.MysqlDataSource;
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
            dataSource.setDefaultFetchSize(1000);
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
}
