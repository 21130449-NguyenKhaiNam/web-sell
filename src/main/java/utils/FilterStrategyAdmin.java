package utils;

import models.Product;
import services.admin.AdminProductServices;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class FilterStrategyAdmin extends FilterStrategy {
    private final int QUANTITY_PAGE_DEFAULT = 5;
    private int quantityPageMin;
    private int quantityPageMax;
    private int currentPage;

    public FilterStrategyAdmin(HttpServletRequest request) {
        super(request);
    }

    @Override
    public void doFilter() {
        List<Integer> filterByDate;
        try {
            filterByDate = filterByTimeUpdate();
        } catch (ParseException ignored) {
            filterByDate = new ArrayList<>();
        }

        List<Integer> filterByName = filterByNameProduct();
        List<Integer> filterByColor = filterByColor();
        List<Integer> filterByCategoryId = filterByCategory();
        List<Integer> filterByMoneyRange = filterMyMoney();
        List<Integer> filterBySize = filterBySize();

        String pageNumber = request.getParameter("page");

        try {
            currentPage = Integer.parseInt(pageNumber);
        } catch (NumberFormatException e) {
            currentPage = 1;
        }

        List<List<Integer>> listId = new ArrayList<>();
        listId.add(filterByDate);
        listId.add(filterByName);
        listId.add(filterByColor);
        listId.add(filterByCategoryId);
        listId.add(filterByMoneyRange);
        listId.add(filterBySize);

        List<Integer> listIDFiltered = findCommonIDs(listId);
        List<Product> productCardFiltered;
        if (listIDFiltered.isEmpty()) {
            productCardFiltered = AdminProductServices.getINSTANCE().filter(null, currentPage);
        } else {
            productCardFiltered = AdminProductServices.getINSTANCE().filter(listIDFiltered, currentPage);
        }

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
        generateQuantityPage();
        if (quantityPageMax > quantityPage) {
            quantityPageMax = quantityPage;
        }

        request.setAttribute("requestURL", requestURL);
        request.setAttribute("productCardList", productCardFiltered);
        request.setAttribute("quantityPageMin", quantityPageMin);
        request.setAttribute("quantityPageMax", quantityPageMax);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("listInputChecked", listInputChecked);
    }

    private List<Integer> filterByTimeUpdate() throws ParseException {
        String[] dates = request.getParameterValues("date");

        if (dates == null || dates.length == 1 || dates[0].isBlank() || dates[1].isBlank())
            return new ArrayList<>();

        try {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate dateStart = LocalDate.parse(dates[0], dateFormatter);
            LocalDate dateEnd = LocalDate.parse(dates[1], dateFormatter);

            // Convert LocalDate to java.sql.Date for database operations
            Date sqlDateStart = Date.valueOf(dateStart);
            Date sqlDateEnd = Date.valueOf(dateEnd);
            request.setAttribute("sqlDateStart", sqlDateStart);
            request.setAttribute("sqlDateEnd", sqlDateEnd);
            List<Integer> listId = AdminProductServices.getINSTANCE().getProductByTimeCreated(sqlDateStart, sqlDateEnd);
            return listId;
        } catch (DateTimeParseException | IllegalArgumentException e) {
            throw new ParseException(e.getMessage(), 0);
        }
    }

    private List<Integer> filterByNameProduct() {
        String nameProduct = request.getParameter("keyword");
        if (nameProduct == null || nameProduct.isBlank())
            return new ArrayList<>();

        List<Integer> listId = AdminProductServices.getINSTANCE().getProductByName(nameProduct);
        request.setAttribute("keyword", nameProduct);

        return listId;
    }

    public void generateQuantityPage() {
        quantityPageMin = currentPage - 2;
        quantityPageMax = currentPage + 2;
        if (quantityPageMin < 1) {
            quantityPageMin = 1;
            quantityPageMax = QUANTITY_PAGE_DEFAULT;
        }
    }

}

