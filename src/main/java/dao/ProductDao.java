package dao;

import models.Color;
import models.Image;
import models.Product;
import models.Size;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductDao {

    public List<Image> getListImagesByProductId(int productId) {
        String sql = "SELECT id, nameImage, productId FROM images WHERE productId = ?";
        return GeneralDao.executeQueryWithSingleTable(sql, Image.class, productId);
    }


    public List<Color> getListColorsByProductId(int productId) {
        String sql = "SELECT id, codeColor, productId FROM colors WHERE productId = ?";
        return GeneralDao.executeQueryWithSingleTable(sql, Color.class, productId);
    }

    public List<Size> getListSizesByProductId(int productId) {
        String sql = "SELECT id, nameSize, productId, sizePrice FROM sizes WHERE productId = ?";
        return GeneralDao.executeQueryWithSingleTable(sql, Size.class, productId);
    }

    public double getPriceSizeByName(String nameSize, int productId) {
        String sql = "SELECT sizePrice FROM sizes WHERE nameSize = ? AND productId = ?";
        return GeneralDao.executeQueryWithSingleTable(sql, Size.class, nameSize, productId).get(0).getSizePrice();
    }

    public Product getProductByProductId(int productId) {
        String sql = "SELECT id, `name`, categoryId, `description`, salePrice, originalPrice FROM products WHERE id = ?";
        return GeneralDao.executeQueryWithSingleTable(sql, Product.class, productId).get(0);
    }

    public Size getSizeByNameSizeWithProductId(String nameSize, int productId) {
        String sql = "SELECT id, sizePrice, nameSize FROM sizes WHERE nameSize = ? AND productId = ?";
        if (GeneralDao.executeQueryWithSingleTable(sql, Size.class, nameSize, productId).isEmpty()) return null;

        return GeneralDao.executeQueryWithSingleTable(sql, Size.class, nameSize, productId).get(0);
    }

    public Image getImageByNameImageWithProductId(String nameImage, int productId) {
        String sql = "SELECT id, nameImage, productId FROM images WHERE nameImage = ? AND productId = ?";
        if (GeneralDao.executeQueryWithSingleTable(sql, Image.class, nameImage, productId).isEmpty()) return null;

        return GeneralDao.executeQueryWithSingleTable(sql, Image.class, nameImage, productId).get(0);
    }

    public Color getColorByCodeColorWithProductId(String codeColor, int productId) {
        String sql = "SELECT id, codeColor FROM colors WHERE codeColor = ? AND productId = ?";
        if (GeneralDao.executeQueryWithSingleTable(sql, Color.class, codeColor, productId).isEmpty()) return null;

        return GeneralDao.executeQueryWithSingleTable(sql, Color.class, codeColor, productId).get(0);
    }

    public List<Product> getIdProductByName(String name) {
        String sql = "SELECT id, name FROM products WHERE name = ?";
        return GeneralDao.executeQueryWithSingleTable(sql, Product.class, name);
    }

    // Insert
    public int addProduct(Product product) {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO products (name, categoryId, description, originalPrice, salePrice, visibility, createAt) ")
                .append("VALUES (?,?,?,?,?,?,?) ");
        return GeneralDao.executeInsert(sql.toString(), product.getName(), product.getCategoryId(), product.getDescription(), product.getOriginalPrice(), product.getSalePrice(), product.isVisibility(), product.getCreateAt());
    }

    //Update
    public void updateProduct(Product product) {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE products ")
                .append("SET ").append(updatedFieldProduct(product))
                .append(" WHERE id = ?");
        GeneralDao.executeAllTypeUpdate(sql.toString(), product.getId());
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

    public Product getMaxId() {
        StringBuilder sql = new StringBuilder();
        sql.append("select id from products order by id DESC LIMIT 1");
        return GeneralDao.executeQueryWithSingleTable(sql.toString(), Product.class).get(0);
    }

    public Product getProductByMultipleParam(String name, int categoryId, String des, double originalPrice, double salePrice) {
        StringBuilder sql = new StringBuilder();
        sql.append("select id from products").append(" where name = ? and categoryId = ? and description = ? and originalPrice = ? and salePrice = ?");
        if (GeneralDao.executeQueryWithSingleTable(sql.toString(), Product.class, name, categoryId, des, originalPrice, salePrice).isEmpty())
            return null;
        return GeneralDao.executeQueryWithSingleTable(sql.toString(), Product.class, name, categoryId, des, originalPrice, salePrice).get(0);
    }


    public List<Product> getLimit(int limit, int offset) {
        String sql = "select id, name, categoryId, description, originalPrice, salePrice, visibility, createAt from products limit ? offset ?";
        return GeneralDao.executeQueryWithSingleTable(sql, Product.class, limit, offset);
    }
}
