package controller.api.admin.category;

import models.Category;
import models.Parameter;
import properties.PathProperties;
import services.admin.AdminCategoryServices;
import services.image.UploadImageServices;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@WebServlet(name = "adminUpdateCategory", value = "/admin-update-category")
@MultipartConfig(
        fileSizeThreshold = 1024 * 12024,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 100
)
public class UpdateCategory extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        StringBuilder objJson = new StringBuilder();

        String idString = request.getParameter("categoryId");
        int categoryId;
        try {
            categoryId = Integer.parseInt(idString);
            //        Category
            String nameCategory = request.getParameter("nameCategory");
            Part sizeTableImagePart = request.getPart("sizeTableImage");
            String sizeTableImage = uploadImageSizeTable(sizeTableImagePart);
            Category category = new Category();
            category.setId(categoryId);
            category.setNameType(nameCategory);
            category.setSizeTableImage(sizeTableImage);
            AdminCategoryServices.getINSTANCE().updateCategory(category);

//        Parameters
            String[] nameParameters = request.getParameterValues("nameParameter");
            String[] minValues = request.getParameterValues("minValue");
            String[] maxValues = request.getParameterValues("maxValue");
            String[] units = request.getParameterValues("unit");

            Collection<Part> collectionGuideImage = new ArrayList<>();
            for (Part part : request.getParts()) {
                if (part.getName().equals("guideImg"))
                    collectionGuideImage.add(part);
            }
            List<String> guideImages = uploadGuideImages(collectionGuideImage);
            List<Parameter> listParameter = new ArrayList<>();
            for (int i = 0; i < nameParameters.length; i++) {
                Parameter parameter = new Parameter();
                parameter.setName(nameParameters[i]);
                parameter.setMinValue(Double.parseDouble(minValues[i]));
                parameter.setMaxValue(Double.parseDouble(maxValues[i]));
                parameter.setUnit(units[i]);
                parameter.setCategoryId(categoryId);
                parameter.setGuideImg(guideImages.get(i));
                listParameter.add(parameter);
            }
            AdminCategoryServices.getINSTANCE().updateParameters(listParameter, categoryId);
            objJson.append("{\"status\":").append("true}");
        } catch (NumberFormatException e) {
            objJson.append("{\"status\":").append("false}");
        }
        response.getWriter().write(objJson.toString());
    }

    //Update image size table;
    private String uploadImageSizeTable(Part part) throws IOException {
        ServletContext servletContext = getServletContext();
        String root = servletContext.getRealPath("/") + PathProperties.getINSTANCE().getPathCategoryWeb();
        UploadImageServices uploadImageServices = new UploadImageServices(root);
        uploadImageServices.addImage(part);
        return uploadImageServices.getNameImages().get(0);
    }

    private List<String> uploadGuideImages(Collection<Part> parts) throws IOException {
        ServletContext servletContext = getServletContext();
        String root = servletContext.getRealPath("/") + PathProperties.getINSTANCE().getPathParameterWeb();
        UploadImageServices uploadImageServices = new UploadImageServices(root);
        uploadImageServices.addImages(parts);
        return uploadImageServices.getNameImages();
    }
}