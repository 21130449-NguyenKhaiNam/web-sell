package services;

import dao.*;
import models.*;
import utils.MoneyRange;

import java.util.*;

public class ProductCardServices {
    private static final int LIMIT = 9;
    private static ProductCardServices INSTANCE;
    private ProductDAOImp productDAO;
    private ProductCardDAOImp productCardDAO;
    private ReviewDAOImp reviewDAO;
    private SizeDAOImp sizeDAO;
    private ColorDAOImp colorDAO;
    private CategoryDAOImp categoryDAO;
    private ProductCardServices() {
        this.productDAO = new ProductDAOImp();
        this.productCardDAO = new ProductCardDAOImp();
        this.sizeDAO = new SizeDAOImp();
        this.colorDAO = new ColorDAOImp();
        this.categoryDAO = new CategoryDAOImp();
        this.reviewDAO = new ReviewDAOImp();

    }

    public static ProductCardServices getINSTANCE() {
        if (INSTANCE == null)
            INSTANCE = new ProductCardServices();
        return INSTANCE;
    }

    public List<Category> getAllCategory() {
        List<Category> listCategories = categoryDAO.getAllCategory();
        return listCategories;
    }

    public List<Color> getAllColor() {
        List<Color> listColor = colorDAO.getAllColor();
        return listColor;
    }

    public List<Size> getAllSize() {
        List<Size> listSize = sizeDAO.getAllSize();
        return listSize;
    }

    public List<Product> getProducts(int numberPage) {
        List<Product> productCardList = productCardDAO.getProducts(numberPage, LIMIT, true);
        return productCardList;
    }

    public int getQuantityPage() {
        double quantityPage = Math.ceil(Double.parseDouble(productCardDAO.getQuantityProduct(true) + "") / LIMIT);
        return (int) quantityPage;
    }

    public int getQuantityPage(List<Integer> listId) {
        double quantityPage = Math.ceil(Double.parseDouble(productCardDAO.getQuantityProduct(listId, true) + "") / LIMIT);
        return (int) quantityPage;
    }

    public List<Product> filter(List<Integer> listId, int pageNumber) {
        List<Product> productList = productCardDAO.pagingAndFilter(listId, pageNumber, LIMIT, true);
        return productList;
    }
    public List<Integer> getIdProductFromCategoryId(String[] categoryIds) {
        List<Product> listProduct = productCardDAO.getIdProductByCategoryId(Arrays.asList(categoryIds));
        if (listProduct.isEmpty()) return null;
        List<Integer> listId = new ArrayList<>();
        for (Product p :
                listProduct) {
            listId.add(p.getId());
        }
        return listId;
    }

    public List<Integer> getIdProductFromSize(String[] sizes) {
        List<Product> listProduct = productCardDAO.getIdProductBySize(Arrays.asList(sizes));
        if (listProduct.isEmpty()) return null;
        List<Integer> listId = new ArrayList<>();
        for (Product p :
                listProduct) {
            listId.add(p.getId());
        }
        return listId;
    }

    public List<Integer> getIdProductFromColor(String[] colors) {
        List<Product> listProduct = productCardDAO.getIdProductByColor(Arrays.asList(colors));
        if (listProduct.isEmpty()) return null;
        List<Integer> listId = new ArrayList<>();
        for (Product p :
                listProduct) {
            listId.add(p.getId());
        }
        return listId;
    }

    public List<Integer> getIdProductFromMoneyRange(List<MoneyRange> moneyRangeList) {
        List<Product> listProduct = productCardDAO.getIdProductByMoneyRange(moneyRangeList);
        if (listProduct.isEmpty()) return null;
        List<Integer> listId = new ArrayList<>();
        for (Product p :
                listProduct) {
            listId.add(p.getId());
        }
        return listId;
    }

    public int getReviewCount(int productId) {
        List<Review> list = reviewDAO.getReviewStar(productId);
        if (list.isEmpty()) return 0;
        return list.size();
    }

    public int calculateStar(int productId) {
        List<Review> list = reviewDAO.getReviewStar(productId);
        if (list.isEmpty()) return 0;
        int totalStar = 0;
        for (Review item : list) {
            totalStar += item.getRatingStar();
        }
        return totalStar / list.size();
    }

    public List<Product> getProductByCategoryId(int categoryId, int quantity, boolean isRandom) {
        List<Product> productList = productCardDAO.getProductByCategoryId(categoryId);
        List<Product> result = new ArrayList<>();
        if ((productList.size() - quantity) < 10) {
            for (int i = 0; i < quantity; i++) {
                try {
                    result.add(productList.get(i));
                } catch (IndexOutOfBoundsException e) {
                    break;
                }
            }
            return result;
        }
        int numRd = 0;
        Random rd = new Random();
        for (int i = 0; i < quantity; i++) {
            Product p = null;
            if (isRandom) {
                numRd = rd.nextInt(productList.size());
                p = productList.get(numRd);
                while (productList.contains(p)) {
                    numRd = rd.nextInt(productList.size());
                    System.out.println(rd);
                    p = productList.get(numRd);
                }
            } else {
                p = productList.get(i);
            }
            result.add(p);
        }
        return result;
    }

    public String getNameCategoryById(int id) {
        return productCardDAO.getNameCategoryById(id).get(0).getNameType();
    }

    public List<Parameter> getParameterByIdCategory(int id) {
        return productCardDAO.getParametersByProductId(id);
    }

    public Category getCategoryById(int id) {
        return productCardDAO.getCategoryByProductId(id).get(0);
    }

    public String getNameProductByIdOrderDetail(int orderDetailId) {
        return reviewDAO.getNameProductByOrderDetailId(orderDetailId).get(0).getName();
    }

    public String getNameProductById(int productId) {
        return productCardDAO.getNameProductById(productId).get(0).getName();
    }


}
