package controller.web.authentication;

import config.ConfigPage;
import models.User;
import models.shoppingCart.ShoppingCart;
import services.authentication.AuthenticateServices;
import session.SessionManager;
import utils.Validation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
//            Cookie ko co user, ko sessionId
            User userAuth = (User) validation.getObjReturn();
            SessionManager.getInstance(request, response).addUser(userAuth);
            request.getSession().setAttribute(userAuth.getId() + "", new ShoppingCart());
            response.sendRedirect(ConfigPage.HOME);
        } else {
            request.setAttribute("usernameError", validation.getFieldUsername());
            request.setAttribute("passwordError", validation.getFieldPassword());
            request.getRequestDispatcher(ConfigPage.SIGN_IN).forward(request, response);
        }
    }
}


