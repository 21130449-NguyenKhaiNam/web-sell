package dao.product;

import annotations.LogTable;
import dao.general.GeneralDAO;
import models.Image;

import java.util.List;

@LogTable(LogTable.PRODUCT)
public class ImageDAOImp implements IImageDAO {
    @Override
    public List<Image> getThumbnail(int productId) {
        String sql = "SELECT nameImage FROM images WHERE productId = ? AND isThumbnail = 1";
        return GeneralDAO.executeQueryWithSingleTable(sql, Image.class, productId);
    }

    @Override
    public void addImages(List<Image> images) {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO images (nameImage, productId) ")
                .append("VALUES ");
        for (int i = 0; i < images.size(); i++) {
            if (i != 0) {
                sql.append(" , ");
            }
            sql.append(" (\"")
                    .append(images.get(i).getNameImage())
                    .append("\", ")
                    .append(images.get(i).getProductId()).append(") ");
        }
        System.out.println(sql);
        GeneralDAO.executeAllTypeUpdate(sql.toString());
    }

    @Override
    public List<Image> getNameImages(int productId) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT nameImage FROM images WHERE productId = ?");
        return GeneralDAO.executeQueryWithSingleTable(sql.toString(), Image.class, productId);
    }

    @Override
    public List<Image> getIdImages(int productId) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT id FROM images WHERE productId = ?");
        return GeneralDAO.executeQueryWithSingleTable(sql.toString(), Image.class, productId);
    }

    @Override
    public void deleteImages(List<Integer> nameImages) {
        StringBuilder idRange = new StringBuilder();
        if (nameImages.size() == 1) idRange.append(nameImages.get(0));
        else
            for (int i = 0; i < nameImages.size(); i++) {
                idRange.append(nameImages.get(i));
                if (i != nameImages.size() - 1) {
                    idRange.append(" , ");
                }
            }
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM images ").append("WHERE id IN (").append(idRange).append(")");
        GeneralDAO.executeAllTypeUpdate(sql.toString());
    }
}
