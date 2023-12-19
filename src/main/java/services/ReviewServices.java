package services;

import dao.ReviewDAO;
import models.Review;

import java.util.List;

public class ReviewServices {
    private static ReviewServices INSTANCE;
    private ReviewDAO reviewDAO = new ReviewDAO();

    private ReviewServices() {
    }
    public static ReviewServices getINSTANCE() {
        if (INSTANCE == null)
            INSTANCE = new ReviewServices();
        return INSTANCE;
    }
    public List<Review> getListReview(int productId) {
        return reviewDAO.getReview(productId);
    }
}
