package controller.api.admin.order;

import com.google.gson.JsonObject;
import controller.exception.AppException;
import controller.exception.ErrorCode;
import services.admin.AdminOrderServices;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UpdateStatusOrderTran", value = "/api/admin/order/update-status")
public class UpdateStatusOrderController extends HttpServlet {

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JsonObject jsonObject = new JsonObject();
        String orderId = (String) request.getAttribute("orderId");
        try {
            Integer orderStatusId = null, transactionStatusId = null;
            if (request.getAttribute("orderStatusId") != null) {
                orderStatusId = Integer.parseInt((String) request.getAttribute("orderStatusId"));
            }
            if (request.getAttribute("transactionStatusId") != null) {
                transactionStatusId = Integer.parseInt((String) request.getAttribute("transactionStatusId"));
            }

            boolean updateSuccess = AdminOrderServices.getINSTANCE().updateOrder(orderId, orderStatusId, transactionStatusId);

            if (!updateSuccess) throw new AppException(ErrorCode.UPDATE_FAILED);
            jsonObject.addProperty("code", ErrorCode.UPDATE_SUCCESS.getCode());
            jsonObject.addProperty("message", ErrorCode.UPDATE_SUCCESS.getMessage());
            response.getWriter().print(jsonObject);
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new AppException(ErrorCode.ERROR_PARAM_REQUEST);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}