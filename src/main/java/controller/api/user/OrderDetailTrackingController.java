package controller.api.user;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import dto.OrderDetailResponseDTO;
import dto.OrderItemResponseDTO;
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

@WebServlet(name = "OrderDetailTracking", value = "/api/user/order/detail")
public class OrderDetailTrackingController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        JsonObject jsonResponse = new JsonObject();
        User user = SessionManager.getInstance(req, resp).getUser();
        int userId = user.getId();
        String orderId = req.getParameter("orderId");
        OrderDetailResponseDTO orderDetail = HistoryService.getINSTANCE().getOrderByOrderId(orderId, userId);
        if (orderDetail == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            jsonResponse.addProperty("success", false);
            jsonResponse.addProperty("message", "Order not found");
        } else {
            jsonResponse.addProperty("success", true);
            jsonResponse.addProperty("message", "Order found");
            JsonObject dataObject = getOrderDetailJson(orderDetail);
            jsonResponse.add("data", dataObject);
        }
        resp.getWriter().print(gson.toJson(jsonResponse));
    }

    private JsonObject getOrderDetailJson(OrderDetailResponseDTO orderDetail) {
        JsonObject orderDetailJson = new JsonObject();
        orderDetailJson.addProperty("name", orderDetail.getFullName());
        orderDetailJson.addProperty("phone", orderDetail.getPhone());
        orderDetailJson.addProperty("dateOrder", String.valueOf(orderDetail.getOrderDate()));
        JsonObject addressJson = getAddress(orderDetail);
        orderDetailJson.add("address", addressJson);
        JsonArray orderItemsJson = getOrderItemsJsonArray(orderDetail.getOrderItems());
        orderDetailJson.add("items", orderItemsJson);
        return orderDetailJson;
    }

    private JsonArray getOrderItemsJsonArray(List<OrderItemResponseDTO> orderItems) {
        JsonArray jsonArray = new JsonArray();
        for (OrderItemResponseDTO item : orderItems) {
            JsonObject itemJson = new JsonObject();
            itemJson.addProperty("name", item.getName());
            itemJson.addProperty("size", item.getSize());
            itemJson.addProperty("color", item.getColor());
            itemJson.addProperty("quantity", item.getQuantity());
            itemJson.addProperty("price", item.getPrice());
            itemJson.addProperty("thumbnail", item.getThumbnail());
            jsonArray.add(itemJson);
        }
        return jsonArray;
    }



    private JsonObject getAddress(OrderDetailResponseDTO orderDetail){
        JsonObject addressJson = new JsonObject();
        addressJson.addProperty("province", orderDetail.getProvince());
        addressJson.addProperty("district", orderDetail.getDistrict());
        addressJson.addProperty("ward", orderDetail.getWard());
        addressJson.addProperty("detail", orderDetail.getDetail());
        return addressJson;
    }
}
