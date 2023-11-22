package utils;

import java.io.*;
import java.util.Arrays;
import java.util.Properties;

public class JDBIUtil {
    private final static  String PATH = ".\\src\\main\\resources\\db.properties";
    private static JDBIUtil INSTANCE = null;
    private Properties properties;

    private JDBIUtil() throws IOException {
        this.properties = new Properties();
        File file = new File(PATH);
        if (file.exists())
            properties.load(new InputStreamReader(new FileInputStream(file)));
    }

    public static JDBIUtil getINSTANCE() throws IOException {
        if (INSTANCE == null)
            INSTANCE = new JDBIUtil();
        return INSTANCE;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }
}
