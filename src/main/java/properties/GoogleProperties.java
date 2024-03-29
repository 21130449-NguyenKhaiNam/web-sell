package properties;

import java.io.InputStream;
import java.util.Properties;

public class GoogleProperties {
    private static Properties properties;
    private static String clientId;
    private static String clientSecret;
    private static String redirectUri;
    private static String grantType;
    private static String linkGetToken;
    private static String linkGetUserInfo;

    static {
        properties = new Properties();
        InputStream inputStream = DBProperties.class.getClassLoader().getResourceAsStream("google.properties");
        try {
            properties.load(inputStream);
            clientId = properties.getProperty("client.id");
            clientSecret = properties.getProperty("client.secret");
            redirectUri = properties.getProperty("redirect.uri");
            grantType = properties.getProperty("grant.type");
            linkGetToken = properties.getProperty("link.get.token");
            linkGetUserInfo = properties.getProperty("link.get.user.info");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Properties getProperties() {
        return properties;
    }

    public static String getClientId() {
        return clientId;
    }

    public static String getClientSecret() {
        return clientSecret;
    }

    public static String getRedirectUri() {
        return redirectUri;
    }

    public static String getGrantType() {
        return grantType;
    }

    public static String getLinkGetToken() {
        return linkGetToken;
    }

    public static String getLinkGetUserInfo() {
        return linkGetUserInfo;
    }
}