package controller.admin.category;

import models.Category;
import models.Parameter;
import org.json.JSONObject;
import services.AdminCategoryServices;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "adminReadCategory", value = "/admin-read-category")
public class ReadCategory extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String idParameter = request.getParameter("categoryId");
        JSONObject jsonObject = new JSONObject();
        int id;
        try {
            id = Integer.parseInt(idParameter);
        } catch (NumberFormatException e) {
            response.sendError(404);
            return;
        }
        try {
            Category category = AdminCategoryServices.getINSTANCE().getCategoryById(id).get(0);
            List<Parameter> listParameter = AdminCategoryServices.getINSTANCE().getParameterByCategoryId(id);
            JSONObject categoryObj = new JSONObject(category);
            jsonObject.put("category", categoryObj);
            jsonObject.put("parameters", listParameter);
            response.getWriter().println(jsonObject);
        } catch (IndexOutOfBoundsException e) {
            response.sendError(404);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendError(404);
    }
}