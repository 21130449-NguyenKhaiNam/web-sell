package services;

import dao.ColorDAO;
import dao.ImageDAO;
import dao.ProductDao;
import dao.SizeDAO;
import models.*;

import javax.sound.sampled.Port;
import java.util.ArrayList;
import java.util.List;

public class ProductServices {

    private ProductDao productDao;
    private ImageDAO imageDAO;
    private ColorDAO colorDAO;
    private SizeDAO sizeDAO;
    private static ProductServices INSTANCE;

    public ProductServices() {
        productDao = new ProductDao();
        imageDAO = new ImageDAO();
        colorDAO = new ColorDAO();
        sizeDAO = new SizeDAO();
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

    public double getPriceSizeByName(String nameSize, int productId) {
        return productDao.getPriceSizeByName(nameSize, productId);

    }

    public Size getSizeByNameSizeWithProductId(String nameSize, int productId) {
        return productDao.getSizeByNameSizeWithProductId(nameSize, productId);
    }

    public Product getProductByProductId(int productId){
        return productDao.getProductByProductId(productId);
    }

    public Color getColorByCodeColorWithProductId(String codeColor, int productId) {
        return productDao.getColorByCodeColorWithProductId(codeColor, productId);
    }
}

