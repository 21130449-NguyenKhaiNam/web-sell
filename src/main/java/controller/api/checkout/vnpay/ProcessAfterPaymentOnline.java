package controller.api.checkout.vnpay;

import com.google.gson.Gson;
import config.ConfigPage;
import controller.web.checkout.SuccessOrderController;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import models.DeliveryMethod;
import models.PaymentMethod;
import models.User;
import models.shoppingCart.AbstractCartProduct;
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
import java.time.LocalDate;
import java.util.List;

@WebServlet(name = "ProcessAfterPaymentOnline", value = "/ProcessAfterPaymentOnline")
public class ProcessAfterPaymentOnline extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int vnp_TransactionStatus = Integer.parseInt(request.getParameter("vnp_TransactionStatus"));

        if(vnp_TransactionStatus == 00){
            HttpSession session = request.getSession();
            User user = SessionManager.getInstance(request, response).getUser();
            String userIdCart = String.valueOf(user.getId());

            ShoppingCart cart = (ShoppingCart) session.getAttribute(userIdCart);
//            int totalPrice = (int)cart.getTotalPrice(true);
            Gson gson = new Gson();
            // Chuyển đổi json sang obj
            OrderSuccess order = gson.fromJson((String) session.getAttribute("orders"), OrderSuccess.class);
            TempOrder[] tempOrders = order.getOrders();
            int totalPrice = 0;
            for (int i = 0; i < tempOrders.length; i++) {
                totalPrice += tempOrders[i].getPrice();
            }

//            int invoiceNo = Integer.parseInt(request.getParameter("invoiceNo"));
//            String dateOrder = LocalDate.now().toString();
//            String fullNameBuyer = cart.getDeliveryInfo().getFullName();
//            String emailBuyer = cart.getDeliveryInfo().getEmail();
//            String phoneBuyer = cart.getDeliveryInfo().getPhone();
//            String addressBuyer = cart.getDeliveryInfo().getAddress();
//            int paymentMethodId = cart.getPaymentMethod().getId();
//            Integer voucherId = null;
//            Integer deliveryMethodId = null;
//
//            try {
//                if (cart.getVoucherApplied() != null) {
//                    voucherId = cart.getVoucherApplied().getId();
//                }
//
//                if (cart.getDeliveryMethod() != null) {
//                    deliveryMethodId = cart.getDeliveryMethod().getId();
//                }
//
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
//
//
//            session.removeAttribute(userIdCart);
//            session.removeAttribute("promotionCode");
//            session.removeAttribute("failedApply");
//            session.removeAttribute("successApplied");
            session.removeAttribute("promotionCode");
            session.removeAttribute("failedApply");
            session.removeAttribute("successApplied");
            request.setAttribute("invoiceNo", order.getInvoiceNo());
            // Cần điều chỉnh khi có voucher
            CheckoutServices.getINSTANCE().addNewOrder(order.getInvoiceNo(), user.getId(), order.getDateOrder(), user.getFullName(), user.getEmail(), user.getPhone(), order.getAddress(), order.getDelivery(), order.getPayment(), null);
            for (int i = 0; i < tempOrders.length; i++) {
                cart.remove(tempOrders[i].getId(), tempOrders[i].getInd());
                CheckoutServices.getINSTANCE().addEachOrderDetail(order.getInvoiceNo(), tempOrders[i].getId(), tempOrders[i].getName(), tempOrders[i].getSize(), tempOrders[i].getColor(), tempOrders[i].getCount(), tempOrders[i].getPrice());
            }
            session.setAttribute(userIdCart, cart);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(ConfigPage.USER_SUCCESS_ORDER);
            requestDispatcher.forward(request, response);
        }
//
//        request.getRequestDispatcher(ConfigPage.PRODUCT_BUYING).forward(request, response);
    }

    @Data
    @Getter
    @NoArgsConstructor
    class OrderSuccess {
        private int delivery;
        private int payment;
        private String address;
        private TempOrder[] orders;
        private int invoiceNo;
        private String dateOrder;
    }

    @Data
    @Getter
    @NoArgsConstructor
    class TempOrder {
        private int id;
        private int ind;
        private String name;
        private String color;
        private String size;
        private int count;
        private double price;
    }
}