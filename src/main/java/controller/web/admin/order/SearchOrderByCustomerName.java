package controller.web.admin.order;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import config.ConfigPage;
import models.*;
import services.admin.AdminOrderServices;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "SearchOrderByCustomerName", value = "/api/admin/order/search-customer")
public class SearchOrderByCustomerName extends HttpServlet {
    Gson gson = new GsonBuilder().create();

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JsonObject jsonObject = new JsonObject();
        String contentSearch = (String) request.getAttribute("contentSearch");
        String searchSelected = (String) request.getAttribute("searchSelect");
        int start = Integer.parseInt(request.getAttribute("start").toString());
        int length = Integer.parseInt(request.getAttribute("length").toString());
        Map<Object, List<String>> mapOrderFilter = (Map<Object, List<String>>) request.getAttribute("mapOrderFilter");

        String startDateFiltered = (String) request.getAttribute("startDateFilter");
        String endDateFiltered = (String) request.getAttribute("endDateFilter");

        List<Order> listOrderByCustomerName = AdminOrderServices.getINSTANCE().getListOrdersBySearchFilter(mapOrderFilter, contentSearch, searchSelected, startDateFiltered, endDateFiltered);

        int size = listOrderByCustomerName.size();
        List<Order> listOrdersPerPage = getListOrdersPerPage(listOrderByCustomerName, start, start + length);

        jsonObject.addProperty("recordsTotal", size);
        jsonObject.addProperty("recordsFiltered", size);
        jsonObject.add("data", gson.toJsonTree(listOrdersPerPage));
        response.getWriter().print(jsonObject.toString());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private List<Order> getListOrdersPerPage(List<Order> listOrderByCustomerName, int start, int end) {
        if (end > listOrderByCustomerName.size()) {
            end = listOrderByCustomerName.size();
        }
        return listOrderByCustomerName.subList(start, end);
    }
}