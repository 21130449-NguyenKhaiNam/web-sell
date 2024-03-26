package controller.web.authentication;

import com.restfb.exception.FacebookException;
import com.restfb.types.User;
import config.ConfigPage;
import properties.FacebookProperties;
import services.UserServices;
import services.authentication.FacebookLoginServices;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

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
                User user = (User) FacebookLoginServices.getINSTANCE().getUserInfo(accessToken);
                String emailFacebook = user.getEmail();

                List<models.User> users = UserServices.getINSTANCE().getUserById(emailFacebook);
                if (users.size() == 1) {
//                Tài khoản đã tồn tại
                    request.getSession().setAttribute("auth", users.get(0));
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