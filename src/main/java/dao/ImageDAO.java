package dao;

import models.Image;

import java.util.List;

public class ImageDAO  {
    public List<Image> getThumbnail(int productId) {
        String sql = "SELECT nameImage FROM images WHERE productId = ? AND isThumbnail = 1";
        return GeneralDao.executeQueryWithSingleTable(sql, Image.class, productId);
    }
}
