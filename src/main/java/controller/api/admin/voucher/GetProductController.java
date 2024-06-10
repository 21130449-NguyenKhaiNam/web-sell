package controller.api.admin.voucher;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import models.Product;
import services.ProductServices;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "adminGetProduct", value = "/api/admin/voucher/get-product")
public class GetProductController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JsonArray jsonArray = new JsonArray();
        List<Product> listProduct = ProductServices.getINSTANCE().getAllProductSelect();
        for (Product product : listProduct) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("id", product.getId());
            jsonObject.addProperty("name", product.getName());
            jsonArray.add(jsonObject);
        }
        resp.getWriter().print(jsonArray);
    }
}
