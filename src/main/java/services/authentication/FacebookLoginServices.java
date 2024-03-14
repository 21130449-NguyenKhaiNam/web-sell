package services.authentication;

import com.restfb.*;
import com.restfb.types.User;
import properties.FacebookProperties;

import java.io.IOException;

public class FacebookLoginServices implements OAuthServices {
    private static FacebookLoginServices INSTANCE;
    private FacebookClient facebookClient;

    private FacebookLoginServices() {

    }

    public static FacebookLoginServices getINSTANCE() {
        if (INSTANCE == null)
            INSTANCE = new FacebookLoginServices();
        return INSTANCE;
    }

    @Override
    public String getToken(String code) throws IOException {
        facebookClient = new DefaultFacebookClient(Version.VERSION_19_0);
        AccessToken accessToken = facebookClient.obtainUserAccessToken(FacebookProperties.getAppId(), FacebookProperties.getAppSecret(), FacebookProperties.getRedirectUri(), code);
        return accessToken.getAccessToken();
    }

    @Override
    public Object getUserInfo(String accessToken) throws IOException {
        facebookClient = new DefaultFacebookClient(accessToken, Version.VERSION_19_0);
        User user = facebookClient.fetchObject("me", User.class, Parameter.with("fields", "email"));
        return user;
    }
}
