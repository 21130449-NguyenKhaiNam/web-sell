package dao.product;

import annotations.LogTable;
import dao.general.GeneralDAOImp;
import models.Product;
import models.Review;

import java.util.List;

@LogTable(LogTable.PRODUCT)
public class ReviewDAOImp implements IReviewDAO {
    @Override
    public <T> int insert(T o) {
        if(o instanceof Review) {
            Review review = (Review) o;
            String query = "INSERT INTO reviews (orderDetailId, ratingStar, feedback, reviewDate) VALUES (?,?,?,?)";
            GeneralDAOImp.executeAllTypeUpdate(query, review.getOrderDetailId(), review.getRatingStar(), review.getFeedback(), review.getReviewDate());
            return 1;
        } else {
            throw new UnsupportedOperationException("ProductDAOImp >> Phương thức thêm không hỗ trợ tham số kiểu khác");
        }
    }

    @Override
    public List<Review> checkReview(int userId, int orderProductIdRequest) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT orders.id ")
                .append("FROM orders JOIN order_details ON orders.id = order_details.orderId  ")
                .append("WHERE orders.userId = ? AND order_details.id = ? AND order_details.id IN (SELECT orderDetailId FROM reviews)");
        return GeneralDAOImp.executeQueryWithSingleTable(sql.toString(), Review.class, userId, orderProductIdRequest);
    }

    @Override
    public List<Product> getNameProduct(int orderProductId) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT products.`name` ")
                .append("FROM order_details JOIN products ON order_details.productId = products.id ")
                .append("WHERE order_details.id = ?");
        return GeneralDAOImp.executeQueryWithSingleTable(sql.toString(), Product.class, orderProductId);
    }

    @Override
    public List<Review> getReviewStar(int productId) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ratingStar ")
                .append("FROM products JOIN (order_details JOIN reviews ON order_details.id = reviews.orderDetailId) ON products.id = order_details.productId ")
                .append("WHERE products.id = ? AND reviews.visibility = true");
        return GeneralDAOImp.executeQueryWithSingleTable(sql.toString(), Review.class, productId);
    }

    @Override
    public List<Review> getReviewByProductId(int productId, boolean visibility) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT reviews.orderDetailId, reviews.ratingStar, reviews.feedback, reviews.reviewDate ")
                .append("FROM reviews JOIN order_details ON reviews.orderDetailId = order_details.id ")
                .append("WHERE order_details.productId = ? AND reviews.visibility = ?");
        return GeneralDAOImp.executeQueryWithSingleTable(sql.toString(), Review.class, productId, visibility);
    }

    @Override
    public List<Review> getReviewByOrderDetailId(int orderDetailId) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT order_details.productId AS orderDetailId ")
                .append("FROM order_details JOIN reviews ON reviews.orderDetailId = order_details.id ")
                .append("WHERE order_details.id = ?");
        return GeneralDAOImp.executeQueryWithSingleTable(sql.toString(), Review.class, orderDetailId);
    }

    @Override
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

    @Override
    public int getQuantityProduct() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT id FROM reviews ");
        return GeneralDAOImp.executeQueryWithSingleTable(sql.toString(), Review.class).size();
    }

    @Override
    public List<Product> getNameProductByOrderDetailId(int orderDetailId) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT products.name ")
                .append("FROM products JOIN order_details ON products.id = order_details.productId ")
                .append("WHERE order_details.id = ?");
        return GeneralDAOImp.executeQueryWithSingleTable(sql.toString(), Product.class, orderDetailId);
    }

    @Override
    public int getOrderDetailId(int reviewId) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT orderDetailId ")
                .append("FROM reviews ")
                .append("WHERE id = ?");
        return GeneralDAOImp.executeQueryWithSingleTable(sql.toString(), Review.class, reviewId).get(0).getOrderDetailId();
    }

    @Override
    public Review getReviewById(int reviewId) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT orderDetailId, ratingStar, feedback, reviewDate, visibility ")
                .append("FROM reviews ")
                .append("WHERE id = ?");
        return GeneralDAOImp.executeQueryWithSingleTable(sql.toString(), Review.class, reviewId).get(0);
    }

    @Override
    public void updateVisibility(int reviewId, boolean hideState) {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE reviews ")
                .append("SET visibility = ? ")
                .append("WHERE id = ?");
        GeneralDAOImp.executeAllTypeUpdate(sql.toString(), hideState, reviewId);
    }

    @Override
    public List<Review> isVisibility(int id) {
        StringBuilder sql = new StringBuilder("SELECT visibility FROM reviews WHERE id = ?");
        return GeneralDAOImp.executeQueryWithSingleTable(sql.toString(), Review.class, id);
    }
}
