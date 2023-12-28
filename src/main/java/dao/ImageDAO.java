package dao;

import models.Image;

import java.util.List;

public class ImageDAO  {
    public List<Image> getThumbnail(int productId) {
        String sql = "SELECT nameImage FROM images WHERE productId = ? AND isThumbnail = 1";
        return GeneralDao.executeQueryWithSingleTable(sql, Image.class, productId);
    }

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
        GeneralDao.executeAllTypeUpdate(sql.toString());
    }
}
