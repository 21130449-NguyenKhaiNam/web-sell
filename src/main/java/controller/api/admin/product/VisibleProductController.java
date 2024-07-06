package controller.api.admin.product;

import com.google.gson.JsonObject;
import services.admin.AdminProductServices;
import services.state.ProductState;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/api/admin/product/visible")
public class VisibleProductController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParameter = request.getParameter("id");
        String type = request.getParameter("type");
        JsonObject jsonObject = new JsonObject();
        int productId;
        if (idParameter == null || type == null) {
            jsonObject.addProperty("success", false);
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().println(jsonObject);
            return;
        }
        try {
            ProductState state = ProductState.valueOf(type.toUpperCase());
            productId = Integer.parseInt(idParameter);
            AdminProductServices.getINSTANCE().updateVisibility(productId, state);
            jsonObject.addProperty("success", "success");
        } catch (NumberFormatException e) {
            jsonObject.addProperty("success", false);
        }
        response.getWriter().write(jsonObject.toString());
    }
}
