package controller.api.user;

import dto.OrderDTO;
import org.json.JSONObject;
import services.HistoryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "OrderTracking", value = "/api/user/order")
public class OrderTracking extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        int start = Integer.parseInt(request.getParameter("start"));
//        int length = Integer.parseInt(request.getParameter("length"));
        int statusId = Integer.parseInt(request.getParameter("statusId"));

        List<OrderDTO> orders = HistoryService.getINSTANCE().getOrderByStatusId(statusId);

        JSONObject json = new JSONObject();
        json.put("data", orders);
        response.getWriter().print(json);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
