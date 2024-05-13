package controller.web.review;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Review;
import org.json.JSONObject;
import services.admin.AdminReviewServices;
import utils.ProductFactory;
import utils.UserFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ReviewPage", value = "/api/admin/review/page")
public class ReviewPage extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pageOnUrl = request.getParameter("page");
        int currentPage;
        try {
            currentPage = Integer.parseInt(pageOnUrl);
        } catch (NumberFormatException e) {
            currentPage = 1;
        }

        List<Review> listReview = AdminReviewServices.getINSTANCE().getReviews(currentPage);

        List<ReviewResponse> reviewResponses = new ArrayList<>();
        for (Review review : listReview) {
            int userId = -1;
            if (UserFactory.getUserByIdProductDetail(review.getOrderDetailId()) != null) {
                userId = UserFactory.getUserByIdProductDetail(review.getOrderDetailId()).getId();
            }
            ReviewResponse reviewResponse = new ReviewResponse(review.getId(),
                    userId,
                    ProductFactory.getNameProductByIdOrderDetail(review.getOrderDetailId()),
                    review.getOrderDetailId(),
                    review.getRatingStar(),
                    review.getFeedback(),
                    review.getReviewDate(),
                    review.isVisibility());
            reviewResponses.add(reviewResponse);
        }

        int quantityPageTotal = AdminReviewServices.getINSTANCE().getQuantityPage();
        ListReviewResponse listReviewResponse = new ListReviewResponse(quantityPageTotal, reviewResponses);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("quantity", quantityPageTotal);
        jsonObject.put("reviews", listReviewResponse);

        ObjectMapper mapper = new ObjectMapper();
        String jsonResponse = null;
        try {
            jsonResponse = mapper.writeValueAsString(listReviewResponse);
            response.getWriter().write(jsonResponse);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    private class ReviewResponse {
        private int id;
        private int userId;
        private String productName;
        private int orderDetailId;
        private int ratingStar;
        private String feedback;
        private Date reviewDate;
        private boolean visibility;

        public ReviewResponse(int id, int userId, String productName, int orderDetailId, int ratingStar, String feedback, Date reviewDate, boolean visibility) {
            this.id = id;
            this.userId = userId;
            this.productName = productName;
            this.orderDetailId = orderDetailId;
            this.ratingStar = ratingStar;
            this.feedback = feedback;
            this.reviewDate = reviewDate;
            this.visibility = visibility;
        }

        @JsonGetter("userId")
        public int getUserId() {
            return userId;
        }

        @JsonGetter("orderDetailId")
        public int getOrderDetailId() {
            return orderDetailId;
        }

        @JsonGetter("id")
        public int getId() {
            return id;
        }

        @JsonGetter("productName")
        public String getProductName() {
            return productName;
        }

        @JsonGetter("ratingStar")
        public int getRatingStar() {
            return ratingStar;
        }

        @JsonGetter("feedBack")
        public String getFeedback() {
            return feedback;
        }

        @JsonGetter("reviewDate")
        public Date getReviewDate() {
            return reviewDate;
        }

        @JsonGetter("visibility")
        public boolean isVisibility() {
            return visibility;
        }
    }

    private class ListReviewResponse {
        private int quantity;
        private List<ReviewResponse> listReivewResponse;

        public ListReviewResponse(int quantity, List<ReviewResponse> listReivewResponse) {
            this.quantity = quantity;
            this.listReivewResponse = listReivewResponse;
        }

        @JsonGetter("quantity")
        public int getQuantity() {
            return quantity;
        }

        @JsonGetter("reviews")
        public List<ReviewResponse> getListReviewResponse() {
            return listReivewResponse;
        }
    }
}