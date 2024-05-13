package controller.web.authentication;

import config.ConfigPage;
import models.User;
import session.SessionManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "signOut", value = "/signOut")
public class SignOut extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = SessionManager.getInstance(request, response).getUser();
        if (user != null)
            SessionManager.getInstance(request, response).removeUser();
        response.sendRedirect(ConfigPage.HOME);
    }
}