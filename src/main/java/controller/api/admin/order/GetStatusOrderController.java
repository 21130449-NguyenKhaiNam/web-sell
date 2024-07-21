package controller.api.admin.order;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import models.Order;
import models.OrderStatus;
import models.TransactionStatus;
import services.admin.AdminOrderServices;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ShowDialogUpdate", value = "/api/admin/order/update-dialog")
public class GetStatusOrderController extends HttpServlet {
    Gson gson = new GsonBuilder().create();

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<OrderStatus> listAllOrderStatus = AdminOrderServices.getINSTANCE().getListAllOrderStatus();
        List<TransactionStatus> listAllTransactionStatus = AdminOrderServices.getINSTANCE().getListAllTransactionStatus();

        String orderId = (String) request.getAttribute("orderId");
        Order order = AdminOrderServices.getINSTANCE().getOrderById(orderId);

        OrderStatus orderStatusTarget = AdminOrderServices.getINSTANCE().getOrderStatusById(order.getOrderStatusId());
        TransactionStatus transactionStatusTarget = AdminOrderServices.getINSTANCE().getTransactionStatusById(order.getTransactionStatusId());

        JsonObject jsonObject = new JsonObject();
        jsonObject.add("listAllOrderStatus", gson.toJsonTree(listAllOrderStatus));
        jsonObject.add("listAllTransactionStatus", gson.toJsonTree(listAllTransactionStatus));

        jsonObject.add("orderStatusTarget", gson.toJsonTree(orderStatusTarget));
        jsonObject.add("transactionStatusTarget", gson.toJsonTree(transactionStatusTarget));

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