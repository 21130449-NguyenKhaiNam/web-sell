package controller.web.review;

<<<<<<< HEAD
import config.ConfigPage;
import models.Review;
import services.admin.AdminReviewServices;
=======
import models.Review;
import services.AdminReviewServices;
>>>>>>> 21130449

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ReviewPage", value = "/reviewPage")
public class ReviewPage extends HttpServlet {
<<<<<<< HEAD
    private final int QUANTITY_PAGE_DEFAULT = 5;
    private int quantityPageMin;
    private int quantityPageMax;
    private int currentPage;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pageOnUrl = request.getParameter("page");

        try {
            currentPage = Integer.parseInt(pageOnUrl);
        } catch (NumberFormatException e) {
            currentPage = 1;
        }

        List<Review> listReview = AdminReviewServices.getINSTANCE().getReviews(currentPage);
        int quantityPageTotal = AdminReviewServices.getINSTANCE().getQuantityPage();

        generateQuantityPage();
        if (quantityPageMax > quantityPageTotal) {
            quantityPageMax = quantityPageTotal;
        }


        request.setAttribute("requestURL", "/reviewPage");
        request.setAttribute("listReview", listReview);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("quantityPageMin", quantityPageMin);
        request.setAttribute("quantityPageMax", quantityPageMax);
        request.setAttribute("quantityPage", quantityPageTotal);

        request.getRequestDispatcher(ConfigPage.ADMIN_REVIEW).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    public void generateQuantityPage() {
        quantityPageMin = currentPage - 2;
        quantityPageMax = currentPage + 2;
        if (quantityPageMin < 1) {
            quantityPageMin = 1;
            quantityPageMax = QUANTITY_PAGE_DEFAULT;
        }
=======
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pageParameter = request.getParameter("page");
        int page;
        try{
            page = Integer.parseInt(pageParameter);
        }catch (NumberFormatException e){
            page = 1;
        }
        List<Review> listReview = AdminReviewServices.getINSTANCE().getReviews(page);
        int quantityPage = AdminReviewServices.getINSTANCE().getQuantityPage();
        request.setAttribute("listReview", listReview);
        request.setAttribute("currentPage", page);
        request.setAttribute("quantityPage", quantityPage);
        request.getRequestDispatcher("adminReviews.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

>>>>>>> 21130449
    }
}