package filter.home;

import models.Product;
import models.Slider;
import models.shoppingCart.ShoppingCart;
import services.HomeServices;
import session.SessionManager;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebFilter(urlPatterns = "/public/index.jsp")
public class Home implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        List<Product> listAllTrendingProducts = HomeServices.getINSTANCE().getListTrendProducts(true);

        List<Slider> listSlideShow = HomeServices.getINSTANCE().getListSlideShow();
        List<Product> list6NewProducts = HomeServices.getINSTANCE().getListNewProducts(false);
        List<Product> list6TrendProducts = HomeServices.getINSTANCE().getListTrendProducts(false);
        request.setAttribute("listSlideShow", listSlideShow);
        request.setAttribute("list6TrendingProducts", list6TrendProducts);
        request.setAttribute("list6NewProducts", list6NewProducts);
        request.setAttribute("listAllTrendingProducts", listAllTrendingProducts);
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
