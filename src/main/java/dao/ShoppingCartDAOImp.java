package dao;

import models.Color;
import models.Product;
import models.Size;
import models.Voucher;
import models.shoppingCart.AbstractCartProduct;
import models.shoppingCart.CartProduct;
import models.shoppingCart.CartProductCustom;
import models.shoppingCart.ShoppingCart;

import java.util.*;
import java.util.stream.Collectors;

public class ShoppingCartDAOImp implements IShoppingCartDAO {

    public List<Voucher> getListVouchers() {
        String sql = "SELECT id, `code`, `description`, minimumPrice, discountPercent, expiryDate FROM vouchers WHERE expiryDate >= CURDATE() AND availableTurns > 0";
        return GeneralDAOImp.executeQueryWithSingleTable(sql, Voucher.class);
    }

//    public static Voucher getDiscountPercentByCode(double temporaryPrice, String code){
//        String sql = "SELECT discountPercent, `code` FROM vouchers WHERE minimumPrice <= ? AND expiryDate >= CURDATE() AND `code` = ?";
//        List<Voucher> listVouchers = GeneralDao.executeQueryWithSingleTable(sql, Voucher.class, temporaryPrice, code);
//        if (!listVouchers.isEmpty()){
//            return listVouchers.get(0);
//        }else{
//            return null;
//        }
//    }

    public Voucher getValidVoucherApply(String code) {
        String sql = "SELECT id, discountPercent, `code`, minimumPrice, `description` FROM vouchers WHERE expiryDate >= CURDATE() AND availableTurns > 0 AND `code` = ?";
        List<Voucher> listVouchers = GeneralDAOImp.executeQueryWithSingleTable(sql, Voucher.class, code);
        if (!listVouchers.isEmpty()) {
            return listVouchers.get(0);
        } else {
            return null;
        }
    }

    public List<String> getListCodeOfVouchers() {
        List<String> listCodeOfVouchers = new ArrayList<>();
        String sql = "SELECT `code` FROM Vouchers";
        List<Voucher> listVouchers = GeneralDAOImp.executeQueryWithSingleTable(sql, Voucher.class);
        for (Voucher voucher : listVouchers) {
            listCodeOfVouchers.add(voucher.getCode());
        }
        return listCodeOfVouchers;
    }

    @Override
    public void insertCart(int cartId, int userId, Map<Integer, List<AbstractCartProduct>> mapCart) {
        String sql = "INSERT INTO cart (id, user_id) VALUES (?, ?)";
        GeneralDAOImp.executeAllTypeUpdate(sql, cartId, userId);

        // Chưa có kích thước
        String sqlCart = "INSERT INTO cart_items (cart_id, product_id, color_id, quantity, size) VALUES (?, ?, ?, ?, ?)";
        List<Integer> productIds = new ArrayList<>(mapCart.keySet());
        for (int i = 0; i < mapCart.size(); i++) {
            Integer productId = productIds.get(i);
            List<AbstractCartProduct> products = mapCart.get(productId);
            for (int j = 0; j < products.size(); j++) {
                AbstractCartProduct cartProduct = products.get(j);
                GeneralDAOImp.executeAllTypeUpdate(sqlCart, cartId, productId,
                        cartProduct.getColor().getId(), cartProduct.getQuantity(), cartProduct.getSize());
            }
        }
    }

    @Override
    public int findCartByUserId(int userId) {
        String sql = "SELECT id FROM cart WHERE user_id = ?";
        List<Integer> cartIds = GeneralDAOImp.executeQueryWithSingleTable(sql, models.shoppingCart.Cart.class, userId).stream()
                .map(cart -> cart.getId()).collect(Collectors.toList());
        if (cartIds.size() != 1) {
            // Lỗi
            return -1;
        } else {
            return cartIds.get(0);
        }
    }

    @Override
    public ShoppingCart findById(int cartId) {
        String sql = "SELECT product_id, color_id, quantity, size FROM cart_items WHERE cart_id =?";
        ProductDAOImp productDAOImp = new ProductDAOImp();
        ColorDAOImp colorDao = new ColorDAOImp();
        List<Cart> carts = GeneralDAOImp.executeQueryWithSingleTable(sql, Cart.class, cartId);
        HashMap<Integer, List<AbstractCartProduct>> map = new HashMap<>();
        for (int i = 0; i < carts.size(); i++) {
            int productId = carts.get(i).getProductId();
            int colorId = carts.get(i).getColorId();
            int quantity = carts.get(i).getQuantity();

            if (!map.containsKey(productId))
                map.put(productId, new ArrayList<>());
            Product product = productDAOImp.getProductByProductId(productId);
            Color color = colorDao.findById(colorId);
            AbstractCartProduct cartProduct = null;
            try {
                // Nó là của sản phẩm custom
                String size = String.valueOf(carts.get(i).getSize());
                cartProduct = new CartProductCustom(product, quantity, color, size);
            } catch (Exception e) {
                // Nó là của sản phẩm có sẵn
                Size size = (Size) carts.get(i).getSize();
                cartProduct = new CartProduct(product, quantity, color, size);
            }
            map.get(productId).add(cartProduct);
        }
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setShoppingCartMap(map);
        return shoppingCart;
    }

    @Override
    public void deleteByCartIdAndIdProduct(int cartId, Integer[] productIds) {
        String sql = "DELETE FROM cart_items WHERE cart_id = ? AND product_id = ?";
        for (int i = 0; i < productIds.length; i++) {
            GeneralDAOImp.executeAllTypeUpdate(sql, cartId, productIds[i]);
        }
    }

    @Override
    public void update(Map<Integer, List<AbstractCartProduct>> change) {
        System.out.println(change);
    }

    public static class Cart {
        private int productId;
        private int colorId;
        private int quantity;
        private Object size;

        public Cart() {
        }

        public Cart(int productId, int colorId, int quantity, Object size) {
            this.productId = productId;
            this.colorId = colorId;
            this.quantity = quantity;
            this.size = size;
        }

        public int getProductId() {
            return productId;
        }

        public void setProductId(int productId) {
            this.productId = productId;
        }

        public int getColorId() {
            return colorId;
        }

        public void setColorId(int colorId) {
            this.colorId = colorId;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public Object getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
        }
    }

//    public double getMinPriceApplyVoucherByCode(String code){
//        String sql = "SELECT minimumPrice FROM vouchers WHERE code = ?";
//        return GeneralDao.executeQueryWithSingleTable(sql, Voucher.class, code).get(0).getMinimumPrice();
//    }
}
