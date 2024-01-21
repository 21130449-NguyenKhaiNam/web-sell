package properties;

import java.io.InputStream;
import java.util.Properties;

public class DBProperties {
    private static Properties properties = new Properties();
    private static String host;
    private static String username;
    private static String password;
    private static String port;
    private static String name;
    private static int connectionSize;


    static {
        InputStream inputStream = DBProperties.class.getClassLoader().getResourceAsStream("db.properties");
        try {
            properties.load(inputStream);

            host = properties.getProperty("host");
            username = properties.getProperty("username");

            password = properties.getProperty("password");
            port = properties.getProperty("port");
            name = properties.getProperty("name");
//            connectionSize = Integer.parseInt(properties.getProperty("connection.size"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private DBProperties() {
    }

    public static void setProperties(Properties properties) {
        DBProperties.properties = properties;
    }

    public static String getHost() {
        return host;
    }

    public static void setHost(String host) {
        DBProperties.host = host;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        DBProperties.username = username;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        DBProperties.password = password;
    }

    public static String getPort() {
        return port;
    }

    public static void setPort(String port) {
        DBProperties.port = port;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        DBProperties.name = name;
    }
}
