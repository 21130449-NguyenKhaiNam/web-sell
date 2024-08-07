package controller.web.checkout;

import com.google.gson.Gson;
import config.ConfigPage;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import models.User;
import models.shoppingCart.AbstractCartProduct;
import models.shoppingCart.ShoppingCart;
import org.apache.commons.collections.map.HashedMap;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            TempOrder[] tempOrders = order.getOrders();
            int totalPrice = 0;
            for (int i = 0; i < tempOrders.length; i++) {
                totalPrice += tempOrders[i].getPrice();
            }
            String dateOrder = LocalDate.now().toString();
            order.setDateOrder(dateOrder);
            int paymentMethodId = order.getPayment();
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
            // Gửi sang trang thanh toán onl
            if (paymentMethodId == 2 || paymentMethodId == 3) {
                request.setAttribute("totalPrice", totalPrice);
                session.setAttribute("orders", gson.toJson(order));
                request.getRequestDispatcher("/public/user/vnpPay.jsp").forward(request, response);
            } else {
                session.removeAttribute("promotionCode");
                session.removeAttribute("failedApply");
                session.removeAttribute("successApplied");
                request.setAttribute("invoiceNo", order.getInvoiceNo());
                // Cần điều chỉnh khi có voucher
                CheckoutServices.getINSTANCE().addNewOrder(order.getInvoiceNo(), user.getId(), order.getDateOrder(), user.getFullName(), user.getEmail(), user.getPhone(), order.getAddress(), order.getDelivery(), order.getPayment(), null);
                Map<Integer, Integer> counts = new HashMap<>();
                for (int i = 0; i < tempOrders.length; i++) {
                    int id = tempOrders[i].getId();
                    if(!counts.containsKey(id))
                        counts.put(id, 0);
                    cart.remove(id, tempOrders[i].getInd() - counts.get(id));
                    counts.put(id, counts.get(id) + 1);
                    CheckoutServices.getINSTANCE().addEachOrderDetail(order.getInvoiceNo(), tempOrders[i].getId(), tempOrders[i].getName(), tempOrders[i].getSize(), tempOrders[i].getColor(), tempOrders[i].getCount(), tempOrders[i].getPrice());
                }
                session.setAttribute(userIdCart, cart);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher(ConfigPage.USER_SUCCESS_ORDER);
                requestDispatcher.forward(request, response);
            }
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
//        }
        }
    }

    public void addItem(Map<Integer, List<AbstractCartProduct>> cart, Integer k, List<AbstractCartProduct> value) {
        if (!cart.containsKey(k)) {
            cart.put(k, new ArrayList<>());
        }
        cart.get(k).addAll(value);
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