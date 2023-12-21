package filter.shoppingCart;

import models.ShoppingCart;
import models.Voucher;
import services.ShoppingCartServices;
import utils.FormatCurrency;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class VoucherValidityFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String requestURI = request.getServletPath();

        HttpSession session = request.getSession(true);
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");

        if (!requestURI.contains("shoppingCart.jsp") && !requestURI.contains("ShoppingCart") && !requestURI.contains("IncreaseQuantity")
                && !requestURI.contains("DecreaseQuantity") && !requestURI.contains("DeleteCartProduct") && !requestURI.contains("ApplyVoucher")) {
            session.removeAttribute("successApplied");
            session.removeAttribute("failedApply");
            session.removeAttribute("code");

            if (cart != null && cart.getVoucherApplied() != null) {
                cart.setVoucherApplied(null);
                session.setAttribute("cart", cart);
            }
        }
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
