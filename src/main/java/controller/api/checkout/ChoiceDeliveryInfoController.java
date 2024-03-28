package controller.api.checkout;

import models.DeliveryInfo;
import models.DeliveryInfoStorage;
import models.shoppingCart.ShoppingCart;
import models.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ChoiceDeliveryInfoController", value = "/api/checkout/delivery/choice")
public class ChoiceDeliveryInfoController extends HttpServlet {

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String deliveryInfoKey = (String) request.getAttribute("deliveryInfoKey");
        HttpSession session = request.getSession(true);
        User userAuth = (User) session.getAttribute("auth");
        String userIdCart = String.valueOf(userAuth.getId());
        DeliveryInfoStorage deliveryInfoStorage = (DeliveryInfoStorage) session.getAttribute("deliveryInfoStorage");
        DeliveryInfo deliveryInfo = deliveryInfoStorage.getDeliveryInfoByKey(deliveryInfoKey);
        ShoppingCart cart = (ShoppingCart) session.getAttribute(userIdCart);
        cart.setDeliveryInfo(deliveryInfo);
        session.setAttribute(userIdCart, cart);

        response.getWriter().write("Đã chọn");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}