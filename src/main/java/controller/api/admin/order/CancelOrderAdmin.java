package controller.api.admin.order;

import models.Order;
import models.OrderStatus;
import org.json.JSONObject;
import services.admin.AdminOrderServices;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Random;

@WebServlet(name = "CancelOrderAdmin", value = "/CancelOrderAdmin")
public class CancelOrderAdmin extends HttpServlet {

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        String actionTarget = (String) request.getAttribute("action");
        String[] multipleOrderId = (String[]) request.getAttribute("multipleOrderId");
        AdminOrderServices.getINSTANCE().cancelOrderByMultipleId(multipleOrderId);

        Random random = new Random();
        String orderIdRepresent = multipleOrderId[random.nextInt(multipleOrderId.length)];
        Order orderRepresent = AdminOrderServices.getINSTANCE().getOrderById(orderIdRepresent);
        OrderStatus orderStatusRepresent = AdminOrderServices.getINSTANCE().getOrderStatusById(orderRepresent.getOrderStatusId());

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("successProcess", "Hủy đơn hàng có mã " + String.join(", ", multipleOrderId) +  " thành công");
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