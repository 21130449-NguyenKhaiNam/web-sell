package controller.web.admin.user;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
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

@WebServlet(name = "AdminUser", value = "/api/admin/user/search")
public class LoadDataUser extends HttpServlet {
    Gson gson = new GsonBuilder().create();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JsonObject jsonObject = new JsonObject();
        String search = request.getParameter("search");

        if (search != null && !search.isEmpty()) {
            List<User> searchResult = UserServices.getINSTANCE().searchUsersByName(search);
            jsonObject.add("lists", gson.toJsonTree(searchResult));
        } else {
            List<User> allUsers = UserServices.getINSTANCE().selectAll();
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
            jsonObject.addProperty("page", page);
            jsonObject.addProperty("totalPage", totalPage);
            jsonObject.add("lists", gson.toJsonTree(listUserPerPage));
        }

        response.getWriter().println(gson.toJson(jsonObject));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    public List<User> getListUserPerPage(List<User> listUser, int start, int end) {
        return listUser.subList(start, end);
    }
}