package services.voucher;

import lombok.Setter;
import models.CartItem;
import models.Voucher;

import java.util.List;
import java.util.Map;

@Setter
public class VoucherCategoryStrategy extends AbstractVoucherStrategy {

    public VoucherCategoryStrategy(List<Integer> listCartItemId, Voucher voucher) {
        super(listCartItemId, voucher);
    }

    @Override
    protected List<CartItem> getListCartItemId(List<Integer> listCategoryId) {
        return voucherDAO.getCartProductByCategory(listCategoryId, listCartItemId);
    }
}
