package services;

import dao.OrderDetailDAO;
import dao.ReviewDAO;
import models.OrderDetail;
import models.Review;

import java.util.List;

public class ReviewServices {
    private static ReviewServices INSTANCE;
    private ReviewDAO reviewDAO;

    private OrderDetailDAO orderDetailDAO;
    private ReviewServices() {
        reviewDAO = new ReviewDAO();
        orderDetailDAO = new OrderDetailDAO();
    }
    public static ReviewServices getINSTANCE() {
        if (INSTANCE == null)
            INSTANCE = new ReviewServices();
        return INSTANCE;
    }
    public List<Review> getListReview(int productId) {
        return reviewDAO.getReviewByProductId(productId, true);
    }

    public boolean hasReview(int orderDetailId) {
        return reviewDAO.getReviewByOrderDetailId(orderDetailId).isEmpty();
    }

    public OrderDetail getOrderDetail(int orderDetailId) {
        return orderDetailDAO.getOrderDetailById(orderDetailId).get(0);
    }

    public void createReview(Review review){
        reviewDAO.createReview(review);
    }
}
