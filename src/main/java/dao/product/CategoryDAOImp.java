package dao.product;

import annotations.LogTable;
import dao.general.GeneralDAO;
import models.Category;
import models.Order;
import models.Parameter;

import java.util.List;
@LogTable(LogTable.PRODUCT)
public class CategoryDAOImp implements ICategoryDAO {
    @Override
    public <T> int insert(T o) {
        if(o instanceof Category) {
            Category category = (Category) o;
            String query = "INSERT INTO categories (nameType, sizeTableImage) VALUES (?, ?) ";
            GeneralDAO.executeAllTypeUpdate(query, category.getNameType(), category.getSizeTableImage());
            return 1;
        } else {
            throw new UnsupportedOperationException("CategoryDAOImp >> Phương thức thêm không hỗ trợ tham số kiểu khác");
        }
    }

    @Override
    public Category selectById(Object id) {
        if(id instanceof Integer) {
            String sql = "SELECT id, nameType, sizeTableImage FROM categories WHERE id = ?";
            return GeneralDAO.executeQueryWithSingleTable(sql, Category.class, id).get(0);
        } else {
            throw new UnsupportedOperationException("CategoryDAOImp >> Phương thức thêm không hỗ trợ tham số kiểu khác");
        }
    }

    @Override
    public int update(Object o) {
        if(o instanceof Category) {
            Category category = (Category) o;
            StringBuilder sql = new StringBuilder();
            if (category.getSizeTableImage() == null) {
                sql.append("UPDATE categories SET nameType = ? WHERE id = ?");
                GeneralDAO.executeAllTypeUpdate(sql.toString(), category.getNameType(), category.getId());
            } else {
                sql.append("UPDATE categories SET nameType = ?, sizeTableImage = ? WHERE id = ?");
                GeneralDAO.executeAllTypeUpdate(sql.toString(), category.getNameType(), category.getSizeTableImage(), category.getId());
            }
            return 1;
        } else {
            throw new UnsupportedOperationException("CategoryDAOImp >> Phương thức thêm không hỗ trợ tham số kiểu khác");
        }
    }

    public List<Category> getAllCategory() {
        String sql = "SELECT id, nameType FROM categories";
        return GeneralDAO.executeQueryWithSingleTable(sql, Category.class);
    }

    public List<Category> getCategoryByNameType(String nameType) {
        String query = "SELECT id FROM categories WHERE nameType = ?";
        return GeneralDAO.executeQueryWithSingleTable(query, Category.class, nameType);
    }


//    public void updateCategory(Category category) {
//        StringBuilder sql = new StringBuilder();
//        if (category.getSizeTableImage() == null) {
//            sql.append("UPDATE categories SET nameType = ? WHERE id = ?");
//            GeneralDAO.executeAllTypeUpdate(sql.toString(), category.getNameType(), category.getId());
//        } else {
//            sql.append("UPDATE categories SET nameType = ?, sizeTableImage = ? WHERE id = ?");
//            GeneralDAO.executeAllTypeUpdate(sql.toString(), category.getNameType(), category.getSizeTableImage(), category.getId());
//        }
//    }

    //    public void addParameter(Parameter parameter) {
//        StringBuilder sql = new StringBuilder();
//        sql.append("INSERT INTO parameters (name, minValue, `maxValue`, unit, categoryId, guideImg) VALUES (?, ?, ?, ?, ?, ?) ");
//        GeneralDAO.executeAllTypeUpdate(sql.toString(), parameter.getName(), parameter.getMinValue(), parameter.getMaxValue(), parameter.getUnit(), parameter.getCategoryId(), parameter.getGuideImg());
//    }

    //    public List<Category> getCategoryById(int id) {
//        String sql = "SELECT id, nameType, sizeTableImage " +
//                "FROM categories " +
//                "WHERE id = ?";
//        return GeneralDAO.executeQueryWithSingleTable(sql, Category.class, id);
//    }
}
