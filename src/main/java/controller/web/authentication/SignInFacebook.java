package controller.web.authentication;

import com.restfb.exception.FacebookException;
import com.restfb.types.User;
import config.ConfigPage;
import properties.FacebookProperties;
import services.authentication.AuthenticateServices;
import services.authentication.FacebookLoginServices;
import session.SessionManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SignInFaceBook", value = "/signInFacebook")
public class SignInFacebook extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");

        if (code == null || code.isEmpty()) {
            String facebookLoginUrl = FacebookProperties.getLoginURL();
            response.sendRedirect(facebookLoginUrl);
        } else {
            try {
                String accessToken = FacebookLoginServices.getINSTANCE().getToken(code);

                // Retrieve user's email address
                User userFacebook = (User) FacebookLoginServices.getINSTANCE().getUserInfo(accessToken);
                String emailFacebook = userFacebook.getEmail();

                models.User userValid = AuthenticateServices.getINSTANCE().checkSignIn(emailFacebook);
                if (userValid != null) {
//                Tài khoản đã tồn tại
                    SessionManager.getInstance(request, response).addUser(userValid);
                    response.sendRedirect(ConfigPage.HOME);
                } else {
//                Tài khoản chưa tồn tại
                    request.setAttribute("email", emailFacebook);
                    RequestDispatcher dis = request.getRequestDispatcher(ConfigPage.SIGN_UP);
                    dis.forward(request, response);
                }
            } catch (FacebookException e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to retrieve user data from Facebook.");
            }
        }
    }
}