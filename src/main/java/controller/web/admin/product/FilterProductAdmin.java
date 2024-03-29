package controller.web.admin.product;

import config.ConfigPage;
import utils.FilterStrategy;
import utils.FilterStrategyAdmin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "filterProductAdmin", value = "/filterProductAdmin")
public class FilterProductAdmin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
<<<<<<< HEAD
        FilterStrategy filterStrategy = new FilterStrategyAdmin(request);
        if (filterStrategy.isAllParameterEmpty()) {
            request.getRequestDispatcher(ConfigPage.ADMIN_PRODUCT).forward(request, response);
            return;
        }
        filterStrategy.doFilter();
        request.getRequestDispatcher(ConfigPage.ADMIN_PRODUCT).forward(request, response);
=======
        FilterStrategy filterStrategy = new FilterStrategyAdmin(request, response);
        if (!filterStrategy.isAllParameterEmpty()) {
            filterStrategy.doFilter();
        }
//        request.getRequestDispatcher(ConfigPage.ADMIN_PRODUCT).forward(request, response);
>>>>>>> 21130449
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}