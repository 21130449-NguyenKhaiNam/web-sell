package controller.product;

import dao.ProductCardDAO;
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
            //            Product
            request.setAttribute("product", product);
            //Reviews
            List<Review> listReview = getListReview(id);
            request.setAttribute("listReview", listReview);
//            Related product
            List<Product> listProductRelated = getListProductRandom(product.getCategoryId(), 4);
            request.setAttribute("listProductRelated", listProductRelated);
            request.getRequestDispatcher("/productDetail.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    public List<Product> getListProductRandom(int categoryId, int quantity) {
        return ProductCardServices.getINSTANCE().getProductByCategoryId(categoryId, quantity, true);
    }
    public List<Review> getListReview(int productId) {
        return ReviewServices.getINSTANCE().getListReview(productId);
    }
}