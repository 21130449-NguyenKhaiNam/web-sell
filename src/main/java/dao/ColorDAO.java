package dao;

import models.Color;

import java.util.ArrayList;
import java.util.List;

public class ColorDAO {
    public List<Color> getAllColor() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT DISTINCT codeColor ").append("FROM colors");
        return GeneralDao.executeQueryWithSingleTable(sql.toString(), Color.class);
    }

    public void addColors(Color[] colors) {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO colors (codeColor, productId) ")
                .append("VALUES ");
        for (int i = 0; i < colors.length; i++) {
            if (i != 0)
                sql.append(" , ");
            sql.append(" (\"")
                    .append(colors[i].getCodeColor())
                    .append("\"")
                    .append(", ")
                    .append(colors[i].getProductId()).append(") ");
        }
        GeneralDao.executeAllTypeUpdate(sql.toString());
    }
}
