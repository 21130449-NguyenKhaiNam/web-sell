package dao;

import models.Voucher;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartDao {
    public List<Voucher> getListVouchers(){
        String sql = "SELECT id, `code`, `description`, minimumPrice, discountPercent, expiryDate FROM vouchers";
        return GeneralDao.executeQueryWithSingleTable(sql, Voucher.class);
    }

    public static Voucher getDiscountPercentByCode(double temporaryPrice, String code){
        String sql = "SELECT discountPercent, minimumPrice FROM vouchers WHERE minimumPrice <= ? AND expiryDate >= CURDATE() AND `code` = ?";
        List<Voucher> listVouchers = GeneralDao.executeQueryWithSingleTable(sql, Voucher.class, temporaryPrice, code);
        if (!listVouchers.isEmpty()){
            return listVouchers.get(0);
        }else{
            return null;
        }
    }

    public List<String> getListCodeOfVouchers(){
        List<String> listCodeOfVouchers = new ArrayList<>();
        String sql = "SELECT `code` FROM Vouchers";
        List<Voucher> listVouchers = GeneralDao.executeQueryWithSingleTable(sql, Voucher.class);
        for (Voucher voucher: listVouchers){
            listCodeOfVouchers.add(voucher.getCode());
        }
        return listCodeOfVouchers;
    }

    public static void main(String[] args) {
        System.out.println(getDiscountPercentByCode(222, "sss"));
    }
}
