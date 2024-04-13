package services;

import dao.voucher.IShoppingCartDAO;
import dao.voucher.ShoppingCartDAOImp;
import models.Voucher;

import java.util.List;

public class ShoppingCartServices {

    private IShoppingCartDAO shoppingCartDao;

    private static ShoppingCartServices INSTANCE;

    public ShoppingCartServices() {
        shoppingCartDao = LogService.getINSTANCE().createProxy(new ShoppingCartDAOImp());
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
