package filter.adminPage;

import models.Product;
import models.Review;
import services.AdminProductServices;
import services.AdminReviewServices;

import javax.servlet.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebFilter(filterName = "adminReviews", urlPatterns = {"/adminReviews.jsp"})
public class AdminReviews implements Filter {
    private final int LIMIT = 15;
    private final int DEFAULT_PAGE = 1;
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        List<Review> listReview  = AdminReviewServices.getINSTANCE().getReviews(DEFAULT_PAGE);
        request.setAttribute("listReview", listReview);
        int quantityPage = AdminReviewServices.getINSTANCE().getQuantityPage();
        request.setAttribute("quantityPage", quantityPage);
        String requestURL = "/filterProductAdmin?";
        request.setAttribute("requestURL", requestURL);
        chain.doFilter(request, response);
    }
    
}
 
