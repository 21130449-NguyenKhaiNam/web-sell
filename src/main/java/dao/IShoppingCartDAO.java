package dao;

import models.Voucher;

import java.util.List;

public interface IShoppingCartDAO {
    List<Voucher> getListVouchers();
    Voucher getValidVoucherApply(String code);
    List<String> getListCodeOfVouchers();
}
