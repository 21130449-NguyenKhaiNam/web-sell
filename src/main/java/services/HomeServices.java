package services;

import dao.HomeDao;
import models.Products;
import models.Sliders;

import java.util.List;
import java.util.Map;

public class HomeServices {
    private HomeDao homeDao;
    private static HomeServices INSTANCE;

    public HomeServices() {
        homeDao = new HomeDao();
    }

    public static HomeServices getINSTANCE() {
        if (INSTANCE == null)
            INSTANCE = new HomeServices();
        return INSTANCE;
    }

    public List<Map<String, Object>> getListTrendingProducts(boolean isSeeMore){
        return homeDao.getListTrendingProducts(isSeeMore);
    }

    public List<Map<String, Object>> getListNewProducts(boolean isSeeMore){
        return homeDao.getListNewProducts(isSeeMore);
    }

    public List<Sliders> getListSlideShow(){
        return homeDao.getListSlideShow();
    }
}
