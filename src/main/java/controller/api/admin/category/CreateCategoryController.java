package controller.api.admin.category;

import com.google.gson.JsonObject;
import controller.exception.AppException;
import controller.exception.ErrorCode;
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

@WebServlet(name = "adminCreateCategory", value = "/api/admin/category/create")
@MultipartConfig(
        fileSizeThreshold = 1024 * 12024,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 100
)
public class CreateCategoryController extends HttpServlet {
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
        JsonObject jsonObject = new JsonObject();
        try {
            String nameCategory = request.getParameter("nameCategory");
            String sizeTableImage = request.getParameter("sizeTableImage");
            String[] nameParameters = request.getParameterValues("nameParameter[]");
            String[] unit = request.getParameterValues("unit[]");
            String[] minValue = request.getParameterValues("minValue[]");
            String[] maxValue = request.getParameterValues("maxValue[]");
            String[] guideImg = request.getParameterValues("guideImg[]");

            if (nameParameters == null || unit == null || minValue == null || maxValue == null || guideImg == null) {
                throw new AppException(ErrorCode.MISSING_REQUEST);
            }

            if (nameParameters.length != unit.length || nameParameters.length != minValue.length || nameParameters.length != maxValue.length || nameParameters.length != guideImg.length) {
                throw new AppException(ErrorCode.MISSING_REQUEST);
            }

            this.category = new Category();
            category.setNameType(nameCategory);
            category.setSizeTableImage(sizeTableImage);

            this.parameterList = new ArrayList<>();
            for (int i = 0; i < nameParameters.length; i++) {
                Parameter parameter = new Parameter();
                parameter.setName(nameParameters[i]);
                try {
                    parameter.setMinValue(Double.parseDouble(minValue[i]));
                    parameter.setMaxValue(Double.parseDouble(maxValue[i]));
                    parameter.setUnit(unit[i]);
                    parameter.setGuideImg(guideImg[i]);
                } catch (NumberFormatException e) {
                    throw new AppException(ErrorCode.PARAMETER_ERROR);
                }
                parameterList.add(parameter);
            }
            int categoryId = AdminCategoryServices.getINSTANCE().addCategory(category);
            if (categoryId == -1)
                throw new AppException(ErrorCode.CATEGORY_ERROR);

            parameterList.forEach(parameter -> parameter.setCategoryId(categoryId));
            AdminCategoryServices.getINSTANCE().addParameters(parameterList);
            jsonObject.addProperty("code", 200);
            jsonObject.addProperty("message", "Add category success");
            response.getWriter().println(jsonObject.toString());
        } catch (Exception e) {
            throw new AppException(ErrorCode.PARAMETER_ERROR);
        }
    }
}