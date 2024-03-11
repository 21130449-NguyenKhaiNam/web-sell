package controller.web.authentication;

import models.User;
import services.AuthenticateServices;
import utils.Validation;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "forgetPassword", value = "/forgetPassword")
public class ForgetPassword extends HttpServlet {

    String sendMailSuccess = "Email đã được gửi đến hộp thư của bạn";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        Validation validation = AuthenticateServices.getINSTANCE().checkForgetPassword(email);

        if (validation.getObjReturn() != null) {
            User user = (User) validation.getObjReturn();
            request.setAttribute("sendMail",sendMailSuccess);
            AuthenticateServices.getINSTANCE().sendMailResetPassword(user);
        }else{
            request.setAttribute("emailError", validation.getFieldEmail());
        }
        request.getRequestDispatcher("forgotPassword.jsp").forward(request, response);
    }
}