package dao.product;

import annotations.LogTable;
import dao.general.GeneralDAO;
import models.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@LogTable(LogTable.PRODUCT)
public class ProductDAOImp implements IProductDAO {
    @Override
    public <T> int insert(T o) {
        if(o instanceof Product) {
            Product product = (Product) o;
            String query = "INSERT INTO products (name, categoryId, description, originalPrice, salePrice, visibility, createAt) " +
                    "VALUES (?,?,?,?,?,?,?) ";
            GeneralDAO.executeAllTypeUpdate(query, product.getName(), product.getCategoryId(), product.getDescription(), product.getOriginalPrice(), product.getSalePrice(), product.isVisibility(), product.getCreateAt());
            return 1;
        } else {
            throw new UnsupportedOperationException("ProductDAOImp >> Phương thức thêm không hỗ trợ tham số kiểu khác");
        }
    }

    public List<Image> getListImagesByProductId(int productId) {
        String sql = "SELECT id, nameImage, productId FROM Images WHERE productId = ?";
        return GeneralDAO.executeQueryWithSingleTable(sql, Image.class, productId);
    }


    public List<Color> getListColorsByProductId(int productId) {
        String sql = "SELECT id, codeColor, productId FROM colors WHERE productId = ?";
        return GeneralDAO.executeQueryWithSingleTable(sql, Color.class, productId);
    }

    public List<Size> getListSizesByProductId(int productId) {
        String sql = "SELECT id, nameSize, productId, sizePrice FROM sizes WHERE productId = ?";
        return GeneralDAO.executeQueryWithSingleTable(sql, Size.class, productId);
    }

    public double getPriceSizeByName(String nameSize, int productId) {
        String sql = "SELECT sizePrice FROM sizes WHERE nameSize = ? AND productId = ?";
        return GeneralDAO.executeQueryWithSingleTable(sql, Size.class, nameSize, productId).get(0).getSizePrice();
    }

    public Product getProductByProductId(int productId) {
        String sql = "SELECT id, `name`, categoryId, `description`, salePrice, originalPrice FROM products WHERE id = ?";
        return GeneralDAO.executeQueryWithSingleTable(sql, Product.class, productId).get(0);
    }

    public Size getSizeByNameSizeWithProductId(String nameSize, int productId) {
        String sql = "SELECT id, sizePrice, nameSize FROM sizes WHERE nameSize = ? AND productId = ?";
        return GeneralDAO.executeQueryWithSingleTable(sql, Size.class, nameSize, productId).get(0);
    }

    public Color getColorByCodeColorWithProductId(String codeColor, int productId) {
        String sql = "SELECT id, codeColor FROM colors WHERE codeColor = ? AND productId = ?";
        return GeneralDAO.executeQueryWithSingleTable(sql, Color.class, codeColor, productId).get(0);
    }

    public List<Product> getIdProductByName(String name) {
        String sql = "SELECT id, name FROM products WHERE name = ?";
        return GeneralDAO.executeQueryWithSingleTable(sql, Product.class, name);
    }

    //Update
    public void updateProduct(Product product, int id) {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE products ")
                .append("SET ").append(updatedFieldProduct(product))
                .append(" WHERE id = ?");
        GeneralDAO.executeAllTypeUpdate(sql.toString(), id);
    }

    private String updatedFieldProduct(Product product) {
        StringBuilder updatedField = new StringBuilder();
        Map<String, String> updatedFieldMap = new HashMap<>();
        if (product.getName() != null)
            updatedFieldMap.put("name", "\"" + product.getName() + "\"");
        if (product.getCategoryId() != 0)
            updatedFieldMap.put("categoryId", product.getCategoryId() + "");
        if (product.getDescription() != null)
            updatedFieldMap.put("description", "\"" + product.getDescription() + "\"");
        if (product.getOriginalPrice() != -1)
            updatedFieldMap.put("originalPrice", product.getOriginalPrice() + "");
        if (product.getSalePrice() != -1)
            updatedFieldMap.put("salePrice", product.getSalePrice() + "");
        if (product.getCreateAt() != null)
            updatedFieldMap.put("createAt", "\"" + product.getCreateAt().toString() + "\"");

        for (Map.Entry<String, String> entry : updatedFieldMap.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            updatedField.append(key).append(" = ").append(value).append(",");
        }
        return updatedField.toString().substring(0, updatedField.toString().length() - 1);
    }
}
