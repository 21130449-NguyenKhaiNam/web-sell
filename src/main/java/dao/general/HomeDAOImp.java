package dao.general;

import annotations.LogTable;
import models.*;

import java.time.LocalDate;
import java.util.List;

@LogTable(LogTable.PRODUCT)
public class HomeDAOImp implements IHomeDAO {
    @Override
    public List<Slider> getListSlideShow() {
        String query = "SELECT nameSlide, nameImage FROM sliders WHERE visibility = 1";
        return GeneralDAO.executeQueryWithSingleTable(query, Slider.class);
    }

    @Override
    public List<Product> getListNewProducts(boolean isSeeMore) {
        String now = LocalDate.now().toString();
        StringBuilder sql = new StringBuilder("SELECT id, `name`, `description`, salePrice, originalPrice FROM products");
        sql.append(" WHERE visibility = 1 AND createAt >= DATE_SUB('" + now + "', INTERVAL 1 MONTH)");
        if (!isSeeMore) {
            sql.append(" LIMIT 6");
        }
        return GeneralDAO.executeQueryWithSingleTable(sql.toString(), Product.class);
    }

    @Override
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
        return GeneralDAO.executeQueryWithSingleTable(sql.toString(), Product.class, 10);
    }
}
