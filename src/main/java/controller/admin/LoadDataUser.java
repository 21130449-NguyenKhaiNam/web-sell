package controller.admin;

import dao.UserImplement;
import models.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminUser", value = "/AdminUser")
public class LoadDataUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserImplement user = new UserImplement();
        List<User> list = user.selectALl();
        request.setAttribute("lists", list);
        request.getRequestDispatcher("adminUsers.jsp").forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}