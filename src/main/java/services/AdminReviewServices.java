package services;

import dao.IReviewDAO;
import dao.IUserDAO;
import dao.ReviewDAOImp;
import dao.UserDAOImp;
import models.Review;
import models.User;

import java.util.List;

public class AdminReviewServices {
    public static AdminReviewServices INSTANCE;
    private static final int LIMIT = 10;
    private IReviewDAO reviewDAO;
    private IUserDAO userDAO;

    private AdminReviewServices() {
        this.reviewDAO = LogService.getINSTANCE().createProxy(new ReviewDAOImp());
        this.userDAO = LogService.getINSTANCE().createProxy(new UserDAOImp());
    }

    public static AdminReviewServices getINSTANCE() {
        if (INSTANCE == null)
            INSTANCE = new AdminReviewServices();
        return INSTANCE;
    }

    public List<Review> getReviews(int pageNumber) {
        return reviewDAO.getReviews(pageNumber, LIMIT);
    }

    public int getQuantityPage() {
        double quantityPage = Math.ceil(Double.parseDouble(reviewDAO.getQuantityProduct() + "") / LIMIT);
        return (int) quantityPage;
    }

    public int getOrderDetailId(int reviewId) {
        return reviewDAO.getOrderDetailId(reviewId);
    }

    public Review getReview(int reviewId) {
        return reviewDAO.getReviewById(reviewId);
    }

    public boolean updateVisibility(int reviewId, boolean visibility) {
        if (reviewDAO.isVisibility(reviewId).isEmpty() || visibility == reviewDAO.isVisibility(reviewId).get(0).isVisibility()) {
            return false;
        }
        reviewDAO.updateVisibility(reviewId, visibility);
        return true;
    }

    public User getUserByIdProductDetail(int orderDetailId) {
        List<User> listUser = userDAO.getUserByIdProductDetail(orderDetailId);
        if (listUser.isEmpty()) {
            return null;
        }
        return listUser.get(0);
    }
}
