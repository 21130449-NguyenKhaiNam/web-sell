package filter.shoppingCart;

import models.ShoppingCart;
import models.Voucher;
import services.ShoppingCartServices;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebFilter("/shoppingCart.jsp")
public class ShoppingCartFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
//
//        List<Voucher> listVouchers = ShoppingCartServices.getINSTANCE().getListVouchers();
//        HttpSession session = request.getSession(true);
//        session.setAttribute("listVouchers", listVouchers);
//
        String url = request.getServletPath();
        if(url.contains("shoppingCart.jsp") && !url.contains("error404.jsp")){
            response.sendRedirect("ShoppingCart");
        }
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
