package properties;

import java.io.InputStream;
import java.util.Properties;

public class CloudinaryProperties {
    private static Properties properties;
    private static String cloudName;
    private static String apiKey;
    private static String apiSecret;

    static {
        properties = new Properties();
        InputStream inputStream = CloudinaryProperties.class.getClassLoader().getResourceAsStream("cloudinary.properties");
        try {
            properties.load(inputStream);

            cloudName = properties.getProperty("cloud.name");
            apiKey = properties.getProperty("api.key");

            apiSecret = properties.getProperty("api.secret");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Properties getProperties() {
        return properties;
    }

    public static String getCloudName() {
        return cloudName;
    }

    public static String getApiKey() {
        return apiKey;
    }

    public static String getApiSecret() {
        return apiSecret;
    }
}