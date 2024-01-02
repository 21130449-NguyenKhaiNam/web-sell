package utils;

import services.ProductCardServices;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public abstract class FilterStrategy {
    protected HttpServletRequest request;

    public FilterStrategy(HttpServletRequest request) {
        this.request = request;
    }

    public abstract void doFilter();

    //    Common filter
    protected List<Integer> filterByCategory() {
        String[] categoryId = request.getParameterValues("categoryId");
        List<Integer> listId = null;
        if (categoryId != null) {
            listId = ProductCardServices.getINSTANCE().getIdProductFromCategoryId(categoryId);
        }
        return listId;
    }

    protected List<Integer> filterByColor() {
        String[] colors = request.getParameterValues("color");
        List<Integer> listId = null;
        if (colors != null) {
            listId = ProductCardServices.getINSTANCE().getIdProductFromColor(colors);
        }
        return listId;
    }

    protected List<Integer> filterBySize() {
        String[] sizes = request.getParameterValues("size");
        List<Integer> listId = null;
        if (sizes != null) {
            listId = ProductCardServices.getINSTANCE().getIdProductFromSize(sizes);
        }
        return listId;
    }

    protected List<Integer> filterMyMoney() {
        String[] moneyRange = request.getParameterValues("moneyRange");
        List<Integer> listId = null;
        if (moneyRange != null) {
            List<MoneyRange> moneyRangeList = new ArrayList<>();
            for (String s :
                    moneyRange) {
                StringTokenizer token = new StringTokenizer(s, "-");
                try {
                    double from = Double.parseDouble(token.nextToken());
                    double to = Double.parseDouble(token.nextToken());
                    moneyRangeList.add(new MoneyRange(from, to));
                } catch (NumberFormatException e) {
                    throw new RuntimeException(e);
                }
            }
            listId = ProductCardServices.getINSTANCE().getIdProductFromMoneyRange(moneyRangeList);
        }
        return listId;
    }

    protected List<Integer> findCommonIDs(List<List<Integer>> lists) {
        if (lists.isEmpty()) return new ArrayList<>();
        if (lists.size() == 1) return lists.get(0);
        List<Integer> result = new ArrayList<>();
        result = lists.get(0);
        for (int i = 1; i < lists.size(); i++) {
            result.retainAll(lists.get(i));
        }
        return result;
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

    //    Lấy ra các value của các key trên query string
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
