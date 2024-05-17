package controller.api.user;

import dto.OrderResponseDTO;
import models.User;
import org.json.JSONObject;
import services.HistoryService;
import session.SessionManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "OrderTracking", value = "/api/user/order")
public class OrderTrackingController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int statusId = Integer.parseInt(request.getParameter("statusId"));
        User user = SessionManager.getInstance(request, response).getUser();
        List<OrderResponseDTO> orders = HistoryService.getINSTANCE().getOrder(user.getId(), statusId);
        JSONObject json = new JSONObject();
        json.put("data", orders);
        response.getWriter().print(json);
    }
}
