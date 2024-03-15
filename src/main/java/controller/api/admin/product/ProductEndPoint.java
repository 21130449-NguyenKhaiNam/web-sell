package controller.api.admin.product;

import models.Color;
import models.Image;
import models.Product;
import models.Size;
import org.json.JSONArray;
import org.json.JSONObject;
import services.ProductServices;
import utils.ProductFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductEndPoint", value = "/api/admin/product")
public class ProductEndPoint extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        JSONObject jsonResponse = new JSONObject();
        if (request.getParameter("id") != null) {
            // Get product by id
            int id;
            try {
                id = Integer.parseInt(request.getParameter("id"));
            } catch (NumberFormatException e) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().flush();
                return;
            }

            Product product = ProductServices.getINSTANCE().getProductByProductId(id);
            if (product == null) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.getWriter().flush();
                return;
            }

            List<Size> sizeList = ProductFactory.getListSizesByProductId(id);
            List<Color> colorList = ProductFactory.getListColorsByProductId(id);
            List<Image> imageList = ProductFactory.getListImagesByProductId(id);

            JSONObject jsonProduct = new JSONObject(product);
            JSONArray jsonSizes = new JSONArray(sizeList);
            JSONArray jsonColors = new JSONArray(colorList);
            JSONArray jsonImages = new JSONArray(imageList);


            jsonResponse.put("product", jsonProduct);
            jsonResponse.put("sizes", jsonSizes);
            jsonResponse.put("colors", jsonColors);
            jsonResponse.put("images", jsonImages);

            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write(jsonResponse.toString());
        } else {
//            Get all products
//            List<Product> products = ProductServices.getINSTANCE().getAllProduct();
//            response.setStatus(HttpServletResponse.SC_OK);
//            response.getWriter().write(jsonResponse.toString());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}