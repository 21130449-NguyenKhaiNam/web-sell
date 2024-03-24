package controller.web.admin.user;

import config.ConfigPage;
import models.User;
import services.UserServices;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "AdminUser", value = "/AdminUser")
public class LoadDataUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String search = request.getParameter("search");

        if (search != null && !search.isEmpty()) {
            List<User> searchResult = UserServices.getINSTANCE().searchUsersByName(search);
            request.setAttribute("lists", searchResult);
        } else {
            List<User> allUsers = UserServices.getINSTANCE().selectALl();
            int page, itemsPerPage = 8;
            int size = allUsers.size();
            int totalPage = (size % itemsPerPage == 0 ? (size / itemsPerPage) : ((size / itemsPerPage)) + 1);
            String xPage = request.getParameter("page");
            if (xPage == null) {
                page = 1;
            } else {
                page = Integer.parseInt(xPage);
            }
            int start, end;
            start = (page - 1) * itemsPerPage;
            end = Math.min(page * itemsPerPage, size);
            List<User> listUserPerPage = getListUserPerPage(allUsers, start, end);
            request.setAttribute("page", page);
            request.setAttribute("totalPage", totalPage);

            request.setAttribute("lists", listUserPerPage);
        }

        request.getRequestDispatcher(ConfigPage.ADMIN_USER).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    public List<User> getListUserPerPage(List<User> listUser, int start, int end) {
        List<User> listUserPerPage = new ArrayList<>();
        for (int i = start; i < end; i++) {
            listUserPerPage.add(listUser.get(i));
        }
        return listUserPerPage;
    }
}