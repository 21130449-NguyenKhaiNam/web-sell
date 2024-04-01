package dao;

import models.Product;
import models.Size;

import java.util.List;

public interface ISizeDAO {
    List<Size> getAllSize();

    List<Product> getIdProduct(String size);

    void addSizes(Size[] sizes);

    List<Size> getIdSizeByProductId(int productId);

    void updateSize(Size size, int id);

    void deleteSizeList(List<Integer> listId);
}
