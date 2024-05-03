package dao;

import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import controller.shoppingCart.HandelCart;
import models.Category;
import models.Parameter;
import models.shoppingCart.AbstractCartProduct;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CategoryDAOImp implements ICategoryDAO {
    public List<Category> getAllCategory() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT id, nameType ")
                .append("FROM categories");
        return GeneralDAOImp.executeQueryWithSingleTable(sql.toString(), Category.class);
    }

    public void add(Category category) {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO categories (nameType, sizeTableImage) VALUES (?, ?) ");
        GeneralDAOImp.executeAllTypeUpdate(sql.toString(), category.getNameType(), category.getSizeTableImage());
    }

    public List<Category> getCategoryByNameType(String nameType) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT id FROM categories WHERE nameType = ?");
        return GeneralDAOImp.executeQueryWithSingleTable(sql.toString(), Category.class, nameType);
    }

    public void addParameter(Parameter parameter) {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO parameters (name, minValue, `maxValue`, unit, categoryId, guideImg) VALUES (?, ?, ?, ?, ?, ?) ");
        GeneralDAOImp.executeAllTypeUpdate(sql.toString(), parameter.getName(), parameter.getMinValue(), parameter.getMaxValue(), parameter.getUnit(), parameter.getCategoryId(), parameter.getGuideImg());
    }

    public List<Category> getCategoryById(int id) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT id, nameType, sizeTableImage ")
                .append("FROM categories ")
                .append("WHERE id = ?");
        return GeneralDAOImp.executeQueryWithSingleTable(sql.toString(), Category.class, id);
    }


    public void updateCategory(Category category) {
        StringBuilder sql = new StringBuilder();
        if (category.getSizeTableImage() == null) {
            sql.append("UPDATE categories SET nameType = ? WHERE id = ?");
            GeneralDAOImp.executeAllTypeUpdate(sql.toString(), category.getNameType(), category.getId());
        } else {
            sql.append("UPDATE categories SET nameType = ?, sizeTableImage = ? WHERE id = ?");
            GeneralDAOImp.executeAllTypeUpdate(sql.toString(), category.getNameType(), category.getSizeTableImage(), category.getId());
        }
    }

}
