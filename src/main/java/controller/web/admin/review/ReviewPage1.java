package controller.web.admin.review;

import config.ConfigPage;
import models.Review;
import services.admin.AdminReviewServices;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ReviewPage", value = "/reviewPage1")
public class ReviewPage1 extends HttpServlet {
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
    }
}