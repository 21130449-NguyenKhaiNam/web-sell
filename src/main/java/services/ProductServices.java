package services;

import dao.ColorDAOImp;
import dao.ImageDAOImp;
import dao.ProductDAOImp;
import dao.SizeDAOImp;
import models.*;

import java.util.List;

public class ProductServices {

    private ProductDAOImp productDao;
    private ImageDAOImp imageDAO;
    private ColorDAOImp colorDAO;
    private SizeDAOImp sizeDAO;
    private static ProductServices INSTANCE;

    public ProductServices() {
        productDao = new ProductDAOImp();
        imageDAO = new ImageDAOImp();
        colorDAO = new ColorDAOImp();
        sizeDAO = new SizeDAOImp();
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

