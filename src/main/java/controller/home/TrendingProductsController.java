package controller.home;

import models.Product;
import services.HomeServices;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "trendingProductsController", value = "/trendingProducts")
public class TrendingProductsController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> listAllTrendingProducts = HomeServices.getINSTANCE().getListTrendProducts(true);
        int page, itemsPerPage = 8;
        int size = listAllTrendingProducts.size();
        int totalPage = (size % itemsPerPage == 0 ? (size / itemsPerPage) : ((size / itemsPerPage)) + 1);

        String xPage = request.getParameter("page");
        if (xPage == null) {
            page = 1;
        } else {
            page = Integer.parseInt(xPage);
        }

        int start, end;
        start = (page - 1) * itemsPerPage;
        end = Math.min(page * itemsPerPage, size);
        List<Product> listProductsPerPage = getListProductsPerPage(listAllTrendingProducts, start, end);

        request.setAttribute("page", page);
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("listProductsPerPage", listProductsPerPage);
        System.out.println(listProductsPerPage);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("trendingProducts.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    public List<Product> getListProductsPerPage(List<Product> listProducts, int start, int end) {
        List<Product> listProductsPerPage = new ArrayList<>();
        for (int i = start; i < end; i++) {
            listProductsPerPage.add(listProducts.get(i));
        }
        return listProductsPerPage;
    }
}