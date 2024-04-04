package dao;

import annotations.LogParam;
import annotations.WriteLog;
import models.Color;

import java.util.List;

public interface IColorDAO {
    //    Lấy ra danh sách tất cả các màu
    List<Color> getAllColor();

    //    Thêm mới 1 mảng màu
    @WriteLog(WriteLog.INSERT)
    void addColors(@LogParam("colors") Color[] colors);

    //    Lấy ra danh sách id màu dựa vào id sản phẩm
    List<Color> getIdColorByProductId(int productId);

    //    Cập nhập màu dựa theo id màu
    @WriteLog(WriteLog.UPDATE)
    void updateColor(@LogParam("color") Color color,@LogParam("id") int id);

    //    Xóa màu dựa theo danh sách id màu
    @WriteLog(WriteLog.UPDATE)
    void deleteColorList(@LogParam("id-list-delete") List<Integer> listIdDelete);
}
