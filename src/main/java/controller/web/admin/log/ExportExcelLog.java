package controller.web.admin.log;

import models.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import services.LogService;
import services.LogServices;
import services.admin.AdminOrderServices;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.List;

@WebServlet(name = "exportExcelLog", value = "/exportExcelLog")
public class ExportExcelLog extends HttpServlet implements Serializable {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Thiết lập header cho phản hồi HTTP
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=log_report.xlsx");

        // Tạo workbook và sheet
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Data");

        List<Log> logList = LogService.getINSTANCE().getAll();

        // Tạo tiêu đề cho các cột
        Row headerRow = sheet.createRow(0);
        String[] columnHeaders = {"Mã số", "IP", "Mức độ", "Ngày tạo", "Tác động", "Giá trị trước", "Giá trị sau"};
        for (int i = 0; i < columnHeaders.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columnHeaders[i]);
        }

        // Viết dữ liệu vào sheet
        int rowNum = 1;
        for (Log log : logList) {
            Row row = sheet.createRow(rowNum++);

            row.createCell(0).setCellValue(log.getId());
            row.createCell(1).setCellValue(log.getIp());
            row.createCell(2).setCellValue(log.getLevel());
            row.createCell(3).setCellValue(log.getDateCreated());
            row.createCell(4).setCellValue(log.getResource());
            row.createCell(5).setCellValue(log.getPrevious());
            row.createCell(6).setCellValue(log.getCurrent());
        }

        // Ghi workbook vào OutputStream
        try (OutputStream out = response.getOutputStream()) {
            workbook.write(out);
        } finally {
            workbook.close();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}