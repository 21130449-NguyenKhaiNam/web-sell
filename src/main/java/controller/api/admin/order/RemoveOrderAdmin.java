package controller.api.admin.order;

import org.json.JSONObject;
import services.AdminOrderServices;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "RemoveOrderAdmin", value = "/RemoveOrderAdmin")
public class RemoveOrderAdmin extends HttpServlet {

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        String actionTarget = (String) request.getAttribute("action");
        String[] multipleOrderId = (String[]) request.getAttribute("multipleOrderId");

        AdminOrderServices.getINSTANCE().removeOrderByMultipleOrderId(multipleOrderId);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("successProcess", "Xóa đơn hàng có mã " + String.join(",", multipleOrderId) +  " thành công");
        jsonObject.put("removeOrderAction", actionTarget);
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