package filter.adminPage;

import models.User;
import services.UserServices;

import javax.servlet.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebFilter(filterName = "AdminUsers", urlPatterns = {"/public/admin/adminUsers.jsp"})
public class AdminUsers implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
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
        chain.doFilter(request, response);
    }

    public List<User> getListUserPerPage(List<User> listUser, int start, int end) {
        List<User> listUserPerPage = new ArrayList<>();
        for (int i = start; i < end; i++) {
            listUserPerPage.add(listUser.get(i));
        }
        return listUserPerPage;
    }
}