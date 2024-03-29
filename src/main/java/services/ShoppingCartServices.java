package services;

import dao.GeneralDao;
import dao.ShoppingCartDao;
import models.Voucher;

import java.util.List;

public class ShoppingCartServices {

    private ShoppingCartDao shoppingCartDao;

    private static ShoppingCartServices INSTANCE;

    public ShoppingCartServices() {
        shoppingCartDao = new ShoppingCartDao();
    }

    public static ShoppingCartServices getINSTANCE() {
        if (INSTANCE == null)
            INSTANCE = new ShoppingCartServices();
        return INSTANCE;
    }
    public List<Voucher> getListVouchers(){
        return shoppingCartDao.getListVouchers();
    }

//    public Voucher getDiscountPercentByCode(double temporaryPrice, String code){
//        return shoppingCartDao.getDiscountPercentByCode(temporaryPrice, code);
//    }

    public Voucher getValidVoucherApply(String code){
        return shoppingCartDao.getValidVoucherApply(code);
    }


    public List<String> getListCodeOfVouchers(){
        return shoppingCartDao.getListCodeOfVouchers();
    }

//    public double getMinPriceApplyVoucherByCode(String code){
//        return shoppingCartDao.getMinPriceApplyVoucherByCode(code);
//    }
}
