package controller.api.voucher;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import models.Voucher;
import services.VoucherServices;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/api/voucher/getAll")
public class LoadVoucherController extends HttpServlet {
    VoucherServices voucherService = new VoucherServices();
    Gson gson = new GsonBuilder().create();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Voucher voucher = new Voucher();
        resp.getWriter().write(gson.toJson(voucherService.getAll()));
    }
}
