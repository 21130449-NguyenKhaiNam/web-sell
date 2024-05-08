package controller.shoppingCart;

import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import models.User;
import models.shoppingCart.AbstractCartProduct;
import models.shoppingCart.ShoppingCart;
import services.ShoppingCartServices;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import java.util.List;
import java.util.Map;

@WebListener
public class HandelCart implements HttpSessionAttributeListener {
    private ShoppingCartServices services = ShoppingCartServices.getINSTANCE();
    private User user;
    private ShoppingCart cart;
    private boolean isReplaceReal = true;

    /**
     * Xử lý sự kiện khi session bị thay đổi
     *
     * @param event the HttpSessionBindingEvent containing the session
     *              and the name and (old) value of the attribute that was replaced
     */
    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        if (isReplaceReal && (event.getValue() instanceof ShoppingCart && user != null)) {
            Integer cartId = Integer.parseInt(event.getName());
            ShoppingCart newCart = (ShoppingCart) event.getValue();
            Map<Integer, List<AbstractCartProduct>> source = cart.getShoppingCartMap();
            Map<Integer, List<AbstractCartProduct>> target = newCart.getShoppingCartMap();
            // Tìm sự khác biệt
            MapDifference<Integer, List<AbstractCartProduct>> diff = Maps.difference(source, target);
            HttpSession session = event.getSession();
            if (!diff.entriesOnlyOnLeft().isEmpty()) {
                // Có sản phẩm bị loại bỏ
                Map<Integer, List<AbstractCartProduct>> onlyLeft = diff.entriesOnlyOnRight();
                Integer[] productIds = new Integer[onlyLeft.size()];
                services.deleteByCartIdAndIdProduct(cartId, onlyLeft.keySet().toArray(productIds));
                source.clear();
                source.putAll(newCart.getShoppingCartMap());
            } else if (!diff.entriesOnlyOnRight().isEmpty()) {
                // Có sản phẩm thêm vào
                Map<Integer, List<AbstractCartProduct>> onlyRight = diff.entriesOnlyOnRight();
                services.insertCart(cartId, user.getId(), onlyRight);
                source.putAll(onlyRight);
            } else {
                // Có nội dung thay đổi trong cart
                System.out.println("Change >> ");
            }
            isReplaceReal = false;
            session.setAttribute(cartId + "", newCart);
        } else {
            isReplaceReal = true;
        }
    }

    /**
     * Xử lý khi session được thêm mới
     *
     * @param event the HttpSessionBindingEvent containing the session
     *              and the name and value of the attribute that was added
     */
    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        String name = event.getName();
        if (name.equals("auth")) {
            // Ghi lại người dùng đăng nhập
            user = (User) event.getValue();
        } else if (event.getValue() instanceof ShoppingCart && user != null) {
            ShoppingCart newCart = (ShoppingCart) event.getValue();
            HttpSession session = event.getSession();
            // Mã được hệ thống khởi tạo
            int cartId = Integer.parseInt(name);
            // Nếu lần đầu hệ thống chạy
            if (cart == null) {
                // Tìm kiếm giỏ hàng trong DB
                int cartIdDb = services.findCartByUserId(user.getId()); // Số âm là không có
                // Xử lý nếu có
                if (cartIdDb > 0) {
                    cartId = cartIdDb;
                    cart = services.findCartByCartId(cartId);
                    newCart.getShoppingCartMap().putAll(cart.getShoppingCartMap());
                } else {
                    cart = new ShoppingCart();
                    cart.getShoppingCartMap().putAll(newCart.getShoppingCartMap());
                    services.insertCart(cartId, user.getId(), cart.getShoppingCartMap());
                }
                isReplaceReal = true;
                session.setAttribute(name, newCart);
            }
        }
    }
}
