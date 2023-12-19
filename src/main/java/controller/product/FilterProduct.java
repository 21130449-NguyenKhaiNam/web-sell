package controller.product;

import models.Product;

import services.ProductCardServices;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;

@WebServlet(name = "filterProduct", value = "/filterProduct")
public class FilterProduct extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Integer> filterByColor = (List<Integer>) request.getAttribute("filterByColor");
        List<Integer> filterByCategoryId = (List<Integer>) request.getAttribute("filterByCategoryId");
        List<Integer> filterByMoneyRange = (List<Integer>) request.getAttribute("filterByMoneyRange");
        List<Integer> filterBySize = (List<Integer>) request.getAttribute("filterBySize");
        String pageNumber = request.getParameter("page");
        int page;
        try {
            page = Integer.parseInt(pageNumber);
        } catch (NumberFormatException e) {
            page = 1;
        }
        List<List<Integer>> listId = new ArrayList<>();
        if (filterByColor != null) {
            listId.add(filterByColor);
        }
        if (filterByCategoryId != null) {
            listId.add(filterByCategoryId);
        }
        if (filterByMoneyRange != null) {
            listId.add(filterByMoneyRange);
        }
        if (filterBySize != null) {
            listId.add(filterBySize);
        }
        List<Integer> listIDFiltered = findCommonIDs(listId);
        List<Product> productCardFiltered = ProductCardServices.getINSTANCE().filter(listIDFiltered, page);
        int quantityPage;
        if (!listIDFiltered.isEmpty()) {
            quantityPage = ProductCardServices.getINSTANCE().getQuantityPage(listIDFiltered.size());
        } else {
            quantityPage = ProductCardServices.getINSTANCE().getQuantityPage();
        }

        StringBuffer requestURL = request.getRequestURL();
        String queryString = request.getQueryString();
        queryString = cutParameterInURL(queryString, "page");
        requestURL.append("?").append(queryString);

        List<String> listInputChecked = listValueChecked(queryString);
        System.out.println(listInputChecked);
        request.setAttribute("requestURL", requestURL);
        request.setAttribute("productCardList", productCardFiltered);
        request.setAttribute("quantityPage", quantityPage);
        request.setAttribute("currentPage", page);
        request.setAttribute("listInputChecked", listInputChecked);
        request.getRequestDispatcher("productBuying.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        doGet(request, response);
    }

    private List<Integer> findCommonIDs(List<List<Integer>> lists) {
        if (lists.isEmpty()) return new ArrayList<>();
        if (lists.size() == 1) return lists.get(0);
        List<Integer> result = new ArrayList<>();
        result = lists.get(0);
        for (int i = 1; i < lists.size(); i++) {
            result.retainAll(lists.get(i));
        }
        return result;
    }

    private String cutParameterInURL(String queryString, String key) {
        if (queryString != null) {
            String[] params = queryString.split("&");
            StringBuilder updatedQueryString = new StringBuilder();
            for (String param : params) {
                String[] keyValue = param.split("=");
                if (keyValue.length == 2 && key.equals(keyValue[0])) {
                    continue;
                } else {
                    updatedQueryString.append(param).append("&");
                }
            }
            return updatedQueryString.toString();
        }
        return null;
    }

    public List<String> listValueChecked(String queryString) {
        List<String> result = new ArrayList<>();
        if (queryString != null) {
            String[] params = queryString.split("&");
            for (String param : params) {
                String[] keyValue = param.split("=");
                try {
                    result.add(URLDecoder.decode(keyValue[1], "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return result;
    }
}