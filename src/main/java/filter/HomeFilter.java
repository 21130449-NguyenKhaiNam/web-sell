package filter;

import models.Product;
import models.Slider;
import services.HomeServices;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebFilter(urlPatterns = "/public/index.jsp")
public class HomeFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        List<Product> listAllTrendingProducts = HomeServices.getINSTANCE().getListTrendProducts(true);
        HttpSession session = request.getSession(true);
        session.setAttribute("listAllTrendingProducts", listAllTrendingProducts);

        List<Slider> listSlideShow = HomeServices.getINSTANCE().getListSlideShow();
        List<Product> list6NewProducts = HomeServices.getINSTANCE().getListNewProducts(false);
        List<Product> list6TrendProducts = HomeServices.getINSTANCE().getListTrendProducts(false);
        request.setAttribute("listSlideShow", listSlideShow);
        request.setAttribute("list6TrendingProducts", list6TrendProducts);
        request.setAttribute("list6NewProducts", list6NewProducts);
//        String url = request.getServletPath();
//        if (url.contains("index.jsp") && !url.contains("error404.jsp")) {
//            response.sendRedirect("Home");
//        }
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
