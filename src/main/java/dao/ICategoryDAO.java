package dao;

import annotations.LogParam;
import annotations.WriteLog;
import models.Category;
import models.Parameter;

import java.util.List;

public interface ICategoryDAO extends IDAO {
    List<Category> getAllCategory();

    @WriteLog(WriteLog.INSERT)
    void add(@LogParam("category") Category category);

    @WriteLog(WriteLog.SELECT)
    List<Category> getCategoryByNameType(@LogParam("name-type") String nameType);

    @WriteLog(WriteLog.UPDATE)
    void addParameter(@LogParam("parameter") Parameter parameter);

    List<Category> getCategoryById(int id);

    void updateCategory(Category category);
}
