package services;

import dao.*;
import models.*;

import java.util.List;

public class ProductServices {

    private IProductDAO productDao;
    private IImageDAO imageDAO;
    private IColorDAO colorDAO;
    private ISizeDAO sizeDAO;
    private static ProductServices INSTANCE;

    public ProductServices() {
        productDao = LogService.getINSTANCE().createProxy(new ProductDAOImp());
        imageDAO = LogService.getINSTANCE().createProxy(new ImageDAOImp());
        colorDAO = LogService.getINSTANCE().createProxy(new ColorDAOImp());
        sizeDAO = LogService.getINSTANCE().createProxy(new SizeDAOImp());
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

