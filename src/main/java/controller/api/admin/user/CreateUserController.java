package controller.api.admin.user;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import models.User;
import org.json.JSONObject;
import services.UserServices;
import utils.Encoding;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@WebServlet(value = "/api/admin/user/create")
public class CreateUserController extends HttpServlet {
    Gson gson = new GsonBuilder().create();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("passwordEncoding");
        String fullName = request.getParameter("fullName");
        String gender = request.getParameter("gender");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String birthDayString = request.getParameter("birthDay");
        String role = request.getParameter("role");

        JSONObject jsonObject = new JSONObject();
        try {
            Date birthDay = Date.valueOf(birthDayString);
            User user = User.builder()
                    .username(username)
                    .email(email)
                    .fullName(fullName)
                    .phone(phone)
                    .gender(gender)
                    .birthDay(birthDay)
                    .role(role)
                    .build();
            UserServices.getINSTANCE().insertUser(user, password);
            jsonObject.put("success", true);
        } catch (Exception e) {
            jsonObject.put("success", false);
        }
        response.getWriter().println(gson.toJson(jsonObject));
    }
}