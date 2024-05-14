package controller.api.payment;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import models.Order;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

@WebServlet(value = "/payment/handle")
public class PaymentController extends HttpServlet {
    VNPayServices vnPayServices = new VNPayServices();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String listCartItem = req.getParameter("listCartItem");
        String vnp_TnnCode = req.getParameter("vnp_TnnCode");
        String vnp_TxnRef = req.getParameter("vnp_TxnRef");
        String vnp_Amount = req.getParameter("vnp_Amount");
        String vnp_OrderInfo = req.getParameter("vnp_OrderInfo");
        String vnp_ResponseCode = req.getParameter("vnp_ResponseCode");
        String vnp_BankCode = req.getParameter("vnp_BankCode");
        String vnp_BankTranNo = req.getParameter("vnp_BankTranNo");
        String vnp_CardType = req.getParameter("vnp_CardType");
        String vnp_PayDate = req.getParameter("vnp_PayDate");
        String vnp_TransactionNo = req.getParameter("vnp_TransactionNo");
        String vnp_TransactionStatus = req.getParameter("vnp_TransactionStatus");
        String vnp_SecureHash = req.getParameter("vnp_SecureHash");
//        Trường hợp thanh toán thành công
        if (vnp_TransactionNo.equals("00")) {
            Order order = Order.builder().id(vnp_TxnRef).message(vnp_OrderInfo).dateOrder(Date.valueOf(vnp_PayDate)).build();
            vnPayServices.saveOrder(order);
//        Tạo đơn hàng mới
        }
    }

    //    Nhận vào số tiền => redirect đến trang thanh toán của VNPay
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String paymentUrl = vnPayServices.generateURL(req);
        com.google.gson.JsonObject job = new JsonObject();
        job.addProperty("code", "00");
        job.addProperty("message", "success");
        job.addProperty("data", paymentUrl);
        Gson gson = new Gson();
        resp.getWriter().write(gson.toJson(job));
    }
}
