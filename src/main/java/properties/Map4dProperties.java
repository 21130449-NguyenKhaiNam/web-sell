package properties;

import java.io.InputStream;
import java.util.Properties;

public class Map4dProperties {
    private static Map4dProperties INSTANCE;
    private String url;
    private String apiKey;

    private Map4dProperties() {
        Properties properties = new Properties();
        InputStream inputStream = Map4dProperties.class.getClassLoader().getResourceAsStream("map4d.properties");
        try {
            properties.load(inputStream);
            url = properties.getProperty("map.url");
            apiKey = properties.getProperty("map.apikey");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Map4dProperties getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new Map4dProperties();
        }
        return INSTANCE;
    }

    public static void setINSTANCE(Map4dProperties INSTANCE) {
        Map4dProperties.INSTANCE = INSTANCE;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
}
