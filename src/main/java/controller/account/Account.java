package controller.account;

import dao.UserDAO;
import dao.UserDAOImplement;
import models.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet(name = "Account", value = "/Account")
public class Account extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User auth = (User) session.getAttribute("auth");

        if (auth != null) {
            try {
                UserDAO userDao = new UserDAOImplement();
                List<User> userList = userDao.getUserByID(auth.getId());

                if (!userList.isEmpty()) {
                    User user = userList.get(0);
                    request.setAttribute("accountInfo", user);
                    request.getRequestDispatcher("account.jsp").forward(request, response);
                } else {
                    response.getWriter().println("User not found");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            response.sendRedirect("login.jsp");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}