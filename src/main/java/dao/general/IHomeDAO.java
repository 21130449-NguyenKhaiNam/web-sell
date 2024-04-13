package dao.general;

import dao.IDAO;
import models.Product;
import models.Slider;

import java.util.List;

public interface IHomeDAO extends IDAO {
    @Override
    default <T> T selectById(Object id) {
        throw new UnsupportedOperationException("IHomeDAO >> Phương thức SELECT không được hỗ trợ");
    }

    @Override
    default <T> int insertAll(List<T> list) {
        throw new UnsupportedOperationException("IHomeDAO >> Phương thức INSERT nhiều phần tử không được hỗ trợ");
    }

    @Override
    default int update(Object o) {
        throw new UnsupportedOperationException("IHomeDAO >> Phương thức UPDATE từng phần tử không được hỗ trợ");
    }

    @Override
    default <T> int insert(T o) {
        throw new UnsupportedOperationException("IHomeDAO >> Phương thức INSERT phần tử không được hỗ trợ");
    }

    //    Lấy ra danh sách các slider
    List<Slider> getListSlideShow();

    //    Lấy ra danh sách các sản phẩm bán chạy nhất (dựa theo số lượng bán được trong order_details > 10)
    //    theo thứ tự giảm dần của số lượng bán được.
    List<Product> getListTrendProducts(boolean isSeeMore);
}
