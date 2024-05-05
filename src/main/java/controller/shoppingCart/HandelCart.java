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
import java.util.Set;

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
            if (event.getValue() instanceof ShoppingCart && user != null) {
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
                        if (cart == null) {
                            // Chỉ thực hiện lấy nếu chưa có
                            cart = services.findCartByCartId(cartId);
                            // id được tạo ra cho giỏ hàng chưa chắc là id đúng nên cần thiết lập lại
                            session.removeAttribute(name); // id hệ thống tạo
                        }
                    }
                }
                handelChangeCart(session, cartId, newCart);
            }
        }
    }

    private void handelChangeCart(HttpSession session, int cartId, ShoppingCart newCart) {
        if (cart == null || !cart.getShoppingCartMap().equals(newCart.getShoppingCartMap())) {
            // Không tìm thấy giỏ hàng trong db hoặc Có sự thay đổi trong giỏ hàng
            if (cart.getTotalItems() < newCart.getTotalItems()) {
                // Thêm sản phẩm
                cart = newCart;
                services.insertCart(cartId, user.getId(), cart);
            } else {
                Map<Integer, List<AbstractCartProduct>> source = cart.getShoppingCartMap();
                Map<Integer, List<AbstractCartProduct>> target = newCart.getShoppingCartMap();
                MapDifference<Integer, List<AbstractCartProduct>> diff = Maps.difference(source, target);
                if (cart.getTotalItems() > newCart.getTotalItems()) {
                    // Trường hợp có sản phẩm bị loại bỏ
                    // Những khóa bị loại bỏ
                    Set<Integer> keysOnlyInSource = diff.entriesOnlyOnLeft().keySet();
                    Integer[] productIds = new Integer[keysOnlyInSource.size()];
                    services.deleteByCartIdAndIdProduct(cartId,
                            keysOnlyInSource.toArray(productIds));
                } else {
                    // Trường hợp có sự thay đổi thông tin

                }
            }
        }
        session.setAttribute(cartId + "", cart);
    }
}
