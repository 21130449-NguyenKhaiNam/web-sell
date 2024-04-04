package dao;

import annotations.LogParam;
import annotations.WriteLog;
import models.Product;
import models.Review;

import java.util.List;

public interface IReviewDAO {
    //    Lấy ra danh sách review dựa vào userId và orderProductId
    List<Review> checkReview(int userId, int orderProductIdRequest);

    //    Lấy ra tên sản phẩm dựa vào orderProductId
    List<Product> getNameProduct(int orderProductId);

    //    Lấy ra danh sách sao ( ratingStar ) đánh giá dựa vào productId
    List<Review> getReviewStar(int productId);

    //    Lấy ra danh sách review dựa vào productId và visibility (ẩn hiện sản phẩm)
    List<Review> getReviewByProductId(int productId, boolean visibility);

    //    Lấy ra danh sách review dựa vào orderDetailId
    //    Kiểm tra xem orderDetailId có tồn tại trong bảng reviews không?
    //    Nếu chưa thì sản phẩm chưa được review
    List<Review> getReviewByOrderDetailId(int orderDetailId);

    //    Thêm mới review
    @WriteLog(WriteLog.INSERT)
    void createReview(@LogParam("review") Review review);

    //    Lấy ra danh sách chi tiết review
    //    Sử dụng tham số để phân trang
    List<Review> getReviews(int pageNumber, int limit);

    //    Lấy ra số lượng nhận xét
    //    Cần rename lại tên hàm
    int getQuantityProduct();

    //    Lấy ra danh sách tên sản phẩm dựa vào orderDetailId
    List<Product> getNameProductByOrderDetailId(int orderDetailId);

    //    Lấy ra orderDetailId dựa vào reviewId
    int getOrderDetailId(int reviewId);

    //    Lấy ra review dựa vào id review
    Review getReviewById(int reviewId);

    //    Cập nhập trạng thái ẩn/hiện review dựa vào id review
    @WriteLog(WriteLog.UPDATE)
    void updateVisibility(@LogParam("id-review") int reviewId,@LogParam("hide-state") boolean hideState);

    //    Lấy ra danh sách trạng thái ẩn/hiện review dựa vào id review
    List<Review> isVisibility(int id);
}
