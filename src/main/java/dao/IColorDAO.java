package dao;

import models.Color;

import java.util.List;

public interface IColorDAO {
    List<Color> getAllColor();
    void addColors(Color[] colors);
    List<Color> getIdColorByProductId(int productId);
    void updateColor(Color color, int id);
    void deleteColorList(List<Integer> listIdDelete);
}
