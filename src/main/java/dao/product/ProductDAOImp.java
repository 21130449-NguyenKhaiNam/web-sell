package dao.product;

import annotations.LogTable;
import dao.general.GeneralDAO;
import models.*;
import utils.MoneyRange;

import java.sql.Date;
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

    public List<Product> getProducts(int pageNumber, int limit, boolean visibility) {
        int offset = (pageNumber - 1) * limit;
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT id, `name`, categoryId, originalPrice, salePrice ")
                .append("FROM products ")
                .append("WHERE visibility = ? ")
                .append("LIMIT ")
                .append(limit)
                .append(" OFFSET ")
                .append(offset);

        List<Product> list = GeneralDAO.executeQueryWithSingleTable(sql.toString(), Product.class, visibility);
        return list;
    }

    public List<Product> getProducts(int pageNumber, int limit) {
        int offset = (pageNumber - 1) * limit;
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT id, `name`, categoryId, originalPrice, salePrice, visibility ")
                .append("FROM products ")
                .append("LIMIT ")
                .append(limit)
                .append(" OFFSET ")
                .append(offset);

        List<Product> list = GeneralDAO.executeQueryWithSingleTable(sql.toString(), Product.class);
        return list;
    }


    public int getQuantityProduct() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT id FROM products ");
        return GeneralDAO.executeQueryWithSingleTable(sql.toString(), Product.class).size();
    }

    public int getQuantityProduct(boolean visibility) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT id FROM products where visibility = ?");
        return GeneralDAO.executeQueryWithSingleTable(sql.toString(), Product.class, visibility).size();
    }

    public int getQuantityProduct(List<Integer> listId, boolean visibility) {
        StringBuilder listIdString = new StringBuilder();
        if (listId != null && !listId.isEmpty()) {
            listIdString.append(" AND id IN (");
            for (int i = 0; i < listId.size(); i++) {
                if (i == 0)
                    listIdString.append(listId.get(i));
                listIdString.append(", ").append(listId.get(i));
            }
            listIdString.append(")");
        }
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT id ")
                .append("FROM products ")
                .append("WHERE visibility = ?")
                .append(listIdString);

        return GeneralDAO.executeQueryWithSingleTable(sql.toString(), Product.class, visibility).size();
    }

    public int getQuantityProduct(List<Integer> listId) {
        StringBuilder listIdString = new StringBuilder();
        if (listId != null && !listId.isEmpty()) {
            listIdString.append("WHERE id IN (");
            for (int i = 0; i < listId.size(); i++) {
                if (i == 0)
                    listIdString.append(listId.get(i));
                listIdString.append(", ").append(listId.get(i));
            }
            listIdString.append(")");
        }
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT id ")
                .append("FROM products ")
                .append(listIdString);
        return GeneralDAO.executeQueryWithSingleTable(sql.toString(), Product.class).size();
    }

    public List<Product> pagingAndFilter(List<Integer> listId, int pageNumber, int limit) {
        int offset = (pageNumber - 1) * limit;
        StringBuilder listIdString = new StringBuilder();
        if (listId != null && !listId.isEmpty()) {
            listIdString.append("WHERE id IN (");
            for (int i = 0; i < listId.size(); i++) {
                if (i == 0)
                    listIdString.append(listId.get(i));
                listIdString.append(", ").append(listId.get(i));
            }
            listIdString.append(")");
        }
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT id, `name`, originalPrice, salePrice, visibility ")
                .append("FROM products ")
                .append(listIdString)
                .append(" LIMIT ")
                .append(limit)
                .append(" OFFSET ")
                .append(offset);
        return GeneralDAO.executeQueryWithSingleTable(sql.toString(), Product.class);
    }

    public List<Product> pagingAndFilter(List<Integer> listId, int pageNumber, int limit, boolean visibility) {
        int offset = (pageNumber - 1) * limit;
        StringBuilder listIdString = new StringBuilder();
        if (listId != null && !listId.isEmpty()) {
            listIdString.append("AND id IN (");
            for (int i = 0; i < listId.size(); i++) {
                if (i == 0)
                    listIdString.append(listId.get(i));
                listIdString.append(", ").append(listId.get(i));
            }
            listIdString.append(")");
        }
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT id, `name`, originalPrice, salePrice ")
                .append("FROM products ")
                .append("WHERE visibility = ? ")
                .append(listIdString)
                .append(" LIMIT ")
                .append(limit)
                .append(" OFFSET ")
                .append(offset);
        return GeneralDAO.executeQueryWithSingleTable(sql.toString(), Product.class, visibility);
    }

    public List<Product> getIdProductByCategoryId(List<String> listIdCategory) {
        StringBuilder categoryIdQuery = new StringBuilder();
        if (!listIdCategory.isEmpty()) {
            categoryIdQuery.append(" categoryId IN (");
            for (int i = 0; i < listIdCategory.size(); i++) {
                if (i != 0) {
                    categoryIdQuery.append(", ");
                }
                categoryIdQuery.append(listIdCategory.get(i));
            }
            categoryIdQuery.append(")");
        }
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT id ")
                .append("FROM products  ")
                .append("WHERE ")
                .append(categoryIdQuery);
        return GeneralDAO.executeQueryWithSingleTable(sql.toString(), Product.class);
    }

    public List<Product> getIdProductByColor(List<String> listCodeColor) {
        StringBuilder colorQuery = new StringBuilder();
        if (!listCodeColor.isEmpty()) {
            colorQuery.append(" colors.codeColor IN (");
            for (int i = 0; i < listCodeColor.size(); i++) {
                if (i != 0) {
                    colorQuery.append(", ");
                }
                colorQuery.append("\'")
                        .append(listCodeColor.get(i))
                        .append("\'");
            }
            colorQuery.append(")");
        }
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT products.id ")
                .append("FROM products JOIN colors ON products.id = colors.productId ")
                .append("WHERE ")
                .append(colorQuery);
        return GeneralDAO.executeQueryWithSingleTable(sql.toString(), Product.class);
    }

    public List<Product> getIdProductBySize(List<String> listSize) {
        StringBuilder sizeQuery = new StringBuilder();
        if (!listSize.isEmpty()) {
            sizeQuery.append(" sizes.nameSize IN (");
            for (int i = 0; i < listSize.size(); i++) {
                if (i != 0) {
                    sizeQuery.append(", ");
                }
                sizeQuery.append("\'")
                        .append(listSize.get(i))
                        .append("\'");
            }
            sizeQuery.append(")");
        }
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT products.id ")
                .append("FROM products JOIN sizes ON products.id = sizes.productId ")
                .append("WHERE ")
                .append(sizeQuery);
        return GeneralDAO.executeQueryWithSingleTable(sql.toString(), Product.class);
    }


    public List<Product> getIdProductByMoneyRange(List<MoneyRange> moneyRangeList) {
        StringBuilder moneyRangeQuery = new StringBuilder();
        for (int i = 0; i < moneyRangeList.size(); i++) {
            if (i != 0) {
                moneyRangeQuery.append(" OR ");
            }
            moneyRangeQuery.append("( originalPrice BETWEEN ")
                    .append(moneyRangeList.get(i).getFrom()).
                    append(" AND ").append(moneyRangeList.get(i).getTo())
                    .append(" )");
        }
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT id ")
                .append("FROM products ")
                .append("WHERE ")
                .append(moneyRangeQuery);
        return GeneralDAO.executeQueryWithSingleTable(sql.toString(), Product.class);
    }

    public List<Product> getProductByCategoryId(int categoryId) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT id, `name`, originalPrice, salePrice ")
                .append("FROM products  ")
                .append("WHERE categoryId = ?");
        return GeneralDAO.executeQueryWithSingleTable(sql.toString(), Product.class, categoryId);
    }

    public List<Product> getIdProductByName(String name) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT id ").append("FROM products ").append("WHERE name LIKE ?");
        return GeneralDAO.executeQueryWithSingleTable(sql.toString(), Product.class, "%" + name + "%");
    }

    public List<Product> getProductByTimeCreated(Date dateBegin, Date dateEnd) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT id ").append("FROM products ").append("WHERE createAt BETWEEN ? AND ? ");
        return GeneralDAO.executeQueryWithSingleTable(sql.toString(), Product.class, dateBegin, dateEnd);
    }

    public List<Category> getNameCategoryById(int id) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT categories.nameType ").append("FROM products JOIN categories ON products.categoryId = categories.id ")
                .append("WHERE products.id = ?");
        return GeneralDAO.executeQueryWithSingleTable(sql.toString(), Category.class, id);
    }

    public List<Category> getCategoryByProductId(int id) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT nameType, sizeTableImage ")
                .append("FROM categories JOIN products ON products.categoryId = categories.id ")
                .append("WHERE products.id = ?");
        return GeneralDAO.executeQueryWithSingleTable(sql.toString(), Category.class, id);
    }

    public List<Parameter> getParametersByProductId(int id) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT parameters.name, parameters.minValue, parameters.maxValue, parameters.unit, parameters.guideImg ")
                .append("FROM products JOIN (parameters JOIN categories ON parameters.categoryId = categories.id) ON products.categoryId = categories.id ")
                .append("WHERE products.id = ?");
        return GeneralDAO.executeQueryWithSingleTable(sql.toString(), Parameter.class, id);
    }

    public List<Product> getNameProductById(int id) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT name ")
                .append("FROM products ")
                .append("WHERE products.id = ?");
        return GeneralDAO.executeQueryWithSingleTable(sql.toString(), Product.class, id);
    }

    public List<Product> isVisibility(int id) {
        StringBuilder sql = new StringBuilder("SELECT visibility FROM products WHERE id = ?");
        return GeneralDAO.executeQueryWithSingleTable(sql.toString(), Product.class, id);
    }

    public void updateVisibility(int productId, boolean visibility) {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE products ").append("SET visibility = ? ").append("WHERE id = ?");
        GeneralDAO.executeAllTypeUpdate(sql.toString(), visibility, productId);
    }

    public List<Product> getNameProductByIdOrderDetail(int orderDetailId) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT productName AS name ")
                .append("FROM order_details ")
                .append("WHERE id = ?");
        return GeneralDAO.executeQueryWithSingleTable(sql.toString(), Product.class, orderDetailId);
    }
}
