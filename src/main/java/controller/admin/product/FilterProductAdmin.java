package controller.admin.product;


import utils.FilterStrategy;
import utils.FilterStrategyAdmin;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "filterProductAdmin", value = "/filterProductAdmin")
public class FilterProductAdmin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FilterStrategy filterStrategy = new FilterStrategyAdmin(request);
        filterStrategy.doFilter();
        request.getRequestDispatcher("adminProducts.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}