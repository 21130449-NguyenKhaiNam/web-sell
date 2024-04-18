package dao.admin;

import models.Order;
import models.OrderDetail;
import models.Product;

import java.time.LocalDate;
import java.util.List;

public interface IDashboardOrderDAO extends IDashboadDAO {
    // Lấy ra số lượng sản phẩm bán chạy (giá trị N)
    List<Product> getTopNProduct(int n);

    //    Lấy đơn hàng theo thời gian
    List<Order> getOrderByDate(LocalDate date);
}
