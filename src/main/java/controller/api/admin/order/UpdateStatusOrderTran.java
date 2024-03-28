package controller.api.admin.order;

import models.OrderStatus;
import models.TransactionStatus;
import org.json.JSONObject;
import services.admin.AdminOrderServices;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UpdateStatusOrderTran", value = "/api/admin/order/update-status")
public class UpdateStatusOrderTran extends HttpServlet {

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONObject jsonObject = new JSONObject();
        String orderId = (String) request.getAttribute("orderId");
        try {
            int orderStatusId = Integer.parseInt((String) request.getAttribute("orderStatusId"));
            int transactionStatusId = Integer.parseInt((String) request.getAttribute("transactionStatusId"));
            AdminOrderServices.getINSTANCE().updateStatusByOrderId(orderId, orderStatusId, transactionStatusId);

            OrderStatus orderStatus = AdminOrderServices.getINSTANCE().getOrderStatusById(orderStatusId);
            TransactionStatus transactionStatus = AdminOrderServices.getINSTANCE().getTransactionStatusById(transactionStatusId);

            jsonObject.put("successUpdate", "Cập nhật trạng thái thành công");
            jsonObject.put("orderStatusUpdate", orderStatus.getTypeStatus());
            jsonObject.put("transactionStatusUpdate", transactionStatus.getTypeStatus());
            response.getWriter().print(jsonObject);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}