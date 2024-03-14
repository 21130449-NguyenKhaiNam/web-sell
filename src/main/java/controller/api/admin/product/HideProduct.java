package controller.api.admin.product;

import services.admin.AdminProductServices;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "HideProduct", value = "/admin-hide-product")
public class HideProduct extends HttpServlet {
    private final boolean HIDE_STATE = false;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        String idParameter = request.getParameter("id");
        int productId;
        try {
            productId = Integer.parseInt(idParameter);
            boolean success = AdminProductServices.getINSTANCE().updateVisibility(productId, HIDE_STATE);
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