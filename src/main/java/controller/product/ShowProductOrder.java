package controller.product;

import config.ConfigPage;
import models.Category;
import models.Parameter;
import models.Product;
import models.Review;
import services.ProductCardServices;
import services.ProductServices;
import services.ReviewServices;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "showProductOrder", value = "/showProductOrder")
public class ShowProductOrder extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParameter = request.getParameter("id");
        int id;
        try {
            id = Integer.parseInt(idParameter.trim());
        } catch (NumberFormatException e) {
            response.sendError(404);
            return;
        }
        Product product = ProductServices.getINSTANCE().getProductByProductId(id);
        if (product == null) {
            response.sendError(404);
        } else {
//            Category
            Category category = ProductCardServices.getINSTANCE().getCategoryById(id);
//            Parameter
            List<Parameter> listParameter = ProductCardServices.getINSTANCE().getParameterByIdCategory(id);
//            Product
            request.setAttribute("product", product);
            request.setAttribute("category", category);
            request.setAttribute("listParameter", listParameter);
            request.getRequestDispatcher(ConfigPage.PRODUCT_ORDER).forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}