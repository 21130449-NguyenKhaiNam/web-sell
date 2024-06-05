package controller.web.admin.user;

import models.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import services.admin.AdminCategoryServices;
import services.admin.AdminProductServices;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.List;

@WebServlet(name = "exportExcelProduct", value = "/exportExcelProduct")
public class ExportExcelProduct extends HttpServlet implements Serializable {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Thiết lập header cho phản hồi HTTP
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=product_report.xlsx");

        // Tạo workbook và sheet
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Data");


        List<Product> productList = AdminProductServices.getINSTANCE().getAll();

        // Tạo tiêu đề cho các cột
        Row headerRow = sheet.createRow(0);
        String[] columnHeaders = {"Mã sản phẩm", "Tên sản phẩm", "Phân loại sản phẩm", "Giá gốc", "Giá giảm"};
        for (int i = 0; i < columnHeaders.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columnHeaders[i]);
        }

        // Viết dữ liệu vào sheet
        int rowNum = 1;
        for (Product product : productList) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(product.getId());
            row.createCell(1).setCellValue(product.getName());

            List<Category> category = AdminCategoryServices.getINSTANCE().getCategoryById(product.getCategoryId());
            if(!category.isEmpty()){
                row.createCell(2).setCellValue(category.get(0).getNameType());
            }

            row.createCell(3).setCellValue(product.getOriginalPrice());
            row.createCell(4).setCellValue(product.getSalePrice());

        }

        // Ghi workbook vào OutputStream
        try (OutputStream out = response.getOutputStream()) {
            workbook.write(out);
        } finally {
            workbook.close();
        }

        System.out.println(" in report thanh cong");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}