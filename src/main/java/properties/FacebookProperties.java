package properties;

import java.io.InputStream;
import java.util.Properties;

public class FacebookProperties {
    private static Properties properties;
    private static String appId;
    private static String graphApiUrl;
    private static String appSecret;
    private static String redirectUri;
    private static String loginURL;

    static {
        properties = new Properties();
        InputStream inputStream = FacebookProperties.class.getClassLoader().getResourceAsStream("facebook.properties");
        try {
            properties.load(inputStream);
            appId = properties.getProperty("app.id");
            graphApiUrl = properties.getProperty("graph.api.url");
            redirectUri = properties.getProperty("redirect.uri");
            appSecret = properties.getProperty("app.secret");
            loginURL = properties.getProperty("login.url");
            loginURL.replace("${redirect.uri}", redirectUri);
            loginURL.replace("${client_id}", appId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Properties getProperties() {
        return properties;
    }

    public static String getAppId() {
        return appId;
    }

    public static String getGraphApiUrl() {
        return graphApiUrl;
    }

    public static String getAppSecret() {
        return appSecret;
    }

    public static String getRedirectUri() {
        return redirectUri;
    }

    public static String getLoginURL() {
        return loginURL;
    }
}