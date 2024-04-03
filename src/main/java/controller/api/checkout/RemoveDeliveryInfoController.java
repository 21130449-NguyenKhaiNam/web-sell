package controller.api.checkout;

import models.*;
import models.shoppingCart.ShoppingCart;
import session.SessionManager;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "RemoveDeliveryInfoController", value = "/api/checkout/delivery/remove")
public class RemoveDeliveryInfoController extends HttpServlet {

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String deliveryInfoKey = (String) request.getAttribute("deliveryInfoKey");
        String statusChoice = (String) request.getAttribute("statusChoice");
        HttpSession session = request.getSession(true);
        DeliveryInfoStorage deliveryInfoStorage = (DeliveryInfoStorage) session.getAttribute("deliveryInfoStorage");
        deliveryInfoStorage.remove(deliveryInfoKey);

        User user = SessionManager.getInstance(request, response).getUser();
        String userIdCart = String.valueOf(user.getId());
        ShoppingCart cart = (ShoppingCart) session.getAttribute(userIdCart);
        if(statusChoice.equals("Đã chọn")) {
            DeliveryInfo deliveryInfoAuth = deliveryInfoStorage.getDeliveryInfoByKey("defaultDeliveryInfo");
            cart.setDeliveryInfo(deliveryInfoAuth);
            session.setAttribute(userIdCart, cart);
        }

        session.setAttribute("deliveryInfoStorage", deliveryInfoStorage);
        response.getWriter().write("Xóa thành công");
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