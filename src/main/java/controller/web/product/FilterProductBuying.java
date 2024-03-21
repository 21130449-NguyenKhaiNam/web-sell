package controller.web.product;

import config.ConfigPage;
import utils.FilterStrategy;
import utils.FilterStrategyBuying;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "filterProductBuying", value = "/filterProductBuying")
public class FilterProductBuying extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FilterStrategy filterStrategy = new FilterStrategyBuying(request, response);
        if (!filterStrategy.isAllParameterEmpty()) {
            filterStrategy.doFilter();
        }
//        request.getRequestDispatcher(ConfigPage.PRODUCT_BUYING).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        doGet(request, response);
    }
}