package dao;

import models.Color;
import models.Image;
import models.Product;
import models.Size;

import java.util.List;

public interface IProductDAO {
//    Các hàm dưới dùng để xác định sản phẩm với thông số đã chọn có thực sự tồn tại không
//    Lấy ra danh sách ảnh theo id sản phẩm
    List<Image> getListImagesByProductId(int productId);

//    Lấy ra danh sách màu theo id sản phẩm
    List<Color> getListColorsByProductId(int productId);

//    Lấy ra danh sách size theo id sản phẩm
    List<Size> getListSizesByProductId(int productId);

//    Lấy ra giá của sản phẩm theo id sản phẩm và tên size
    double getPriceSizeByName(String nameSize, int productId);

//    Lấy ra sản phẩm theo id sản phẩm
    Product getProductByProductId(int productId);

//    Lấy ra size theo tên size và id sản phẩm
    Size getSizeByNameSizeWithProductId(String nameSize, int productId);

//    Lấy ra màu theo tên màu và id sản phẩm
    Color getColorByCodeColorWithProductId(String codeColor, int productId);

//    Lấy ra id sản phẩm theo tên sản phẩm
    List<Product> getIdProductByName(String name);

//    Thêm mới sản phẩm
    void addProduct(Product product);

//    Cập nhập sản phẩm
    void updateProduct(Product product, int id);
}
