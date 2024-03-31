package filter.adminPage;

import models.Category;
import services.ProductCardServices;

import javax.servlet.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebFilter(filterName = "adminProductForm", urlPatterns = {"/adminProductForm.jsp"})
public class AdminProductForm implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        List<Category> categoryList = ProductCardServices.getINSTANCE().getAllCategory();
        request.setAttribute("categoryList", categoryList);
        chain.doFilter(request, response);
    }
}
 
