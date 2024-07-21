package controller.api.admin.order;

import models.Order;
import models.OrderStatus;
import org.json.JSONObject;
import services.admin.AdminOrderServices;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;

@WebServlet(name = "CancelOrderAdmin", value = "/api/admin/order/cancel")
public class CancelOrderController extends HttpServlet {

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String actionTarget = (String) request.getAttribute("action");
        String[] multipleOrderId = (String[]) request.getAttribute("multipleOrderId");
        AdminOrderServices.getINSTANCE().cancelOrderByMultipleId(multipleOrderId);

        Random random = new Random();
        String orderIdRepresent = multipleOrderId[random.nextInt(multipleOrderId.length)];
        Order orderRepresent = AdminOrderServices.getINSTANCE().getOrderById(orderIdRepresent);
        OrderStatus orderStatusRepresent = AdminOrderServices.getINSTANCE().getOrderStatusById(orderRepresent.getOrderStatusId());

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("successProcess", "Hủy đơn hàng có mã " + String.join(", ", multipleOrderId) + " thành công");
        jsonObject.put("cancelOrderAction", actionTarget);
        jsonObject.put("cancelStatus", orderStatusRepresent.getTypeStatus());
        response.getWriter().print(jsonObject);
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