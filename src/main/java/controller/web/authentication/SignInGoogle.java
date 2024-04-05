package controller.web.authentication;

import config.ConfigPage;
import models.GoogleUser;
import models.User;
import services.authentication.AuthenticateServices;
import services.authentication.GoogleLoginServices;
import session.SessionManager;
import utils.Validation;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
            Validation validation = AuthenticateServices.getINSTANCE().checkSignIn(emailGoogle);
            if (validation.getObjReturn() != null) {
                User user = (User) validation.getObjReturn();
//                Tài khoản đã tồn tại
                SessionManager.getInstance(request, response).addUser(user);
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