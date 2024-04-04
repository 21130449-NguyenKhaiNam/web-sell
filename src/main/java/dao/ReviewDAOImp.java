package dao;

import annotations.LogTable;
import models.Product;
import models.Review;

import java.util.List;

@LogTable(LogTable.PRODUCT)
public class ReviewDAOImp implements IReviewDAO {
    public List<Review> checkReview(int userId, int orderProductIdRequest) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT orders.id ")
                .append("FROM orders JOIN order_details ON orders.id = order_details.orderId  ")
                .append("WHERE orders.userId = ? AND order_details.id = ? AND order_details.id IN (SELECT orderDetailId FROM reviews)");
        return GeneralDAOImp.executeQueryWithSingleTable(sql.toString(), Review.class, userId, orderProductIdRequest);
    }

    public List<Product> getNameProduct(int orderProductId) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT products.`name` ")
                .append("FROM order_details JOIN products ON order_details.productId = products.id ")
                .append("WHERE order_details.id = ?");
        return GeneralDAOImp.executeQueryWithSingleTable(sql.toString(), Product.class, orderProductId);
    }

    public List<Review> getReviewStar(int productId) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ratingStar ")
                .append("FROM products JOIN (order_details JOIN reviews ON order_details.id = reviews.orderDetailId) ON products.id = order_details.productId ")
                .append("WHERE products.id = ? AND reviews.visibility = true");
        return GeneralDAOImp.executeQueryWithSingleTable(sql.toString(), Review.class, productId);
    }

    public List<Review> getReviewByProductId(int productId, boolean visibility) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT reviews.orderDetailId, reviews.ratingStar, reviews.feedback, reviews.reviewDate ")
                .append("FROM reviews JOIN order_details ON reviews.orderDetailId = order_details.id ")
                .append("WHERE order_details.productId = ? AND reviews.visibility = ?");
        return GeneralDAOImp.executeQueryWithSingleTable(sql.toString(), Review.class, productId, visibility);
    }

    public List<Review> getReviewByOrderDetailId(int orderDetailId) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT order_details.productId AS orderDetailId ")
                .append("FROM order_details JOIN reviews ON reviews.orderDetailId = order_details.id ")
                .append("WHERE order_details.id = ?");
        return GeneralDAOImp.executeQueryWithSingleTable(sql.toString(), Review.class, orderDetailId);
    }

    public void createReview(Review review) {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO reviews (orderDetailId, ratingStar, feedback, reviewDate) ")
                .append("VALUES (?,?,?,?)");
        GeneralDAOImp.executeAllTypeUpdate(sql.toString(), review.getOrderDetailId(), review.getRatingStar(), review.getFeedback(), review.getReviewDate());
    }

    public List<Review> getReviews(int pageNumber, int limit) {
        int offset = (pageNumber - 1) * limit;
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT id, orderDetailId, ratingStar, feedback, reviewDate, visibility ")
                .append("FROM reviews ")
                .append(" LIMIT ")
                .append(limit)
                .append(" OFFSET ")
                .append(offset);
       return  GeneralDAOImp.executeQueryWithSingleTable(sql.toString(), Review.class);
    }

    public int getQuantityProduct() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT id FROM reviews ");
        return GeneralDAOImp.executeQueryWithSingleTable(sql.toString(), Review.class).size();
    }

    public List<Product> getNameProductByOrderDetailId(int orderDetailId) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT products.name ")
                .append("FROM products JOIN order_details ON products.id = order_details.productId ")
                .append("WHERE order_details.id = ?");
        return GeneralDAOImp.executeQueryWithSingleTable(sql.toString(), Product.class, orderDetailId);
    }

    public int getOrderDetailId(int reviewId) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT orderDetailId ")
                .append("FROM reviews ")
                .append("WHERE id = ?");
        return GeneralDAOImp.executeQueryWithSingleTable(sql.toString(), Review.class, reviewId).get(0).getOrderDetailId();
    }

    public Review getReviewById(int reviewId) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT orderDetailId, ratingStar, feedback, reviewDate, visibility ")
                .append("FROM reviews ")
                .append("WHERE id = ?");
        return GeneralDAOImp.executeQueryWithSingleTable(sql.toString(), Review.class, reviewId).get(0);
    }

    public void updateVisibility(int reviewId, boolean hideState) {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE reviews ")
                .append("SET visibility = ? ")
                .append("WHERE id = ?");
        GeneralDAOImp.executeAllTypeUpdate(sql.toString(), hideState, reviewId);
    }

    public List<Review> isVisibility(int id) {
        StringBuilder sql = new StringBuilder("SELECT visibility FROM reviews WHERE id = ?");
        return GeneralDAOImp.executeQueryWithSingleTable(sql.toString(), Review.class, id);
    }

    @Override
    public Object getModelById(Object id) {
        return null;
    }
}
