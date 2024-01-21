package services;

import dao.CategoryDAO;
import models.Category;
import models.Parameter;

import java.util.List;

public class AdminCategoryServices {
    private static AdminCategoryServices INSTANCE;
    private CategoryDAO categoryDAO;

    private AdminCategoryServices() {
        categoryDAO = new CategoryDAO();
    }

    public static AdminCategoryServices getINSTANCE() {
        if (INSTANCE == null)
            INSTANCE = new AdminCategoryServices();
        return INSTANCE;
    }

    public List<Category> getCategories() {
        return categoryDAO.getAllCategory();
    }

    public List<Category> getCategoryById(int id){
        return categoryDAO.getCategoryById(id);
    }

    public int addCategory(Category category) {
        boolean isExist = !categoryDAO.getCategoryByNameType(category.getNameType()).isEmpty();
        if (!isExist) {
            categoryDAO.add(category);
            return categoryDAO.getCategoryByNameType(category.getNameType()).get(0).getId();
        } else {
            return -1;
        }
    }

    public void addParameters(List<Parameter> parameterList, int categoryId) {
        for (Parameter parameter :parameterList) {
            parameter.setCategoryId(categoryId);
            categoryDAO.addParameter(parameter);
        }
    }

    public List<Parameter> getParameterByCategoryId(int id) {
        return categoryDAO.getParameterByCategoryId(id);
    }
}
