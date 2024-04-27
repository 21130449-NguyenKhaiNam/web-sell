package controller.web.review;

import config.ConfigPage;
import models.Review;
import models.User;
import services.ReviewServices;
import session.SessionManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        User user = SessionManager.getInstance(request, response).getUser();
        int userId = user.getId();
        int orderProductId;


        try {
            orderProductId = Integer.parseInt(orderProductIdRequest);
            if (!ReviewServices.getINSTANCE().canReview(userId, orderProductId)) {
                response.sendError(404);
                return;
            }
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
        review.setOrderDetailId(orderProductId);
        review.setRatingStar(ratingStar);
        review.setFeedback(desc);
        review.setReviewDate(Date.valueOf(LocalDate.now()));
        ReviewServices.getINSTANCE().createReview(review);

        String nameProduct = ReviewServices.getINSTANCE().getNameProduct(orderProductId);
        if (nameProduct == null) {
            response.sendError(404);
            return;
        }
        request.setAttribute("nameProduct", nameProduct);
        request.getRequestDispatcher(ConfigPage.USER_REVIEW_SUCCESS).forward(request, response);
    }
}