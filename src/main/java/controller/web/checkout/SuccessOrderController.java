package controller.web.checkout;

import com.google.gson.Gson;
import config.ConfigPage;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import models.User;
import models.shoppingCart.ShoppingCart;
import services.mail.MailPlaceOrderService;
import session.SessionManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;

@WebServlet(name = "SuccessOrderController", value = "/public/user/successOrder")
public class SuccessOrderController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);

        User user = SessionManager.getInstance(request, response).getUser();
        String userIdCart = String.valueOf(user.getId());

        ShoppingCart cart = (ShoppingCart) session.getAttribute(userIdCart);
        HashMap<String, String[]> parameter = new HashMap<>(request.getParameterMap());
        String[] models = parameter.get("order");
        if (models == null || models.length == 0) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(ConfigPage.USER_CART);
            requestDispatcher.forward(request, response);
        } else {
            Gson gson = new Gson();
            String model = models[0];
            // Chuyển đổi json sang obj
            OrderSuccess order = gson.fromJson(model, OrderSuccess.class);
            String dateOrder = LocalDate.now().toString();
            //        int totalPrice = (int)cart.getTotalPrice(true);

//        Lỗi do thiếu thông tin giao
//        String fullNameBuyer = cart.getDeliveryInfo().getFullName();
//        String emailBuyer = cart.getDeliveryInfo().getEmail();
//        String phoneBuyer = cart.getDeliveryInfo().getPhone();
//        String addressBuyer = cart.getDeliveryInfo().getAddress();
//        int paymentMethodId = cart.getPaymentMethod().getId();
//        Integer voucherId = null;
//        Integer deliveryMethodId = null;

//        IMailServices mailPlaceOrderService = new MailPlaceOrderService(cart, dateOrder, invoiceNo);
//        try {
//            mailPlaceOrderService.send();
//        } catch (MessagingException e) {
//            throw new RuntimeException(e);
//        }

//        if(paymentMethodId == 2 || paymentMethodId == 3){
//            session.setAttribute("totalPrice", totalPrice);
//            request.getRequestDispatcher("/public/user/vnpPay.jsp").forward(request,response);
//        }
//        else{

//            try {
//                if (cart.getVoucherApplied() != null) {
//                    voucherId = cart.getVoucherApplied().getId();
//                }

//                if (cart.getDeliveryMethod() != null) {
//                    deliveryMethodId = cart.getDeliveryMethod().getId();
//                }

//                CheckoutServices.getINSTANCE().addNewOrder(invoiceNo, user.getId(), dateOrder, fullNameBuyer, emailBuyer, phoneBuyer, addressBuyer, deliveryMethodId, paymentMethodId, voucherId);
//            } catch (NullPointerException exception) {
//                exception.printStackTrace();
//                CheckoutServices.getINSTANCE().addNewOrder(invoiceNo, user.getId(), dateOrder, fullNameBuyer, emailBuyer, phoneBuyer, addressBuyer, deliveryMethodId, paymentMethodId, voucherId);
//            }
//
//            for (int productId : cart.getShoppingCartMap().keySet()) {
//                for (AbstractCartProduct cartProduct : cart.getShoppingCartMap().get(productId)) {
//                    CheckoutServices.getINSTANCE().addEachOrderDetail(invoiceNo, productId, cartProduct.getProduct().getName(), cartProduct.sizeRequired(), cartProduct.getColor().getCodeColor(), cartProduct.getQuantity(), cartProduct.getPriorityPrice());
//                }
//            }

//            session.removeAttribute(userIdCart);
            session.removeAttribute("promotionCode");
            session.removeAttribute("failedApply");
            session.removeAttribute("successApplied");
//            request.setAttribute("invoiceNo", invoiceNo);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(ConfigPage.USER_SUCCESS_ORDER);
            requestDispatcher.forward(request, response);
//        }
        }
    }

    @Data
    @Getter
    @NoArgsConstructor
    class OrderSuccess {
        private int delivery;
        private int payment;
        private TempOrder[] orders;
        private int invoiceNo;

    }

    @Data
    @Getter
    @NoArgsConstructor
    class TempOrder {
        private int id;
        private String name;
        private String color;
        private String size;
        private int count;
        private double price;
    }

}