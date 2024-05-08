package controller.shoppingCart;

import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import models.Product;
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

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        // Xử lý khi một thuộc tính được thêm vào session
        String name = event.getName();
        if (name.equals("auth")) {
            user = (User) event.getValue();
        } else {
            if (event.getValue() instanceof ShoppingCart && user != null) {
                System.out.println("Handel cart success");
                // Nếu có thể ép thành giỏ hàng thì xử lý
                ShoppingCart newCart = (ShoppingCart) event.getValue();
                HttpSession session = event.getSession();
                // Nếu không tồn tại trong db mặc định lấy mã số đã được hệ thống tạo
                int cartId = Integer.parseInt(name);
                if (cart == null) {
                    // Lần đầu hoặc khởi tạo lại giỏ hàng
                    int cartIdDb = services.findCartByUserId(user.getId()); // Số âm là không có
                    if (cartIdDb > 0) {
                        // Xử lý nếu có cart trong db
                        cartId = cartIdDb;
                        cart = services.findCartByCartId(cartId);
                        // id được tạo ra cho giỏ hàng chưa chắc là id đúng nên cần thiết lập lại
                    }
                }
                handelChangeCart(session, cartId, newCart);
            }
        }
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        // Xử lý khi một thuộc tính được thêm vào session
        String name = event.getName();
        if (name.equals("auth")) {
            user = (User) event.getValue();
        } else {
            if (event.getValue() instanceof ShoppingCart && user != null) {
                System.out.println("Handel cart success");
                // Nếu có thể ép thành giỏ hàng thì xử lý
                ShoppingCart newCart = (ShoppingCart) event.getValue();
                HttpSession session = event.getSession();
                // Nếu không tồn tại trong db mặc định lấy mã số đã được hệ thống tạo
                int cartId = Integer.parseInt(name);
                if (cart == null) {
                    // Lần đầu hoặc khởi tạo lại giỏ hàng
                    int cartIdDb = services.findCartByUserId(user.getId()); // Số âm là không có
                    if (cartIdDb > 0) {
                        // Xử lý nếu có cart trong db
                        cartId = cartIdDb;
                        cart = services.findCartByCartId(cartId);
                        // id được tạo ra cho giỏ hàng chưa chắc là id đúng nên cần thiết lập lại
                    }
                }
                handelChangeCart(session, cartId, newCart);
            }
        }
    }

    private void handelChangeCart(HttpSession session, int cartId, ShoppingCart newCart) {
        System.out.println("Cart Db >> " + cart.getShoppingCartMap());
        System.out.println("Default cart >> " + newCart.getShoppingCartMap());
        if (cart == null) {
            cart = newCart;
            session.setAttribute(cartId + "", newCart);
        } else {
            Map<Integer, List<AbstractCartProduct>> source = cart.getShoppingCartMap();
            Map<Integer, List<AbstractCartProduct>> target = newCart.getShoppingCartMap();
            // Tìm sự khác biệt
            MapDifference<Integer, List<AbstractCartProduct>> diff = Maps.difference(source, target);
            if (!diff.entriesOnlyOnLeft().isEmpty()) {
                // Tìm thấy sản phẩm chỉ tồn tại trong db
                if(target.isEmpty()) {
                    // Chỉ là lần khởi tạo đầu nên chưa có sản phẩm
                    session.setAttribute(cartId + "", cart);
                } else {
                    // Có sản phẩm bị loại bỏ
                    Map<Integer, List<AbstractCartProduct>> onlyLeft = diff.entriesOnlyOnRight();
                    System.out.println("Remove >> " + onlyLeft);
                    Integer[] productIds = new Integer[onlyLeft.size()];
                    services.deleteByCartIdAndIdProduct(cartId, onlyLeft.keySet().toArray(productIds));
                    cart = newCart;
                    session.setAttribute(cartId + "", newCart);
                }
            } else if (!diff.entriesOnlyOnRight().isEmpty()) {
                // Có sản phẩm thêm vào
                Map<Integer, List<AbstractCartProduct>> onlyRight = diff.entriesOnlyOnRight();
                System.out.println("Add >> " + onlyRight);
                services.insertCart(cartId, user.getId(), onlyRight);
                source.putAll(onlyRight);
                session.setAttribute(cartId + "", cart);
            } else {
                // Có nội dung thay đổi trong cart
                System.out.println("Change >> ");
            }
        }
    }
}
