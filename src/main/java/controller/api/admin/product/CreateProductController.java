package controller.api.admin.product;

import com.google.gson.JsonObject;
import controller.exception.AppException;
import controller.exception.ErrorCode;
import models.Image;
import models.Product;
import properties.PathProperties;
import services.admin.AdminProductServices;
import services.image.UploadImageServices;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "adminCreateProduct", value = "/api/admin/product/create")
@MultipartConfig(
        fileSizeThreshold = 1024 * 12024,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 100
)
public class CreateProductController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String idCategory = request.getParameter("idCategory");
        String originalPrice = request.getParameter("originalPrice");
        String salePrice = request.getParameter("salePrice");
        String description = request.getParameter("description");
        String[] nameSizes = request.getParameterValues("nameSize[]");
        String[] sizePrices = request.getParameterValues("sizePrice[]");
        String[] colors = request.getParameterValues("color");
        String[] nameImageAdded = request.getParameterValues("nameImageAdded[]");

//        Add Product
        Product product = new Product();
        product.setName(name);
        product.setCategoryId(Integer.parseInt(idCategory));
        product.setOriginalPrice(Double.parseDouble(originalPrice));
        product.setDescription(description);
        product.setSalePrice(Double.parseDouble(salePrice));
        product.setVisibility(true);
        product.setCreateAt(Date.valueOf(LocalDate.now()));

        if (nameSizes.length == 0) throw new AppException(ErrorCode.SIZE_ERROR);
        if (colors.length == 0) throw new AppException(ErrorCode.COLOR_ERROR);
        if (nameImageAdded.length == 0) throw new AppException(ErrorCode.IMAGE_ERROR);

//        Add Product
        int productId = AdminProductServices.getINSTANCE().addProduct(product);

        JsonObject objJson = new JsonObject();
//       Sản phẩm đã tồn tại
        if (productId == 0) throw new AppException(ErrorCode.PRODUCT_NOT_FOUND);

//        Add Size
        double[] sizePricesDouble = new double[sizePrices.length];
        for (int i = 0; i < sizePricesDouble.length; i++) {
            sizePricesDouble[i] = Double.parseDouble(sizePrices[i]);
        }
        AdminProductServices.getINSTANCE().addSize(nameSizes, sizePricesDouble, productId);

//        Add Color
        AdminProductServices.getINSTANCE().addColor(colors, productId);

//        Add Images
        List<Image> imagesAdded = Arrays.stream(nameImageAdded).map(nameImage -> {
            Image image = new Image();
            image.setNameImage(nameImage);
            image.setProductId(productId);
            return image;
        }).collect(Collectors.toList());

        AdminProductServices.getINSTANCE().addImages(imagesAdded);

        objJson.addProperty("code", ErrorCode.CREATE_SUCCESS.getCode());
        objJson.addProperty("message", ErrorCode.CREATE_SUCCESS.getMessage());
        response.getWriter().write(objJson.toString());
    }
}