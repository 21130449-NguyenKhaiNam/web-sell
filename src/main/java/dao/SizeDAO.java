package dao;

import models.Product;
import models.Size;

import java.sql.SQLException;
import java.util.ArrayList;
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
        List<Object> params = new ArrayList<>();
        sql.append("INSERT INTO sizes (nameSize, productId, sizePrice) ")
                .append("VALUES ");
        for (int i = 0; i < sizes.length; i++) {
            if (i != 0)
                sql.append(" , ");
            sql.append("( ?, ?, ? )");
            params.add(sizes[i].getNameSize());
            params.add(sizes[i].getProductId());
            params.add(sizes[i].getSizePrice());
        }
        GeneralDao.executeAllTypeUpdate(sql.toString(), params.toArray());
    }

    public List<Size> getIdSizeByProductId(int productId) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT id FROM sizes WHERE productId = ?");
        return GeneralDao.executeQueryWithSingleTable(sql.toString(), Size.class, productId);
    }

    public void updateSize(Size size, int id) {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE sizes ")
                .append("SET ")
                .append(" nameSize = ? ,")
                .append(" sizePrice = ? ")
                .append(" WHERE id = ? ");
        GeneralDao.executeAllTypeUpdate(sql.toString(), size.getNameSize(), size.getSizePrice(), id);
    }

    public void deleteSizeList(List<Integer> listId) {
        StringBuilder idRange = new StringBuilder();
        if (listId.size() == 1) idRange.append(listId.get(0));
        else
            for (int i = 0; i < listId.size(); i++) {
                idRange.append(listId.get(i));
                if (i != listId.size() - 1) {
                    idRange.append(" , ");
                }
            }
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM sizes ").append("WHERE id IN (").append(idRange).append(")");
        GeneralDao.executeAllTypeUpdate(sql.toString());
    }
}
