package controller.api.admin.product;

import services.admin.AdminProductServices;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UnHideProduct", value = "/api/admin/product/un-hide")
public class UnHideProduct extends HttpServlet {
    private final boolean UN_HIDE_STATE = true;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParameter = request.getParameter("id");
        int productId;
        try {
            productId = Integer.parseInt(idParameter);
            boolean success = AdminProductServices.getINSTANCE().updateVisibility(productId, UN_HIDE_STATE);
            StringBuilder objJson = new StringBuilder();
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