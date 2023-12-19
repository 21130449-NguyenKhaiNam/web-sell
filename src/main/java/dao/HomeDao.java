package dao;

import models.*;

import java.util.List;

public class HomeDao {
    public List<Slider> getListSlideShow() {
        return GeneralDao.executeQueryWithSingleTable("SELECT nameSlide, nameImage FROM sliders WHERE visibility = 1", Slider.class);
    }
//    public List<Map<String, Object>> getListTrendingProducts(boolean isSeeMore) {
//        StringBuilder sql = new StringBuilder("SELECT products.id, products.`name`, products.salePrice, products.originalPrice, images.nameImage FROM products");
//        sql.append(" INNER JOIN order_details ON products.id = order_details.productId INNER JOIN images ON products.id = images.productId");
//        sql.append(" WHERE products.visibility = 1");
//        sql.append(" GROUP BY products.id, products.`name`, products.salePrice, products.originalPrice, images.nameImage");
//        sql.append(" HAVING SUM(order_details.quantityRequired) >= ?");
//        sql.append(" ORDER BY SUM(order_details.quantityRequired) DESC");
//        if(!isSeeMore){
//            sql.append(" LIMIT 6");
//        }
//        return GeneralDao.executeQueryWithJoinTables(sql.toString(), 10);
//    }

//    public List<Map<String, Object>> getListNewProducts(boolean isSeeMore) {
//        StringBuilder sql = new StringBuilder("SELECT products.id, products.`name`, products.salePrice, products.originalPrice, images.nameImage FROM products");
//        sql.append(" INNER JOIN images ON products.id = images.productId");
//        sql.append(" WHERE products.visibility = 1 AND products.createAt >= DATE_SUB('2023-12-01', INTERVAL 1 MONTH)");
//        if (!isSeeMore) {
//            sql.append(" LIMIT 6");
//        }
//        return GeneralDao.executeQueryWithJoinTables(sql.toString());
//    }

    public static List<Product> getListNewProducts(boolean isSeeMore) {
        StringBuilder sql = new StringBuilder("SELECT id, `name`, `description`, salePrice, originalPrice FROM products");
        sql.append(" WHERE visibility = 1 AND createAt >= DATE_SUB('2023-12-01', INTERVAL 1 MONTH)");
        if (!isSeeMore) {
            sql.append(" LIMIT 6");
        }
        return GeneralDao.executeQueryWithSingleTable(sql.toString(), Product.class);
    }

    public List<Product> getListTrendProducts(boolean isSeeMore){
        StringBuilder sql = new StringBuilder("SELECT products.id, products.`name`, products.`description`, products.salePrice, products.originalPrice FROM products");
        sql.append(" INNER JOIN order_details ON products.id = order_details.productId");
        sql.append(" WHERE products.visibility = 1");
        sql.append(" GROUP BY products.id, products.`name`, products.salePrice, products.originalPrice");
        sql.append(" HAVING SUM(order_details.quantityRequired) >= ?");
        sql.append(" ORDER BY SUM(order_details.quantityRequired) DESC");
        if(!isSeeMore){
            sql.append(" LIMIT 6");
        }
        return GeneralDao.executeQueryWithSingleTable(sql.toString(), Product.class, 10);
    }
}
