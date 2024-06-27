package dao;

import models.CategoryMaterial;
import models.Product;

import java.util.List;

public class CategoryMaterialDao {
    public List<CategoryMaterial> getIdByName(String name) {
        String sql = "select id from categories_material where name = ?";
        return GeneralDao.executeQueryWithSingleTable(sql, CategoryMaterial.class, name);
    }

    public void save(String name) {
        String sql = "insert into categories_material(name) values(?)";
        GeneralDao.executeAllTypeUpdate(sql, name);
    }

}
