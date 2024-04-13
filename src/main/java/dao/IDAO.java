package dao;

import models.OrderDetail;

import java.util.List;

// Định nghĩa các phương thức chung cho các lớp DAO
public interface IDAO {
    // Lấy ra đối tượng theo thông tin
    default <T> T selectById(Object id) {
        throw new UnsupportedOperationException("DAO >> Phương thức SELECT không được hỗ trợ");
    }

    // Thêm một đối tượng vào cơ sở dữ liệu
    default <T> int insert(T o) {
        throw new UnsupportedOperationException("DAO >> Phương thức INSERT phần tử không được hỗ trợ");
    }

    // Thêm nhiều đối tượng vào cơ sở dữ liệu
    default <T> int insertAll(List<T> list) {
        throw new UnsupportedOperationException("DAO >> Phương thức INSERT nhiều phần tử không được hỗ trợ");
    }

    // Cập nhật dữ liệu cho dòng trong cơ sở dữ liệu
    default int update(Object o) {
        throw new UnsupportedOperationException("DAO >> Phương thức UPDATE từng phần tử không được hỗ trợ");
    }
}
