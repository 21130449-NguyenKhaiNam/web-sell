package services;

import dao.IOrderDetailDAO;
import dao.IReviewDAO;
import dao.OrderDetailDAOImp;
import dao.ReviewDAOImp;
import models.OrderDetail;
import models.Product;
import models.Review;

import java.util.List;

public class ReviewServices {
    private static ReviewServices INSTANCE;
    private IReviewDAO reviewDAO;

    private IOrderDetailDAO orderDetailDAO;

    private ReviewServices() {
        reviewDAO = LogService.getINSTANCE().createProxy(new ReviewDAOImp());
        orderDetailDAO = LogService.getINSTANCE().createProxy(new OrderDetailDAOImp());
    }

    public static ReviewServices getINSTANCE() {
        if (INSTANCE == null)
            INSTANCE = new ReviewServices();
        return INSTANCE;
    }

    public boolean canReview(int userId, int orderProductIdRequest) {
        List<Review> listReview = reviewDAO.checkReview(userId, orderProductIdRequest);
        return listReview.isEmpty();
    }

    public String getNameProduct(int orderProductId) {
        List<Product> listProduct = reviewDAO.getNameProduct(orderProductId);
        return listProduct.isEmpty() ? null : listProduct.get(0).getName();
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

    public void createReview(Review review) {
        reviewDAO.createReview(review);
    }
}
