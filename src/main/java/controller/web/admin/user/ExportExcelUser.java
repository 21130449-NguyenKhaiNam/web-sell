package controller.web.admin.user;

import models.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import services.UserServices;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.List;

@WebServlet(name = "exportExcelUser", value = "/exportExcelUser")
public class ExportExcelUser extends HttpServlet implements Serializable {
    private final int LIMIT = 5000;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Thiết lập header cho phản hồi HTTP
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=user_report.xlsx");

        // Tạo workbook và sheet
        SXSSFWorkbook workbook = new SXSSFWorkbook();
        Sheet sheet = workbook.createSheet("Data");


        // Tạo tiêu đề cho các cột
        Row headerRow = sheet.createRow(0);
        String[] columnHeaders = {"Mã người dùng", "Tên người dùng", "Email", "Họ tên", "Giới tính", "Ngày sinh", "Số điện thoại", "Địa chỉ", "Vai trò"};
        for (int i = 0; i < columnHeaders.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columnHeaders[i]);
        }

        long quantity = UserServices.getINSTANCE().getQuantity();
        long loop = (quantity + LIMIT - 1) / LIMIT; // làm tròn lên
        int rowNum = 1;
        for (int i = 0; i < loop; i++) {
            List<User> userList = UserServices.getINSTANCE().getLimit(LIMIT, (i * LIMIT));;

            for (User user : userList) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(user.getId());
                row.createCell(1).setCellValue(user.getUsername());
                row.createCell(2).setCellValue(user.getEmail());
                row.createCell(3).setCellValue(user.getFullName());
                row.createCell(4).setCellValue(user.getGender());
                row.createCell(5).setCellValue(user.getBirthDay());
                row.createCell(6).setCellValue(user.getPhone());
                row.createCell(7).setCellValue(user.getAddress());
                String roleId = user.getRole();

                if(roleId.equals("0")){
                    row.createCell(8).setCellValue("Khách");
                }else if(roleId.equals("1")){
                    row.createCell(8).setCellValue("Mod");
                }
                else if(roleId.equals("2")){
                    row.createCell(8).setCellValue("Admin");
                }

            }
        }

        // Ghi workbook vào OutputStream
        try (OutputStream out = response.getOutputStream()) {
            workbook.write(out);
        } finally {
            workbook.close();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}