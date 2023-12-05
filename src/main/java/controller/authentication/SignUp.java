package controller.authentication;

import models.User;
import services.AuthenticateServices;
import utils.ValidationError;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "signUp", value = "/signUp")
public class SignUp extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirm-password");
        System.out.println(username + "\n" + email + "\n" + password + "\n" + confirmPassword);

        ValidationError validationError = AuthenticateServices.getINSTANCE().checkSignUp(username, email, password, confirmPassword);

        if (validationError.getObjReturn() != null) {
            User newUser = (User) validationError.getObjReturn();
            AuthenticateServices.getINSTANCE().createUser(newUser);
            request.setAttribute("sendMail", "Send Mail Success");
        } else {
            request.setAttribute("usernameError", validationError.getFieldUsername());
            request.setAttribute("emailError", validationError.getFieldEmail());
            request.setAttribute("passwordError", validationError.getFieldPassword());
            request.setAttribute("passwordConfirmError", validationError.getFieldConfirmPassword());
        }
        request.getRequestDispatcher("signUp.jsp").forward(request, response);
    }
}