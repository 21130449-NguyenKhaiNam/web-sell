package dao.general;

import dao.IDAO;
import models.Product;
import models.Slider;

import java.util.List;

public interface IHomeDAO extends IDAO {
    //    Lấy ra danh sách các slider
    List<Slider> getListSlideShow();

    //    Lấy ra danh sách các sản phẩm bán chạy nhất (dựa theo số lượng bán được trong order_details > 10)
    //    theo thứ tự giảm dần của số lượng bán được.
    List<Product> getListTrendProducts(boolean isSeeMore);
}
