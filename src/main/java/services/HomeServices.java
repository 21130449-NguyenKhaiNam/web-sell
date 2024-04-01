package services;

import dao.HomeDAOImp;
import models.*;

import java.util.List;

public class HomeServices {
    private HomeDAOImp homeDao;
    private static HomeServices INSTANCE;

    public HomeServices() {
        homeDao = new HomeDAOImp();
    }

    public static HomeServices getINSTANCE() {
        if (INSTANCE == null)
            INSTANCE = new HomeServices();
        return INSTANCE;
    }

//    public List<Map<String, Object>> getListTrendingProducts(boolean isSeeMore){
//        return homeDao.getListTrendingProducts(isSeeMore);
//    }

//    public List<Map<String, Object>> getListNewProducts(boolean isSeeMore){
//        return homeDao.getListNewProducts(isSeeMore);
//    }

    public List<Product> getListNewProducts(boolean isSeeMore){
        return homeDao.getListNewProducts(isSeeMore);
    }

    public List<Slider> getListSlideShow(){
        return homeDao.getListSlideShow();
    }

    public List<Product> getListTrendProducts(boolean isSeeMore){
        return homeDao.getListTrendProducts(isSeeMore);
    }
}
