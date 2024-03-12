package utils;

import models.Product;
import services.ProductCardServices;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class FilterStrategyBuying extends FilterStrategy {
	private final int QUANTITY_PAGE_DEFAULT = 5;
	private int quantityPageTotal;
	private int quantityPageMin;
	private int quantityPageMax;
	private int currentPage;

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

		try {
			currentPage = Integer.parseInt(pageNumber);
		} catch (NumberFormatException e) {
			currentPage = 1;
		}
		List<List<Integer>> listId = new ArrayList<>();
		listId.add(filterByColor);
		listId.add(filterByCategoryId);
		listId.add(filterByMoneyRange);
		listId.add(filterBySize);
		List<Integer> listIDFiltered = findCommonIDs(listId);
		List<Product> productCardFiltered;
		if (listIDFiltered.isEmpty()) {
			productCardFiltered = ProductCardServices.getINSTANCE().filter(null, currentPage);
		} else {
			productCardFiltered = ProductCardServices.getINSTANCE().filter(listIDFiltered, currentPage);
		}
//        listIDFiltered lọc dựa trên visibility
//        listIDFiltered == 0 -> 0
//        listIDFiltered.size() < LIMIT -> 1
//        listIDFiltered.size() >= LIMIT

		if (productCardFiltered.isEmpty()) {
			quantityPageTotal = 0;
		} else {
			quantityPageTotal = ProductCardServices.getINSTANCE().getQuantityPage(listIDFiltered);
		}

		StringBuffer requestURL = request.getRequestURL();
		String queryString = request.getQueryString();
		queryString = cutParameterInURL(queryString, "page");
		requestURL.append("?").append(queryString);

		List<String> listInputChecked = listValueChecked(queryString);
		generateQuantityPage();

		if(quantityPageMax > quantityPageTotal) {
			quantityPageMax = quantityPageTotal;
		}
		request.setAttribute("requestURL", requestURL);
		request.setAttribute("productCardList", productCardFiltered);
		request.setAttribute("quantityPageMin", quantityPageMin);
		request.setAttribute("quantityPageMax", quantityPageMax);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("listInputChecked", listInputChecked);
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
