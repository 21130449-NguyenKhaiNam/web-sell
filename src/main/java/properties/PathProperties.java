package properties;

import java.io.File;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Properties;

public class PathProperties {
    private static PathProperties INSTANCE;
    private String pathProductWeb;
    private String pathProductLocal;
    private String pathAvatarUserWeb;
    private String pathCategoryWeb;
    private String pathParameterWeb;

    private PathProperties() {
        Properties properties = new Properties();
        InputStream inputStream = PathProperties.class.getClassLoader().getResourceAsStream("path.properties");
        try {
            properties.load(inputStream);
            pathProductWeb = properties.getProperty("path.product.webapp");
            pathProductLocal = properties.getProperty("path.product.local");
            pathAvatarUserWeb = properties.getProperty("path.user.webapp");

            pathCategoryWeb = properties.getProperty("path.category.webapp");
            pathParameterWeb = properties.getProperty("path.parameter.webapp");
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

    public void setPathProductWeb(String pathProductWeb) {
        this.pathProductWeb = pathProductWeb;
    }

    public void setPathProductLocal(String pathProductLocal) {
        this.pathProductLocal = pathProductLocal;
    }

    public String getPathCategoryWeb() {
        return pathCategoryWeb;
    }

    public void setPathCategoryWeb(String pathCategoryWeb) {
        this.pathCategoryWeb = pathCategoryWeb;
    }

    public String getPathParameterWeb() {
        return pathParameterWeb;
    }

    public void setPathParameterWeb(String pathParameterWeb) {
        this.pathParameterWeb = pathParameterWeb;
    }

    public static void main(String[] args) {
        System.out.println(getINSTANCE().pathProductLocal);
        File file = new File(getINSTANCE().pathProductLocal);
        System.out.println(file.exists());
        System.out.println(Arrays.toString(file.list()));
    }

    public String getPathAvatarUserWeb() {
        return pathAvatarUserWeb;
    }

    public void setPathAvatarUserWeb(String pathAvatarUserWeb) {
        this.pathAvatarUserWeb = pathAvatarUserWeb;
    }
}
