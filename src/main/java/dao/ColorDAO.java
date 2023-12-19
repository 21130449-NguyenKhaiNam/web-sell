package dao;

import models.Color;

import java.util.List;

public class ColorDAO {
    public List<Color> getAllColor() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT DISTINCT codeColor ").append("FROM colors");
        return GeneralDao.executeQueryWithSingleTable(sql.toString(), Color.class);
    }
}
