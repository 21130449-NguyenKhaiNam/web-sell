package controller.api.shoppingCart;

import models.User;
import models.shoppingCart.AbstractCartProduct;
import models.shoppingCart.ShoppingCart;
import org.json.JSONObject;
import services.ShoppingCartServices;
import session.SessionManager;
import utils.FormatCurrency;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "IncreaseQuantityController", value = "/api/cart/increase")
public class IncreaseQuantityController extends HttpServlet {

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productId = 0;
        int cartProductIndex = 0;
        HttpSession session = request.getSession(true);
        User user = SessionManager.getInstance(request, response).getUser();
        String userIdCart = String.valueOf(user.getId());
        ShoppingCart cart = (ShoppingCart) session.getAttribute(userIdCart);
        try {
            productId = Integer.parseInt((String) request.getAttribute("productId"));
            cartProductIndex = Integer.parseInt((String) request.getAttribute("cartProductIndex"));
        }catch (NumberFormatException exception){
            exception.printStackTrace();
        }

        cart.increase(productId, cartProductIndex);
        String code = (String) session.getAttribute("promotionCode");
        JSONObject jsonObject = new JSONObject();
        response.setContentType("application/json");

        if(code != null){
//            Voucher voucher = cart.getVoucherApplied();
//            if (voucher == null){
//                voucher = ShoppingCartServices.getINSTANCE().getValidVoucherApply(code);
//                double minPriceToApply = voucher.getMinimumPrice();
//                if (cart.getTemporaryPrice() >= minPriceToApply){
//                    session.removeAttribute("failedApply");
//                    cart.setVoucherApplied(voucher);
//                    session.setAttribute("successApplied", "Bạn đã áp dụng mã " + code + " thành công");
//                }else {
//                    double currentTempPrice = cart.getTemporaryPrice();
//                    double priceBuyMore = minPriceToApply - currentTempPrice;
//                    String priceBuyMoreFormat = FormatCurrency.vietNamCurrency(priceBuyMore);
//                    session.removeAttribute("successApplied");
//                    session.setAttribute("failedApply", "Bạn chưa đủ điều kiện để áp dụng mã " + code + ". Hãy mua thêm " + priceBuyMoreFormat);
//                }
//            }
        }

        session.setAttribute(userIdCart, cart);

        AbstractCartProduct cartProduct = cart.getShoppingCartMap().get(productId).get(cartProductIndex);
        int newQuantity = cartProduct.getQuantity();
        String newSubtotalFormat = cartProduct.subtotalFormat();
        String newTemporaryPriceFormat = cart.temporaryPriceFormat();
//        String newTotalPriceFormat = cart.totalPriceFormat(false);
//        String discountPriceFormat = cart.discountPriceFormat();

        jsonObject.put("newQuantity", newQuantity);
        jsonObject.put("newSubtotalFormat", newSubtotalFormat);
        jsonObject.put("newTemporaryPriceFormat", newTemporaryPriceFormat);
//        jsonObject.put("newTotalPriceFormat", newTotalPriceFormat);
        if(session.getAttribute("successApplied") != null){
            jsonObject.put("successApplied", session.getAttribute("successApplied"));
//            jsonObject.put("discountPriceFormat", discountPriceFormat);
        } else if (session.getAttribute("failedApply") != null) {
            jsonObject.put("failedApply", session.getAttribute("failedApply"));
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