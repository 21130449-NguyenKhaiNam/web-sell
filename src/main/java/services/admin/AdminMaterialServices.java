package services.admin;

import dao.MaterialDao;
import models.Log;
import models.Material;

import java.util.List;
import java.util.Map;

public class AdminMaterialServices {
    private static AdminMaterialServices INSTANCE;
    private MaterialDao dao;

    public AdminMaterialServices() {
        dao = new MaterialDao();
    }

    public static AdminMaterialServices getINSTANCE() {
        if (INSTANCE == null)
            INSTANCE = new AdminMaterialServices();
        return INSTANCE;
    }

    public void save(int newId, int quantity) {
        dao.save(newId, quantity);
    }

    public int getIdByCategoryId(int id) {
        List<Material> rs = dao.getIdByCategoryId(id);
        return !rs.isEmpty() ? rs.get(0).getId() : -1;
    }

    public void updateQuantity(int categoryId, int quantity) {
        dao.updateQuantity(categoryId, quantity);
    }

    public List<Map<String, Object>> findPerPage(int perPage, int offset, String searchValue, String columns, String orderBy) {
        return dao.findPerPage(perPage, offset, searchValue, columns, orderBy);
    }

    public long countAll() {
        return dao.countAll();
    }

    public List<Map<String, Object>> getLimit(int limit, int offset) {
        return dao.findPerPage(limit, offset, "", "", "");
    }
}
