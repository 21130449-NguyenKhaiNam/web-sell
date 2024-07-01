package controller.web.review;

import models.OrderDetail;
import models.Review;
import models.User;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import services.ReviewServices;
import services.admin.AdminReviewServices;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.List;

@WebServlet(name = "exportExcelReview", value = "/exportExcelReview")
public class ExportExcelReview extends HttpServlet implements Serializable {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Thiết lập header cho phản hồi HTTP
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=review_report.xlsx");

        // Tạo workbook và sheet
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Data");

        List<Review> reviewList = AdminReviewServices.getINSTANCE().getAll();
        System.out.println(reviewList);

        // Tạo tiêu đề cho các cột
        Row headerRow = sheet.createRow(0);
        String[] columnHeaders = {"Mã khách hàng", "Tên sản phẩm", "feedback", "Mã đơn hàng", "Số sao", "Ngày tạo"};
        for (int i = 0; i < columnHeaders.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columnHeaders[i]);
        }

        // Viết dữ liệu vào sheet
        int rowNum = 1;
        for (Review review : reviewList) {
            Row row = sheet.createRow(rowNum++);

            User user = AdminReviewServices.getINSTANCE().getUserByIdProductDetail(review.getOrderDetailId());
            OrderDetail orderDetail = ReviewServices.getINSTANCE().getOrderDetail(review.getOrderDetailId());

            if (user != null && orderDetail != null) {
                row.createCell(0).setCellValue(user.getId());
                row.createCell(1).setCellValue(orderDetail.getProductName());
                row.createCell(2).setCellValue(review.getFeedback());
                row.createCell(3).setCellValue(review.getOrderDetailId());
                row.createCell(4).setCellValue(review.getRatingStar());
                row.createCell(5).setCellValue(review.getReviewDate());
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