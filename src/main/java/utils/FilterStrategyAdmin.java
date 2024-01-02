package utils;

import models.Product;
import services.AdminProductServices;
import services.ProductCardServices;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class FilterStrategyAdmin extends FilterStrategy {

    public FilterStrategyAdmin(HttpServletRequest request) {
        super(request);
    }

    @Override
    public void doFilter() {
        List<Integer> filterByDate;
        try {
            filterByDate = filterByTimeUpdate();
        } catch (ParseException ignored) {
            filterByDate = null;
        }
        List<Integer> filterByName = filterByNameProduct();
        List<Integer> filterByColor = filterByColor();
        List<Integer> filterByCategoryId = filterByCategory();
        List<Integer> filterByMoneyRange = filterMyMoney();
        List<Integer> filterBySize = filterBySize();

        String pageNumber = request.getParameter("page");
        int page;
        try {
            page = Integer.parseInt(pageNumber);
        } catch (NumberFormatException e) {
            page = 1;
        }
        List<List<Integer>> listId = new ArrayList<>();
        if (filterByDate != null) {
            listId.add(filterByDate);
        }
        if (filterByName != null) {
            listId.add(filterByName);
        }

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
        List<Product> productCardFiltered = AdminProductServices.getINSTANCE().filter(listIDFiltered, page);

        int quantityPage;
        if (productCardFiltered.isEmpty()) {
            quantityPage = 0;
        } else {
            quantityPage = AdminProductServices.getINSTANCE().getQuantityPage(listIDFiltered);
        }

        StringBuffer requestURL = request.getRequestURL();
        String queryString = request.getQueryString();
        queryString = cutParameterInURL(queryString, "page");
        requestURL.append("?").append(queryString);

        List<String> listInputChecked = listValueChecked(queryString);
        request.setAttribute("requestURL", requestURL);
        request.setAttribute("productCardList", productCardFiltered);
        request.setAttribute("quantityPage", quantityPage);
        request.setAttribute("currentPage", page);
        request.setAttribute("listInputChecked", listInputChecked);
    }

    private List<Integer> filterByTimeUpdate() throws ParseException {
        String[] dates = request.getParameterValues("date");
        List<Integer> listId = null;
        if (dates == null) return listId;
        if (dates.length == 2) {
            try {
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate dateStart = LocalDate.parse(dates[0], dateFormatter);
                LocalDate dateEnd = LocalDate.parse(dates[1], dateFormatter);

                // Convert LocalDate to java.sql.Date for database operations
                Date sqlDateStart = Date.valueOf(dateStart);
                Date sqlDateEnd = Date.valueOf(dateEnd);
                request.setAttribute("sqlDateStart", sqlDateStart);
                request.setAttribute("sqlDateEnd", sqlDateEnd);
                listId = AdminProductServices.getINSTANCE().getProductByTimeCreated(sqlDateStart, sqlDateEnd);
            } catch (DateTimeParseException | IllegalArgumentException e) {
            }
        }
        return listId;
    }

    private List<Integer> filterByNameProduct() {
        String nameProduct = request.getParameter("keyword");
        List<Integer> listId = null;
        if (nameProduct != null) {
            listId = AdminProductServices.getINSTANCE().getProductByName(nameProduct);
            request.setAttribute("keyword", nameProduct);
        }
        return listId;
    }

}
