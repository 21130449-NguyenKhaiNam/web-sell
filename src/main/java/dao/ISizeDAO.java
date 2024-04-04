package dao;

import models.Product;
import models.Size;

import java.util.List;

public interface ISizeDAO {
    //    Lấy ra danh sách tất cả các size
    List<Size> getAllSize();

    //    Lấy ra id sản phẩm dựa vào size
    List<Product> getIdProduct(String size);

    //    Thêm mới 1 mảng size
    void addSizes(Size[] sizes);

    //    Lấy ra danh sách id size dựa vào id sản phẩm
    List<Size> getIdSizeByProductId(int productId);

    //    Cập nhập size dựa theo id size
    void updateSize(Size size, int id);

    //    Xóa size dựa theo danh sách id size
    void deleteSizeList(List<Integer> listId);
}
