package controller.product;

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
        filterStrategy.doFilter();
        request.getRequestDispatcher("productBuying.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        doGet(request, response);
    }
}