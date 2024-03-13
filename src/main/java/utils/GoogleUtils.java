package utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import models.GoogleUser;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;

import java.io.IOException;


public class GoogleUtils {
    private final static String GOOGLE_CLIENT_ID = "336186921669-k0vb5vt05phajcah47psl3v0t4h3eah0.apps.googleusercontent.com";
    private final static String GOOGLE_CLIENT_SECRET = "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";
    private final static String GOOGLE_REDIRECT_URI = "http://localhost:8080/signInGoogle";
    private final static String GOOGLE_GRANT_TYPE = "authorization_code";
    private final static String GOOGLE_LINK_GET_TOKEN = "https://accounts.google.com/o/oauth2/token";
    private final static String GOOGLE_LINK_GET_USER_INFO = "https://www.googleapis.com/oauth2/v1/userinfo?access_token=";

    private GoogleUtils() {

    }

    public static String getToken(final String code) throws IOException {
        String response = Request.Post(GOOGLE_LINK_GET_TOKEN)
                .bodyForm(Form.form().add("client_id", GOOGLE_CLIENT_ID)
                        .add("client_secret", GOOGLE_CLIENT_SECRET)
                        .add("redirect_uri", GOOGLE_REDIRECT_URI)
                        .add("code", code).add("grant_type", GOOGLE_GRANT_TYPE)
                        .build())
                .execute().returnContent().asString();
        JsonObject jobj = new Gson().fromJson(response, JsonObject.class);
        String accessToken = jobj.get("access_token").toString().replaceAll("\"", "");
        return accessToken;
    }

    public static GoogleUser getUserInfo(final String accessToken) throws IOException {
        String link = GOOGLE_LINK_GET_USER_INFO + accessToken;
        String response = Request.Get(link).execute().returnContent().asString();
        GoogleUser googleUser = new Gson().fromJson(response, GoogleUser.class);
        return googleUser;
    }
}