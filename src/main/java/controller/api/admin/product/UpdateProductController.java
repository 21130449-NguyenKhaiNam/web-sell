package controller.api.admin.product;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import lombok.SneakyThrows;
import models.Color;
import models.Product;
import models.Size;
import services.admin.AdminProductServices;
import services.ProductServices;
import services.image.UploadImageServices;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Collection;

@MultipartConfig(
        fileSizeThreshold = 1024 * 12024,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 100
)
@WebServlet(name = "adminUpdateProduct", value = "/api/admin/product/update")
public class UpdateProductController extends HttpServlet {
    Gson gson = new GsonBuilder().create();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idString = request.getParameter("id");
        String name = request.getParameter("name");
        String idCategory = request.getParameter("idCategory");
        String originalPrice = request.getParameter("originalPrice");
        String salePrice = request.getParameter("salePrice");
        String description = request.getParameter("description");
        String[] nameSizes = request.getParameterValues("nameSize[]");
        String[] sizePrices = request.getParameterValues("sizePrice[]");
        String[] sizeId = request.getParameterValues("sizeId[]");
        String[] codeColors = request.getParameterValues("color");
        String[] idColors = request.getParameterValues("colorId[]");
        JsonObject jsonObject = new JsonObject();

        int id;
        try {
            id = Integer.parseInt(idString);
        } catch (NumberFormatException e) {
            jsonObject.addProperty("status", false);
            response.getWriter().write(gson.toJson(jsonObject));
            return;
        }

//        Update Product
        Product product = new Product();
        product.setId(id);
        product.setName(name);
        product.setCategoryId(Integer.parseInt(idCategory));
        product.setDescription(description);
        product.setOriginalPrice(Double.parseDouble(originalPrice));
        product.setSalePrice(Double.parseDouble(salePrice));
        product.setCreateAt(Date.valueOf(LocalDate.now()));

//        AdminProductServices.getINSTANCE().updateProduct(product);
//        Update size
        if (nameSizes.length != sizePrices.length) {
            jsonObject.addProperty("status", false);
            jsonObject.addProperty("message", "Có lỗi khi thêm size, hay kiểm tra và thử lại!");
            response.getWriter().write(gson.toJson(jsonObject));
            return;
        }

        Size[] sizes = new Size[nameSizes.length];
        for (int i = 0; i < nameSizes.length; i++) {
            Size size = new Size();
            size.setId(Integer.parseInt(sizeId[i]));
            size.setNameSize(nameSizes[i]);
            size.setSizePrice(Double.parseDouble(sizePrices[i]));
            size.setProductId(id);
            sizes[i] = size;
        }

        if (codeColors.length != idColors.length) {
            jsonObject.addProperty("status", false);
            jsonObject.addProperty("message", "Có lỗi khi thêm color, hay kiểm tra và thử lại!");
            response.getWriter().write(gson.toJson(jsonObject));
            return;
        }

        Color[] colors = new Color[codeColors.length];
        for (int i = 0; i < colors.length; i++) {
            Color color = new Color();
            color.setId(Integer.parseInt(sizeId[i]));
            color.setCodeColor(codeColors[i]);
            color.setProductId(id);
            colors[i] = color;
        }

//        Update color
        AdminProductServices.getINSTANCE().updateSizes(sizes, id);
//        Update images
        AdminProductServices.getINSTANCE().updateColors(colors, id);
        Collection<Part> images = request.getParts();
        if (!images.isEmpty()) {
            try {
                updateImage(images, 0, id);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        jsonObject.addProperty("status", true);
        response.getWriter().write(gson.toJson(jsonObject));
    }

    public void updateImage(Collection<Part> images, int quantityImgDelete, int productId) throws Exception {
        UploadImageServices uploadImageServices = new UploadImageServices("product_img/" + productId);
        images.stream().forEach(part -> System.out.println(UploadImageServices.isPartImage(part) + " - -" + part.getSubmittedFileName()));
//        AdminProductServices.getINSTANCE().updateImages(uploadImageServices, images, productId);
    }
}