package properties;

import java.io.InputStream;
import java.util.Properties;

public class RoleProperties {
    private static RoleProperties INSTANCE;
    private String admin;
    private String subAdmin;
    private String guest;

    private RoleProperties() {
        InputStream inputStream = RoleProperties.class.getClassLoader().getResourceAsStream("role.properties");
        try {
            Properties properties = new Properties();
            properties.load(inputStream);
            admin = properties.getProperty("role.admin");
            subAdmin = properties.getProperty("role.sub.admin");
            guest = properties.getProperty("role.guest");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static RoleProperties getINSTANCE() {
        if (INSTANCE == null)
            INSTANCE = new RoleProperties();
        return INSTANCE;
    }

    public static void setINSTANCE(RoleProperties INSTANCE) {
        RoleProperties.INSTANCE = INSTANCE;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public String getSubAdmin() {
        return subAdmin;
    }

    public void setSubAdmin(String subAdmin) {
        this.subAdmin = subAdmin;
    }

    public String getGuest() {
        return guest;
    }

    public void setGuest(String guest) {
        this.guest = guest;
    }
}
