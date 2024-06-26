package controller.api.admin.review;

import services.admin.AdminReviewServices;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "adminHideReview", value = "/api/admin/review/hide")
public class HideReview extends HttpServlet {
    private final boolean HIDE_STATE = false;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParameter = request.getParameter("id");
        int reviewId;
        try {
            reviewId = Integer.parseInt(idParameter);
            boolean success = AdminReviewServices.getINSTANCE().updateVisibility(reviewId, HIDE_STATE);
            StringBuilder objJson = new StringBuilder();
            System.out.println(success);
            if (success) {
                objJson.append("{\"status\":").append("true}");
            } else {
                objJson.append("{\"status\":").append("false}");
            }
            response.getWriter().write(objJson.toString());
        } catch (NumberFormatException e) {
            response.sendError(404);
        }
    }
}