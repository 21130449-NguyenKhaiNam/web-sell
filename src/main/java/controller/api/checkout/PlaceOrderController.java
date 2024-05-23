package controller.api.checkout;

import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.UUID;
///api/checkout/address
@WebServlet(name = "PlaceOrderController", value = "/PlaceOrder")
public class PlaceOrderController extends HttpServlet {

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        int invoiceNo = Math.abs(UUID.randomUUID().hashCode());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("invoiceNo", invoiceNo);
        response.setContentType("application/json");
        HttpSession session = request.getSession();
        session.setAttribute("invoiceNo", invoiceNo);

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