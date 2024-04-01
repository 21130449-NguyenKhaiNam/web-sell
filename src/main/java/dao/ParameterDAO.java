package dao;

import models.Parameter;

import java.util.List;

public class ParameterDAO {
    public List<Parameter> getParameterByCategoryId(int id) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT id, name, minValue, `maxValue`, unit, guideImg ")
                .append("FROM parameters ")
                .append("WHERE categoryId = ?");
        return GeneralDAO.executeQueryWithSingleTable(sql.toString(), Parameter.class, id);
    }

    public List<Parameter> getParameterByCategoryId(int id, boolean orderById) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT id, name, minValue, `maxValue`, unit, guideImg ")
                .append("FROM parameters ")
                .append("WHERE categoryId = ?");
        if (orderById) sql.append(" ORDER BY id ASC");
        else sql.append(" ORDER BY name DESC");
        return GeneralDAO.executeQueryWithSingleTable(sql.toString(), Parameter.class, id);
    }

    public void updateParameter(Parameter parameter) {
        StringBuilder sql = new StringBuilder();
        if (parameter.getGuideImg() == null) {
            sql.append("UPDATE parameters SET name = ?, minValue = ?, `maxValue` = ?, unit = ? WHERE id = ?");
            GeneralDAO.executeAllTypeUpdate(sql.toString(), parameter.getName(), parameter.getMinValue(), parameter.getMaxValue(), parameter.getUnit(), parameter.getId());
        } else {
            sql.append("UPDATE parameters SET name = ?, minValue = ?, `maxValue` = ?, unit = ?, guideImg = ? WHERE id = ?");
            GeneralDAO.executeAllTypeUpdate(sql.toString(), parameter.getName(), parameter.getMinValue(), parameter.getMaxValue(), parameter.getUnit(), parameter.getGuideImg(), parameter.getId());
        }
    }

    public void addParameter(Parameter parameter) {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO parameters (name, minValue, `maxValue`, unit, categoryId, guideImg) VALUES (?, ?, ?, ?, ?, ?) ");
        GeneralDAO.executeAllTypeUpdate(sql.toString(), parameter.getName(), parameter.getMinValue(), parameter.getMaxValue(), parameter.getUnit(), parameter.getCategoryId(), parameter.getGuideImg());
    }

    public void deleteParameter(int id) {
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM parameters WHERE id = ?");
        GeneralDAO.executeAllTypeUpdate(sql.toString(), id);
    }
}
