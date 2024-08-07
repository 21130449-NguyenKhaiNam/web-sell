package controller.api.user;

import models.User;
import org.json.JSONObject;
import properties.PathProperties;
import services.UserServices;
import services.image.UploadImageServices;
import session.SessionManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

@WebServlet(name = "UploadAvatar", value = "/upload-avatar")
@MultipartConfig(maxFileSize = 16177215)
public class UploadAvatarController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = SessionManager.getInstance(request, response).getUser();
        Part avatar = request.getPart("avatar");
        String root = PathProperties.getINSTANCE().getPathAvatarUserWeb();
        UploadImageServices uploadImageServices = new UploadImageServices(root);
        try {
            uploadImageServices.addImage(avatar);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        String nameAvatar = uploadImageServices.getNameImages().get(0);
        JSONObject json = new JSONObject();
        UserServices.getINSTANCE().updateInfoUser(user.getId(), nameAvatar);
        user.setAvatar(nameAvatar);
        json.put("status", "success");
        json.put("message", "Upload avatar success");
        response.setStatus(200);
        response.getWriter().print(json);
    }
}