package controller.api.admin.user;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import models.User;
import services.UserServices;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

@WebServlet(value = "/api/admin/user/update")
public class UpdateUserController extends HttpServlet {
    Gson gson = new GsonBuilder().create();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userIdString = request.getParameter("id");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String fullName = request.getParameter("fullName");
        String phone = request.getParameter("phone");
        String gender = request.getParameter("gender");
        String birthDayString = request.getParameter("birthDay");
        String role = request.getParameter("role");
        JsonObject jsonObject = new JsonObject();
        try {
            int userId = Integer.parseInt(userIdString);
            Date birthDay = Date.valueOf(birthDayString);
            User user = User.builder()
                    .username(username)
                    .id(userId)
                    .email(email)
                    .fullName(fullName)
                    .phone(phone)
                    .birthDay(birthDay)
                    .gender(gender)
                    .role(role)
                    .build();
            UserServices.getINSTANCE().updateUser(user);
            jsonObject.addProperty("success", true);
        } catch (NumberFormatException e) {
            jsonObject.addProperty("success", false);
        }
        response.getWriter().println(gson.toJson(jsonObject));
    }
}
