package controller.api.checkout;

import models.DeliveryMethod;
import models.shoppingCart.ShoppingCart;
import models.User;
import org.json.JSONObject;
import services.CheckoutServices;
import session.SessionManager;
import utils.FormatCurrency;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ChoiceDeliveryMethodController", value = "/api/checkout/delivery/method")
public class ChoiceDeliveryMethodController extends HttpServlet {

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        int deliveryMethodId = 0;
        try {
            deliveryMethodId = Integer.parseInt((String) request.getAttribute("deliveryMethodId"));
        }catch (NumberFormatException exception){
            exception.printStackTrace();
        }
        DeliveryMethod deliveryMethod = CheckoutServices.getINSTANCE().getDeliveryMethodById(deliveryMethodId);
        System.out.println(deliveryMethod);
        HttpSession session = request.getSession(true);
        User user = SessionManager.getInstance(request, response).getUser();
        String userIdCart = String.valueOf(user.getId());
        ShoppingCart cart = (ShoppingCart) session.getAttribute(userIdCart);
        cart.setDeliveryMethod(deliveryMethod);
        session.setAttribute(userIdCart, cart);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("newTotalPrice", cart.totalPriceFormat(true));
        jsonObject.put("shippingFee", FormatCurrency.vietNamCurrency(cart.getDeliveryMethod().getShippingFee()));
        response.setContentType("application/json");
        PrintWriter printWriter = response.getWriter();
        printWriter.print(jsonObject);

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