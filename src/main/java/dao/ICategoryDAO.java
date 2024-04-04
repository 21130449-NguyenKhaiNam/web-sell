package dao;

import annotations.LogParam;
import annotations.WriteLog;
import models.Category;
import models.Parameter;

import java.util.List;

public interface ICategoryDAO extends IDAO {
//    Lấy ra danh sách tất cả các thể loại
    List<Category> getAllCategory();

//    Thêm mới một thể loại
    @WriteLog(WriteLog.INSERT)
    void add(@LogParam("category") Category category);

//    Lấy ra danh sách thể loại theo tên thể loại
    @WriteLog(WriteLog.SELECT)
    List<Category> getCategoryByNameType(@LogParam("name-type") String nameType);

//    Thêm mới một thông số (id của thể loại được set vào properties trong thông số)
    @WriteLog(WriteLog.UPDATE)
    void addParameter(@LogParam("parameter") Parameter parameter);

//    Lấy ra danh sách các thể loại dựa theo id thể loại
    List<Category> getCategoryById(int id);

//    Cập nhập thể loại
    void updateCategory(Category category);
}
