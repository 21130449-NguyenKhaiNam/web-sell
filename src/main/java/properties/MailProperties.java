package properties;

import java.io.InputStream;
import java.util.Properties;

public class MailProperties {
    private static Properties properties = new Properties();
    private static String host;
    private static String portTSL;
    private static String auth;
    private static String startTSL;
    private static String email;
    private static String password;
    private static String durationTokenVerify;
    private static String durationTokenRestPassword;

    static {
        InputStream inputStream = DBProperties.class.getClassLoader().getResourceAsStream("mail.properties");
        try {
            properties.load(inputStream);
            host = properties.getProperty("mail.smtp.host");
            portTSL = properties.getProperty("mail.smtp.port");
            auth = properties.getProperty("mail.smtp.auth");
            startTSL = properties.getProperty("mail.smtp.starttls.enable");
            email = properties.getProperty("mail.email");
            password = properties.getProperty("mail.password");
            durationTokenVerify = properties.getProperty("mail.verify.durationTime");
            durationTokenRestPassword = properties.getProperty("mail.resetPassword.durationTime");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public MailProperties() {
    }

    public static Properties getProperties() {
        return properties;
    }

    public static void setProperties(Properties properties) {
        MailProperties.properties = properties;
    }

    public static String getHost() {
        return host;
    }

    public static void setHost(String host) {
        MailProperties.host = host;
    }

    public static String getPortTSL() {
        return portTSL;
    }

    public static void setPortTSL(String portTSL) {
        MailProperties.portTSL = portTSL;
    }

    public static String getAuth() {
        return auth;
    }

    public static void setAuth(String auth) {
        MailProperties.auth = auth;
    }

    public static String getStartTSL() {
        return startTSL;
    }

    public static void setStartTSL(String startTSL) {
        MailProperties.startTSL = startTSL;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        MailProperties.email = email;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        MailProperties.password = password;
    }

    public static String getDurationTokenVerify() {
        return durationTokenVerify;
    }

    public static void setDurationTokenVerify(String durationTokenVerify) {
        MailProperties.durationTokenVerify = durationTokenVerify;
    }

    public static String getDurationTokenRestPassword() {
        return durationTokenRestPassword;
    }

    public static void setDurationTokenRestPassword(String durationTokenRestPassword) {
        MailProperties.durationTokenRestPassword = durationTokenRestPassword;
    }
}
