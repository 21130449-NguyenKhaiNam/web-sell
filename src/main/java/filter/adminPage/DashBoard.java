package filter.adminPage;

import services.admin.DashboardServices;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "DashBoard", urlPatterns = {"/public/admin/adminDashboard.jsp"})
public class DashBoard implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    //
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        int quantityUser = DashboardServices.getINSTANCE().countUser();
        int quantityProduct = DashboardServices.getINSTANCE().countProduct();
        int quantityOrder = DashboardServices.getINSTANCE().countOrder();
        int quantityReview = DashboardServices.getINSTANCE().countReview();

        request.setAttribute("user", quantityUser);
        request.setAttribute("product", quantityProduct);
        request.setAttribute("order", quantityOrder);
        request.setAttribute("review", quantityReview);
        chain.doFilter(request, response);
    }
}