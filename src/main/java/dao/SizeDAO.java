package dao;

import models.Product;
import models.Size;

import java.util.List;

public class SizeDAO {
    public List<Size> getAllSize() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT DISTINCT nameSize").append(" FROM sizes");
        return GeneralDao.executeQueryWithSingleTable(sql.toString(), Size.class);
    }

    public List<Product> getIdProduct(String size) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT productId ").append(" FROM sizes ").append("WHERE nameSize = ?");
        return GeneralDao.executeQueryWithSingleTable(sql.toString(), Product.class, size);
    }
}
