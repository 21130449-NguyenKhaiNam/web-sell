package dao;

import models.Product;
import models.Review;

import java.util.List;

public class ReviewDAO {
    public List<Review> getReviewStar(int productId) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ratingStar ")
                .append("FROM products JOIN (order_details JOIN reviews ON order_details.id = reviews.orderDetailId) ON products.id = order_details.productId ")
                .append("WHERE products.id = ?");
        return GeneralDao.executeQueryWithSingleTable(sql.toString(), Review.class, productId);
    }

    public List<Review> getReviewByProductId(int productId, boolean visibility) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT userId, ratingStar, feedback, reviewDate ")
                .append("FROM users JOIN (reviews JOIN (order_details JOIN products ")
                .append("ON products.id = order_details.productId) ")
                .append("ON reviews.orderDetailId = order_details.id) ")
                .append("ON users.id = reviews.userId ")
                .append("WHERE products.id = ? AND reviews.visibility = ?");
        return GeneralDao.executeQueryWithSingleTable(sql.toString(), Review.class, productId, visibility);
    }

    public List<Review> getReviewByOrderDetailId(int orderDetailId) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT order_details.productId AS orderDetailId ")
                .append("FROM order_details JOIN reviews ON reviews.orderDetailId = order_details.id ")
                .append("WHERE order_details.id = ?");
        return GeneralDao.executeQueryWithSingleTable(sql.toString(), Review.class, orderDetailId);
    }

    public void createReview(Review review) {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO reviews (userId, orderDetailId, ratingStar, feedback, reviewDate) ")
                .append("VALUES  (?,?,?,?,?)");
        GeneralDao.executeAllTypeUpdate(sql.toString(), review.getUserId(), review.getOrderDetailId(), review.getRatingStar(), review.getFeedback(), review.getReviewDate());
    }

    public List<Review> getReviews(int pageNumber, int limit) {
        int offset = (pageNumber - 1) * limit;
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT id, userId, orderDetailId, ratingStar, feedback, reviewDate, visibility ")
                .append("FROM reviews ")
                .append(" LIMIT ")
                .append(limit)
                .append(" OFFSET ")
                .append(offset);
       return  GeneralDao.executeQueryWithSingleTable(sql.toString(), Review.class);
    }

    public int getQuantityProduct() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT id FROM reviews ");
        return GeneralDao.executeQueryWithSingleTable(sql.toString(), Review.class).size();
    }

    public List<Product> getNameProductByOrderDetailId(int orderDetailId) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT products.name ")
                .append("FROM products JOIN order_details ON products.id = order_details.productId ")
                .append("WHERE order_details.id = ?");
        return GeneralDao.executeQueryWithSingleTable(sql.toString(), Product.class, orderDetailId);
    }

    public int getOrderDetailId(int reviewId) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT orderDetailId ")
                .append("FROM reviews ")
                .append("WHERE id = ?");
        return GeneralDao.executeQueryWithSingleTable(sql.toString(), Review.class, reviewId).get(0).getOrderDetailId();
    }

    public Review getReviewById(int reviewId) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT userId, orderDetailId, ratingStar, feedback, reviewDate, visibility ")
                .append("FROM reviews ")
                .append("WHERE id = ?");
        return GeneralDao.executeQueryWithSingleTable(sql.toString(), Review.class, reviewId).get(0);
    }

    public void updateVisibility(int reviewId, boolean hideState) {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE reviews ")
                .append("SET visibility = ? ")
                .append("WHERE id = ?");
        GeneralDao.executeAllTypeUpdate(sql.toString(), hideState, reviewId);
    }

    public List<Review> isVisibility(int id) {
        StringBuilder sql = new StringBuilder("SELECT visibility FROM reviews WHERE id = ?");
        return GeneralDao.executeQueryWithSingleTable(sql.toString(), Review.class, id);
    }
}
