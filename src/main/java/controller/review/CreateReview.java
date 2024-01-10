package controller.review;

import models.Review;
import models.User;
import services.ReviewServices;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

@WebServlet(name = "createReview", value = "/createReview")
public class CreateReview extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String orderProductIdRequest = request.getParameter("orderProductId");
        String ratingStarRequest = request.getParameter("ratingStar");
        String desc = request.getParameter("desc");
        User user = (User) request.getSession().getAttribute("auth");
        int userId = user.getId();
        int orderProductId;

        try {
            orderProductId = Integer.parseInt(orderProductIdRequest);

        } catch (NumberFormatException e) {
            response.sendError(404);
            return;
        }
        int ratingStar;
        try {
            ratingStar = Integer.parseInt(ratingStarRequest);
        } catch (NumberFormatException e) {
            ratingStar = 5;
        }
        Review review = new Review();
        review.setUserId(userId);
        review.setOrderDetailId(orderProductId);
        review.setRatingStar(ratingStar);
        review.setFeedback(desc);
        review.setReviewDate(Date.valueOf(LocalDate.now()));
        ReviewServices.getINSTANCE().createReview(review);
        request.getRequestDispatcher("reviewSuccess.jsp").forward(request, response);
    }
}