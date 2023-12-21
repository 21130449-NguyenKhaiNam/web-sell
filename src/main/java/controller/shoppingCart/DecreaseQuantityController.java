package controller.shoppingCart;

import models.ShoppingCart;
import models.Voucher;
import services.ShoppingCartServices;
import utils.FormatCurrency;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "DecreaseQuantityController", value = "/DecreaseQuantity")
public class DecreaseQuantityController extends HttpServlet {

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productId = 0;
        int cartProductIndex = 0;
        HttpSession session = request.getSession(true);
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        try {
            productId = Integer.parseInt((String) request.getAttribute("productId"));
            cartProductIndex = Integer.parseInt((String) request.getAttribute("cartProductIndex"));
        } catch (NumberFormatException exception) {
            exception.printStackTrace();
        }
        cart.decrease(productId, cartProductIndex);
        String code = (String) session.getAttribute("code");
        if (code != null) {
            Voucher voucher = cart.getVoucherApplied();
            if (voucher == null) {
                voucher = ShoppingCartServices.getINSTANCE().getValidVoucherApply(code);
            } else if (cart.getTemporaryPrice() < voucher.getMinimumPrice()) {
                double minPriceToApply = voucher.getMinimumPrice();
                double currentTempPrice = cart.getTemporaryPrice();

                double priceBuyMore = minPriceToApply - currentTempPrice;
                String priceBuyMoreFormat = FormatCurrency.vietNamCurrency(priceBuyMore);
                session.removeAttribute("successApplied");
                cart.setVoucherApplied(null);
                session.setAttribute("failedApply", "Bạn chưa đủ điều kiện để áp dụng mã " + code + ". Hãy mua thêm " + priceBuyMoreFormat);
            }
        }
        session.setAttribute("cart", cart);
        response.sendRedirect("shoppingCart.jsp");
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