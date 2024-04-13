package dao.admin;

import models.Order;
import models.OrderDetail;
import models.Product;

import java.util.List;

// Lấy ra thông tin thống kê của sản phẩm
public interface IDashboardProductDAO extends IDashboadDAO {
    //    Lấy số sản phẩm có trong hệ thống (table Products)
    int countProduct();

    //    Lấy số lượng review có trong hệ thống (table Reviews)
    int countReview();

    //    Lấy tên 5 sản phẩm bán chạy nhất
    List<Product> getTop5ProductName(int productId);

    //    Lấy 5 sản phẩm bán chạy nhất (số lượng sản phẩm đã bán ra nhiều nhất)
    List<OrderDetail> getTop5Product();

    //    Lấy số lượng 5 sản phẩm bán chạy nhất
    List<OrderDetail> getTop5ProductQuantity(int productId);

}
