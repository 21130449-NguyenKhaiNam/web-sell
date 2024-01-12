package services;

import dao.ReviewDAO;
import models.Review;

import java.util.List;

public class AdminReviewServices {
    public static AdminReviewServices INSTANCE;
    private static final int LIMIT = 10;
    private ReviewDAO reviewDAO;

    private AdminReviewServices() {
        this.reviewDAO = new ReviewDAO();
    }

    public static AdminReviewServices getINSTANCE() {
        if (INSTANCE == null)
            INSTANCE = new AdminReviewServices();
        return INSTANCE;
    }

    public List<Review> getReviews(int pageNumber) {
        return reviewDAO.getReviews(pageNumber, LIMIT);
    }
}
