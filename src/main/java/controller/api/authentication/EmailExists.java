package controller.api.authentication;


import services.authentication.AuthenticateServices;
import utils.Validation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "emailExists", value = "/emailExists")
public class EmailExists extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        if(email == null)
            email = "";

        Validation validation = AuthenticateServices.getINSTANCE().checkEmailExists(email);
//        If there are warnings
        if (validation.getFieldEmail() != null && !validation.getFieldEmail().isEmpty()) {
            String emailError = validation.getFieldEmail();
            resp.setContentType("text/plain");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(emailError);
        }
    }
}
