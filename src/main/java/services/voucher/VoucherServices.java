package services.voucher;

import dao.VoucherDAO;
import models.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class VoucherServices {
    VoucherDAO voucherDAO = new VoucherDAO();
    private static VoucherServices INSTANCE;


    private VoucherServices() {
    }

    public static VoucherServices getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new VoucherServices();
        }
        return INSTANCE;
    }

    public List<Voucher> getAll() {
        List<Voucher> listVoucher = voucherDAO.selectAll();
        return listVoucher.isEmpty() ? null : listVoucher;
    }

    public List<Voucher> getAll(List<Integer> listIdProduct) {
        List<Voucher> listVoucher = voucherDAO.selectAll(listIdProduct);
        return listVoucher;
    }

    public VoucherState canApply(User user, String code, List<Integer> listIdCardItem) {
        if (listIdCardItem == null || listIdCardItem.isEmpty()) return VoucherState.NOT_FOUND;
//        Kiểm tra danh sách sản phẩm gửi lên có nằm trong giỏ hàng của user không ?
        List<CartItem> listCartItem = voucherDAO.getCartItem(user.getId());
//        Kiểm tra xem mã giảm giá có tồn tại không
        boolean isExist = listCartItem.stream().map(CartItem::getProductId).collect(Collectors.toList()).containsAll(listIdCardItem);
        if (!isExist) return VoucherState.NOT_FOUND;
        Voucher voucher = voucherDAO.selectByCode(code);
        if (voucher == null) return VoucherState.NOT_FOUND;

        if (voucher.getAvailableTurns() == 0) {
            return VoucherState.EMPTY_AVAILABLE_TURN;
        }

        LocalDate current = LocalDate.now();
        LocalDate givenDate = voucher.getExpiryDate().toLocalDate().plusDays(1);
        if (current.isAfter(givenDate)) {
            return VoucherState.EXPIRED;
        }

        VoucherProductStrategy strategy = new VoucherProductStrategy(listIdCardItem, voucher);
        if (!strategy.apply()) return VoucherState.CAN_NOT_APPLY;
        return VoucherState.CAN_APPLY;
    }

    public List<Voucher> getVoucher(Integer start, Integer length, String searchValue, String orderBy, String orderDir) {
        return voucherDAO.selectWithCondition(start, length, searchValue, orderBy, orderDir);
    }

    public long getTotalWithCondition(String searchValue) {
        return voucherDAO.getSizeWithCondition(searchValue);
    }

    public boolean saveVoucher(Voucher voucher) {
//        Kiểm tra ngày hết hạn voucher có nhỏ hơn ngày hiện tại không
        Date currentDate = new Date(System.currentTimeMillis());
        if (voucher.getExpiryDate().compareTo(currentDate) < 0) return false;
//        Kiểm tra số lần sử dụng voucher có nhỏ hơn 0 không
        if (voucher.getAvailableTurns() < 0) return false;
//        Kiểm tra phần trăm giảm giá có nhỏ hơn 0 không
        if (voucher.getDiscountPercent() < 0) return false;
//        Kiểm tra giá trị đơn hàng tối thiểu có nhỏ hơn 0 không
        if (voucher.getMinimumPrice() < 0) return false;
//        Kiểm tra mã voucher đã tồn tại chưa
        boolean isExist = voucherDAO.selectByCode(voucher.getCode()) != null;
        if (isExist) return false;
        voucherDAO.save(voucher);
        return true;
    }
}
