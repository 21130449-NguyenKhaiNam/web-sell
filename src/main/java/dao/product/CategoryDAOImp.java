package dao.product;

import annotations.LogTable;
import dao.general.GeneralDAO;
import models.Category;

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

    @Override
    public List<Category> getAllCategory() {
        String sql = "SELECT id, nameType FROM categories";
        return GeneralDAO.executeQueryWithSingleTable(sql, Category.class);
    }

    @Override
    public List<Category> getCategoryByNameType(String nameType) {
        String query = "SELECT id FROM categories WHERE nameType = ?";
        return GeneralDAO.executeQueryWithSingleTable(query, Category.class, nameType);
    }
}
