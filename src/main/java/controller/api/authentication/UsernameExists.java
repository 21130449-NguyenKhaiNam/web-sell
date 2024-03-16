package controller.api.authentication;

import services.AuthenticateServices;
import utils.Validation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "usernameExists", value = "/usernameExists")
public class UsernameExists extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        if(username == null)
            username = "";
        Validation validation = AuthenticateServices.getINSTANCE().checkUsernameExists(username);
//        If there are warnings
        if (validation.getFieldUsername() != null && !validation.getFieldUsername().isEmpty()) {
            String usernameError = validation.getFieldUsername();
            resp.setContentType("text/plain");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(usernameError);
        }
    }
}
