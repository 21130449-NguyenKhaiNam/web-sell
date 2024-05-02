package controller.shoppingCart;

import models.shoppingCart.AbstractCartProduct;
import models.shoppingCart.CartProduct;
import models.shoppingCart.ShoppingCart;
import models.User;
import models.Voucher;
import org.json.JSONObject;
import services.ShoppingCartServices;
import utils.FormatCurrency;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "DecreaseQuantityController", value = "/DecreaseQuantity")
public class DecreaseQuantityController extends HttpServlet {

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        int productId = 0;
        int cartProductIndex = 0;
        HttpSession session = request.getSession(true);
        User userAuth = (User) session.getAttribute("auth");
        String userIdCart = String.valueOf(userAuth.getId());
        ShoppingCart cart = (ShoppingCart) session.getAttribute(userIdCart);
        try {
            productId = Integer.parseInt((String) request.getAttribute("productId"));
            cartProductIndex = Integer.parseInt((String) request.getAttribute("cartProductIndex"));
        } catch (NumberFormatException exception) {
            exception.printStackTrace();
        }
        cart.decrease(productId, cartProductIndex);
        String code = (String) session.getAttribute("promotionCode");
        JSONObject jsonObject = new JSONObject();
        response.setContentType("application/json");

        if (code != null) {
//            Voucher voucher = cart.getVoucherApplied();
//            if (voucher == null) {
//                voucher = ShoppingCartServices.getINSTANCE().getValidVoucherApply(code);
//            }

//            if (cart.getTemporaryPrice() < voucher.getMinimumPrice()) {
//                double minPriceToApply = voucher.getMinimumPrice();
//                double currentTempPrice = cart.getTemporaryPrice();
//
//                double priceBuyMore = minPriceToApply - currentTempPrice;
//                String priceBuyMoreFormat = FormatCurrency.vietNamCurrency(priceBuyMore);
//                session.removeAttribute("successApplied");
//                cart.setVoucherApplied(null);
//                session.setAttribute("failedApply", "Bạn chưa đủ điều kiện để áp dụng mã " + code + ". Hãy mua thêm " + priceBuyMoreFormat);
//            }
        }

        session.setAttribute(userIdCart, cart);

        AbstractCartProduct cartProduct = cart.getShoppingCartMap().get(productId).get(cartProductIndex);
        int newQuantity = cartProduct.getQuantity();
        String newSubtotalFormat = cartProduct.subtotalFormat();
        String newTemporaryPriceFormat = cart.temporaryPriceFormat();
//        String discountPriceFormat = cart.discountPriceFormat();
//        String newTotalPriceFormat = cart.totalPriceFormat(false);

        jsonObject.put("newQuantity", newQuantity);
        jsonObject.put("newSubtotalFormat", newSubtotalFormat);
        jsonObject.put("newTemporaryPriceFormat", newTemporaryPriceFormat);
//        jsonObject.put("newTotalPriceFormat", newTotalPriceFormat);
//        jsonObject.put("discountPrice", cart.getDiscountPrice());

        if(session.getAttribute("failedApply") != null){
            jsonObject.put("failedApply", session.getAttribute("failedApply"));
        }else {
            jsonObject.remove("failedApply");
//            jsonObject.put("discountPriceFormat", discountPriceFormat);
        }
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