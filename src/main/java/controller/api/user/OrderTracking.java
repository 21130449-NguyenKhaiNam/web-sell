package controller.api.user;

import dto.OrderDTO;
import models.Paging;
import org.json.JSONObject;
import services.HistoryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "OrderTracking", value = "/api/user/order")
public class OrderTracking extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int start = Integer.parseInt(request.getParameter("start"));
        int length = Integer.parseInt(request.getParameter("length"));

        Paging<OrderDTO> paging =HistoryService.getINSTANCE().getOrderByStatusId(start, length);

        JSONObject json = new JSONObject();
        json.put("data", paging);
        response.getWriter().print(json);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
