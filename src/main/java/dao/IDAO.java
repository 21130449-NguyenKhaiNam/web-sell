package dao;

import java.util.List;

// Định nghĩa các phương thức chung cho các lớp DAO
public interface IDAO {
    // Lấy ra đối tượng theo thông tin
    <T> T selectById(int id);

    // Thêm một đối tượng vào cơ sở dữ liệu
    <T> int insert(T o);

    // Thêm một nhóm đối tượng vào cơ sở dữ liệu
    <T> int insertAll(List<T> list);

    // Cập nhật thông tin cho một đối tượng
    int update(Object o);
}
