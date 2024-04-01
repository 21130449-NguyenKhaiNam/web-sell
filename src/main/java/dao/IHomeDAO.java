package dao;

import models.Product;
import models.Slider;

import java.util.List;

public interface IHomeDAO {
    List<Slider> getListSlideShow();
    List<Product> getListTrendProducts(boolean isSeeMore);
}
