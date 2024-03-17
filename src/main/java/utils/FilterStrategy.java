package utils;

import services.ProductCardServices;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.StringTokenizer;

public abstract class FilterStrategy {
	protected HttpServletRequest request;

	public FilterStrategy(HttpServletRequest request) {
		this.request = request;
	}

	public abstract void doFilter();

	// ko co tham so -> new Array
//co tham so -> loc -> isEmpty -> null
//co tham so -> loc -> !isEmpty -> listId
	// Common filter

	public boolean isAllParameterEmpty() {
		// Get parameter names
		Enumeration<String> parameterNames = request.getParameterNames();
		int countParameterNotBlank = 0;
		int countName = 0;
//        iterator
		while (parameterNames.hasMoreElements()) {
			String paramName = parameterNames.nextElement();
			countName++;
			String paramValue = request.getParameter(paramName);
			if (paramValue == null || paramValue.trim().isEmpty()) {
				countParameterNotBlank++;
			}
		}
		return countParameterNotBlank == countName;
	}

	protected List<Integer> filterByCategory() {
		String[] categoryId = request.getParameterValues("categoryId");
		if (categoryId == null)
			return new ArrayList<>();
		List<Integer> listId = ProductCardServices.getINSTANCE().getIdProductFromCategoryId(categoryId);
		return listId;
	}

	protected List<Integer> filterByColor() {
		String[] colors = request.getParameterValues("color");
		if (colors == null)
			return new ArrayList<>();
		List<Integer> listId = ProductCardServices.getINSTANCE().getIdProductFromColor(colors);
		return listId;
	}

	protected List<Integer> filterBySize() {
		String[] sizes = request.getParameterValues("size");
		if (sizes == null)
			return new ArrayList<>();
		List<Integer> listId = ProductCardServices.getINSTANCE().getIdProductFromSize(sizes);
		return listId;
	}

	protected List<Integer> filterMyMoney() {
		String[] moneyRange = request.getParameterValues("moneyRange");
		if (moneyRange == null)
			return new ArrayList<>();
		List<MoneyRange> moneyRangeList = new ArrayList<>();
		for (String s : moneyRange) {
			StringTokenizer token = new StringTokenizer(s, "-");
			try {
				double from = Double.parseDouble(token.nextToken());
				double to = Double.parseDouble(token.nextToken());
				moneyRangeList.add(new MoneyRange(from, to));
			} catch (NumberFormatException e) {
				throw new RuntimeException(e);
			}
		}
		List<Integer> listId = ProductCardServices.getINSTANCE().getIdProductFromMoneyRange(moneyRangeList);
		return listId;
	}

	protected List<Integer> findCommonIDs(List<List<Integer>> lists) {
		if (lists.isEmpty())
			return new ArrayList<>();
		for (List<Integer> listID : lists) {
			if (listID == null) {
				return new ArrayList<>();
			}
		}
		removeListEmpty(lists);
		if (lists.isEmpty())
			return new ArrayList<>();
		if (lists.size() == 1)
			return lists.get(0);
		List<Integer> result = lists.get(0);
		for (int i = 1; i < lists.size(); i++) {
			result.retainAll(lists.get(i));
		}
		return result;
	}

	private void removeListEmpty(List<List<Integer>> lists) {
		lists.removeIf(List::isEmpty);
	}

	protected String cutParameterInURL(String queryString, String key) {
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

	// Lấy ra các value của các key trên query string
	protected List<String> listValueChecked(String queryString) {
		List<String> values = new ArrayList<>();

		try {
			URI uri = new URI("?" + queryString); // Appending "?" to make it a valid URI
			String query = uri.getQuery();

			if (query != null) {
				String[] queryParams = query.split("&");
				for (String param : queryParams) {
					String[] keyValue = param.split("=");
					if (keyValue.length == 2) {
						String key = keyValue[0];
						String value = keyValue[1];
						values.add(value); // Add the value to the list
					}
				}
			}
		} catch (URISyntaxException e) {
			e.printStackTrace(); // Handle URISyntaxException
		}

		return values;
	}
}
