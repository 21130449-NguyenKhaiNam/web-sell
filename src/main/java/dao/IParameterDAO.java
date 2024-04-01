package dao;

import models.Parameter;

import java.util.List;

public interface IParameterDAO {
    List<Parameter> getParameterByCategoryId(int id);
    List<Parameter> getParameterByCategoryId(int id, boolean orderById);
    void updateParameter(Parameter parameter);
    void addParameter(Parameter parameter);
    void deleteParameter(int id);
}
