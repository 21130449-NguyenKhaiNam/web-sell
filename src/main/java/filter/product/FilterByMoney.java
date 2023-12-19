package filter.product;


import services.ProductCardServices;
import utils.MoneyRange;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

@WebFilter(filterName = "filterMoneyRange"
        , servletNames = {"filterProduct"})
public class FilterByMoney implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String[] moneyRange = request.getParameterValues("moneyRange");
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
            List<Integer> listId = ProductCardServices.getINSTANCE().getIdProductFromMoneyRange(moneyRangeList);
            request.setAttribute("filterByMoneyRange", listId);
        }
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
