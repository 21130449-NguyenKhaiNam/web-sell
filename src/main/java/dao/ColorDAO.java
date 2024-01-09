package dao;

import models.Color;
import models.Size;

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

    public List<Color> getIdColorByProductId(int productId) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT id FROM colors WHERE productId = ?");
        return GeneralDao.executeQueryWithSingleTable(sql.toString(), Color.class, productId);
    }

    public void updateColor(Color color, int id) {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE colors ")
                .append("SET ")
                .append(" codeColor = ? ")
                .append(" WHERE id = ? ");
        GeneralDao.executeAllTypeUpdate(sql.toString(), color.getCodeColor(), id);
    }

    public void deleteColorList(List<Integer> listIdDelete) {
    }
}
