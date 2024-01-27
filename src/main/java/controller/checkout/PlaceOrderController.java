package controller.checkout;

import org.json.JSONObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "PlaceOrderController", value = "/PlaceOrder")
public class PlaceOrderController extends HttpServlet {

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

//        HttpSession session = request.getSession(true);
//        User userAuth = (User) session.getAttribute("auth");
//        String userIdCart = String.valueOf(userAuth.getId());
//        ShoppingCart cart = (ShoppingCart) session.getAttribute(userIdCart);
        int invoiceNo = Math.abs(UUID.randomUUID().hashCode());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("invoiceNo", invoiceNo);
        response.setContentType("application/json");
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