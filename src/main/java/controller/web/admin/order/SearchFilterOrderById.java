package controller.web.admin.order;

import config.ConfigPage;
import models.*;
import services.AdminOrderServices;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "SearchFilterOrderById", value = "/SearchFilterOrderById")
public class SearchFilterOrderById extends HttpServlet {

    //Forward từ 1 servlet sang 1 servlet thì xử lý cùng kiểu method (POST hoặc GET)

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String contentSearch = (String) request.getAttribute("contentSearch");
        String searchSelected = (String) request.getAttribute("searchSelect");
        Map<Object, List<String>> mapOrderFilter = (Map<Object, List<String>>) request.getAttribute("mapOrderFilter");

        String startDateFiltered = (String) request.getAttribute("startDateFilter");
        String endDateFiltered = (String) request.getAttribute("endDateFilter");

        List<Order> listOrderById = AdminOrderServices.getINSTANCE().getListOrdersBySearchFilter(mapOrderFilter, contentSearch, searchSelected, startDateFiltered, endDateFiltered);

        int page = 0, itemsPerPage = 8;
        int size = listOrderById.size();
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
        List<Order> listOrdersPerPage = getListOrdersPerPage(listOrderById, start, end);

        request.setAttribute("page", page);
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("listOrdersPerPage", listOrdersPerPage);

        request.setAttribute("listCheckedDeliveryMethods", mapOrderFilter.get(new DeliveryMethod()));
        request.setAttribute("listCheckedPaymentMethods", mapOrderFilter.get(new PaymentMethod()));
        request.setAttribute("listCheckedOrderStatus", mapOrderFilter.get(new OrderStatus()));
        request.setAttribute("listCheckedTransactionStatus", mapOrderFilter.get(new TransactionStatus()));

        request.setAttribute("contentSearched", contentSearch);
        request.setAttribute("searchSelected", searchSelected);
        request.setAttribute("startDateFiltered", startDateFiltered);
        request.setAttribute("endDateFiltered", endDateFiltered);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(ConfigPage.ADMIN_ORDER);
        requestDispatcher.forward(request, response);
    }

    private List<Order> getListOrdersPerPage(List<Order> listOrderById, int start, int end) {
        return listOrderById.subList(start, end);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}