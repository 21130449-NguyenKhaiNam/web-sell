package controller.web.authentication;

import config.ConfigPage;
import models.GoogleUser;
import models.User;
import services.UserServices;
import services.authentication.GoogleLoginServices;
import session.SessionManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "signInGoogle", value = "/signInGoogle")
public class SignInGoogle extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");
        if (code == null || code.isEmpty()) {
            RequestDispatcher dis = request.getRequestDispatcher(ConfigPage.HOME);
            dis.forward(request, response);
        } else {
            String accessToken = GoogleLoginServices.getINSTANCE().getToken(code);
            GoogleUser googleUserAccount = (GoogleUser) GoogleLoginServices.getINSTANCE().getUserInfo(accessToken);
            String emailGoogle = googleUserAccount.getEmail();
            List<User> users = UserServices.getINSTANCE().getUserById(emailGoogle);
            if (users.size() == 1) {
//                Tài khoản đã tồn tại
                SessionManager.getInstance(request, response).addUser(users.get(0));
                response.sendRedirect(ConfigPage.HOME);
            } else {
//                Tài khoản chưas tồn tại
                request.setAttribute("email", emailGoogle);
                RequestDispatcher dis = request.getRequestDispatcher(ConfigPage.SIGN_UP);
                dis.forward(request, response);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}