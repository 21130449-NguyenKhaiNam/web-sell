package dao;

import annotations.LogTable;
import models.Voucher;

import java.util.ArrayList;
import java.util.List;

@LogTable(LogTable.VOUCHER)
public class ShoppingCartDAOImp implements IShoppingCartDAO {
    public List<Voucher> getListVouchers(){
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

    public Voucher getValidVoucherApply(String code){
        String sql = "SELECT id, discountPercent, `code`, minimumPrice, `description` FROM vouchers WHERE expiryDate >= CURDATE() AND availableTurns > 0 AND `code` = ?";
        List<Voucher> listVouchers = GeneralDAOImp.executeQueryWithSingleTable(sql, Voucher.class, code);
        if (!listVouchers.isEmpty()){
            return listVouchers.get(0);
        }else{
            return null;
        }
    }

    public List<String> getListCodeOfVouchers(){
        List<String> listCodeOfVouchers = new ArrayList<>();
        String sql = "SELECT `code` FROM Vouchers";
        List<Voucher> listVouchers = GeneralDAOImp.executeQueryWithSingleTable(sql, Voucher.class);
        for (Voucher voucher: listVouchers){
            listCodeOfVouchers.add(voucher.getCode());
        }
        return listCodeOfVouchers;
    }

    @Override
    public Object getModelById(Object id) {
        return null;
    }

//    public double getMinPriceApplyVoucherByCode(String code){
//        String sql = "SELECT minimumPrice FROM vouchers WHERE code = ?";
//        return GeneralDao.executeQueryWithSingleTable(sql, Voucher.class, code).get(0).getMinimumPrice();
//    }
}
