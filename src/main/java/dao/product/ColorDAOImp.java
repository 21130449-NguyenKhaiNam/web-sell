package dao.product;

import annotations.LogTable;
import dao.general.GeneralDAOImp;
import models.Color;

import java.util.List;

@LogTable(LogTable.COLOR)
public class ColorDAOImp implements IColorDAO {
    @Override
    public List<Color> getAllColor() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT DISTINCT codeColor ").append("FROM colors");
        return GeneralDAOImp.executeQueryWithSingleTable(sql.toString(), Color.class);
    }

    @Override
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
        GeneralDAOImp.executeAllTypeUpdate(sql.toString());
    }

    @Override
    public List<Color> getIdColorByProductId(int productId) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT id FROM colors WHERE productId = ?");
        return GeneralDAOImp.executeQueryWithSingleTable(sql.toString(), Color.class, productId);
    }

    @Override
    public void updateColor(Color color, int id) {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE colors ")
                .append("SET ")
                .append(" codeColor = ? ")
                .append(" WHERE id = ? ");
        GeneralDAOImp.executeAllTypeUpdate(sql.toString(), color.getCodeColor(), id);
    }

}
