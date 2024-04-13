package dao.product;

import annotations.LogTable;
import dao.general.GeneralDAOImp;
import models.Product;
import models.Size;

import java.util.List;

@LogTable(LogTable.PRODUCT)
public class SizeDAOImp implements ISizeDAO {
    public List<Size> getAllSize() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT DISTINCT nameSize").append(" FROM sizes");
        return GeneralDAOImp.executeQueryWithSingleTable(sql.toString(), Size.class);
    }

    public List<Product> getIdProduct(String size) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT productId ").append(" FROM sizes ").append("WHERE nameSize = ?");
        return GeneralDAOImp.executeQueryWithSingleTable(sql.toString(), Product.class, size);
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
        GeneralDAOImp.executeAllTypeUpdate(sql.toString());
    }

    public List<Size> getIdSizeByProductId(int productId) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT id FROM sizes WHERE productId = ?");
        return GeneralDAOImp.executeQueryWithSingleTable(sql.toString(), Size.class, productId);
    }

    public void updateSize(Size size, int id) {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE sizes ")
                .append("SET ")
                .append(" nameSize = ? ,")
                .append(" sizePrice = ? ")
                .append(" WHERE id = ? ");
        GeneralDAOImp.executeAllTypeUpdate(sql.toString(), size.getNameSize(), size.getNameSize(), id);
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
        GeneralDAOImp.executeAllTypeUpdate(sql.toString());
    }

    @Override
    public Object getModelById(Object id) {
        return null;
    }
}
