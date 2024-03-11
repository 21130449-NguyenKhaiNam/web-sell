package controller.web.admin.order;

import config.ConfigPage;
import models.Order;
import services.AdminOrderServices;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;
@WebServlet(name = "AdminOrders", value = "/AdminOrders")
public class AdminOrders extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Order> listAllOrders = AdminOrderServices.getINSTANCE().getListAllOrders();

        int page = 0, itemsPerPage = 8;
        int size = listAllOrders.size();
        int totalPage = (size % itemsPerPage == 0 ? (size / itemsPerPage) : ((size / itemsPerPage)) + 1);

        String xPage = request.getParameter("page");
        if (xPage == null) {
            page = 1;
        } else {
            try {
                page = Integer.parseInt(xPage);
            }catch (NumberFormatException exception){
                exception.printStackTrace();
            }
        }

        int start, end;
        start = (page - 1) * itemsPerPage;
        end = Math.min(page * itemsPerPage, size);
        List<Order> listOrdersPerPage = getListOrdersPerPage(listAllOrders, start, end);

        request.setAttribute("page", page);
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("listOrdersPerPage", listOrdersPerPage);
        request.setAttribute("servletProcess", getServletName());

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(ConfigPage.ADMIN_ORDER);
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private List<Order> getListOrdersPerPage(List<Order> listAllOrders, int start, int end) {
        return listAllOrders.subList(start, end);
    }
}