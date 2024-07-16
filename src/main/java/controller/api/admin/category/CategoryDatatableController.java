package controller.api.admin.category;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import models.Category;
import services.admin.AdminCategoryServices;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/api/admin/categories")
public class CategoryDatatableController extends HttpServlet {
    Gson gson = new GsonBuilder().create();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Category> listCategory = AdminCategoryServices.getINSTANCE().getCategories();
        resp.getWriter().println(gson.toJsonTree(listCategory));
    }
}
