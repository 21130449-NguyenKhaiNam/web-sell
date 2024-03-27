package controller.web.product;

import config.ConfigPage;
<<<<<<< HEAD
import utils.FilterStrategyBuying;
import utils.FilterStrategy;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
=======
import utils.FilterStrategy;
import utils.FilterStrategyBuying;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
>>>>>>> 21130449
import java.io.IOException;

@WebServlet(name = "filterProductBuying", value = "/filterProductBuying")
public class FilterProductBuying extends HttpServlet {
<<<<<<< HEAD

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FilterStrategy filterStrategy = new FilterStrategyBuying(request);
        if (filterStrategy.isAllParameterEmpty()) {
            request.getRequestDispatcher(ConfigPage.PRODUCT_BUYING).forward(request, response);
            return;
        }
        filterStrategy.doFilter();
        request.getRequestDispatcher(ConfigPage.PRODUCT_BUYING).forward(request, response);
=======
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FilterStrategy filterStrategy = new FilterStrategyBuying(request, response);
        if (!filterStrategy.isAllParameterEmpty()) {
            filterStrategy.doFilter();
        }
//        request.getRequestDispatcher(ConfigPage.PRODUCT_BUYING).forward(request, response);
>>>>>>> 21130449
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        doGet(request, response);
    }
}