package controller.api.payment;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/api/payment/handle")
public class AjaxVNPay extends HttpServlet {
    private final VNPayServices vnPayServices = new VNPayServices();

}
