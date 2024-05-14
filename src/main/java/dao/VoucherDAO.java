package dao;

import models.Product;
import models.Voucher;
import models.VoucherProduct;

import java.util.ArrayList;
import java.util.List;

public class VoucherDAO {
    public List<Voucher> selectAll() {
        String sql = "SELECT id, code, minimumPrice,discountPercent, expired_date, status FROM voucher";
        List<Voucher> vouchers = new ArrayList<>();
        vouchers = GeneralDao.executeQueryWithSingleTable(sql, Voucher.class);
        return vouchers;
    }

    public Voucher selectByCode(Integer code) {
        String sql = "SELECT id, code, minimumPrice,discountPercent, expired_date, status FROM voucher WHERE code = ? AND availableTurns > 0 and expired_date > now()";
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
}
