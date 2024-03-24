package controller.web.account;

import config.ConfigPage;
import services.UserServices;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

@MultipartConfig(maxFileSize = 16777216)
@WebServlet(name = "UpdateAccount", value = "/UpdateAccount")
public class UpdateAccount extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String userIdString = request.getParameter("userId");
        String userName = request.getParameter("userName");
        String email = request.getParameter("email");
        String fullName = request.getParameter("fullName");
        String phone = request.getParameter("phone");
        String gender = request.getParameter("gender");
        String year = request.getParameter("year");
        String month = request.getParameter("month");
        String day = request.getParameter("day");
        String address = request.getParameter("address");

        String birthDayString = year + "-" + month + "-" + day;

        try {
            int userId = Integer.parseInt(userIdString);
            Date birthDay = Date.valueOf(birthDayString);
            UserServices.getINSTANCE().updateUserByID(userId, userName, fullName, gender, email, phone, address, birthDay);
            request.getRequestDispatcher(ConfigPage.USER_ACCOUNT).forward(request, response);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}