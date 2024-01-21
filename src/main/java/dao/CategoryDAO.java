package dao;

import models.Category;
import models.Parameter;

import java.util.List;

public class CategoryDAO {
    public List<Category> getAllCategory() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT id, nameType ")
                .append("FROM categories");
        return GeneralDao.executeQueryWithSingleTable(sql.toString(), Category.class);
    }

    public void add(Category category) {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO categories (nameType, sizeTableImage) VALUES (?, ?) ");
        GeneralDao.executeAllTypeUpdate(sql.toString(), category.getNameType(), category.getSizeTableImage());
    }

    public List<Category> getCategoryByNameType(String nameType) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT id FROM categories WHERE nameType = ?");
        return GeneralDao.executeQueryWithSingleTable(sql.toString(), Category.class, nameType);
    }

    public void addParameter(Parameter parameter) {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO parameters (name, minValue, `maxValue`, unit, categoryId, guideImg) VALUES (?, ?, ?, ?, ?, ?) ");
        GeneralDao.executeAllTypeUpdate(sql.toString(), parameter.getName(), parameter.getMinValue(), parameter.getMaxValue(), parameter.getUnit(), parameter.getCategoryId(), parameter.getGuideImg());
    }

    public List<Category> getCategoryById(int id) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT id, nameType, sizeTableImage ")
                .append("FROM categories ")
                .append("WHERE id = ?");
        return GeneralDao.executeQueryWithSingleTable(sql.toString(), Category.class, id);
    }

    public List<Parameter> getParameterByCategoryId(int id) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT id, name, minValue, `maxValue`, unit, guideImg ")
                .append("FROM parameters ")
                .append("WHERE categoryId = ?");
        return GeneralDao.executeQueryWithSingleTable(sql.toString(), Parameter.class, id);
    }
}
