package filter.product;

import services.ProductCardServices;

import javax.servlet.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebFilter(filterName = "filterBySize"
        , servletNames = {"filterProduct"})
public class FilterBySize implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        String[] sizes = request.getParameterValues("size");
        if (sizes != null) {
            List<Integer> listId = ProductCardServices.getINSTANCE().getIdProductFromSize(sizes);
            request.setAttribute("filterBySize", listId);
        }
        chain.doFilter(request, response);
    }
}
 
