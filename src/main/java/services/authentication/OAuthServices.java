package services.authentication;

import java.io.IOException;

public interface OAuthServices {
    String getToken(String code) throws IOException;

    Object getUserInfo(String accessToken) throws IOException;
}
