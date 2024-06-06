package dao;

import models.Product;
import models.Slider;

import java.util.Arrays;
import java.util.List;

public class HomeDao {

    public static List<Product> getListNewProducts(boolean isSeeMore) {
        StringBuilder sql = new StringBuilder("SELECT id, `name`, `description`, salePrice, originalPrice FROM products");
        sql.append(" WHERE visibility = 1 AND createAt >= DATE_SUB('2023-12-01', INTERVAL 1 MONTH)");
        if (!isSeeMore) {
            sql.append(" LIMIT 6");
        }
        return GeneralDao.executeQueryWithSingleTable(sql.toString(), Product.class);
    }

    public List<Slider> getListSlideShow() {
        return GeneralDao.executeQueryWithSingleTable("SELECT nameSlide, nameImage FROM sliders WHERE visibility = 1", Slider.class);
    }

    public List<Product> getListTrendProducts(boolean isSeeMore) {
        StringBuilder sql = new StringBuilder("SELECT products.id, products.`name`, products.`description`, products.salePrice, products.originalPrice FROM products");
        sql.append(" INNER JOIN order_details ON products.id = order_details.productId");
        sql.append(" WHERE products.visibility = 1");
        sql.append(" GROUP BY products.id, products.`name`, products.salePrice, products.originalPrice");
        sql.append(" HAVING SUM(order_details.quantityRequired) >= ?");
        sql.append(" ORDER BY SUM(order_details.quantityRequired) DESC");
        if (!isSeeMore) {
            sql.append(" LIMIT 6");
        }
        return GeneralDao.executeQueryWithSingleTable(sql.toString(), Product.class, 10);
    }
}
