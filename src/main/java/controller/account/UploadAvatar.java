package controller.account;


import models.User;
import properties.PathProperties;
import services.UploadImageServices;
import services.UserServices;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
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


        uploadImageServices.addImage(avatar);
        String nameAvatar = uploadImageServices.getNameImages().get(0);


        UserServices.getINSTANCE().updateInfoUser(id, nameAvatar);
        response.sendRedirect(request.getContextPath() + "/Account");

    }


}