package controller.checkout;

import models.PaymentMethod;
import models.DeliveryMethod;
import models.shoppingCart.ShoppingCart;
import models.User;
import services.CheckoutServices;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CheckoutController", value = "/Checkout")
public class CheckoutController extends HttpServlet {

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");
        if(action != null){
            switch (action) {
                case "choiceDeliveryMethod" -> {
                    String deliveryMethodId = request.getParameter("deliveryMethodId");
                    request.setAttribute("deliveryMethodId", deliveryMethodId);
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("ChoiceDeliveryMethod");
                    requestDispatcher.forward(request, response);
                }
                case "choicePaymentMethod" -> {
                    String paymentMethodId = request.getParameter("paymentMethodId");
                    request.setAttribute("paymentMethodId", paymentMethodId);
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("ChoicePaymentMethod");
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
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("AddDeliveryInfo");
                    requestDispatcher.forward(request, response);
                }

                if(action.equals("editDeliveryInfo")){
                    String deliveryInfoKey = request.getParameter("deliveryInfoKey");
                    request.setAttribute("deliveryInfoKey", deliveryInfoKey);
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("EditDeliveryInfo");
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
                        RequestDispatcher requestDispatcher = request.getRequestDispatcher("RemoveDeliveryInfo");
                        requestDispatcher.forward(request, response);
                    }
                    case "choiceDeliveryInfo" -> {
                        RequestDispatcher requestDispatcher = request.getRequestDispatcher("ChoiceDeliveryInfo");
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

        User userAuth = (User) session.getAttribute("auth");
        String userIdCart = String.valueOf(userAuth.getId());
        ShoppingCart cart = (ShoppingCart) session.getAttribute(userIdCart);


        if(cart.getTotalPrice(false) < 5000000){
            if(cart.getDeliveryMethod() == null){
                DeliveryMethod deliveryMethodDefault = CheckoutServices.getINSTANCE().getDeliveryMethodById(1);
                cart.setDeliveryMethod(deliveryMethodDefault);
                session.setAttribute(userIdCart, cart);
            }
        }else {
            cart.setDeliveryMethod(null);
            session.setAttribute(userIdCart, cart);
        }

        if(cart.getPaymentMethod() == null){
            PaymentMethod paymentMethodDefault = CheckoutServices.getINSTANCE().getPaymentMethodById(1);
            cart.setPaymentMethod(paymentMethodDefault);
            session.setAttribute(userIdCart, cart);
        }

        request.setAttribute("listDeliveryMethod",listDeliveryMethod);
        request.setAttribute("listPaymentMethod", listPaymentMethod);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("checkout.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}