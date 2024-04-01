package dao;

import models.Product;
import models.Review;

import java.util.List;

public interface IReviewDAO {
    List<Review> checkReview(int userId, int orderProductIdRequest);
    List<Product> getNameProduct(int orderProductId);
    List<Review> getReviewStar(int productId);
    List<Review> getReviewByProductId(int productId, boolean visibility);
    List<Review> getReviewByOrderDetailId(int orderDetailId);
    void createReview(Review review);
    List<Review> getReviews(int pageNumber, int limit);
    int getQuantityProduct();
    List<Product> getNameProductByOrderDetailId(int orderDetailId);
    int getOrderDetailId(int reviewId);
    Review getReviewById(int reviewId);
    void updateVisibility(int reviewId, boolean hideState);
    List<Review> isVisibility(int id);
}
