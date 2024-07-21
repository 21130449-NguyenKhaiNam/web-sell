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

@WebServlet(name = "SearchFilterOrderById", value = "/api/admin/order/search-id")
public class SearchFilterOrderById extends HttpServlet {
    //Forward từ 1 servlet sang 1 servlet thì xử lý cùng kiểu method (POST hoặc GET)
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

        List<Order> listOrderById = AdminOrderServices.getINSTANCE().getListOrdersBySearchFilter(mapOrderFilter, contentSearch, searchSelected, startDateFiltered, endDateFiltered);

        int size = listOrderById.size();
        List<Order> listOrdersPerPage = getListOrdersPerPage(listOrderById, start, start + length);

        jsonObject.addProperty("recordsTotal", size);
        jsonObject.addProperty("recordsFiltered", size);
        jsonObject.add("data", gson.toJsonTree(listOrdersPerPage));

        response.getWriter().print(jsonObject.toString());
    }

    private List<Order> getListOrdersPerPage(List<Order> listOrderById, int start, int end) {
        if (end > listOrderById.size()) {
            end = listOrderById.size();
        }
        return listOrderById.subList(start, end);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}