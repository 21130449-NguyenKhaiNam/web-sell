package controller.web.checkout;

import config.ConfigPage;
import models.User;
import models.shoppingCart.AbstractCartProduct;
import models.shoppingCart.ShoppingCart;
import services.CheckoutServices;
<<<<<<< HEAD
import services.mail.IMailServices;
import services.mail.MailPlaceOrderService;
=======
import services.IMailServices;
import services.MailPlaceOrderService;
>>>>>>> 21130449

import javax.mail.MessagingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet(name = "SuccessOrderController", value = "/SuccessOrder")
public class SuccessOrderController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        User userAuth = (User) session.getAttribute("auth");
        String userIdCart = String.valueOf(userAuth.getId());
        ShoppingCart cart = (ShoppingCart) session.getAttribute(userIdCart);
        int invoiceNo = Integer.parseInt(request.getParameter("invoiceNo"));
        String dateOrder = LocalDate.now().toString();
        String fullNameBuyer = cart.getDeliveryInfo().getFullName();
        String emailBuyer = cart.getDeliveryInfo().getEmail();
        String phoneBuyer = cart.getDeliveryInfo().getPhone();
        String addressBuyer = cart.getDeliveryInfo().getAddress();
        int paymentMethodId = cart.getPaymentMethod().getId();
        Integer voucherId = null;
        Integer deliveryMethodId = null;

        try {
            if (cart.getVoucherApplied() != null) {
                voucherId = cart.getVoucherApplied().getId();
            }

            if (cart.getDeliveryMethod() != null) {
                deliveryMethodId = cart.getDeliveryMethod().getId();
            }

            CheckoutServices.getINSTANCE().addNewOrder(invoiceNo, userAuth.getId(), dateOrder, fullNameBuyer, emailBuyer, phoneBuyer, addressBuyer, deliveryMethodId, paymentMethodId, voucherId);
        } catch (NullPointerException exception) {
            exception.printStackTrace();
            CheckoutServices.getINSTANCE().addNewOrder(invoiceNo, userAuth.getId(), dateOrder, fullNameBuyer, emailBuyer, phoneBuyer, addressBuyer, deliveryMethodId, paymentMethodId, voucherId);
        }

        for (int productId : cart.getShoppingCartMap().keySet()) {
            for (AbstractCartProduct cartProduct : cart.getShoppingCartMap().get(productId)) {
                CheckoutServices.getINSTANCE().addEachOrderDetail(invoiceNo, productId, cartProduct.getProduct().getName(), cartProduct.sizeRequired(), cartProduct.getColor().getCodeColor(), cartProduct.getQuantity(), cartProduct.getPriorityPrice());
            }
        }

        IMailServices mailPlaceOrderService = new MailPlaceOrderService(cart, dateOrder, invoiceNo);
        try {
            mailPlaceOrderService.send();
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

        session.removeAttribute(userIdCart);
        session.removeAttribute("promotionCode");
        session.removeAttribute("failedApply");
        session.removeAttribute("successApplied");
        request.setAttribute("invoiceNo", invoiceNo);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(ConfigPage.USER_SUCCESS_ORDER);
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}