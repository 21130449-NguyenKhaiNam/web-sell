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

    public void addSizes(Size[] sizes) {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO sizes (nameSize, productId, sizePrice) ")
                .append("VALUES ");
        for (int i = 0; i < sizes.length; i++) {
            if (i != 0)
                sql.append(" , ");
            sql.append(" (\"")
                    .append(sizes[i].getNameSize())
                    .append("\" , ")
                    .append(sizes[i].getProductId())
                    .append(", ")
                    .append(sizes[i].getSizePrice())
                    .append(") ");
        }
        GeneralDao.executeAllTypeUpdate(sql.toString());
    }
}
