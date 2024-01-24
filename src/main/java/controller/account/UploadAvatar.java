package controller.account;

import dao.GeneralDao;
import dao.UserDAO;
import dao.UserDAOImplement;
import models.User;
import properties.PathProperties;
import services.UploadImageServices;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;

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


        UserDAO userDAO = new UserDAOImplement();
        userDAO.updateInfoUser(id, nameAvatar);
        response.sendRedirect(request.getContextPath() + "/Account");

    }


}