package controller.api.checkout;

import config.ConfigPage;
import models.DeliveryMethod;
import models.PaymentMethod;
import models.User;
import models.shoppingCart.ShoppingCart;
import services.CheckoutServices;
import session.SessionManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CheckoutController", value = "/Checkout")
public class CheckoutController extends HttpServlet {

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String action = request.getParameter("action");
        if(action != null){
            switch (action) {
                case "choiceDeliveryMethod" -> {
                    String deliveryMethodId = request.getParameter("deliveryMethodId");
                    request.setAttribute("deliveryMethodId", deliveryMethodId);
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("/api/checkout/delivery/method");
                    requestDispatcher.forward(request, response);
                }
                case "choicePaymentMethod" -> {
                    String paymentMethodId = request.getParameter("paymentMethodId");
                    request.setAttribute("paymentMethodId", paymentMethodId);
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("/api/checkout/payment/method");
                    requestDispatcher.forward(request, response);
                }
            }

            if(action.equals("addDeliveryInfo") || action.equals("editDeliveryInfo")){
                String fullName = request.getParameter("fullName");
                String email = request.getParameter("email");
                String phone = request.getParameter("phone");
                String address = request.getParameter("address");
                request.setAttribute("fullName", fullName);
                request.setAttribute("email", email);
                request.setAttribute("phone", phone);
                request.setAttribute("address", address);

                if(action.equals("addDeliveryInfo")){
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("/api/checkout/delivery/add");
                    requestDispatcher.forward(request, response);
                }

                if(action.equals("editDeliveryInfo")){
                    String deliveryInfoKey = request.getParameter("deliveryInfoKey");
                    request.setAttribute("deliveryInfoKey", deliveryInfoKey);
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("/api/checkout/delivery/edit");
                    requestDispatcher.forward(request, response);
                }
            }
        }else{
            String typeEdit = request.getParameter("typeEdit");
            if(typeEdit != null) {
                String deliveryInfoKey = request.getParameter("deliveryInfoKey");
                request.setAttribute("deliveryInfoKey", deliveryInfoKey);
                switch (typeEdit) {
                    case "removeDeliveryInfo" -> {
                        String statusChoice = request.getParameter("statusChoice");
                        request.setAttribute("statusChoice", statusChoice);
                        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/api/checkout/delivery/remove");
                        requestDispatcher.forward(request, response);
                    }
                    case "choiceDeliveryInfo" -> {
                        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/api/checkout/delivery/choice");
                        requestDispatcher.forward(request, response);
                    }
                }
            }
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<DeliveryMethod> listDeliveryMethod = CheckoutServices.getINSTANCE().getAllInformationDeliveryMethod();
        List<PaymentMethod> listPaymentMethod = CheckoutServices.getINSTANCE().getAllPaymentMethod();
        HttpSession session = request.getSession();

        User user = SessionManager.getInstance(request, response).getUser();
        String userIdCart = String.valueOf(user.getId());
        ShoppingCart cart = (ShoppingCart) session.getAttribute(userIdCart);


//        if(cart.getTotalPrice(false) < 5000000){
//            if(cart.getDeliveryMethod() == null){
//                DeliveryMethod deliveryMethodDefault = CheckoutServices.getINSTANCE().getDeliveryMethodById(1);
//                cart.setDeliveryMethod(deliveryMethodDefault);
//                session.setAttribute(userIdCart, cart);
//            }
//        }else {
//            cart.setDeliveryMethod(null);
//            session.setAttribute(userIdCart, cart);
//        }
//
//        if(cart.getPaymentMethod() == null){
//            PaymentMethod paymentMethodDefault = CheckoutServices.getINSTANCE().getPaymentMethodById(1);
//            cart.setPaymentMethod(paymentMethodDefault);
//            session.setAttribute(userIdCart, cart);
//        }

        request.setAttribute("listDeliveryMethod",listDeliveryMethod);
        request.setAttribute("listPaymentMethod", listPaymentMethod);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(ConfigPage.USER_CHECKOUT);
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}