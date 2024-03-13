package controller.web.authentication;

import com.restfb.*;
import com.restfb.exception.FacebookException;
import com.restfb.types.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SignInFaceBook", value = "/signInFacebook")
public class SignInFaceBook extends HttpServlet {
    private static final String GRAPH_API_URL = "https://graph.facebook.com/me?fields=email&access_token=";
    private static final String APP_ID = "2825100177629702";
    private static final String APP_SECRET = "f52c1f23c0884cccf05178bbbe24f810";
    private static final String REDIRECT_URI = "http://localhost:8080/signInFacebook"; // Update the URI accordingly

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");
        boolean emailPermission = request.getParameter("emailPermission") != null && request.getParameter("emailPermission").equals("on");

        if (code == null || code.isEmpty()|| emailPermission) {
            // Redirect user to Facebook login page
            String facebookLoginUrl = "https://www.facebook.com/v19.0/dialog/oauth?client_id=" + APP_ID +
                    "&redirect_uri=" + REDIRECT_URI + "&scope=email";
            response.sendRedirect(facebookLoginUrl);
        } else {
            try {
                // Exchange code for access token
                FacebookClient facebookClient = new DefaultFacebookClient(Version.VERSION_19_0);
                AccessToken accessToken = facebookClient.obtainUserAccessToken(APP_ID, APP_SECRET, REDIRECT_URI, code);

                // Retrieve user's email address
                facebookClient = new DefaultFacebookClient(accessToken.getAccessToken(), Version.VERSION_19_0);
                User user = facebookClient.fetchObject("me", User.class, Parameter.with("fields", "email"));
                String email = user.getEmail();

                // Do something with the email address (e.g., store it in your database, create session, etc.)
                response.getWriter().println("Logged in with Facebook. Email: " + email);
            } catch (FacebookException e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to retrieve user data from Facebook.");
            }
        }
    }
}