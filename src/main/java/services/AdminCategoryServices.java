package services;

import dao.product.CategoryDAOImp;
import dao.product.ICategoryDAO;
import dao.product.IParameterDAO;
import dao.product.ParameterDAOImp;
import models.Category;
import models.Parameter;

import java.util.List;

public class AdminCategoryServices {
    private static AdminCategoryServices INSTANCE;
    private ICategoryDAO categoryDAO;
    private IParameterDAO parameterDAO;
    private AdminCategoryServices() {
        categoryDAO = LogService.getINSTANCE().createProxy(new CategoryDAOImp());
        parameterDAO = LogService.getINSTANCE().createProxy(new ParameterDAOImp());
    }

    public static AdminCategoryServices getINSTANCE() {
        if (INSTANCE == null)
            INSTANCE = new AdminCategoryServices();
        return INSTANCE;
    }

    public List<Category> getCategories() {
        List<Category> categories = categoryDAO.getAllCategory();
        return categories;
    }

    public List<Category> getCategoryById(int id){
        return categoryDAO.selectById(id);
    }

    public int addCategory(Category category) {
        boolean isExist = !categoryDAO.getCategoryByNameType(category.getNameType()).isEmpty();
        if (!isExist) {
            categoryDAO.insert(category);
            return categoryDAO.getCategoryByNameType(category.getNameType()).get(0).getId();
        } else {
            return -1;
        }
    }

    public void addParameters(List<Parameter> parameterList, int categoryId) {
        for (Parameter parameter :parameterList) {
            parameter.setCategoryId(categoryId);
            categoryDAO.insert(parameter);
        }
    }

    public List<Parameter> getParameterByCategoryId(int id) {
        return parameterDAO.getParameterByCategoryId(id);
    }

    public void updateCategory(Category category) {
        categoryDAO.update(category);
    }

    public void updateParameters(List<Parameter> listParameter, int categoryId) {
//        Get quantity parameter exist
        List<Parameter> listParameterExist = parameterDAO.getParameterByCategoryId(categoryId);
        int quantityParameterUpdate = Math.min(listParameterExist.size(), listParameter.size());
        int quantityParameterAdded = Math.max((listParameter.size() - quantityParameterUpdate), 0);
        int quantityParameterDeleted = listParameter.size() - listParameterExist.size();
//        Update: left - right
        for (int i = 0; i < quantityParameterUpdate; i++) {
            listParameter.get(i).setId(listParameterExist.get(i).getId());
            parameterDAO.update(listParameter.get(i));
        }
//       Delete: right to left
        if (quantityParameterDeleted < 0) {
            quantityParameterDeleted = Math.abs(quantityParameterDeleted);
            listParameterExist = parameterDAO.getParameterByCategoryId(categoryId, false);
            for (int i = 0; i < quantityParameterDeleted; i++) {
                parameterDAO.deleteParameter(listParameterExist.get(i).getId());
            }
        }
//       Add: right to left
        if (quantityParameterAdded > 0) {
            for (int i = quantityParameterUpdate; i < listParameter.size(); i++) {
                parameterDAO.insert(listParameter.get(i));
            }
        }
    }
}
