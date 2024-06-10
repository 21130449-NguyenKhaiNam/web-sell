package controller.api.admin.user;

import com.google.gson.Gson;
import models.User;
import services.UserServices;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UserControllerAPI", value = "/api/admin/user")
public class UserControllerAPI extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String isString = request.getParameter("id");
        Gson gson = new Gson();
        try {
            int id = Integer.parseInt(isString);
            List<User> users = UserServices.getINSTANCE().getUserByID(id);
            if (users.size() == 0) {
                response.getWriter().print("Not Found");
                return;
            }
            response.getWriter().print(gson.toJson(users.get(0)));
        } catch (NumberFormatException e) {
            response.getWriter().print("Not Found");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}