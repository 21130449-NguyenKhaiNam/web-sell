package controller.admin.user;

import dao.UserDAO;
import dao.UserDAOImplement;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "delete", value = "/Delete")
public class DeleteUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idString = request.getParameter("userId");
        if (idString != null && !idString.isEmpty()) {
            try{
                int id = Integer.parseInt(idString);
                UserDAO user = new UserDAOImplement();
                user.deleteUserById(id);
                System.out.println("Deleted user with ID: " + id);
            }catch(NumberFormatException e){
                System.out.println("id không hợp lệ");
            }
        }
        response.sendRedirect("AdminUser");


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}