package dao;

import annotations.LogParam;
import annotations.WriteLog;
import models.Product;
import models.Size;

import java.util.List;

public interface ISizeDAO extends IDAO {
    //    Lấy ra danh sách tất cả các size
    List<Size> getAllSize();

    //    Lấy ra id sản phẩm dựa vào size
    @WriteLog(WriteLog.SELECT)
    List<Product> getIdProduct(@LogParam("size") String size);

    //    Thêm mới 1 mảng size
    @WriteLog(WriteLog.UPDATE)
    void addSizes(@LogParam("sizes") Size[] sizes);

    //    Lấy ra danh sách id size dựa vào id sản phẩm
    List<Size> getIdSizeByProductId(int productId);

    //    Cập nhập size dựa theo id size
    @WriteLog(WriteLog.UPDATE)
    void updateSize(@LogParam("size") Size size,@LogParam("id") int id);

    //    Xóa size dựa theo danh sách id size
    @WriteLog(WriteLog.UPDATE)
    void deleteSizeList(@LogParam("id-list-delete") List<Integer> listId);
}
