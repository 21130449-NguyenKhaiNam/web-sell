package controller.home;

import models.Product;
import models.Slider;
import services.HomeServices;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "HomeController", value = "/Home")
public class HomeController extends HttpServlet {

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
//        List<Map<String, Object>> list6TrendingProducts = HomeServices.getINSTANCE().getListTrendingProducts(false);
//        List<Map<String, Object>> list6NewProducts = HomeServices.getINSTANCE().getListNewProducts(false);
        List<Slider> listSlideShow = HomeServices.getINSTANCE().getListSlideShow();
        List<Product> list6NewProducts = HomeServices.getINSTANCE().getListNewProducts(false);
        List<Product> list6TrendProducts = HomeServices.getINSTANCE().getListTrendProducts(false);

        request.setAttribute("listSlideShow", listSlideShow);
//        request.setAttribute("list6TrendingProducts", list6TrendingProducts);
//        request.setAttribute("list6NewProducts", list6NewProducts);

        request.setAttribute("list6TrendingProducts", list6TrendProducts);
        request.setAttribute("list6NewProducts", list6NewProducts);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
        requestDispatcher.forward(request, response);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}