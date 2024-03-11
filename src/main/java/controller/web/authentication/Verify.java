package controller.web.authentication;

import services.AuthenticateServices;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Timestamp;

@WebServlet(name = "verify", value = "/verify")
public class Verify extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String tokenVerify = request.getParameter("token-verify");
        System.out.println(username + "\n" + tokenVerify);
        boolean status = AuthenticateServices.getINSTANCE().verify(username, tokenVerify);

        request.setAttribute("username", username);
        if (status) {
            request.getRequestDispatcher("verifySuccess.jsp").forward(request, response);
        }else{
            response.sendError(404);
        }
    }
}