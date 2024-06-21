package services.admin;

import dao.CategoryMaterialDao;
import models.CategoryMaterial;

import java.util.List;

public class AdminCategoryMaterialServices {
    private static AdminCategoryMaterialServices INSTANCE;
    private CategoryMaterialDao dao;

    public AdminCategoryMaterialServices() {
        dao = new CategoryMaterialDao();
    }

    public static AdminCategoryMaterialServices getINSTANCE() {
        if (INSTANCE == null)
            INSTANCE = new AdminCategoryMaterialServices();
        return INSTANCE;
    }

    public int getIdByName(String name) {
        List<CategoryMaterial> cs = dao.getIdByName(name);
        return !cs.isEmpty() ? cs.get(0).getId() : -1;
    }

    public void save(String name) {
        dao.save(name);
    }
}
