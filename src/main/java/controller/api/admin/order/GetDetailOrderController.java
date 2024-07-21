package controller.api.admin.order;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import models.*;
import services.admin.AdminOrderServices;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "SeeDetailOrderAdmin", value = "/api/admin/order/detail")
public class GetDetailOrderController extends HttpServlet {
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderId = (String) request.getAttribute("orderId");
        List<OrderDetail> listOrderDetailByOrderId = AdminOrderServices.getINSTANCE().getListOrderDetailByOrderId(orderId);
        Order orderTarget = AdminOrderServices.getINSTANCE().getOrderById(orderId);

        DeliveryMethod deliveryMethod = AdminOrderServices.getINSTANCE().getDeliveryMethodManageById(orderTarget.getDeliveryMethodId());
        PaymentMethod paymentMethod = AdminOrderServices.getINSTANCE().getPaymentMethodMangeById(orderTarget.getPaymentMethodId());
        OrderStatus orderStatus = AdminOrderServices.getINSTANCE().getOrderStatusById(orderTarget.getOrderStatusId());
        TransactionStatus transactionStatus = AdminOrderServices.getINSTANCE().getTransactionStatusById(orderTarget.getTransactionStatusId());

        JsonObject jsonObject = new JsonObject();
        Voucher voucher;
        if (orderTarget.getVoucherId() != 0) {
            voucher = AdminOrderServices.getINSTANCE().getVoucherById(orderTarget.getVoucherId());
            jsonObject.add("voucherTarget", gson.toJsonTree(voucher));
        }

        jsonObject.add("listOrderDetailByOrderId", gson.toJsonTree(listOrderDetailByOrderId));
        jsonObject.add("orderTarget", gson.toJsonTree(orderTarget));
        jsonObject.add("deliveryMethodTarget", gson.toJsonTree(deliveryMethod));
        jsonObject.add("paymentMethodTarget", gson.toJsonTree(paymentMethod));
        jsonObject.add("orderStatusTarget", gson.toJsonTree(orderStatus));
        jsonObject.add("transactionStatusTarget", gson.toJsonTree(transactionStatus));

        response.getWriter().print(jsonObject);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}