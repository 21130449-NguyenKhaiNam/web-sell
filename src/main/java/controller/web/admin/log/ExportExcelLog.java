package controller.web.admin.log;

import models.Log;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import services.LogService;
import java.text.SimpleDateFormat;
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
    private final int LIMIT = 10000;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        // Thiết lập header cho phản hồi HTTP
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=log_report.xlsx");

        SXSSFWorkbook workbook = new SXSSFWorkbook();
        Sheet sheet = workbook.createSheet("Data");

        // Tạo tiêu đề cho các cột
        Row headerRow = sheet.createRow(0);
        String[] columnHeaders = {"Mã số", "IP", "Mức độ", "Ngày tạo", "Tác động", "Giá trị trước", "Giá trị sau"};
        for (int i = 0; i < columnHeaders.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columnHeaders[i]);
        }

        String targetFormat = "dd/MM/yyyy";
        long quantity = LogService.getINSTANCE().getQuantity();
        long loop = (quantity + LIMIT - 1) / LIMIT; // làm tròn lên
        int rowNum = 1;

        for (long i = 0; i < loop; i++) {
            List<Log> logList = LogService.getINSTANCE().getLimit(LIMIT, (int) (i * LIMIT));
            System.out.println(i);
            for (Log log : logList) {

                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(log.getId());
                row.createCell(1).setCellValue(log.getIp());
                row.createCell(2).setCellValue(log.getLevel());

                SimpleDateFormat targetSdf = new SimpleDateFormat(targetFormat);
                String targetDateString = targetSdf.format(log.getDateCreated());

                row.createCell(3).setCellValue(targetDateString);
                row.createCell(4).setCellValue(log.getResource());

                String prev = log.getPrevious();
                String current = log.getCurrent();

                row.createCell(5).setCellValue(prev != null ? prev : "");
                row.createCell(6).setCellValue(current != null ? current : "");
            }
        }

        try (OutputStream out = response.getOutputStream()) {
            workbook.write(out);
        }
    }

}
