package dao.admin;

import dao.IDAO;
import models.Order;
import models.OrderDetail;
import models.Product;

import java.util.List;

public interface IDashboadDAO extends IDAO {
    @Override
    default <T> T selectById(int id) {
        throw new UnsupportedOperationException("Dashboard >> Phương thức SELECT từng phần tử không được hỗ trợ");
    }

    @Override
    default <T> int insert(T o) {
        throw new UnsupportedOperationException("Dashboard >> Phương thức INSERT từng phần tử không được hỗ trợ");
    }

    @Override
    default <T> int insertAll(List<T> list) {
        throw new UnsupportedOperationException("Dashboard >> Phương thức INSERT nhiều phần tử không được hỗ trợ");
    }

    @Override
    default int update(Object o) {
        throw new UnsupportedOperationException("Dashboard >> Phương thức UPDATE từng phần tử không được hỗ trợ");
    }
}
