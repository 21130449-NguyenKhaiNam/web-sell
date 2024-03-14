package controller.web.review;

import models.Review;
import services.admin.AdminReviewServices;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ReviewPage", value = "/reviewPage")
public class ReviewPage extends HttpServlet {
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

    }
}