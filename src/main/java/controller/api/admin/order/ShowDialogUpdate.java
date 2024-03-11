package controller.api.admin.order;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.Order;
import models.OrderStatus;
import models.TransactionStatus;
import org.json.JSONObject;
import services.AdminOrderServices;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ShowDialogUpdate", value = "/ShowDialogUpdate")
public class ShowDialogUpdate extends HttpServlet {

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        List<OrderStatus> listAllOrderStatus = AdminOrderServices.getINSTANCE().getListAllOrderStatus();
        List<TransactionStatus> listAllTransactionStatus = AdminOrderServices.getINSTANCE().getListAllTransactionStatus();

        String orderId = (String) request.getAttribute("orderId");
        Order order = AdminOrderServices.getINSTANCE().getOrderById(orderId);

        OrderStatus orderStatusTarget = AdminOrderServices.getINSTANCE().getOrderStatusById(order.getOrderStatusId());
        TransactionStatus transactionStatusTarget = AdminOrderServices.getINSTANCE().getTransactionStatusById(order.getTransactionStatusId());

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("listAllOrderStatus", listAllOrderStatus);
        jsonObject.put("listAllTransactionStatus", listAllTransactionStatus);

        ObjectMapper objectMapper = new ObjectMapper();

        jsonObject.put("orderStatusTarget", objectMapper.writeValueAsString(orderStatusTarget));
        jsonObject.put("transactionStatusTarget", objectMapper.writeValueAsString(transactionStatusTarget));

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