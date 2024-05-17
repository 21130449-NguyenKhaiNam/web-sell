package controller.api.shoppingCart;

import models.User;
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
import java.io.IOException;

@WebServlet(name = "ApplyVoucherController", value = "/ApplyVoucher")
public class ApplyVoucherController extends HttpServlet {

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        request.setCharacterEncoding("UTF-8");
//        List<String> listCodeOfVouchers = ShoppingCartServices.getINSTANCE().getListCodeOfVouchers();
//        String code = (String) request.getAttribute("promotionCode");
//        double temporaryPrice = (double) request.getAttribute("temporaryPrice");
//
//        HttpSession session = request.getSession(true);
//        User userAuth = (User) session.getAttribute("auth");
//        String userIdCart = String.valueOf(userAuth.getId());
//        ShoppingCart cart = (ShoppingCart) session.getAttribute(userIdCart);
//
//        JSONObject jsonObject = new JSONObject();
//        response.setContentType("application/json");
//
//        if (listCodeOfVouchers.contains(code)) {
//            //Check null voucher
//            Voucher voucher = ShoppingCartServices.getINSTANCE().getValidVoucherApply(code);
////            double minPriceToApply = ShoppingCartServices.getINSTANCE().getMinPriceApplyVoucherByCode(code);
//            double minPriceToApply = voucher.getMinimumPrice();
////            if(cart.getTemporaryPrice() >= voucher.getMinimumPrice()){
//            if (cart.getTemporaryPrice() >= minPriceToApply) {
//                cart.setVoucherApplied(voucher);
//                session.setAttribute(userIdCart, cart);
//                session.removeAttribute("failedApply");
//                session.setAttribute("successApplied", "Bạn đã áp dụng mã " + code + " thành công");
//                jsonObject.put("successApplied", session.getAttribute("successApplied"));
//                jsonObject.put("discountPriceFormat", cart.discountPriceFormat());
//                jsonObject.put("newTotalPriceFormat", cart.totalPriceFormat(false));
//            } else {
//                double priceBuyMore = minPriceToApply - temporaryPrice;
//                String priceBuyMoreFormat = FormatCurrency.vietNamCurrency(priceBuyMore);
//                cart.setVoucherApplied(null);
//                session.setAttribute(userIdCart, cart);
//                session.removeAttribute("successApplied");
//                session.setAttribute("failedApply", "Bạn chưa đủ điều kiện để áp dụng mã " + code + ". Hãy mua thêm " + priceBuyMoreFormat);
//                jsonObject.put("failedApply", session.getAttribute("failedApply"));
//            }
//        } else {
//            cart.setVoucherApplied(null);
//            session.setAttribute(userIdCart, cart);
//            session.removeAttribute("successApplied");
//            session.setAttribute("failedApply", "Mã " + code + " mà bạn nhập không tồn tại");
//            jsonObject.put("failedApply", session.getAttribute("failedApply"));
//        }
//        session.setAttribute("promotionCode", code);
//        response.getWriter().print(jsonObject);
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