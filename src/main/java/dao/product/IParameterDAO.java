package dao.product;

import annotations.LogParam;
import annotations.WriteLog;
import dao.IDAO;
import models.Parameter;

import java.util.List;

public interface IParameterDAO extends IDAO {
    //    Lấy ra danh sách các tham số dựa theo id thể loại
    List<Parameter> getParameterByCategoryId(int id);

    //    Lấy ra danh sách các tham số dựa theo id thể loại và sắp xếp theo id
    List<Parameter> getParameterByCategoryId(int id, boolean orderById);

    //    Xóa tham số
    @WriteLog(WriteLog.UPDATE)
    void deleteParameter(@LogParam("id-delete") int id);

    //    Cập nhật thông tin tham số
//    void updateParameter(@LogParam("parameter") Parameter parameter);
//
//    //    Thêm mới tham số
//    void addParameter(@LogParam("parameter") Parameter parameter);
}
