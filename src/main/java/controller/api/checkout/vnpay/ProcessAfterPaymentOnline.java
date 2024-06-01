package controller.api.checkout.vnpay;

import config.ConfigPage;
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

//        if(vnp_TransactionStatus == 00){
//            HttpSession session = request.getSession();
//            User user = SessionManager.getInstance(request, response).getUser();
//            String userIdCart = String.valueOf(user.getId());
//
//            ShoppingCart cart = (ShoppingCart) session.getAttribute(userIdCart);
//            int totalPrice = (int)cart.getTotalPrice(true);
//
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
        }
//
//        request.getRequestDispatcher(ConfigPage.PRODUCT_BUYING).forward(request, response);
//    }
}