package controller.api.admin.review;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import models.Order;
import models.OrderDetail;
import models.Review;
import services.admin.AdminReviewServices;
import services.ProductCardServices;
import services.ReviewServices;
import utils.ProductFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "adminReadReview", value = "/api/admin/review/get")
public class GetReviewController extends HttpServlet {
    Gson gson = new GsonBuilder().create();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParameter = request.getParameter("id");
        int reviewId;
        try {
            reviewId = Integer.parseInt(idParameter);
        } catch (NumberFormatException e) {
            response.sendError(404);
            return;
        }
        Review review = AdminReviewServices.getINSTANCE().getReview(reviewId);
        if (review == null) {
            response.sendError(404);
            return;
        }
        OrderDetail orderDetail = ReviewServices.getINSTANCE().getOrderDetail(review.getOrderDetailId());
        Order order = ReviewServices.getINSTANCE().getOrder(orderDetail.getOrderId());
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("review", convertReview(review));
        jsonObject.add("orderDetail", convertOrderDetail(orderDetail));
        jsonObject.add("order", convertOrder(order));
        response.getWriter().write(jsonObject.toString());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    private JsonElement convertReview(Review review) {
        JsonObject result = new JsonObject();
        result.addProperty("id", review.getId());
        result.addProperty("ratingStar", review.getRatingStar());
        result.addProperty("feedback", review.getFeedback());
        result.addProperty("date", review.getReviewDate().toString());
        result.addProperty("visibility", review.isVisibility());
        return result;
    }

    private JsonElement convertOrderDetail(OrderDetail orderDetail) {
        JsonObject result = new JsonObject();
        result.addProperty("productName", orderDetail.getProductName());
        result.addProperty("size", orderDetail.getSizeRequired());
        result.addProperty("color", orderDetail.getColorRequired());
        result.addProperty("quantity", orderDetail.getQuantityRequired());
        result.addProperty("price", orderDetail.getPrice());
        return result;
    }

    private JsonElement convertOrder(Order order) {
        JsonObject result = new JsonObject();
        result.addProperty("fullName", order.getFullName());
        result.addProperty("email", order.getEmail());
        result.addProperty("phone", order.getPhone());
        result.addProperty("province", order.getProvince());
        result.addProperty("district", order.getDistrict());
        result.addProperty("ward", order.getWard());
        result.addProperty("detail", order.getDetail());
        return result;
    }
}