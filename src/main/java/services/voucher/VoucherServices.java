package services.voucher;

import dao.ProductCardDAO;
import dao.ProductDao;
import dao.VoucherDAO;
import models.*;

import java.util.List;

public class VoucherServices {
    VoucherDAO voucherDAO = new VoucherDAO();

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
        boolean isExist = listCartItem.stream().allMatch(item -> listIdCardItem.contains(item.getCartId()));
        if (!isExist) return VoucherState.NOT_FOUND;
//        Kiểm tra xem mã giảm giá có tồn tại không
        Voucher voucher = voucherDAO.selectByCode(code);
        if (voucher == null) return VoucherState.NOT_FOUND;

        if (voucher.getAvailableTurns() == 0) {
            return VoucherState.EMPTY_AVAILABLE_TURN;
        }

        if (voucher.getExpiryDate().getTime() < System.currentTimeMillis()) {
            return VoucherState.EXPIRED;
        }

        AbstractVoucherStrategy strategy = AbstractVoucherStrategy.create(listIdCardItem, voucher);
        if (strategy == null) return VoucherState.NOT_FOUND;
        if (!strategy.apply()) return VoucherState.CAN_NOT_APPLY;
        return VoucherState.CAN_APPLY;
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
