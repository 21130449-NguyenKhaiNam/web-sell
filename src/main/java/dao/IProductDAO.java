package dao;

import models.Color;
import models.Image;
import models.Product;
import models.Size;

import java.util.List;

public interface IProductDAO {
    List<Image> getListImagesByProductId(int productId);

    List<Color> getListColorsByProductId(int productId);

    List<Size> getListSizesByProductId(int productId);

    double getPriceSizeByName(String nameSize, int productId);

    Product getProductByProductId(int productId);

    Size getSizeByNameSizeWithProductId(String nameSize, int productId);

    Color getColorByCodeColorWithProductId(String codeColor, int productId);

    List<Product> getIdProductByName(String name);

    void addProduct(Product product);

    void updateProduct(Product product, int id);

}
