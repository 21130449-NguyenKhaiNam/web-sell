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
    List<Category> getCategoryByNameType(String nameType);
}
