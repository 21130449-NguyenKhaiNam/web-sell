package controller.web.admin.order;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import models.DeliveryMethod;
import models.OrderStatus;
import models.PaymentMethod;
import models.TransactionStatus;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "SearchFilterOrderAdmin", value = "/api/admin/order/search")
public class SearchFilterOrderAdmin extends HttpServlet {

    private final int countFilter = 0;
//    private String queryStringStorage = null;

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchSelect = request.getParameter("searchSelect");
        String contentSearch = request.getParameter("contentSearch");

        String[] arrCheckedDeliveryMethods = request.getParameterValues("deliveryMethod[]");
        String[] arrCheckedPaymentMethods = request.getParameterValues("paymentMethod[]");
        String[] arrCheckedOrderStatus = request.getParameterValues("orderStatus[]");
        String[] arrCheckedTransactionStatus = request.getParameterValues("transactionStatus[]");

        Map<Object, List<String>> mapOrderFilter = new HashMap<>();

        String startDateFilter = request.getParameter("startDate");
        String endDateFilter = request.getParameter("endDate");


        String queryStringFilter = request.getQueryString();
        String regex = "page=\\d+&";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(queryStringFilter);
        queryStringFilter = matcher.replaceFirst("");

        request.setAttribute("queryStringFilter", queryStringFilter);
        if (arrCheckedDeliveryMethods != null) {
            List<String> listCheckedDeliveryMethods = new ArrayList<>(Arrays.asList(arrCheckedDeliveryMethods));
            mapOrderFilter.put(new DeliveryMethod(), listCheckedDeliveryMethods);
        }

        if (arrCheckedPaymentMethods != null) {
            List<String> listCheckedPaymentMethods = new ArrayList<>(Arrays.asList(arrCheckedPaymentMethods));
            mapOrderFilter.put(new PaymentMethod(), listCheckedPaymentMethods);
        }

        if (arrCheckedOrderStatus != null) {
            List<String> listCheckedOrderStatus = new ArrayList<>(Arrays.asList(arrCheckedOrderStatus));
            mapOrderFilter.put(new OrderStatus(), listCheckedOrderStatus);
        }

        if (arrCheckedTransactionStatus != null) {
            List<String> listCheckedTransactionStatus = new ArrayList<>(Arrays.asList(arrCheckedTransactionStatus));
            mapOrderFilter.put(new TransactionStatus(), listCheckedTransactionStatus);
        }

        request.setAttribute("searchSelect", searchSelect);
        request.setAttribute("contentSearch", contentSearch);
        request.setAttribute("mapOrderFilter", mapOrderFilter);
        request.setAttribute("startDateFilter", startDateFilter);
        request.setAttribute("endDateFilter", endDateFilter);
        request.setAttribute("servletProcess", getServletName());
//        jsonObject.addProperty("searchSelect", searchSelect);
//        jsonObject.addProperty("contentSearch", contentSearch);
//        jsonObject.add("mapOrderFilter", gson.toJsonTree(mapOrderFilter));
//        jsonObject.addProperty("startDateFilter", startDateFilter);
//        jsonObject.addProperty("endDateFilter", endDateFilter);
//        jsonObject.addProperty("servletProcess", getServletName());
//        request.setAttribute("jsonObject", jsonObject);

        RequestDispatcher requestDispatcher = null;
        switch (searchSelect) {
            case "orderId" -> {
                requestDispatcher = request.getRequestDispatcher("/api/admin/order/search-id");
            }

            case "customerName" -> {
                requestDispatcher = request.getRequestDispatcher("/api/admin/order/search-customer");
            }
        }
        if (requestDispatcher != null) {
            requestDispatcher.forward(request, response);
        }
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