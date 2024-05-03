package filter.shoppingCart;

//import cartShopping.ShoppingCart;
import models.shoppingCart.ShoppingCart;
import models.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.*;
import java.io.IOException;

@WebFilter(filterName = "shoppingCartFilter", urlPatterns = {"/shoppingCart.jsp", "/ShoppingCart"})
public class ShoppingCartFilter implements Filter {



    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession(true);
//        session.setAttribute("listVouchers", listVouchers);
//        List<Voucher> listVouchers = ShoppingCartServices.getINSTANCE().getListVouchers();

        User userAuth = (User) session.getAttribute("auth");
        if(userAuth == null){
            response.sendRedirect("signIn.jsp");
        }else {
            String userIdCart = String.valueOf(userAuth.getId());
            String url = request.getServletPath();
            if(url.contains("shoppingCart.jsp") && !url.contains("error404.jsp")){
                response.sendRedirect("ShoppingCart");
            }
            filterChain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
