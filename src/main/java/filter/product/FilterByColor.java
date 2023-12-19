package filter.product;

import services.ProductCardServices;

import javax.servlet.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebFilter(filterName = "FilterByColor",
        servletNames = {"filterProduct"})
public class FilterByColor implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        String[] colors = request.getParameterValues("color");
        if (colors != null) {
            List<Integer> listId = ProductCardServices.getINSTANCE().getIdProductFromColor(colors);
            request.setAttribute("filterByColor", listId);
        }
        chain.doFilter(request, response);
    }
}
 
