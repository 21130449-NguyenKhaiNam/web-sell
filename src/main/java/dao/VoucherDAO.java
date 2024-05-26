package dao;

import models.*;

import java.util.ArrayList;
import java.util.List;

public class VoucherDAO {
    public List<Voucher> selectAll() {
        String sql = "SELECT id, code, minimumPrice,discountPercent, expired_date, status FROM vouchers";
        List<Voucher> vouchers = new ArrayList<>();
        vouchers = GeneralDao.executeQueryWithSingleTable(sql, Voucher.class);
        return vouchers;
    }

    public List<Voucher> selectAll(List<Integer> listIdProduct) {
        String sql = "SELECT id, `code`, description,minimumPrice,discountPercent, expiryDate, availableTurns, state FROM vouchers JOIN voucher_products ON vouchers.id = voucher_products.voucherId WHERE voucher_products.productId IN (";
        for (int i = 0; i < listIdProduct.size(); i++) {
            sql += listIdProduct.get(i);
            if (i != listIdProduct.size() - 1) {
                sql += ",";
            }
        }
        sql += ")";
        List<Voucher> vouchers = new ArrayList<>();
        vouchers = GeneralDao.executeQueryWithSingleTable(sql, Voucher.class);
        return vouchers;
    }

    public Voucher selectByCode(String code) {
        String sql = "SELECT id, code, minimumPrice,discountPercent, expired_date, status FROM vouchers WHERE code = ?";
        List<Voucher> vouchers = new ArrayList<>();
        vouchers = GeneralDao.executeQueryWithSingleTable(sql, Voucher.class, code);
        return vouchers.isEmpty() ? null : vouchers.get(0);
    }

    public List<VoucherProduct> selectProductByVoucher(Integer voucherId) {
        String sql = "SELECT DISTINCT productId FROM voucher_product WHERE voucherId = ?";
        List<VoucherProduct> result = new ArrayList<>();
        result = GeneralDao.executeQueryWithSingleTable(sql, VoucherProduct.class, voucherId);
        return result.isEmpty() ? null : result;
    }

    public List<CartItem> getCartItem(Integer userId) {
        String sql = "SELECT id, product_id, size, color_id, sizeCustomize, quantity FROM cart_items JOIN cart ON cart_items.cart_id = cart.id WHERE cart.user_id = ? ";
        return GeneralDao.executeQueryWithSingleTable(sql, CartItem.class, userId);
    }

    public List<VoucherType> getVoucherType(Integer voucherId) {
        String sql = "SELECT voucherId, type, jsonIds FROM voucher_types WHERE id = ?";
        return GeneralDao.executeQueryWithSingleTable(sql, VoucherType.class, voucherId);
    }

    public List<CartItem> getCartProductByCategory(List<Integer> listCategoryId, List<Integer> listCartItemId) {
        String sql = "SELECT  cart_id, product_id, size, color_id, sizeCustomize, quantity \n" +
                "FROM cart_items JOIN products ON cart_items.product_id = products.id;\n" +
                "WHERE  products.categoryId IN (" + convertToSQL(listCategoryId) + ") AND cart_items.id IN  (" + convertToSQL(listCartItemId) + ")";
        return GeneralDao.executeQueryWithSingleTable(sql, CartItem.class);
    }

    public List<CartItem> getCartProductByProduct(List<Integer> listProductId, List<Integer> listCartItemId) {
        String sql = "SELECT cart_id, product_id, size, color_id, sizeCustomize, quantity \n" +
                "FROM cart_items " +
                "WHERE  cart_items.product_id IN (" + convertToSQL(listProductId) + ") AND cart_items.id IN  (" + convertToSQL(listCartItemId) + ")";
        return GeneralDao.executeQueryWithSingleTable(sql, CartItem.class);
    }

    public double getPriceSize(Integer sizeId) {
        String sql = "SELECT sizePrice FROM sizes WHERE id = ?";
        List<Size> productSizes = GeneralDao.executeQueryWithSingleTable(sql, Size.class, sizeId);
        return productSizes.isEmpty() ? 0 : productSizes.get(0).getSizePrice();
    }

    private String convertToSQL(List<Integer> list) {
        String sql = "";
        for (int i = 0; i < list.size(); i++) {
            sql += list.get(i);
            if (i != list.size() - 1) {
                sql += ",";
            }
        }
        return sql;
    }
}
