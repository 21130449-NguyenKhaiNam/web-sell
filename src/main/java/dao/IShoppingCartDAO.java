package dao;

import models.Voucher;
import models.shoppingCart.ShoppingCart;

import java.util.List;

public interface IShoppingCartDAO extends IDAO {
    //    Lấy ra danh sách tất cả các voucher còn thời hạn sử dụng và lựợt sử dụng
    List<Voucher> getListVouchers();

    //    Lấy ra voucher dựa vào mã code
    //    Kiểm tra xem voucher còn thời hạn sử dụng và lượt sử dụng không
    Voucher getValidVoucherApply(String code);

    //    Lấy ra danh sách mã code của các voucher
    List<String> getListCodeOfVouchers();

    void insertCart(int cartId, int userId, ShoppingCart cart);

    int findCartByUserId(int userId);
}
