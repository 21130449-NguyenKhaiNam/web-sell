package controller.product;

import models.Product;
import models.Review;
import services.ProductServices;
import services.ReviewServices;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "showProductDetail", value = "/showProductDetail")
public class ShowProductDetail extends HttpServlet {
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
            List<Review> listReview = getListReview(id);
            request.setAttribute("listReview", listReview);
            request.setAttribute("product", product);
            request.getRequestDispatcher("/productDetail.jsp").forward(request, response);
        }
//Reviews
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    public List<Review> getListReview(int productId) {
        return ReviewServices.getINSTANCE().getListReview(productId);
    }
}