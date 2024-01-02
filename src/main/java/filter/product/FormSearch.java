package filter.product;

import models.*;
import services.ProductCardServices;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@WebFilter(
        servletNames = {"formSearch"},
        urlPatterns = {"/productBuying.jsp",
                "/adminProducts.jsp",
                "/filterProductBuying",
                "/filterProductAdmin"})
public class FormSearch implements Filter {

    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        List<Category> categoryList = ProductCardServices.getINSTANCE().getAllCategory();
        request.setAttribute("categoryList", categoryList);

        List<Size> sizeList = ProductCardServices.getINSTANCE().getAllSize();
        request.setAttribute("sizeList", sizeList);

        List<Color> colorList = ProductCardServices.getINSTANCE().getAllColor();
        request.setAttribute("colorList", colorList);
        request.setAttribute("currentPage", 1);

        chain.doFilter(request, response);
    }
}
 
