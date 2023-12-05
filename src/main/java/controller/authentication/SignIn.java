package controller.authentication;

import dao.UserDAO;
import models.User;
import services.AuthenticateServices;
import utils.Encoding;
import utils.ValidationError;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "signIn", value = "/signIn")
public class SignIn extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();

        ValidationError validationError = AuthenticateServices.getINSTANCE().checkSignIn(username, password);

        if (validationError.getObjReturn() != null) {
            User userAuth = (User) validationError.getObjReturn();
            HttpSession session = request.getSession(true);
            session.setAttribute("auth", userAuth);
            response.sendRedirect("index.jsp");
        } else {
            request.setAttribute("usernameError", validationError.getFieldUsername());
            request.setAttribute("passwordError", validationError.getFieldPassword());
            request.getRequestDispatcher("signIn.jsp").forward(request, response);
        }
    }
}



