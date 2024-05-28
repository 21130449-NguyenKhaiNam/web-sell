package services.voucher;

import com.google.gson.Gson;
import dao.ProductDao;
import dao.VoucherDAO;
import io.leangen.geantyref.TypeToken;
import models.CartItem;
import models.Product;
import models.Voucher;
import models.VoucherType;

import java.lang.reflect.Type;
import java.util.List;

public class VoucherProductStrategy implements IVoucherStrategy {
    protected List<Integer> listCartItemId;
    protected Voucher voucher;
    protected static VoucherDAO voucherDAO = new VoucherDAO();
    protected ProductDao productDao = new ProductDao();

    public VoucherProductStrategy(List<Integer> listCartItemId, Voucher voucher) {
        this.listCartItemId = listCartItemId;
        this.voucher = voucher;
    }


    @Override
    public boolean apply() {
        if (!checkMinimumPrice()) {
            return false;
        }
        return true;
    }

    private boolean checkMinimumPrice() {
        double totalPrice = 0;
        List<CartItem> listCartItemsAfterFiled = getListCartItemId(listCartItemId);
        for (CartItem cartItem : listCartItemsAfterFiled) {
            Product product = productDao.getProductByProductId(cartItem.getProductId());
            double price = product.getSalePrice() == 0.0 ? product.getOriginalPrice() : product.getSalePrice();
            double priceSize = voucherDAO.getPriceSize(cartItem.getSize());
            totalPrice += (price + priceSize) * cartItem.getQuantity();
            if (totalPrice >= voucher.getMinimumPrice()) {
                return true;
            }
        }
        return false;
    }

    public List<CartItem> getListCartItemId(List<Integer> listProductIds) {
        return voucherDAO.getCartProductByProduct(listProductIds, listCartItemId);
    }
}
