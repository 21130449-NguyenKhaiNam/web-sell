package controller.api.admin.order;

import models.OrderStatus;
import models.TransactionStatus;
import org.json.JSONObject;
import services.admin.AdminOrderServices;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UpdateStatusOrderTran", value = "/UpdateStatusOrderTran")
public class UpdateStatusOrderTran extends HttpServlet {

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

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
        }catch (Exception exception){
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