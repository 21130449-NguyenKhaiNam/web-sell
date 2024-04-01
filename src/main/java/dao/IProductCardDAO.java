package dao;

import models.Category;
import models.Parameter;
import models.Product;
import utils.MoneyRange;

import java.sql.Date;
import java.util.List;

public interface IProductCardDAO {
    List<Product> getProducts(int pageNumber, int limit, boolean visibility);

    List<Product> getProducts(int pageNumber, int limit);

    int getQuantityProduct();

    int getQuantityProduct(boolean visibility);

    int getQuantityProduct(List<Integer> listId, boolean visibility);

    int getQuantityProduct(List<Integer> listId);

    List<Product> pagingAndFilter(List<Integer> listId, int pageNumber, int limit);

    List<Product> pagingAndFilter(List<Integer> listId, int pageNumber, int limit, boolean visibility);

    List<Product> getIdProductByCategoryId(List<String> listIdCategory);

    List<Product> getIdProductByColor(List<String> listCodeColor);

    List<Product> getIdProductBySize(List<String> listSize);

    List<Product> getIdProductByMoneyRange(List<MoneyRange> moneyRangeList);

    List<Product> getProductByCategoryId(int categoryId);

    List<Product> getIdProductByName(String name);

    List<Product> getProductByTimeCreated(Date dateBegin, Date dateEnd);

    List<Category> getNameCategoryById(int id);

    List<Category> getCategoryByProductId(int id);

    List<Parameter> getParametersByProductId(int id);

    List<Product> getNameProductById(int id);

    List<Product> isVisibility(int id);

    void updateVisibility(int productId, boolean visibility);

    List<Product> getNameProductByIdOrderDetail(int orderDetailId);
}
