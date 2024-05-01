package controller.shoppingCart;

import models.User;
import models.shoppingCart.ShoppingCart;
import services.ShoppingCartServices;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Method;
import java.util.List;

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
            try {
                // Nếu có thể ép thành giỏ hàng thì xử lý
                ShoppingCart newCart = (ShoppingCart) event.getValue();
                // Lần đầu hoặc khởi tạo lại giỏ hàng
                if(cart == null) {
                    int cartId = services.findCartByUserId(user.getId());
                    if(cartId > 0) {
                        // Xử lý nếu có cart trong db
                    } else {
                        // Xử lý nếu không có
                    }
                }
            } catch (Exception e) {
                System.out.println("Handel Cart >> Không bắt sự kiện đối với session khác ngoài cart");
            }
        }
    }
}
