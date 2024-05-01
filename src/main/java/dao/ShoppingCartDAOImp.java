package dao;

import models.Voucher;
import models.shoppingCart.AbstractCartProduct;
import models.shoppingCart.ShoppingCart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ShoppingCartDAOImp implements IShoppingCartDAO {
    public List<Voucher> getListVouchers() {
        String sql = "SELECT id, `code`, `description`, minimumPrice, discountPercent, expiryDate FROM vouchers WHERE expiryDate >= CURDATE() AND availableTurns > 0";
        return GeneralDAOImp.executeQueryWithSingleTable(sql, Voucher.class);
    }

//    public static Voucher getDiscountPercentByCode(double temporaryPrice, String code){
//        String sql = "SELECT discountPercent, `code` FROM vouchers WHERE minimumPrice <= ? AND expiryDate >= CURDATE() AND `code` = ?";
//        List<Voucher> listVouchers = GeneralDao.executeQueryWithSingleTable(sql, Voucher.class, temporaryPrice, code);
//        if (!listVouchers.isEmpty()){
//            return listVouchers.get(0);
//        }else{
//            return null;
//        }
//    }

    public Voucher getValidVoucherApply(String code) {
        String sql = "SELECT id, discountPercent, `code`, minimumPrice, `description` FROM vouchers WHERE expiryDate >= CURDATE() AND availableTurns > 0 AND `code` = ?";
        List<Voucher> listVouchers = GeneralDAOImp.executeQueryWithSingleTable(sql, Voucher.class, code);
        if (!listVouchers.isEmpty()) {
            return listVouchers.get(0);
        } else {
            return null;
        }
    }

    public List<String> getListCodeOfVouchers() {
        List<String> listCodeOfVouchers = new ArrayList<>();
        String sql = "SELECT `code` FROM Vouchers";
        List<Voucher> listVouchers = GeneralDAOImp.executeQueryWithSingleTable(sql, Voucher.class);
        for (Voucher voucher : listVouchers) {
            listCodeOfVouchers.add(voucher.getCode());
        }
        return listCodeOfVouchers;
    }

    @Override
    public void insertCart(int cartId, int userId, ShoppingCart cart) {
        String sql = "INSERT INTO cart (id, user_id) VALUES (?, ?)";
        GeneralDAOImp.executeAllTypeUpdate(sql, cartId, userId);

        // Chưa có kích thước
        String sqlCart = "INSERT INTO cart_item (cart_id, product_id, color_id, quantity) VALUES (?, ?, ?, ?)";
        HashMap<Integer, List<AbstractCartProduct>> mapCart = cart.getShoppingCartMap();
        List<Integer> productIds = new ArrayList<>(mapCart.keySet());
        for (int i = 0; i < mapCart.size(); i++) {
            Integer productId = productIds.get(i);
            List<AbstractCartProduct> products = mapCart.get(i);
            for (int j = 0; j < products.size(); j++) {
                AbstractCartProduct cartProduct = products.get(j);
                GeneralDAOImp.executeAllTypeUpdate(sqlCart, cartId, productId, cartProduct.getColor().getId(), cartProduct.getQuantity());
            }
        }
    }

    @Override
    public int findCartByUserId(int userId) {
        String sql = "SELECT cart_id FROM cart WHERE user_id = ?";
        List<Integer> cartIds = GeneralDAOImp.executeQueryWithSingleTable(sql, Integer.class, userId);
        if (cartIds.size() != 1) {
            // Lỗi
            return -1;
        } else {
            return cartIds.get(0);
        }
    }

//    public double getMinPriceApplyVoucherByCode(String code){
//        String sql = "SELECT minimumPrice FROM vouchers WHERE code = ?";
//        return GeneralDao.executeQueryWithSingleTable(sql, Voucher.class, code).get(0).getMinimumPrice();
//    }
}
