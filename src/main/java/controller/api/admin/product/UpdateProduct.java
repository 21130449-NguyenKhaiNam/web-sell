package controller.api.admin.product;

import models.Product;
import properties.PathProperties;
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
public class UpdateProduct extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        String idString = request.getParameter("id");
        String name = request.getParameter("name");
        String idCategory = request.getParameter("idCategory");
        String originalPrice = request.getParameter("originalPrice");
        String salePrice = request.getParameter("salePrice");
        String description = request.getParameter("description");
        String[] nameSizes = request.getParameterValues("nameSize");
        String[] sizePrices = request.getParameterValues("sizePrice");
        String[] colors = request.getParameterValues("color");

        String quantityImgDeleteParameter = request.getParameter("quantityImgDelete");
        int quantityImgDelete;
        try {
            quantityImgDelete = Integer.parseInt(quantityImgDeleteParameter);
        } catch (NumberFormatException e) {
            quantityImgDelete = 0;
        }

        Collection<Part> images = request.getParts();
        int id;
        try {
            id = Integer.parseInt(idString);
        } catch (NumberFormatException e) {
            response.sendError(404);
            return;
        }

//        Check id product exist
        Product oldProduct = ProductServices.getINSTANCE().getProductByProductId(id);
        if (oldProduct != null) {
//        Update Product
            Product newProduct = new Product();
            newProduct.setName(name);
            newProduct.setCategoryId(Integer.parseInt(idCategory));
            newProduct.setDescription(description);
            newProduct.setOriginalPrice(Double.parseDouble(originalPrice));
            newProduct.setSalePrice(Double.parseDouble(salePrice));
            newProduct.setCreateAt(Date.valueOf(LocalDate.now()));
            AdminProductServices.getINSTANCE().updateProduct(oldProduct, newProduct, id);
//        Update size
            AdminProductServices.getINSTANCE().updateSizes(nameSizes, sizePrices, id);
//        Update color
            AdminProductServices.getINSTANCE().updateColors(colors, id);
//        Update images
            if (!images.isEmpty()) {
                updateImage(images, quantityImgDelete, id);
            }
        }
        StringBuilder objJson = new StringBuilder();
        objJson.append("{\"status\":").append("true}");
        response.getWriter().write(objJson.toString());
    }

    public void updateImage(Collection<Part> images, int quantityImgDelete, int productId) {
        ServletContext servletContext = getServletContext();
        String root = servletContext.getRealPath("/") + PathProperties.getINSTANCE().getPathProductWeb();
        UploadImageServices uploadImageServices = new UploadImageServices(root);
        AdminProductServices.getINSTANCE().updateImages(uploadImageServices, images, quantityImgDelete, productId);
    }
}