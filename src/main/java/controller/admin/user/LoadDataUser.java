package controller.admin.user;

import dao.UserDAOImplement;
import dao.UserDAOImplement;
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
        UserDAOImplement userDAO = new UserDAOImplement();
        String search = request.getParameter("search");
        if (search != null && !search.isEmpty()) {
            List<User> searchResult = userDAO.searchUsersByName(search);
            request.setAttribute("lists", searchResult);
        } else {
            List<User> allUsers = userDAO.selectALl();
            request.setAttribute("lists", allUsers);
        }

        request.getRequestDispatcher("adminUsers.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}