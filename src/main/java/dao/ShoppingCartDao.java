package dao;

import models.*;
import models.shoppingCart.AbstractCartProduct;
import models.shoppingCart.CartProduct;
import models.shoppingCart.CartProductCustom;
import models.shoppingCart.ShoppingCart;

import java.util.*;
import java.util.stream.Collectors;

public class ShoppingCartDao {
    public List<Voucher> getListVouchers() {
        String sql = "SELECT id, `code`, `description`, minimumPrice, discountPercent, expiryDate FROM vouchers WHERE expiryDate >= CURDATE() AND availableTurns > 0";
//        return GeneralDao.executeQueryWithSingleTable(sql, Voucher.class);
        return new ArrayList<>();
    }


    public Voucher getValidVoucherApply(String code) {
        String sql = "SELECT id, discountPercent, `code`, minimumPrice, `description` FROM vouchers WHERE expiryDate >= CURDATE() AND availableTurns > 0 AND `code` = ?";
        List<Voucher> listVouchers = GeneralDao.executeQueryWithSingleTable(sql, Voucher.class, code);
        if (!listVouchers.isEmpty()) {
            return listVouchers.get(0);
        } else {
            return null;
        }
    }

    public List<String> getListCodeOfVouchers() {
        List<String> listCodeOfVouchers = new ArrayList<>();
        String sql = "SELECT `code` FROM Vouchers";
        List<Voucher> listVouchers = GeneralDao.executeQueryWithSingleTable(sql, Voucher.class);
        for (Voucher voucher : listVouchers) {
            listCodeOfVouchers.add(voucher.getCode());
        }
        return listCodeOfVouchers;
    }


    public void insertCart(int cartId, int userId, Map<Integer, List<AbstractCartProduct>> mapCart) {
        String sql = "INSERT INTO cart (id, user_id) VALUES (?, ?)";
        GeneralDao.executeAllTypeUpdate(sql, cartId, userId);

        // Chưa có kích thước
        String sqlCart = "INSERT INTO cart_items (cart_id, product_id, color_id, quantity, size) VALUES (?, ?, ?, ?, ?)";
        List<Integer> productIds = new ArrayList<>(mapCart.keySet());
        for (int i = 0; i < mapCart.size(); i++) {
            Integer productId = productIds.get(i);
            List<AbstractCartProduct> products = mapCart.get(productId);
            for (int j = 0; j < products.size(); j++) {
                AbstractCartProduct cartProduct = products.get(j);
                GeneralDao.executeAllTypeUpdate(sqlCart, cartId, productId,
                        cartProduct.getColor().getId(), cartProduct.getQuantity(), cartProduct.getSize());
            }
        }
    }


    public int findCartByUserId(int userId) {
        String sql = "SELECT id FROM cart WHERE user_id = ?";
        List<Integer> cartIds = GeneralDao.executeQueryWithSingleTable(sql, models.shoppingCart.Cart.class, userId).stream()
                .map(cart -> cart.getId()).collect(Collectors.toList());
        if (cartIds.size() != 1) {
            // Lỗi
            return -1;
        } else {
            return cartIds.get(0);
        }
    }


    public ShoppingCart findById(int cartId) {
        String sql = "SELECT product_id, color_id, quantity, size FROM cart_items WHERE cart_id =?";
        ProductDao productDAOImp = new ProductDao();
        ColorDAO colorDao = new ColorDAO();
        List<Cart> carts = GeneralDao.executeQueryWithSingleTable(sql, Cart.class, cartId);
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


    public void deleteByCartIdAndIdProduct(int cartId, Integer[] productIds) {
        String sql = "DELETE FROM cart_items WHERE cart_id = ? AND product_id = ?";
        for (int i = 0; i < productIds.length; i++) {
            GeneralDao.executeAllTypeUpdate(sql, cartId, productIds[i]);
        }
    }


    public void update(Map<Integer, List<AbstractCartProduct>> change) {
        String sql = "UPDATE cart_items SET quantity=? WHERE product_id=? AND color_id=? AND size=?";
        for (Map.Entry<Integer, List<AbstractCartProduct>> entry : change.entrySet()) {
            int productId = entry.getKey();
            List<AbstractCartProduct> list = entry.getValue();
            for (int i = 0; i < list.size(); i++) {
                AbstractCartProduct product = list.get(i);
                int colorId = product.getColor().getId();
                GeneralDao.executeAllTypeUpdate(sql, product.getQuantity(), productId, colorId, product.getSize());
            }
        }
    }

    public List<CartItem> getCartProductByProductIdAndUserId(List<Integer> listProductId, Integer userId) {
        String sql = "SELECT cart_items.id, cart_id, product_id, color_id ,size, quantity \n" +
                "FROM cart_items JOIN cart ON cart_items.cart_id = cart.id " +
                "WHERE cart_items.product_id IN (" + convertToSQL(listProductId) + ") AND cart.user_id = ?";
        return GeneralDao.executeQueryWithSingleTable(sql, CartItem.class, userId);
    }

    private String convertToSQL(List<Integer> list) {
        String sql = "";
        for (int i = 0; i < list.size(); i++) {
            sql += list.get(i);
            if (i != list.size() - 1) {
                sql += ",";
            }
        }
        return sql;
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
