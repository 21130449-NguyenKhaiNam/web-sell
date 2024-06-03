package services.voucher;

import dao.VoucherDAO;
import models.*;

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


//    public boolean canApply(Integer code, List<Integer> cartItem) {
//        Voucher voucher = dao.selectByCode(code);
//        if (voucher == null) return false;
//        cartItem = exportCartItems(voucher.getId(), cartItem);
//        if (cartItem == null) return false;
//        double totalPrice = 0;
//        for (CartItemDTO item : cartItem) {
//            Product product = productDao.getProductByProductId(item.getProductId());
//            double currentPrice = product.getSalePrice() == 0 ? product.getOriginalPrice() : product.getSalePrice();
//            double priceSize = productDao.getPriceSizeByName(item.getSize(), item.getProductId());
//            totalPrice += (currentPrice + priceSize) * item.getQuantity();
//        }
//        return totalPrice >= voucher.getMinimumPrice();
//    }
//
//    private List<AbstractModel> exportCartItems(Integer voucherId, List<Integer> cartItem) {
//        List<VoucherProduct> voucherProduct = dao.selectProductByVoucher(voucherId);
//        List<AbstractModel> cartItem = productCardDAO.getCartItems();
//        if (voucherProduct == null) return null;
//        return cartItem.stream().filter(item -> voucherProduct.stream().anyMatch(product -> product.getProductId().equals(item.getProductId()))).collect(Collectors.toList());
//    }

}
