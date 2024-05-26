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

import static services.voucher.VoucherState.CATEGORY;
import static services.voucher.VoucherState.PRODUCT;

public abstract class AbstractVoucherStrategy implements IVoucherStrategy {
    protected List<Integer> listCartItemId;
    protected Voucher voucher;
    protected static VoucherDAO voucherDAO = new VoucherDAO();
    protected ProductDao productDao = new ProductDao();

    protected AbstractVoucherStrategy(List<Integer> listCartItemId, Voucher voucher) {
        this.listCartItemId = listCartItemId;
        this.voucher = voucher;
    }

    public static AbstractVoucherStrategy create(List<Integer> listCartItemId, Voucher voucher) {
        VoucherType voucherType = voucherDAO.getVoucherType(voucher.getId()).get(0);
        String type = voucherType.getType();
        if (type.equals(CATEGORY)) {
            return new VoucherCategoryStrategy(listCartItemId, voucher);
        } else if (type.equals(PRODUCT)) {
            return new VoucherProductStrategy(listCartItemId, voucher);
        }
        return null;
    }

    @Override
    public boolean apply() {
        if (!checkMinimumPrice()) {
            return false;
        }
        return true;
    }

    protected List<Integer> getListJsonIds() {
        VoucherType voucherType = voucherDAO.getVoucherType(voucher.getId()).get(0);
        String jsonIds = voucherType.getJsonIds();
        if (jsonIds == null) {
            return null;
        }
        try {
            Gson gson = new Gson();
            Type type = new TypeToken<List<Integer>>() {
            }.getType();
            List<Integer> result = gson.fromJson(jsonIds, type);
            return result;
        } catch (Exception e) {
            return null;
        }
    }

    private boolean checkMinimumPrice() {
        double totalPrice = 0;
        List<CartItem> listCartItemsAfterFiled = getListCartItemId(getListJsonIds());
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

    protected abstract List<CartItem> getListCartItemId(List<Integer> listId);
}
