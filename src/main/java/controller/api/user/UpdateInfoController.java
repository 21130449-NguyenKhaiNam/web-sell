package controller.api.user;

import config.ConfigPage;
import models.User;
import services.UserServices;
import session.SessionManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebServlet(name = "UpdateInfo", value = "/api/user/info")
public class UpdateInfoController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = SessionManager.getInstance(request, response).getUser();
        int userId = user.getId();
        String fullName = request.getParameter("fullName");
        String phone = request.getParameter("phone");
        String gender = request.getParameter("gender");
        String birthDayString = request.getParameter("birthDay");

        try {
            Date birthDay = formatDate(birthDayString);
            UserServices.getINSTANCE().updateUserByID(userId, fullName, gender, phone, birthDay);
            SessionManager.getInstance(request, response).updateUser();
            request.getRequestDispatcher(ConfigPage.USER_ACCOUNT).forward(request, response);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    private Date formatDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        // Parse the String to obtain a LocalDate object
        LocalDate localDate = LocalDate.parse(date, formatter);

        // Convert the LocalDate to a java.sql.Date
        Date sqlDate = Date.valueOf(localDate);
        return sqlDate;
    }
}
