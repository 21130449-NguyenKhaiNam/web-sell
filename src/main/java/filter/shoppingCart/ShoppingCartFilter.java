package filter.shoppingCart;

import models.User;
import models.Voucher;
import models.shoppingCart.ShoppingCart;
import services.ShoppingCartServices;
import session.SessionManager;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebFilter({"/public/user/shoppingCart.jsp"})
public class ShoppingCartFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        List<Voucher> listVouchers = ShoppingCartServices.getINSTANCE().getListVouchers();
        request.setAttribute("listVouchers", listVouchers);
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
