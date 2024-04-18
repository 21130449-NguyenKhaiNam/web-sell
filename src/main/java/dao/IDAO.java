package dao;

import annotations.LogParam;
import annotations.WriteLog;
import models.OrderDetail;

import java.util.List;

// Định nghĩa các phương thức chung cho các lớp DAO
public interface IDAO {
    // Lấy ra đối tượng theo thông tin
    @WriteLog(WriteLog.SELECT)
    default <T> T selectById(@LogParam("id-object-select") Object id) {
        throw new UnsupportedOperationException("DAO >> Phương thức SELECT không được hỗ trợ");
    }

    @WriteLog(WriteLog.SELECT)
    default <T> List<T> selectAll() {
        throw new UnsupportedOperationException("DAO >> Phương thức SELECT ALL không được hỗ trợ");
    }

    // Thêm một đối tượng vào cơ sở dữ liệu
    @WriteLog(WriteLog.INSERT)
    default <T> int insert(@LogParam("object-insert") T o) {
        throw new UnsupportedOperationException("DAO >> Phương thức INSERT phần tử không được hỗ trợ");
    }

    // Thêm nhiều đối tượng vào cơ sở dữ liệu
    @WriteLog(WriteLog.INSERT)
    default <T> int insertAll(@LogParam("object-insert-multi") List<T> list) {
        throw new UnsupportedOperationException("DAO >> Phương thức INSERT nhiều phần tử không được hỗ trợ");
    }

    // Cập nhật dữ liệu cho dòng trong cơ sở dữ liệu
    @WriteLog(WriteLog.UPDATE)
    default int update(@LogParam("object-update") Object o) {
        throw new UnsupportedOperationException("DAO >> Phương thức UPDATE từng phần tử không được hỗ trợ");
    }
}
