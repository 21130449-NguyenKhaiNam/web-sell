package controller.api.admin.category;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import controller.exception.AppException;
import controller.exception.ErrorCode;
import models.Category;
import models.Parameter;
import org.json.JSONObject;
import services.admin.AdminCategoryServices;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "adminReadCategory", value = "/api/admin/category/get")
public class GetCategoryController extends HttpServlet {
    Gson gson = new GsonBuilder().create();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParameter = request.getParameter("id");
        int id;
        try {
            id = Integer.parseInt(idParameter);
        } catch (NumberFormatException e) {
            throw new AppException(ErrorCode.CATEGORY_ERROR);
        }
        try {
            Category category = AdminCategoryServices.getINSTANCE().getCategoryById(id);
            List<Parameter> listParameter = AdminCategoryServices.getINSTANCE().getParametersByCategoryId(id);
            JsonObject jsonObject = new JsonObject();
            jsonObject.add("category", gson.toJsonTree(category));
            jsonObject.add("parameters", gson.toJsonTree(listParameter));
            response.getWriter().println(jsonObject);
        } catch (IndexOutOfBoundsException e) {
            throw new AppException(ErrorCode.CATEGORY_ERROR);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}