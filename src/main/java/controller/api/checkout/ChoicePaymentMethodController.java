package controller.api.checkout;

import models.PaymentMethod;
import models.User;
import models.shoppingCart.ShoppingCart;
import org.json.JSONObject;
import services.CheckoutServices;
import session.SessionManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "ChoicePaymentMethodController", value = "/api/checkout/payment/method")
public class ChoicePaymentMethodController extends HttpServlet {

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        int paymentMethodId = 0;
        try {
            paymentMethodId = Integer.parseInt((String) request.getAttribute("paymentMethodId"));
        }catch (NumberFormatException exception){
            exception.printStackTrace();
        }

        PaymentMethod paymentMethod = CheckoutServices.getINSTANCE().getPaymentMethodById(paymentMethodId);
        System.out.println(paymentMethod);
        HttpSession session = request.getSession(true);
        User user = SessionManager.getInstance(request, response).getUser();
        String userIdCart = String.valueOf(user.getId());
        ShoppingCart cart = (ShoppingCart) session.getAttribute(userIdCart);
//        cart.setPaymentMethod(paymentMethod);
        session.setAttribute(userIdCart, cart);

        String contentForPay = (String) session.getAttribute("contentForPay");
        if(contentForPay == null){
            contentForPay = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase().substring(0, 10);
            session.setAttribute("contentForPay", contentForPay);
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("contentForPay", contentForPay);
        response.getWriter().print(jsonObject);
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