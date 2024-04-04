package dao;

import annotations.LogParam;
import annotations.WriteLog;
import models.Image;

import java.util.List;

public interface IImageDAO {
    //    Thêm danh sách ảnh sản phẩm vào table images
    @WriteLog(WriteLog.INSERT)
    void addImages(@LogParam("images") List<Image> images);

    //    Lấy ra danh sách tên ảnh theo id sản phẩm
    List<Image> getNameImages(int productId);

    //    Lấy ra danh sách id ảnh theo id sản phẩm
    List<Image> getIdImages(int productId);

    //    Xóa ảnh theo danh sách id ảnh
    @WriteLog(WriteLog.UPDATE)
    void deleteImages(@LogParam("id-list-image") List<Integer> imageListId);
}
