package controller.api.shoppingCart;

import models.User;
import models.shoppingCart.ShoppingCart;
import org.json.JSONObject;
import models.Voucher;
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

@WebServlet(name = "DeleteCartProductController", value = "/api/cart/delete")
public class DeleteCartProductController extends HttpServlet {

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int productId = 0;
        int cartProductIndex = 0;
        HttpSession session = request.getSession(true);
        User user = SessionManager.getInstance(request, response).getUser();
        String userIdCart = String.valueOf(user.getId());
        ShoppingCart cart = (ShoppingCart) session.getAttribute(userIdCart);
        try {
            if(request.getAttribute("productId") != null){
                productId = Integer.parseInt((String) request.getAttribute("productId"));
                cartProductIndex = Integer.parseInt((String) request.getAttribute("cartProductIndex"));
            }
        } catch (NumberFormatException exception) {
            exception.printStackTrace();
        }

        cart.remove(productId, cartProductIndex);
        String code = (String) session.getAttribute("promotionCode");
        JSONObject jsonObject = new JSONObject();
        response.setContentType("application/json");

        if(code != null){
//            Voucher voucher = cart.getVoucherApplied();
//            if (voucher == null){
//                voucher = ShoppingCartServices.getINSTANCE().getValidVoucherApply(code);
//            }

//            if (cart.getTemporaryPrice() < voucher.getMinimumPrice()){
//                double minPriceToApply = voucher.getMinimumPrice();
//                double currentTempPrice = cart.getTemporaryPrice();
//
//                double priceBuyMore = minPriceToApply - currentTempPrice;
//                String priceBuyMoreFormat = FormatCurrency.vietNamCurrency(priceBuyMore);
//                session.removeAttribute("successApplied");
//                cart.setVoucherApplied(null);
//                session.setAttribute("failedApply", "Bạn chưa đủ điều kiện để áp dụng mã " + code + ". Hãy mua thêm " + priceBuyMoreFormat);
//                jsonObject.put("failedApply", session.getAttribute("failedApply"));
//            }
        }

        session.setAttribute(userIdCart, cart);

        String newTemporaryPriceFormat = cart.temporaryPriceFormat();
//        String discountPriceFormat = cart.discountPriceFormat();
//        String newTotalPriceFormat = cart.totalPriceFormat(false);
        int newTotalItems = cart.getTotalItems();

        jsonObject.put("newTemporaryPriceFormat", newTemporaryPriceFormat);
//        jsonObject.put("newTotalPriceFormat", newTotalPriceFormat);
        jsonObject.put("newTotalItems", newTotalItems);
//        jsonObject.put("discountPrice", cart.getDiscountPrice());

        if (session.getAttribute("failedApply") != null) {
            jsonObject.put("failedApply", session.getAttribute("failedApply"));
        } else {
            jsonObject.remove("failedApply");
//            jsonObject.put("discountPriceFormat", discountPriceFormat);
        }
        response.getWriter().print(jsonObject);
        response.getWriter().flush();
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