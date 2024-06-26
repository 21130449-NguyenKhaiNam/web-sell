package controller.web.authentication;

import config.ConfigPage;
import models.User;
import services.authentication.AuthenticateServices;
import utils.Validation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "forgetPassword", value = "/forgetPassword")
public class ForgetPassword extends HttpServlet {

    String sendMailSuccess = "Email đã được gửi đến hộp thư của bạn";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        Validation validation = AuthenticateServices.getINSTANCE().checkForgetPassword(email);

        if (validation.getObjReturn() != null) {
            User user = (User) validation.getObjReturn();
            request.setAttribute("sendMail", sendMailSuccess);
            AuthenticateServices.getINSTANCE().sendMailResetPassword(user);
        } else {
            request.setAttribute("emailError", validation.getFieldEmail());
        }
        request.getRequestDispatcher(ConfigPage.FORGET_PASSWORD).forward(request, response);
    }
}