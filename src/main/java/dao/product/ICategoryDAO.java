package dao.product;

import annotations.LogParam;
import annotations.WriteLog;
import dao.IDAO;
import models.Category;
import models.Parameter;

import java.util.List;

public interface ICategoryDAO extends IDAO {
    //    Lấy ra danh sách tất cả các thể loại
    List<Category> getAllCategory();

    //    Lấy ra danh sách thể loại theo tên thể loại
    @WriteLog(WriteLog.SELECT)
    List<Category> getCategoryByNameType(@LogParam("name-type") String nameType);

    //    Thêm mới một thể loại
//    void add(@LogParam("category") Category category);

    //    Cập nhập thể loại
//    void updateCategory(@LogParam("category") Category category);

    //    Thêm mới một thông số (id của thể loại được set vào properties trong thông số)
//    void addParameter(@LogParam("parameter") Parameter parameter);

    //    Lấy ra danh sách các thể loại dựa theo id thể loại
//    List<Category> getCategoryById(int id);
}
