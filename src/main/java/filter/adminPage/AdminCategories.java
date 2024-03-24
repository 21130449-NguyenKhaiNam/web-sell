package filter.adminPage;

import models.Category;
import services.AdminCategoryServices;

import javax.servlet.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebFilter(filterName = "adminCategories", urlPatterns = {"/public/admin/adminCategories.jsp"})
public class AdminCategories implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        List<Category> listCategory  = AdminCategoryServices.getINSTANCE().getCategories();
        request.setAttribute("listCategory", listCategory);
        chain.doFilter(request, response);
    }
}
 
