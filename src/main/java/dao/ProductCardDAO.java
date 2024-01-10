package dao;

import models.Category;
import models.Parameter;
import models.Product;
import utils.MoneyRange;

import java.sql.Date;
import java.util.List;

public class ProductCardDAO {
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

        List<Product> list = GeneralDao.executeQueryWithSingleTable(sql.toString(), Product.class, visibility);
        return list;
    }

    public List<Product> getProducts(int pageNumber, int limit) {
        int offset = (pageNumber - 1) * limit;
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT id, `name`, categoryId, originalPrice, salePrice ")
                .append("FROM products ")
                .append("LIMIT ")
                .append(limit)
                .append(" OFFSET ")
                .append(offset);

        List<Product> list = GeneralDao.executeQueryWithSingleTable(sql.toString(), Product.class);
        return list;
    }


    public int getQuantityProduct() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT id FROM products ");
        return GeneralDao.executeQueryWithSingleTable(sql.toString(), Product.class).size();
    }

    public int getQuantityProduct(boolean visibility) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT id FROM products where visibility = ?");
        return GeneralDao.executeQueryWithSingleTable(sql.toString(), Product.class, visibility).size();
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

        return GeneralDao.executeQueryWithSingleTable(sql.toString(), Product.class, visibility).size();
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
        return GeneralDao.executeQueryWithSingleTable(sql.toString(), Product.class).size();
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
        return GeneralDao.executeQueryWithSingleTable(sql.toString(), Product.class);
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
        return GeneralDao.executeQueryWithSingleTable(sql.toString(), Product.class, visibility);
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
        return GeneralDao.executeQueryWithSingleTable(sql.toString(), Product.class);
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
        return GeneralDao.executeQueryWithSingleTable(sql.toString(), Product.class);
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
        return GeneralDao.executeQueryWithSingleTable(sql.toString(), Product.class);
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
        return GeneralDao.executeQueryWithSingleTable(sql.toString(), Product.class);
    }

    public List<Product> getProductByCategoryId(int categoryId) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT id, `name`, originalPrice, salePrice ")
                .append("FROM products  ")
                .append("WHERE categoryId = ?");
        return GeneralDao.executeQueryWithSingleTable(sql.toString(), Product.class, categoryId);
    }

    public List<Product> getIdProductByName(String name) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT id ").append("FROM products ").append("WHERE name LIKE ?");
        return GeneralDao.executeQueryWithSingleTable(sql.toString(), Product.class, "%" + name + "%");
    }

    public List<Product> getProductByTimeCreated(Date dateBegin, Date dateEnd) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT id ").append("FROM products ").append("WHERE createAt BETWEEN ? AND ? ");
        return GeneralDao.executeQueryWithSingleTable(sql.toString(), Product.class, dateBegin, dateEnd);
    }

    public List<Category> getNameCategoryById(int id) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT categories.nameType ").append("FROM products JOIN categories ON products.categoryId = categories.id ")
                .append("WHERE products.id = ?");
        return GeneralDao.executeQueryWithSingleTable(sql.toString(), Category.class, id);
    }

    public List<Category> getCategoryByProductId(int id) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT nameType, sizeTableImage ")
                .append("FROM categories JOIN products ON products.categoryId = categories.id ")
                .append("WHERE products.id = ?");
        return GeneralDao.executeQueryWithSingleTable(sql.toString(), Category.class, id);
    }

    public List<Parameter> getParametersByProductId(int id) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT parameters.name, parameters.minValue, parameters.maxValue, parameters.unit, parameters.guideImg ")
                .append("FROM products JOIN (parameters JOIN categories ON parameters.categoryId = categories.id) ON products.categoryId = categories.id ")
                .append("WHERE products.id = ?");
        return GeneralDao.executeQueryWithSingleTable(sql.toString(), Parameter.class, id);
    }

    public List<Product> getNameProductById(int id) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT name ")
                .append("FROM products ")
                .append("WHERE products.id = ?");
        return GeneralDao.executeQueryWithSingleTable(sql.toString(), Product.class, id);
    }
}
