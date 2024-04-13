package dao.product;

import annotations.LogTable;
import dao.general.GeneralDAOImp;
import models.Category;
import models.Parameter;

import java.util.List;
@LogTable(LogTable.PRODUCT)
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

    @Override
    public Object getModelById(Object id) {
        return null;
    }
}
