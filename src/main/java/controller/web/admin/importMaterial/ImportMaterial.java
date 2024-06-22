package controller.web.admin.importMaterial;

import com.opencsv.CSVReader;
import config.ConfigPage;
import models.CategoryMaterial;
import models.Material;
import services.admin.AdminCategoryMaterialServices;
import services.admin.AdminMaterialServices;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;

@WebServlet(name = "importMaterial", value = "/importMaterial")
@MultipartConfig(fileSizeThreshold = 1024 * 12024, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 100)
public class ImportMaterial extends HttpServlet implements Serializable {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy file từ request
        Part filePart = request.getPart("file");

        // Tạo một InputStream để đọc dữ liệu của file
        InputStream fileContent = filePart.getInputStream();

        try (InputStreamReader reader = new InputStreamReader(fileContent);
             CSVReader csvReader = new CSVReader(reader)) {
            csvReader.skip(1);
            String[] nextRecord;

            while ((nextRecord = csvReader.readNext()) != null) {
                String name = nextRecord[0];
                String quantity = nextRecord[1];

                if (name.isEmpty() || quantity.isEmpty()) continue;

                int parseQuantity = Integer.parseInt(quantity);
                int categoryId = AdminCategoryMaterialServices.getINSTANCE().getIdByName(name);

                CategoryMaterial categoryMaterial = new CategoryMaterial();
                categoryMaterial.setId(categoryId);
                categoryMaterial.setName(name);

                Material material = new Material();

                material.setCategoryMaterial(categoryMaterial);
                material.setRemain(parseQuantity);

                // nếu id là -1 có nghĩa là chưa tồn tại trong DB
                if (categoryId == -1) {
                    // thêm loại mới vào DB
                    AdminCategoryMaterialServices.getINSTANCE().save(name);
                    // thêm hàng mới nhập vào DB
                    int newId = AdminCategoryMaterialServices.getINSTANCE().getIdByName(name);
                    AdminMaterialServices.getINSTANCE().save(newId, parseQuantity);
                } else {
                    // kiểm tra loại hàng đã được nhập hay chưa
                    int idOfMaterial = AdminMaterialServices.getINSTANCE().getIdByCategoryId(categoryId);
                    // thêm vào bảng Material
                    AdminMaterialServices.getINSTANCE().save(categoryId, parseQuantity);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher(ConfigPage.ADMIN_MATERIAL).forward(request, response);
    }
}
