package dao;

import models.Category;
import models.Parameter;

import java.util.List;

public interface ICategoryDAO extends IDAO {
    //    Lấy ra danh sách tất cả các thể loại
    List<Category> getAllCategory();

    //    Thêm mới một thể loại
    void add(Category category);

    //    Lấy ra danh sách thể loại theo tên thể loại
    List<Category> getCategoryByNameType(String nameType);

    //    Thêm mới một thông số (id của thể loại được set vào properties trong thông số)
    void addParameter(Parameter parameter);

    //    Lấy ra danh sách các thể loại dựa theo id thể loại
    List<Category> getCategoryById(int id);

    //    Cập nhập thể loại
    void updateCategory( Category category);
}
