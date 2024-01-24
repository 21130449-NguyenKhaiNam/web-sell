package controller.admin.user;

import dao.UserDAO;
import dao.UserDAOImplement;
import utils.Encoding;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "AddUser", value = "/AddUser")
public class AddUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");
        String passwordEncoding = request.getParameter("passwordEncoding");
        String fullName = request.getParameter("fullName");
        String gender = request.getParameter("gender");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String birthDayString  = request.getParameter("birthDay");

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthDay = null;
        try {
            birthDay = dateFormat.parse(birthDayString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        java.sql.Date sqlBirthDay = new java.sql.Date(birthDay.getTime());

        UserDAO user = new UserDAOImplement();
        user.insertUser(username, Encoding.getINSTANCE().toSHA1(passwordEncoding), fullName, gender, email, phone, address, sqlBirthDay);
        response.sendRedirect("AdminUser");

    }
}