package dao.voucher;

import annotations.LogTable;
import dao.general.GeneralDAOImp;
import models.Voucher;

import java.util.ArrayList;
import java.util.List;

@LogTable(LogTable.VOUCHER)
public class ShoppingCartDAOImp implements IShoppingCartDAO {
    @Override
    public List<Voucher> getListVouchers(){
        String sql = "SELECT id, `code`, `description`, minimumPrice, discountPercent, expiryDate FROM vouchers WHERE expiryDate >= CURDATE() AND availableTurns > 0";
        return GeneralDAOImp.executeQueryWithSingleTable(sql, Voucher.class);
    }

    @Override
    public Voucher getValidVoucherApply(String code){
        String sql = "SELECT id, discountPercent, `code`, minimumPrice, `description` FROM vouchers WHERE expiryDate >= CURDATE() AND availableTurns > 0 AND `code` = ?";
        List<Voucher> listVouchers = GeneralDAOImp.executeQueryWithSingleTable(sql, Voucher.class, code);
        if (!listVouchers.isEmpty()){
            return listVouchers.get(0);
        }else{
            return null;
        }
    }

    @Override
    public List<String> getListCodeOfVouchers(){
        List<String> listCodeOfVouchers = new ArrayList<>();
        String sql = "SELECT `code` FROM Vouchers";
        List<Voucher> listVouchers = GeneralDAOImp.executeQueryWithSingleTable(sql, Voucher.class);
        for (Voucher voucher: listVouchers){
            listCodeOfVouchers.add(voucher.getCode());
        }
        return listCodeOfVouchers;
    }
}
