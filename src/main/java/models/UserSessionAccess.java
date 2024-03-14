package models;

import java.util.HashMap;

public class UserSessionAccess {
    private String userSessionId;
    private static UserSessionAccess INSTANCE;

    public UserSessionAccess() {
    }

    public static UserSessionAccess getINSTANCE() {
        if (INSTANCE == null)
            INSTANCE = new UserSessionAccess();
        return INSTANCE;
    }

    public String getUserSessionId() {
        return userSessionId;
    }

    public void setUserSessionId(String userSessionId) {
        this.userSessionId = userSessionId;
    }
}
