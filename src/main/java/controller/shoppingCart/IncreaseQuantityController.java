package controller.shoppingCart;

import models.ShoppingCart;
import models.Voucher;
import services.ShoppingCartServices;
import utils.FormatCurrency;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "IncreaseQuantityController", value = "/IncreaseQuantity")
public class IncreaseQuantityController extends HttpServlet {

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productId = 0;
        int cartProductIndex = 0;
        HttpSession session = request.getSession(true);
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        try {
            productId = Integer.parseInt((String) request.getAttribute("productId"));
            cartProductIndex = Integer.parseInt((String) request.getAttribute("cartProductIndex"));
        }catch (NumberFormatException exception){
            exception.printStackTrace();
        }

        cart.increase(productId, cartProductIndex);

        String code = (String) session.getAttribute("code");
        if(code != null){
            Voucher voucher = cart.getVoucherApplied();
            if (voucher == null){
                voucher = ShoppingCartServices.getINSTANCE().getValidVoucherApply(code);
                double minPriceToApply = voucher.getMinimumPrice();
                if (cart.getTemporaryPrice() >= minPriceToApply){
                    session.removeAttribute("failedApply");
                    cart.setVoucherApplied(voucher);
                    session.setAttribute("successApplied", "Bạn đã áp dụng mã " + code + " thành công");
                }else {
                    double currentTempPrice = cart.getTemporaryPrice();
                    double priceBuyMore = minPriceToApply - currentTempPrice;
                    String priceBuyMoreFormat = FormatCurrency.vietNamCurrency(priceBuyMore);
                    session.removeAttribute("successApplied");
                    session.setAttribute("failedApply", "Bạn chưa đủ điều kiện để áp dụng mã " + code + ". Hãy mua thêm " + priceBuyMoreFormat);
                }
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