package dao;

import models.Image;

import java.util.List;

public interface IImageDAO {
//    Thêm danh sách ảnh sản phẩm vào table images
    void addImages(List<Image> images);
//    Lấy ra danh sách tên ảnh theo id sản phẩm
    List<Image> getNameImages(int productId);
//    Lấy ra danh sách id ảnh theo id sản phẩm
    List<Image> getIdImages(int productId);
//    Xóa ảnh theo danh sách id ảnh
    void deleteImages(List<Integer> imageListId);
}
