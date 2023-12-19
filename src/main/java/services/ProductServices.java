package services;

import dao.GeneralDao;
import dao.ProductDao;
import models.Color;
import models.Image;
import models.Product;
import models.Size;

import java.util.List;

public class ProductServices {

    private ProductDao productDao;
    private static ProductServices INSTANCE;

    public ProductServices() {
        productDao = new ProductDao();
    }

    public static ProductServices getINSTANCE() {
        if (INSTANCE == null)
            INSTANCE = new ProductServices();
        return INSTANCE;
    }

    public List<Image> getListImagesByProductId(int productId){
        return productDao.getListImagesByProductId(productId);
    }

    public List<Color> getListColorsByProductId(int productId){
        return productDao.getListColorsByProductId(productId);
    }

    public List<Size> getListSizesByProductId(int productId){
        return productDao.getListSizesByProductId(productId);
    }

//    public double getPriceSizeByName(String nameSize, int productId){
//        return productDao.getPriceSizeByName(nameSize, productId);
//    }

    public Size getSizeByNameSizeWithProductId(String nameSize, int productId){
        return productDao.getSizeByNameSizeWithProductId(nameSize, productId);
    }

    public Color getColorByCodeColorWithProductId(String codeColor, int productId){
        return productDao.getColorByCodeColorWithProductId(codeColor, productId);
    }

    public Product getProductByProductId(int productId){
        return productDao.getProductByProductId(productId);
    }
}
