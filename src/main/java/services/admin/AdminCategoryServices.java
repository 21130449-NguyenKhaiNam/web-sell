package services.admin;

import dao.CategoryDAOImp;
import dao.ParameterDAO;
import models.Category;
import models.Parameter;

import java.util.List;

public class AdminCategoryServices {
    private static AdminCategoryServices INSTANCE;
    private CategoryDAOImp categoryDAO;
    private ParameterDAO parameterDAO;
    private AdminCategoryServices() {
        categoryDAO = new CategoryDAOImp();
        parameterDAO = new ParameterDAO();
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
        return parameterDAO.getParameterByCategoryId(id);
    }

    public void updateCategory(Category category) {
        categoryDAO.updateCategory(category);
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
            parameterDAO.updateParameter(listParameter.get(i));
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
                parameterDAO.addParameter(listParameter.get(i));
            }
        }
    }
}
