package controller.api.admin.product;

import models.*;
import org.json.JSONArray;
import org.json.JSONObject;
import services.ProductServices;
import utils.ProductFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "adminReadProduct", value = "/api/admin/product/read")
public class ReadProduct extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String idParameter = request.getParameter("id");

        int id;
        try {
            id = Integer.parseInt(idParameter.trim());
        } catch (NumberFormatException e) {
            response.sendError(404);
            return;
        }

        Product product = ProductServices.getINSTANCE().getProductByProductId(id);
        if (product == null) {
            response.sendError(404);
            return;
        }
        List<Size> sizeList = ProductFactory.getListSizesByProductId(id);
        List<Color> colorList = ProductFactory.getListColorsByProductId(id);
        List<Image> imageList = ProductFactory.getListImagesByProductId(id);

        JSONObject jsonProduct = new JSONObject(product);
        JSONArray jsonSizes = new JSONArray(sizeList);
        JSONArray jsonColors = new JSONArray(colorList);
        JSONArray jsonImages = new JSONArray(imageList);

        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("product", jsonProduct);
        jsonResponse.put("sizes", jsonSizes);
        jsonResponse.put("colors", jsonColors);
        jsonResponse.put("images", jsonImages);

        response.getWriter().write(jsonResponse.toString());
    }

}