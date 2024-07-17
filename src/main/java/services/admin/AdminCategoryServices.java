package services.admin;

import config.ImagePath;
import dao.CategoryDAO;
import dao.ParameterDAO;
import models.Category;
import models.Parameter;
import services.image.CloudinaryUploadServices;

import java.util.List;

public class AdminCategoryServices {
    private static AdminCategoryServices INSTANCE;
    private CategoryDAO categoryDAO;
    private ParameterDAO parameterDAO;

    private AdminCategoryServices() {
        categoryDAO = new CategoryDAO();
        parameterDAO = new ParameterDAO();
    }

    public static AdminCategoryServices getINSTANCE() {
        if (INSTANCE == null)
            INSTANCE = new AdminCategoryServices();
        return INSTANCE;
    }

    public List<Category> getCategories() {
        List<Category> categories = categoryDAO.getAllCategory();
        for (Category category : categories) {
            String url = CloudinaryUploadServices.getINSTANCE().getImage(ImagePath.CATEGORY.getPath(), category.getSizeTableImage());
            category.setSizeTableImage(url);
        }
        return categories;
    }

    public Category getCategoryById(int id) {
        Category category = categoryDAO.getCategoryById(id);
        String url = CloudinaryUploadServices.getINSTANCE().getImage(ImagePath.CATEGORY.getPath(), category.getSizeTableImage());
        category.setSizeTableImage(url);
        return category;
    }

    public List<Category> getListCategoryById(int id) {
        return categoryDAO.getListCategoryById(id);
    }

    public int getIdByNameType(String nameType) {
        return categoryDAO.getCategoryByNameType(nameType).get(0).getId();
    }

    public int addCategory(Category category) {
        boolean isExist = !categoryDAO.getCategoryByNameType(category.getNameType()).isEmpty();
        if (isExist) return -1;
        return categoryDAO.add(category);
    }

    public void addParameters(List<Parameter> parameterList) {
        for (Parameter parameter : parameterList) {
            categoryDAO.addParameter(parameter);
        }
    }

    public List<Parameter> getParametersByCategoryId(int id) {
        List<Parameter> listParameter = parameterDAO.getParameterByCategoryId(id);
        for (Parameter parameter : listParameter) {
            String url = CloudinaryUploadServices.getINSTANCE().getImage(ImagePath.PARAMETER.getPath(), parameter.getGuideImg());
            parameter.setGuideImg(url);
        }
        return listParameter;
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
