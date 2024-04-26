package dao;

import models.Color;

import java.util.List;

public interface IColorDAO extends IDAO {
    //    Lấy ra danh sách tất cả các màu
    List<Color> getAllColor();

    //    Thêm mới 1 mảng màu
    void addColors(Color[] colors);

    //    Lấy ra danh sách id màu dựa vào id sản phẩm
    List<Color> getIdColorByProductId(int productId);

    //    Cập nhập màu dựa theo id màu
    void updateColor(Color color,int id);

    //    Xóa màu dựa theo danh sách id màu
    void deleteColorList(List<Integer> listIdDelete);
}
