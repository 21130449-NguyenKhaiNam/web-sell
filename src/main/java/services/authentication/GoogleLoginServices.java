package services.authentication;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import models.GoogleUser;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import properties.GoogleProperties;

import java.io.IOException;

public class GoogleLoginServices implements OAuthServices {
    private static GoogleLoginServices INSTANCE;

    private GoogleLoginServices() {
    }

    public static GoogleLoginServices getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new GoogleLoginServices();
        }
        return INSTANCE;
    }

    @Override
    public String getToken(String code) throws IOException {
        String response = Request.Post(GoogleProperties.getLinkGetToken())
                .bodyForm(Form.form().add("client_id", GoogleProperties.getClientId())
                        .add("client_secret", GoogleProperties.getClientSecret())
                        .add("redirect_uri", GoogleProperties.getRedirectUri())
                        .add("code", code).add("grant_type", GoogleProperties.getGrantType())
                        .build())
                .execute().returnContent().asString();
        JsonObject jobj = new Gson().fromJson(response, JsonObject.class);
        String accessToken = jobj.get("access_token").toString().replaceAll("\"", "");
        return accessToken;
    }

    @Override
    public Object getUserInfo(String accessToken) throws IOException {
        String link = GoogleProperties.getLinkGetUserInfo() + accessToken;
        String response = Request.Get(link).execute().returnContent().asString();
        GoogleUser googleUser = new Gson().fromJson(response, GoogleUser.class);
        return googleUser;
    }
}