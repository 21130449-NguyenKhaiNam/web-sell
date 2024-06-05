package controller.web.admin.order;

import models.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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

@WebServlet(name = "exportExcelOrder", value = "/exportExcelOrder")
public class ExportExcelOrder extends HttpServlet implements Serializable {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Thiết lập header cho phản hồi HTTP
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=order_report.xlsx");

        // Tạo workbook và sheet
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Data");

        List<Order> orderList = AdminOrderServices.getINSTANCE().getListAllOrders();


        // Tạo tiêu đề cho các cột
        Row headerRow = sheet.createRow(0);
        String[] columnHeaders = {"Mã đơn hàng", "Ngày tạo", "Khách hàng", "Phương thức vận chuyển", "Phương thức thanh toán", "Tình trạng đơn hàng", "Tình trạng giao dịch", "Tổng cộng"};
        for (int i = 0; i < columnHeaders.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columnHeaders[i]);
        }

        // Viết dữ liệu vào sheet
        int rowNum = 1;
        for (Order order : orderList) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(order.getId());
            row.createCell(1).setCellValue(order.getDateOrder());
            row.createCell(2).setCellValue(order.getFullName());

            DeliveryMethod deliveryMethod =  AdminOrderServices.getINSTANCE().getDeliveryMethodManageById(order.getDeliveryMethodId());
            if(deliveryMethod != null){
                row.createCell(3).setCellValue(deliveryMethod.getTypeShipping());
            }

            PaymentMethod paymentMethod = AdminOrderServices.getINSTANCE().getPaymentMethodMangeById(order.getPaymentMethodId());
            if(paymentMethod != null){
                row.createCell(4).setCellValue(paymentMethod.getTypePayment());
            }

            OrderStatus orderStatus = AdminOrderServices.getINSTANCE().getOrderStatusById(order.getOrderStatusId());
            if(orderStatus != null){
                row.createCell(5).setCellValue(orderStatus.getTypeStatus());
            }

            TransactionStatus transactionStatus = AdminOrderServices.getINSTANCE().getTransactionStatusById(order.getTransactionStatusId());
            if(transactionStatus != null){
                row.createCell(6).setCellValue(transactionStatus.getTypeStatus());
            }

            String total = AdminOrderServices.getINSTANCE().getTotalPriceFormatByOrderId(order.getId());
            row.createCell(7).setCellValue(total);
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