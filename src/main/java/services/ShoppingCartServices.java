package services;

import dao.IShoppingCartDAO;
import dao.ShoppingCartDAOImp;
import models.Voucher;
import models.shoppingCart.AbstractCartProduct;
import models.shoppingCart.ShoppingCart;

import java.util.List;
import java.util.Map;

public class ShoppingCartServices {

    private IShoppingCartDAO shoppingCartDao;

    private static ShoppingCartServices INSTANCE;

    public ShoppingCartServices() {
        shoppingCartDao = new ShoppingCartDAOImp();
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

    public void insertCart(int cartId, int userId, Map<Integer, List<AbstractCartProduct>> products) {
        shoppingCartDao.insertCart(cartId, userId, products);
    }

    public int findCartByUserId(int id) {
        return shoppingCartDao.findCartByUserId(id);
    }

    public ShoppingCart findCartByCartId(int cartId) {
        return shoppingCartDao.findById(cartId);
    }

    public void deleteByCartIdAndIdProduct(int cartId, Integer[] productIds) {
        shoppingCartDao.deleteByCartIdAndIdProduct(cartId, productIds);
    }

    public void update(Map<Integer, List<AbstractCartProduct>> change) {
        shoppingCartDao.update(change);
    }
}
