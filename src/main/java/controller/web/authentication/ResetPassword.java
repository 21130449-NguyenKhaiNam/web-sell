package controller.web.authentication;

import config.ConfigPage;
import services.authentication.AuthenticateServices;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "resetPassword", value = "/resetPassword")
public class ResetPassword extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String tokenResetPassword = request.getParameter("token-reset-password");
        boolean status = AuthenticateServices.getINSTANCE().resetPassword(email, tokenResetPassword);
        if (status) {
            request.setAttribute("email", email);
            request.setAttribute("token", tokenResetPassword);
            request.getRequestDispatcher(ConfigPage.RESET_PASSWORD).forward(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}