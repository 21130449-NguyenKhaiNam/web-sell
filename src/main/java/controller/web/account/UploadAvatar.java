package controller.web.account;


import config.ConfigPage;
import models.User;
import properties.PathProperties;
import services.image.UploadImageServices;
import services.UserServices;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;


@MultipartConfig(maxFileSize = 16177215)
@WebServlet(name = "UploadAvatar", value = "/UploadAvatar")
public class UploadAvatar extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User auth = (User) session.getAttribute("auth");
        int id = auth.getId();

        Part avatar = request.getPart("userCoverPhoto");
        String root = request.getServletContext().getRealPath("/") + PathProperties.getINSTANCE().getPathAvatarUserWeb();
        UploadImageServices uploadImageServices = new UploadImageServices(root);

        try {
            uploadImageServices.addImage(avatar);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        String nameAvatar = uploadImageServices.getNameImages().get(0);

        UserServices.getINSTANCE().updateInfoUser(id, nameAvatar);
        request.getRequestDispatcher(ConfigPage.USER_ACCOUNT).forward(request, response);
    }
}