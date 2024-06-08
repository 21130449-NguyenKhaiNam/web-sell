package services.voucher;

import dao.ProductDao;
import dao.ShoppingCartDao;
import dao.VoucherDAO;
import models.CartItem;
import models.Product;
import models.Size;
import models.Voucher;
import models.shoppingCart.CartProduct;
import models.shoppingCart.CartProductCustom;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class VoucherProductStrategy implements IVoucherStrategy {
    protected List<CartItem> cartItems;
    protected Voucher voucher;
    protected static VoucherDAO voucherDAO = new VoucherDAO();
    protected ProductDao productDao = new ProductDao();
    protected List<Integer> listProductIdCanApply;

    public VoucherProductStrategy(List<CartItem> cartItems, Voucher voucher) {
        this.cartItems = cartItems;
        this.voucher = voucher;
        this.listProductIdCanApply = new ArrayList<>();
    }


    @Override
    public boolean apply() {
        if (!checkMinimumPrice()) {
            return false;
        }
        return true;
    }

    private boolean checkMinimumPrice() {
        this.cartItems = getCartItemCanApply(cartItems, voucher.getId());
        double totalPrice = 0;
        for (CartItem cartItem : cartItems) {
            Product product = productDao.getProductByProductId(cartItem.getProductId());
            double price = product.getSalePrice() == 0.0 ? product.getOriginalPrice() : product.getSalePrice();
            double priceSize = (isSizeCustom(cartItem)) ? 0.0 : getSizePrice(cartItem.getSize());
            totalPrice += (price + priceSize) * cartItem.getQuantity();
            if (totalPrice >= voucher.getMinimumPrice()) {
                return true;
            }
        }
        return false;
    }


    public List<CartItem> getCartItemCanApply(List<CartItem> cartItems, Integer voucherId) {
        List<Integer> listCartItemId = cartItems.stream().map(CartItem::getId).collect(Collectors.toList());
        return voucherDAO.getCartItemCanApply(listCartItemId, voucher.getId());
    }

    private boolean isSizeCustom(Object size) {
        try {
            // Nó là của sản phẩm có sẵn
            Size sizeWrapper = (Size) size;
            return false;
        } catch (Exception e) {
            // Nó là của sản phẩm custom
            return true;
        }
    }

    private double getSizePrice(Object size) {
        Size sizeWrapper = (Size) size;
        return voucherDAO.getPriceSize(sizeWrapper.getId());
    }
}
