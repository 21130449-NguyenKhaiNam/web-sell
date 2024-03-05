package utils;

import models.Product;
import services.ProductCardServices;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class FilterStrategyBuying extends FilterStrategy {

    public FilterStrategyBuying(HttpServletRequest request) {
        super(request);
    }

    @Override
    public void doFilter() {
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
            listId.add(filterByColor);
            listId.add(filterByCategoryId);
            listId.add(filterByMoneyRange);
            listId.add(filterBySize);
        List<Integer> listIDFiltered = findCommonIDs(listId);
        List<Product> productCardFiltered;
        if (listIDFiltered.isEmpty()) {
            productCardFiltered = ProductCardServices.getINSTANCE().filter(null, page);
        } else {
            productCardFiltered = ProductCardServices.getINSTANCE().filter(listIDFiltered, page);
        }
//        listIDFiltered lọc dựa trên visibility
//        listIDFiltered == 0 -> 0
//        listIDFiltered.size() < LIMIT -> 1
//        listIDFiltered.size() >= LIMIT
        int quantityPage;
        if (productCardFiltered.isEmpty()) {
            quantityPage = 0;
        } else {
            quantityPage = ProductCardServices.getINSTANCE().getQuantityPage(listIDFiltered);
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
}
