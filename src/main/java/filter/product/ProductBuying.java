package filter.product;

import models.*;
import services.ProductCardServices;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@WebFilter(
        servletNames = {"pagingProduct"},
        urlPatterns = {"/productBuying.jsp", "/filterProduct"})
public class ProductBuying implements Filter {

    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        List<Product> productCardList = ProductCardServices.getINSTANCE().getProducts(1);
        request.setAttribute("productCardList", productCardList);

        List<Category> categoryList = ProductCardServices.getINSTANCE().getAllCategory();
        request.setAttribute("categoryList", categoryList);

        List<Size> sizeList = ProductCardServices.getINSTANCE().getAllSize();
        request.setAttribute("sizeList", sizeList);

        List<Color> colorList = ProductCardServices.getINSTANCE().getAllColor();
        request.setAttribute("colorList", colorList);

        int quantityPage = ProductCardServices.getINSTANCE().getQuantityPage();
        request.setAttribute("quantityPage", quantityPage);

        request.setAttribute("currentPage", 1);

        String requestURL = "/filterProduct?";
        request.setAttribute("requestURL", requestURL);

        chain.doFilter(request, response);
    }
}
 
