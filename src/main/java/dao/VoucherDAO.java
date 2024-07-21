package dao;

import models.*;
import services.voucher.VoucherState;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class VoucherDAO {
    public List<Voucher> selectAll() {
        String sql = "SELECT id, code, minimumPrice,discountPercent, expiryDate, description, state FROM vouchers WHERE state = 1 AND expiryDate >= NOW() AND availableTurns > 0";
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

    public boolean existVoucher(Integer id) {
        String sql = "SELECT id, code, minimumPrice, description, discountPercent, expiryDate, state, availableTurns FROM vouchers WHERE id = ?";
        List<Voucher> vouchers = new ArrayList<>();
        vouchers = GeneralDao.executeQueryWithSingleTable(sql, Voucher.class, id);
        return !vouchers.isEmpty();
    }

    public Voucher selectByCode(String code) {
        String sql = "SELECT id, code, minimumPrice, description, discountPercent, expiryDate, state, availableTurns FROM vouchers WHERE code = ?";
        List<Voucher> vouchers = new ArrayList<>();
        vouchers = GeneralDao.executeQueryWithSingleTable(sql, Voucher.class, code);
        return vouchers.isEmpty() ? null : vouchers.get(0);
    }

    public double getPriceSize(Integer sizeId) {
        String sql = "SELECT sizePrice FROM sizes WHERE id = ?";
        List<Size> productSizes = GeneralDao.executeQueryWithSingleTable(sql, Size.class, sizeId);
        return productSizes.isEmpty() ? 0 : productSizes.get(0).getSizePrice();
    }

    public long getSizeWithCondition(String search) {
        String sql = "SELECT COUNT(*) count FROM vouchers WHERE code LIKE :search OR availableTurns LIKE :search OR state LIKE :search";
        LogDAOImp.CountResult result = new LogDAOImp.CountResult();
        GeneralDao.customExecute(handle -> {
            result.setCount(handle.createQuery(sql)
                    .bind("search", "%" + search + "%")
                    .mapToBean(LogDAOImp.CountResult.class)
                    .list().get(0).getCount());
        });
        return result.getCount();
    }

    public List<Voucher> selectWithCondition(Integer start, Integer limit, String search, String orderBy, String orderDir) {
        String sql = "SELECT id, `code`, createAt, expiryDate, availableTurns, state " + "FROM vouchers " +
                "WHERE code LIKE :search OR createAt LIKE :search OR expiryDate LIKE :search OR availableTurns LIKE :search OR state LIKE :search " +
                "ORDER BY :orderBy :orderDir LIMIT :limit OFFSET :start";
        List<Voucher> vouchers = new ArrayList<>();
        GeneralDao.customExecute(handle -> {
            vouchers.addAll(handle.createQuery(sql)
                    .bind("search", "%" + search + "%")
                    .bind("limit", limit)
                    .bind("start", start)
                    .bind("orderBy", orderBy)
                    .bind("orderDir", orderDir)
                    .mapToBean(Voucher.class)
                    .list());
        });
        if (orderDir.equals("desc")) {
            Collections.reverse(vouchers);
        }
        return vouchers;
    }

    public int save(Voucher voucher) {
        String sql = "INSERT INTO vouchers (code, minimumPrice, description, discountPercent, expiryDate, state, availableTurns) VALUES (?,?,?,?,?,?,?)";
        int id = GeneralDao.executeInsert(sql, voucher.getCode(), voucher.getMinimumPrice(), voucher.getDescription(), voucher.getDiscountPercent(), voucher.getExpiryDate(), voucher.getState(), voucher.getAvailableTurns());
        return id;
    }

    public void save(Integer voucherId, List<Integer> listProductId) {
        String sql = "INSERT INTO voucher_products (voucherId, productId) VALUES ";
        List<Integer> params = new ArrayList<>();
        for (Integer productId : listProductId) {
            sql += " ( ?, ? ),";
            params.add(voucherId);
            params.add(productId);
        }
        sql = sql.substring(0, sql.length() - 1);
        GeneralDao.executeAllTypeUpdate(sql, params.toArray());
    }

    public List<CartItem> getCartItemCanApply(List<Integer> cartItemId, Integer voucherId) {
        String sql = "SELECT cart_id, product_id, color_id ,size, quantity \n" +
                "FROM cart_items JOIN cart ON cart_items.cart_id = cart.id \n" +
                "WHERE cart_items.id IN ( ";
        for (Integer id : cartItemId) {
            sql += id + " ,";
        }
        sql = sql.substring(0, sql.length() - 1);
        sql += ") AND cart_items.product_id IN (\n" +
                "SELECT productId from voucher_products WHERE voucherId = ?\n" +
                ") ";
        return GeneralDao.executeQueryWithSingleTable(sql, CartItem.class, voucherId);
    }

    public List<Integer> getListProductByCode(String code) {
        String sql = "SELECT productId FROM voucher_products JOIN vouchers on voucher_products.voucherId = vouchers.id WHERE vouchers.code = ?";
        List<Integer> listId = new ArrayList<>();
        GeneralDao.customExecute(
                handle -> {
                    listId.addAll(handle.createQuery(sql)
                            .bind(0, code)
                            .mapTo(Integer.class)
                            .list());
                }
        );
        return listId;
    }

    public List<Integer> getListProductById(Integer id) {
        String sql = "SELECT productId FROM voucher_products JOIN vouchers on voucher_products.voucherId = vouchers.id WHERE vouchers.id = ?";
        List<Integer> listId = new ArrayList<>();
        GeneralDao.customExecute(
                handle -> {
                    listId.addAll(handle.createQuery(sql)
                            .bind(0, id)
                            .mapTo(Integer.class)
                            .list());
                }
        );
        return listId;
    }

    public void changeState(String code, VoucherState type) {
        String sql = "UPDATE vouchers SET state = ? WHERE code = ?";
        GeneralDao.executeAllTypeUpdate(sql, type.getValue(), code);
    }

    public void update(Voucher voucher) {
        String sql = "UPDATE vouchers SET code = ?, minimumPrice = ?, description = ?, discountPercent = ?, expiryDate = ?, state = ?, availableTurns = ? WHERE code = ?";
        GeneralDao.executeAllTypeUpdate(sql, voucher.getCode(), voucher.getMinimumPrice(), voucher.getDescription(), voucher.getDiscountPercent(), voucher.getExpiryDate(), voucher.getState(), voucher.getAvailableTurns(), voucher.getCode());
    }

    public void deleteProductVoucher(Integer code, List<Integer> delete) {
        String sql = "DELETE FROM voucher_products WHERE voucherId = ? AND productId IN (";
        List<Integer> params = new ArrayList<>();
        params.add(code);
        for (int i = 0; i < delete.size(); i++) {
            sql += "?";
            params.add(delete.get(i));
            if (i != delete.size() - 1) {
                sql += ",";
            }
        }
        sql += ")";
        GeneralDao.executeAllTypeUpdate(sql, params.toArray(new Object[0]));
    }

    public void insertProductVoucher(Integer code, List<Integer> insert) {
        String sql = "INSERT INTO voucher_products (voucherId, productId) VALUES ";
        List<Integer> params = new ArrayList<>();
        for (Integer productId : insert) {
            sql += " ( ? ,  ? ),";
            params.add(code);
            params.add(productId);
        }

        sql = sql.substring(0, sql.length() - 1);
        GeneralDao.executeAllTypeUpdate(sql, params.toArray(new Object[0]));
    }

    public void updateProductVoucher(Integer code, List<Integer> update) {
        String sql = "UPDATE voucher_products SET productId = CASE";
        List<Integer> params = new ArrayList<>();
        for (Integer productId : update) {
            sql += " WHEN productId = ? THEN ? ";
            params.add(productId);
            params.add(productId);
        }
        sql += " END WHERE voucherId = ?";
        params.add(code);
        GeneralDao.executeAllTypeUpdate(sql, params.toArray(new Object[0]));
    }

}
