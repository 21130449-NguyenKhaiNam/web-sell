package models;

import utils.FormatCurrency;
import utils.ProductFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ShoppingCart {
    private static HashMap<Integer, List<CartProduct>> shoppingCartMap = new HashMap<>();
    private Voucher voucherApplied;

    public ShoppingCart() {
    }

    public HashMap<Integer, List<CartProduct>> getShoppingCartMap() {
        return shoppingCartMap;
    }

    public void setShoppingCartMap(HashMap<Integer, List<CartProduct>> shoppingCartMap) {
        this.shoppingCartMap = shoppingCartMap;
    }

    public Voucher getVoucherApplied() {
        return voucherApplied;
    }

    public void setVoucherApplied(Voucher voucherApplied) {
        this.voucherApplied = voucherApplied;
    }

    // Dùng cho trang productDetail khi người dùng chọn số lượng và nhấn nút "thêm vào giỏ hàng"
    public void add(int productId, int quantity, Color color, Size size) {
        if (shoppingCartMap.containsKey(productId)) {
            List<CartProduct> listCartProducts = shoppingCartMap.get(productId);
            Product product = ProductFactory.getProductById(productId);
            CartProduct cartProduct = new CartProduct(product, quantity, color, size);
            if(!listCartProducts.contains(cartProduct)){
                listCartProducts.add(cartProduct);
            }else {
                int currentIndex = listCartProducts.indexOf(cartProduct);
                int currentQuantity = listCartProducts.get(currentIndex).getQuantity();
                cartProduct.setQuantity(currentQuantity + quantity);
                listCartProducts.set(currentIndex, cartProduct);
            }
        } else {
            List<CartProduct> listCartProducts = new ArrayList<>();
            Product product = ProductFactory.getProductById(productId);
            if(product == null){
                return;
            }else{
                CartProduct cartProduct = new CartProduct(product, quantity, color, size);
                listCartProducts.add(cartProduct);
                shoppingCartMap.put(productId, listCartProducts);
            }
        }
    }

    // Xử lý cập nhật số lượng của sản phẩm còn lại trong kho
    //  public void update(int productId, int quantity) {}

    // Dùng cho trang shoppingCart khi người dùng nhấn nút "+" để tăng số lượng
    public void increase(int productId, int cartProductIndex) {
        if(shoppingCartMap.containsKey(productId)){
            List<CartProduct> listCartProducts = shoppingCartMap.get(productId);
            int currentQuantity = listCartProducts.get(cartProductIndex).getQuantity();
            CartProduct cartProduct = listCartProducts.get(cartProductIndex);
            cartProduct.setQuantity(currentQuantity + 1);
            System.out.println(cartProduct);
            listCartProducts.set(cartProductIndex, cartProduct);
        }
    }

    // Dùng cho trang shoppingCart khi người dùng nhấn nút "-" để giảm số lượng
//    public void decrease(int productId) {
//        if(shoppingCartMap.containsKey(productId)){
//            CartProduct cartProduct= shoppingCartMap.get(productId);
//            int currentQuantity = cartProduct.getQuantity();
//            int quantityDecreased = currentQuantity - 1;
//            if(quantityDecreased > 0){
//                cartProduct.setQuantity(quantityDecreased);
//            }
//        }
//    }
    public void decrease(int productId, int cartProductIndex) {
        if(shoppingCartMap.containsKey(productId)){
            List<CartProduct> listCartProducts = shoppingCartMap.get(productId);
            int currentQuantity = listCartProducts.get(cartProductIndex).getQuantity();
            CartProduct cartProduct = listCartProducts.get(cartProductIndex);
            int quantityDecreased = currentQuantity - 1;
            if(quantityDecreased > 0){
                cartProduct.setQuantity(quantityDecreased);
                System.out.println(cartProduct);
                listCartProducts.set(cartProductIndex, cartProduct);
            }
        }
    }

    // Dùng cho trang shoppingCart khi người dùng nhấn nút "xóa" để xóa sản phẩm ra khỏi giỏ hàng
    public void remove(int productId, int cartProductIndex) {
        if(shoppingCartMap.containsKey(productId)){
            List<CartProduct> listCartProducts = shoppingCartMap.get(productId);
            CartProduct cartProductTarget = listCartProducts.get(cartProductIndex);
            listCartProducts.remove(cartProductTarget);
            if(listCartProducts.isEmpty()){
                shoppingCartMap.remove(productId);
            }
        }
    }

    public double getTemporaryPrice(){
        double temporaryPrice = 0;
        for(int productId : shoppingCartMap.keySet()){
            for (CartProduct cartProduct : shoppingCartMap.get(productId)){
                temporaryPrice += cartProduct.getSubtotal();
            }
        }
        return temporaryPrice;
    }

    public double getDiscountPrice(){
        if(voucherApplied != null){
            return getTemporaryPrice() * voucherApplied.getDiscountPercent();
        }
        return 0;
    }

    public double getTotalPrice(){
        return getTemporaryPrice() - getDiscountPrice();
    }

    public String temporaryPriceFormat(){
        return FormatCurrency.vietNamCurrency(getTemporaryPrice());
    }

    public String discountPriceFormat(){
        return FormatCurrency.vietNamCurrency(getDiscountPrice());
    }

    public String totalPriceFormat(){
        return FormatCurrency.vietNamCurrency(getTotalPrice());
    }

    public static int getTotalItems() {
        int totalItems = 0;
        for (int productId : shoppingCartMap.keySet()) {
            List<CartProduct> listCartProducts = shoppingCartMap.get(productId);
            totalItems += listCartProducts.size();
        }
        return totalItems;
    }

    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();
        Color color = new Color();
        color.setCodeColor("aaa");
        color.setId(1);
        color.setProductId(1);

        Size size = new Size();
        size.setNameSize("x");
        size.setSizePrice(100000);
        size.setId(1);
        size.setProductId(1);

        cart.add(1, 1, color, size);
        cart.add(1, 1, color, size);

        Product product1 = ProductFactory.getProductById(1);
        Product product2 = ProductFactory.getProductById(1);

        System.out.println(shoppingCartMap);
    }
}