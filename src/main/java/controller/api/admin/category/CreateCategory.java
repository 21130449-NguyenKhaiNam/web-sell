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

@WebServlet(name = "adminCreateCategory", value = "/admin-create-category")
@MultipartConfig(
        fileSizeThreshold = 1024 * 12024,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 100
)
public class CreateCategory extends HttpServlet {
    private Category category;
    private List<Parameter> parameterList;
    private UploadImageServices uploadImageSizeGuideServices;
    private UploadImageServices uploadImageParameterGuideImgServices;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String nameCategory = request.getParameter("nameCategory");
        if (nameCategory == null) {
            response.getWriter().write("{\"status\":true}");
            return;
        }
        this.category = new Category();
        this.parameterList = new ArrayList<>();
        category.setNameType(nameCategory);

        String[] nameParameters = request.getParameterValues("nameParameter");
        String[] unit = request.getParameterValues("unit");
        String[] minValue = request.getParameterValues("minValue");
        String[] maxValue = request.getParameterValues("maxValue");

        for (int i = 0; i < nameParameters.length; i++) {
            Parameter parameter = new Parameter();
            parameter.setName(nameParameters[i]);
            try {
                parameter.setMinValue(Double.parseDouble(minValue[i]));
                parameter.setMaxValue(Double.parseDouble(maxValue[i]));
                parameter.setUnit(unit[i]);
            } catch (NumberFormatException e) {
                response.sendError(404);
                return;
            }
            parameterList.add(parameter);
        }
        try {
            uploadImg(request.getParts());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        response.getWriter().write("{\"status\":true}");
    }

    public void uploadImg(Collection<Part> parts) throws Exception {
        List<Part> partAfterFilter = new ArrayList<>();
        ServletContext servletContext = getServletContext();
        String rootSizeGuideImg = servletContext.getRealPath("/") + PathProperties.getINSTANCE().getPathCategoryWeb();
        String rootParameterGuideImg = servletContext.getRealPath("/") + PathProperties.getINSTANCE().getPathParameterWeb();
        uploadImageSizeGuideServices = new UploadImageServices(rootSizeGuideImg);
        uploadImageParameterGuideImgServices = new UploadImageServices(rootParameterGuideImg);

        for (Part part : parts) {
            if (UploadImageServices.isPartImage(part)) {
                partAfterFilter.add(part);
            }
        }

        for (int i = 0; i < partAfterFilter.size(); i++) {
            if (i == 0) {
                uploadImageSizeGuideServices.addImage(partAfterFilter.get(i));
            } else {
                uploadImageParameterGuideImgServices.addImage(partAfterFilter.get(i));
            }
        }
        handleAddCategoryAndParameter();
    }

    public void handleAddCategoryAndParameter() {
        category.setSizeTableImage(uploadImageSizeGuideServices.getNameImages().get(0));
        for (int i = 0; i < uploadImageParameterGuideImgServices.getNameImages().size(); i++) {
            parameterList.get(i).setGuideImg(uploadImageParameterGuideImgServices.getNameImages().get(i));
        }
//        Add to db
        int categoryIdAdded = AdminCategoryServices.getINSTANCE().addCategory(category);
        if (categoryIdAdded != -1) {
            AdminCategoryServices.getINSTANCE().addParameters(parameterList, categoryIdAdded);
        }

    }

}