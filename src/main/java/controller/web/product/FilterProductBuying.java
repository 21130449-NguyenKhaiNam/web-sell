package controller.web.product;

import config.ConfigPage;
import utils.FilterStrategyBuying;
import utils.FilterStrategy;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "filterProductBuying", value = "/filterProductBuying")
public class FilterProductBuying extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FilterStrategy filterStrategy = new FilterStrategyBuying(request);
        if (filterStrategy.isAllParameterEmpty()) {
            request.getRequestDispatcher(ConfigPage.PRODUCT_BUYING).forward(request, response);
            return;
        }
        filterStrategy.doFilter();
        request.getRequestDispatcher(ConfigPage.PRODUCT_BUYING).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        doGet(request, response);
    }
}