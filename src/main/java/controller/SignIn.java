package controller;

import models.User;
import services.UserServices;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "signIn", value = "/signIn")
public class SignIn extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = UserServices.getINSTANCE().checkSignIn(username, password);
        String errorMessage = "Tài khoản hoặc mật khẩu sai, vui lòng nhập lại";
        if (user == null) {
            request.setAttribute("error", errorMessage);
            request.getRequestDispatcher("signIn.jsp").forward(request, response);
        } else {
            HttpSession session = request.getSession(true);
            session.setAttribute("auth", user);
            response.sendRedirect("index.jsp");
        }
    }
}