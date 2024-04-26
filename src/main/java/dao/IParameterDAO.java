package dao;

import models.Parameter;

import java.util.List;

public interface IParameterDAO extends IDAO {
    //    Lấy ra danh sách các tham số dựa theo id thể loại
    List<Parameter> getParameterByCategoryId(int id);

    //    Lấy ra danh sách các tham số dựa theo id thể loại và sắp xếp theo id
    List<Parameter> getParameterByCategoryId(int id, boolean orderById);

    //    Cập nhật thông tin tham số
    void updateParameter(Parameter parameter);

    //    Thêm mới tham số
    void addParameter(Parameter parameter);

    //    Xóa tham số
    void deleteParameter(int id);
}
