package filter.adminPage;

<<<<<<< HEAD
import models.Review;
import services.admin.AdminReviewServices;
=======
import models.Product;
import models.Review;
import services.AdminProductServices;
import services.AdminReviewServices;
>>>>>>> 21130449

import javax.servlet.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebFilter(filterName = "adminReviews", urlPatterns = {"/public/admin/adminReviews.jsp"})
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
<<<<<<< HEAD

        int quantityPage = AdminReviewServices.getINSTANCE().getQuantityPage();
        request.setAttribute("quantityPage", quantityPage);
        String requestURL = "/reviewPage?";

        int quantityPageMin = 1;
        int quantityPageMax = 5;

        if(quantityPage < quantityPageMax){
            quantityPageMax = quantityPage;
        }

        request.setAttribute("listReview", listReview);
        request.setAttribute("quantityPageMin", quantityPageMin);
        request.setAttribute("quantityPageMax", quantityPageMax);
        request.setAttribute("currentPage", 1);
=======
        request.setAttribute("listReview", listReview);
        int quantityPage = AdminReviewServices.getINSTANCE().getQuantityPage();
        request.setAttribute("quantityPage", quantityPage);
        String requestURL = "/reviewPage?";
>>>>>>> 21130449
        request.setAttribute("requestURL", requestURL);
        chain.doFilter(request, response);
    }
    
}
 
