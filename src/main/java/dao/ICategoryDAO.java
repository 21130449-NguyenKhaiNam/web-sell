package dao;

import models.Category;
import models.Parameter;

import java.util.List;

public interface ICategoryDAO {
    List<Category> getAllCategory();
    void add(Category category);
    List<Category> getCategoryByNameType(String nameType);
    void addParameter(Parameter parameter);
    List<Category> getCategoryById(int id);
    void updateCategory(Category category);
}
