package services;

import dao.*;
import models.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class AdminProductServices {
    private static final int LIMIT = 15;
    private static AdminProductServices INSTANCE;
    ProductDao productDAO = new ProductDao();
    ColorDAO colorDAO = new ColorDAO();
    ImageDAO imageDAO = new ImageDAO();
    SizeDAO sizeDAO = new SizeDAO();
    ProductCardDAO productCardDAO = new ProductCardDAO();

    private AdminProductServices() {
    }

    public static AdminProductServices getINSTANCE() {
        if (INSTANCE == null)
            INSTANCE = new AdminProductServices();
        return INSTANCE;
    }

    public int addProduct(Product product) {
        List<Product> productList = productDAO.getIdProductByName(product.getName());
        if (!productList.isEmpty()) return 0;
        productDAO.addProduct(product);
        return productDAO.getIdProductByName(product.getName()).get(0).getId();
    }

    public void addColor(String[] codeColors, int productId) {
        Color[] colors = new Color[codeColors.length];
        for (int i = 0; i < codeColors.length; i++) {
            colors[i] = new Color();
            colors[i].setCodeColor(codeColors[i]);
            colors[i].setProductId(productId);
        }
        colorDAO.addColors(colors);
    }

    public void addImages(List<String> nameImages, int productId) {
        List<Image> imageList = new ArrayList<>();
        for (int i = 0; i < nameImages.size(); i++) {
            Image image = new Image();
            image.setNameImage(nameImages.get(i));
            image.setProductId(productId);
            imageList.add(image);
        }
        imageDAO.addImages(imageList);
    }

    public void addSize(String[] nameSizes, double[] sizePrices, int productId) {
        Size[] sizes = new Size[nameSizes.length];
        for (int i = 0; i < sizes.length; i++) {
            sizes[i] = new Size();
            sizes[i].setNameSize(nameSizes[i]);
            sizes[i].setSizePrice(sizePrices[i]);
            sizes[i].setProductId(productId);
        }
        sizeDAO.addSizes(sizes);
    }

    public List<Product> filter(List<Integer> listId, int pageNumber) {
        List<Product> productList = productCardDAO.pagingAndFilter(listId, pageNumber, LIMIT);
        return productList;
    }

    public int getQuantityPage() {
        double quantityPage = Math.ceil(Double.parseDouble(productCardDAO.getQuantityProduct() + "") / LIMIT);
        return (int) quantityPage;
    }

    public int getQuantityPage(List<Integer> listId) {
        double quantityPage = Math.ceil(Double.parseDouble(productCardDAO.getQuantityProduct(listId) + "") / LIMIT);
        return (int) quantityPage;
    }

    public List<Integer> getProductByName(String name) {
        List<Product> listProduct = productCardDAO.getIdProductByName(name);
        if (listProduct.isEmpty()) return null;
        List<Integer> listId = new ArrayList<>();
        for (Product p :
                listProduct) {
            listId.add(p.getId());
        }
        return listId;
    }

    public List<Integer> getProductByTimeCreated(Date dateBegin, Date dateEnd) {
        List<Product> listProduct = productCardDAO.getProductByTimeCreated(dateBegin, dateEnd);
        if (listProduct.isEmpty()) return null;
        List<Integer> listId = new ArrayList<>();
        for (Product p :
                listProduct) {
            listId.add(p.getId());
        }
        return listId;
    }
    public List<Product> getProducts(int numberPage) {
        List<Product> productList = productCardDAO.getProducts(numberPage, LIMIT);
        return productList;
    }
}
