package controller.api.admin.product;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import controller.exception.AppException;
import controller.exception.ErrorCode;
import lombok.SneakyThrows;
import models.Color;
import models.Image;
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
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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

    }

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
        String[] nameImageAdded = request.getParameterValues("nameImageAdded[]");
        String[] idImagesDeletedParam = request.getParameterValues("idImageDeleted[]");

//        Precondition
        int id;
        List<Integer> idImagesDeleted;
        List<Image> imagesAdded;
        try {
            id = Integer.parseInt(idString);
        } catch (NumberFormatException e) {
            throw new AppException(ErrorCode.UPDATE_PRODUCT_FAILED);
        }

        if (nameSizes.length != sizePrices.length) {
            throw new AppException(ErrorCode.SIZE_ERROR);
        }

        if (codeColors.length != idColors.length) {
            throw new AppException(ErrorCode.COLOR_ERROR);
        }

        if (idImagesDeletedParam != null) {
            idImagesDeleted = Arrays.stream(idImagesDeletedParam).map(Integer::parseInt).collect(Collectors.toList());
        } else {
            idImagesDeleted = List.of();
        }

        if (nameImageAdded != null) {
            imagesAdded = Arrays.stream(nameImageAdded).map(nameImage -> {
                Image image = new Image();
                image.setNameImage(nameImage);
                image.setProductId(id);
                return image;
            }).collect(Collectors.toList());
        } else {
            imagesAdded = List.of();
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
        AdminProductServices.getINSTANCE().updateProduct(product);

//        Update size
        Size[] sizes = new Size[nameSizes.length];
        for (int i = 0; i < nameSizes.length; i++) {
            Size size = new Size();
            size.setId(Integer.parseInt(sizeId[i]));
            size.setNameSize(nameSizes[i]);
            size.setSizePrice(Double.parseDouble(sizePrices[i]));
            size.setProductId(id);
            sizes[i] = size;
        }

        Color[] colors = new Color[codeColors.length];
        for (int i = 0; i < colors.length; i++) {
            Color color = new Color();
            color.setId(Integer.parseInt(idColors[i]));
            color.setCodeColor(codeColors[i]);
            color.setProductId(id);
            colors[i] = color;
        }

//        Update sizes
        AdminProductServices.getINSTANCE().updateSizes(sizes, id);

//        Update colors
        AdminProductServices.getINSTANCE().updateColors(colors, id);

//        delete images
        if (!idImagesDeleted.isEmpty())
            AdminProductServices.getINSTANCE().deleteImages(idImagesDeleted);

//        add images
        if (!imagesAdded.isEmpty())
            AdminProductServices.getINSTANCE().addImages(imagesAdded);

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("code", ErrorCode.UPDATE_PRODUCT_SUCCESS.getCode());
        jsonObject.addProperty("message", ErrorCode.UPDATE_PRODUCT_SUCCESS.getMessage());
        response.getWriter().write(gson.toJson(jsonObject));
    }

}