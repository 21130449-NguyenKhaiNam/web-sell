package dao;

import models.Image;

import java.util.List;

public interface IImageDAO {
    List<Image> getThumbnail(int productId);
    void addImages(List<Image> images);
    List<Image> getNameImages(int productId);
    List<Image> getIdImages(int productId);
    void deleteImages(List<Integer> nameImages);
}
