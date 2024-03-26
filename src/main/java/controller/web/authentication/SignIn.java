package controller.web.authentication;

import config.ConfigPage;
import models.User;
import services.authentication.AuthenticateServices;
import utils.Validation;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "signIn", value = "/signIn")
public class SignIn extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();

        Validation validation = AuthenticateServices.getINSTANCE().checkSignIn(username, password);

        if (validation.getObjReturn() != null) {
            User userAuth = (User) validation.getObjReturn();
            HttpSession session = request.getSession(true);
            session.setAttribute("auth", userAuth);
            response.sendRedirect(ConfigPage.HOME);
            return;
        } else {
            request.setAttribute("usernameError", validation.getFieldUsername());
            request.setAttribute("passwordError", validation.getFieldPassword());
            request.getRequestDispatcher(ConfigPage.SIGN_IN).forward(request, response);
        }
    }
}



