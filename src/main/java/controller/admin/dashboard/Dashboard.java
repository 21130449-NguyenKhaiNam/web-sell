package controller.admin.dashboard;

import dao.DashboadDAO;
import dao.ProductDao;
import models.OrderDetail;
import models.Product;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "Dashboard", value = "/Dashboard")
public class Dashboard extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DashboadDAO dshb = new DashboadDAO();
        int userNumber = dshb.countUser();
        int productNumber = dshb.countProduct();
        int orderNumber = dshb.countOrder();
        int reviewNumber = dshb.countReview();

        request.setAttribute("user", userNumber);
        request.setAttribute("product", productNumber);
        request.setAttribute("order", orderNumber);
        request.setAttribute("review", reviewNumber);

        List<OrderDetail> top5Product= dshb.getTop5Product();
        List<Product> listTop5Product = null;
        List<Integer> listProducId = new ArrayList<>();

        List<String> listtop5NameProduct = new ArrayList<>();
        List<Integer> listtop5Quantity = new ArrayList<>();

        for (OrderDetail top5OrderDetail:top5Product) {
            listProducId.add(top5OrderDetail.getProductId());
        }
        for (Integer productId:listProducId) {
            listTop5Product= dshb.getTop5ProductName(productId);
        }
        for (Product product:listTop5Product) {
            listtop5NameProduct.add(product.getName());
        }



        request.setAttribute("top5",top5Product);

        request.getRequestDispatcher("dashboard.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



//        request.getRequestDispatcher("dashboard.jsp").forward(request,response);
    }

}