package properties;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;

public class PathProperties {
    private static PathProperties INSTANCE;
    private String pathProductWeb;
    private String pathAvatarUserWeb;
    private String pathCategoryWeb;
    private String pathParameterWeb;
    List<String> pathAdmin;
    List<String> pathMod;
    List<String> pathGuest;
    List<String> preventAll;
    private PathProperties() {
        Properties properties = new Properties();
        InputStream inputStream = PathProperties.class.getClassLoader().getResourceAsStream("path.properties");
        try {
            properties.load(inputStream);
            pathProductWeb = properties.getProperty("path.product.webapp");
            pathAvatarUserWeb = properties.getProperty("path.user.webapp");

            pathCategoryWeb = properties.getProperty("path.category.webapp");
            pathParameterWeb = properties.getProperty("path.parameter.webapp");
            pathAdmin = readList(properties, "path.admin");
            pathMod = readList(properties, "path.mod");
            pathGuest = readList(properties, "path.guest");
            preventAll = readList(properties, "path.prevent.all");
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

    public void setPathProductWeb(String pathProductWeb) {
        this.pathProductWeb = pathProductWeb;
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

    public List<String> getPathAdmin() {
        return pathAdmin;
    }

    public void setPathAdmin(List<String> pathAdmin) {
        this.pathAdmin = pathAdmin;
    }

    private List<String> readList(Properties properties, String key) {
        List<String> list = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(properties.getProperty(key), ", ");
        while (tokenizer.hasMoreTokens()) {
            list.add(tokenizer.nextToken());
        }
        return list;
    }

    public List<String> getPathMod() {
        return pathMod;
    }

    public void setPathMod(List<String> pathMod) {
        this.pathMod = pathMod;
    }

    public List<String> getPathGuest() {
        return pathGuest;
    }

    public void setPathGuest(List<String> pathGuest) {
        this.pathGuest = pathGuest;
    }

    public String getPathAvatarUserWeb() {
        return pathAvatarUserWeb;
    }

    public void setPathAvatarUserWeb(String pathAvatarUserWeb) {
        this.pathAvatarUserWeb = pathAvatarUserWeb;
    }

    public List<String> getPreventAll() {
        return preventAll;
    }

    public void setPreventAll(List<String> preventAll) {
        this.preventAll = preventAll;
    }
}
