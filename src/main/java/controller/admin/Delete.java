package controller.admin;

import dao.UserDAO;
import dao.UserDAOImplement;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "delete", value = "/delete")
public class Delete extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idString = request.getParameter("userId");
        if (idString != null && !idString.isEmpty()) {
            int id = Integer.parseInt(idString);
            UserDAO user = new UserDAOImplement();
            user.deleteUserById(id);
        }
        response.sendRedirect("AdminUser");


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}