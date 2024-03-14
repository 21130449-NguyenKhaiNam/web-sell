package filter.adminPage;

import models.Product;
import services.admin.AdminProductServices;

import javax.servlet.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebFilter(filterName = "adminProducts", urlPatterns = {
        "/public/admin/adminProducts.jsp", "/filterProductAdmin" ,"/public/admin/adminProductForm.jsp"
})
public class AdminProducts implements Filter {
    private final int LIMIT = 15;
    private final int DEFAULT_PAGE = 1;

    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        List<Product> productCardList = AdminProductServices.getINSTANCE().getProducts(DEFAULT_PAGE);
        request.setAttribute("productCardList", productCardList);
        int quantityPage = AdminProductServices.getINSTANCE().getQuantityPage();
        request.setAttribute("quantityPage", quantityPage);
        String requestURL = "/filterProductAdmin?";
        request.setAttribute("requestURL", requestURL);
        chain.doFilter(request, response);
    }
}
 
