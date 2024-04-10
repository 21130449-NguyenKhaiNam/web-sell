package controller.api.user;

import models.User;
import org.json.JSONObject;
import services.UserServices;
import session.SessionManager;
import utils.Encoding;
import utils.ValidatePassword;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ChangePassword", value = "/api/user/password")
public class UpdatePasswordController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().println("Not found");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONObject json = new JSONObject();

        String currentPassword = request.getParameter("currentPassword");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");
        User user = SessionManager.getInstance(request, response).getUser();

        if (currentPassword == null || newPassword == null || confirmPassword == null) {
            json.put("error", "Missing required fields");
            json.put("isValid", false);
            response.getWriter().println(json.toString());
            return;
        }

        if (!Encoding.getINSTANCE().toSHA1(currentPassword).equals(user.getPasswordEncoding())) {
            json.put("error", "Current password is incorrect");
            json.put("isValid", false);
            response.getWriter().println(json.toString());
            return;
        }

        ValidatePassword validatePassword = new ValidatePassword(newPassword);
        boolean isValid = validatePassword.check();
        if (isValid) {
            UserServices.getINSTANCE().updateUserPassword(user.getId(), Encoding.getINSTANCE().toSHA1(newPassword));
            json.put("isValid", true);
        } else {
            json.put("isValid", false);
            json.put("error", validatePassword.getErrorMap());
        }
        response.getWriter().println(json.toString());
    }
}