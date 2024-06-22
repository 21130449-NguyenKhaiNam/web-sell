package controller.web.admin.importMaterial;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import services.admin.AdminMaterialServices;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@WebServlet(name = "exportExcelMaterial", value = "/exportExcelMaterial")
public class ExportExcelMaterial extends HttpServlet implements Serializable {
    private final int LIMIT = 10000;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Thiết lập header cho phản hồi HTTP
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=log_report.xlsx");

        SXSSFWorkbook workbook = new SXSSFWorkbook();
        Sheet sheet = workbook.createSheet("Data");


        // Tạo tiêu đề cho các cột
        Row headerRow = sheet.createRow(0);
        String[] columnHeaders = {"ID", "Loại vải", "Số lượng nhập", "Ngày nhập"};
        for (int i = 0; i < columnHeaders.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columnHeaders[i]);
        }

        String targetFormat = "dd/MM/yyyy";
        long quantity = AdminMaterialServices.getINSTANCE().countAll();
        long loop = (quantity + LIMIT - 1) / LIMIT; // làm tròn lên

        for (long i = 0; i < loop; i++) {
            List<Map<String, Object>> materialList = AdminMaterialServices.getINSTANCE().getLimit(LIMIT, (int) (i * LIMIT));
            int rowNum = 1;
            for (Map<String, Object> m : materialList) {
                Row row = sheet.createRow(rowNum++);

                int col = 0;
                for (Map.Entry<String, Object> entry : m.entrySet()) {
                    row.createCell(col).setCellValue(entry.getValue()+"");
                    col++;
                }
            }
        }

        try (OutputStream out = response.getOutputStream()) {
            workbook.write(out);
        }
    }
}
