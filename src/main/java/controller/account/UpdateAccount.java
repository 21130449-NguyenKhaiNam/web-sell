package controller.account;

import dao.UserDAO;
import dao.UserDAOImplement;
import properties.PathProperties;
import services.UploadImageServices;
import services.UserServices;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
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

//        Part avatar = request.getPart("userCoverPhoto");
//        String root = request.getServletContext().getRealPath("/") + PathProperties.getINSTANCE().getPathAvatarUserWeb();
//        UploadImageServices uploadImageServices = new UploadImageServices(root);
//
//        uploadImageServices.addImage(avatar);
//
//        List<String> nameImages = uploadImageServices.getNameImages();
//        String nameAvatar = nameImages.isEmpty() ? "" : nameImages.get(0);

        try {
            int userId = Integer.parseInt(userIdString);
            Date birthDay = Date.valueOf(birthDayString);

//            UserDAO userDAO = new UserDAOImplement();
//            userDAO.updateInfoUser(userId, userName, fullName, gender, email, phone, address, birthDay, nameAvatar);
           UserServices.getINSTANCE().updateUserByID(userId, userName, fullName, gender, email, phone, address, birthDay);
            response.sendRedirect(request.getContextPath() + "/Account");
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}