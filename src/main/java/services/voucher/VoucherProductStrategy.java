package services.voucher;

import models.CartItem;
import models.Voucher;

import java.util.List;

public class VoucherProductStrategy extends AbstractVoucherStrategy {

    public VoucherProductStrategy(List<Integer> listCartItemId, Voucher voucher) {
        super(listCartItemId, voucher);
    }

    @Override
    protected List<CartItem> getListCartItemId(List<Integer> listProductIds) {
        return voucherDAO.getCartProductByProduct(listProductIds, listCartItemId);
    }
}
