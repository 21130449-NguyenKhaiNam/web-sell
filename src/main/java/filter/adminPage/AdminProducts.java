package filter.adminPage;

import models.Product;
<<<<<<< HEAD
import services.admin.AdminProductServices;
=======
import services.AdminProductServices;
>>>>>>> 21130449

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
<<<<<<< HEAD

        int quantityPageMin = 1;
        int quantityPageMax = 5;
        request.setAttribute("quantityPageMin", quantityPageMin);
        request.setAttribute("quantityPageMax", quantityPageMax);

=======
>>>>>>> 21130449
        chain.doFilter(request, response);
    }
}
 
