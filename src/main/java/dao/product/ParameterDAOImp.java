package dao.product;

import annotations.LogTable;
import dao.general.GeneralDAO;
import models.Parameter;

import java.util.List;

@LogTable(LogTable.PRODUCT)
public class ParameterDAOImp implements IParameterDAO {
    @Override
    public <T> int insert(T o) {
        if(o instanceof Parameter) {
            Parameter parameter = (Parameter) o;
            String query = "INSERT INTO parameters (name, minValue, `maxValue`, unit, categoryId, guideImg) VALUES (?, ?, ?, ?, ?, ?) ";
            GeneralDAO.executeAllTypeUpdate(query, parameter.getName(), parameter.getMinValue(), parameter.getMaxValue(), parameter.getUnit(), parameter.getCategoryId(), parameter.getGuideImg());
            return 1;
        } else {
            throw new UnsupportedOperationException("ParameterDAOImp >> Phương thức thêm không hỗ trợ tham số kiểu khác");
        }
    }

    @Override
    public int update(Object o) {
        if(o instanceof Parameter) {
            Parameter parameter = (Parameter) o;
            StringBuilder sql = new StringBuilder();
            if (parameter.getGuideImg() == null) {
                sql.append("UPDATE parameters SET name = ?, minValue = ?, `maxValue` = ?, unit = ? WHERE id = ?");
                GeneralDAO.executeAllTypeUpdate(sql.toString(), parameter.getName(), parameter.getMinValue(), parameter.getMaxValue(), parameter.getUnit(), parameter.getId());
            } else {
                sql.append("UPDATE parameters SET name = ?, minValue = ?, `maxValue` = ?, unit = ?, guideImg = ? WHERE id = ?");
                GeneralDAO.executeAllTypeUpdate(sql.toString(), parameter.getName(), parameter.getMinValue(), parameter.getMaxValue(), parameter.getUnit(), parameter.getGuideImg(), parameter.getId());
            }
            return 1;
        } else {
            throw new UnsupportedOperationException("ParameterDAOImp >> Phương thức thêm không hỗ trợ tham số kiểu khác");
        }
    }

    @Override
    public List<Parameter> getParameterByCategoryId(int id) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT id, name, minValue, `maxValue`, unit, guideImg ")
                .append("FROM parameters ")
                .append("WHERE categoryId = ?");
        return GeneralDAO.executeQueryWithSingleTable(sql.toString(), Parameter.class, id);
    }

    @Override
    public List<Parameter> getParameterByCategoryId(int id, boolean orderById) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT id, name, minValue, `maxValue`, unit, guideImg ")
                .append("FROM parameters ")
                .append("WHERE categoryId = ?");
        if (orderById) sql.append(" ORDER BY id ASC");
        else sql.append(" ORDER BY name DESC");
        return GeneralDAO.executeQueryWithSingleTable(sql.toString(), Parameter.class, id);
    }

    @Override
    public void deleteParameter(int id) {
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM parameters WHERE id = ?");
        GeneralDAO.executeAllTypeUpdate(sql.toString(), id);
    }
}
