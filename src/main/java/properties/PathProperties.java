package properties;

import java.io.File;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Properties;

public class PathProperties {
    private static PathProperties INSTANCE;
    private String pathProductWeb;
    private String pathProductLocal;

    private PathProperties() {
        Properties properties = new Properties();
        InputStream inputStream = PathProperties.class.getClassLoader().getResourceAsStream("path.properties");
        try {
            properties.load(inputStream);
            pathProductWeb = properties.getProperty("path.product.webapp");
            pathProductLocal = properties.getProperty("path.product.local");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static PathProperties getINSTANCE() {
        if (INSTANCE == null)
            INSTANCE = new PathProperties();
        return INSTANCE;
    }

    public String getPathProductWeb() {
        return pathProductWeb;
    }

    public String getPathProductLocal() {
        return pathProductLocal;
    }

    public static void main(String[] args) {
        System.out.println(getINSTANCE().pathProductLocal);
        File file = new File(getINSTANCE().pathProductLocal);
        System.out.println(file.exists());
        System.out.println(Arrays.toString(file.list()));
    }
}
