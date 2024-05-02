package controller.shoppingCart;

import models.User;
import models.shoppingCart.ShoppingCart;
import services.ShoppingCartServices;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

@WebListener
public class HandelCart implements HttpSessionAttributeListener {
    private ShoppingCartServices services = ShoppingCartServices.getINSTANCE();
    private User user;
    private ShoppingCart cart;

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        // Xử lý khi một thuộc tính được thêm vào session
        String name = event.getName();
        if (name.equals("auth")) {
            user = (User) event.getValue();
        } else {
            if(event.getValue() instanceof  ShoppingCart) {
                // Nếu có thể ép thành giỏ hàng thì xử lý
                ShoppingCart newCart = (ShoppingCart) event.getValue();
                HttpSession session = event.getSession();
                // Lần đầu hoặc khởi tạo lại giỏ hàng
                if (cart == null) {
                    int cartId = services.findCartByUserId(user.getId());
                    if (cartId > 0) {
                        // Xử lý nếu có cart trong db
                        if (cart == null) {
                            // Chỉ thực hiện lấy nếu chưa có
                            cart = services.findCartByCartId(cartId);
                            // id được tạo ra cho giỏ hàng chưa chắc là id đúng nên cần thiết lập lại
                            session.removeAttribute(name); // id hệ thống tạo
                            session.setAttribute(cartId + "", null);
                        }

                        handelChangeCart(session, cartId, newCart);
                    } else {
                        // Xử lý nếu không có
                        cartId = Integer.parseInt(name);
                        handelChangeCart(session, cartId, newCart);
                    }
                } else {
                    handelChangeCart(session, Integer.parseInt(name), newCart);
                }
            }
        }
    }

    private void handelChangeCart(HttpSession session, int cartId, ShoppingCart newCart) {
        if (cart == null || !cart.getShoppingCartMap().equals(newCart.getShoppingCartMap())) {
            // Không tìm thấy giỏ hàng trong db hoặc Có sự thay đổi trong giỏ hàng
            cart = newCart;
            services.insertCart(cartId, user.getId(), cart);
        }
        session.setAttribute(cartId + "", cart);
    }
}
