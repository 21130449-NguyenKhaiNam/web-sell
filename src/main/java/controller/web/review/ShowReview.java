package controller.web.review;

import config.ConfigPage;
import models.OrderDetail;
import models.Parameter;
import org.json.JSONArray;
import services.ProductCardServices;
import services.ReviewServices;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "review", value = "/review")
public class ShowReview extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderDetailIdRequest = request.getParameter("orderDetailId");
        int orderDetailId;
        if (orderDetailIdRequest == null) {
            response.sendError(404);
            return;
        }
        try {
            orderDetailId = Integer.parseInt(orderDetailIdRequest);
        } catch (NumberFormatException e) {
            return;
        }
//        Check
        boolean listReview = ReviewServices.getINSTANCE().hasReview(orderDetailId);
        if (!listReview) {
            response.sendError(404);
            return;
        }

        OrderDetail orderDetail = ReviewServices.getINSTANCE().getOrderDetail(orderDetailId);

        String color =orderDetail.getColorRequired();
        String[] sizes = readSizes(orderDetail.getSizeRequired());
        int quantity = orderDetail.getQuantityRequired();
        int productId = orderDetail.getProductId();
        String nameProduct = ProductCardServices.getINSTANCE().getNameProductById(productId);
        List<Parameter> listParameter = ProductCardServices.getINSTANCE().getParameterByIdCategory(productId);
        request.setAttribute("orderDetailId", orderDetailId);
        request.setAttribute("productId", productId);
        request.setAttribute("nameProduct", nameProduct);
        request.setAttribute("listParameter", listParameter);
        request.setAttribute("color", color);
        request.setAttribute("sizes", sizes);
        request.setAttribute("quantity", quantity);
        request.getRequestDispatcher(ConfigPage.USER_REVIEW).forward(request, response);
    }

    private String[] readSizes(String sizesRequired) {
//        Dài áo: 70 cm, Dài tay: 22 cm, Rộng gấu: 54 cm, Rộng bắp tay: 24 cm, Rộng vai: 50 cm
        return sizesRequired.split(", ");
    }
}