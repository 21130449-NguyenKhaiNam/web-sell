package dao;

import models.Voucher;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartDao {
    public List<Voucher> getListVouchers(){
        String sql = "SELECT id, `code`, `description`, minimumPrice, discountPercent, expiryDate FROM vouchers WHERE expiryDate >= CURDATE() AND availableTurns > 0";
        return GeneralDao.executeQueryWithSingleTable(sql, Voucher.class);
    }


    public Voucher getValidVoucherApply(String code){
        String sql = "SELECT id, discountPercent, `code`, minimumPrice, `description` FROM vouchers WHERE expiryDate >= CURDATE() AND availableTurns > 0 AND `code` = ?";
        List<Voucher> listVouchers = GeneralDao.executeQueryWithSingleTable(sql, Voucher.class, code);
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

}
