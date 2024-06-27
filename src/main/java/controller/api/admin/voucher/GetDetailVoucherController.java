package controller.api.admin.voucher;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.VoucherDTO;
import services.voucher.VoucherServices;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/api/admin/voucher/detail")
public class GetDetailVoucherController extends HttpServlet {
    VoucherServices voucherServices = VoucherServices.getINSTANCE();
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd") // Customize date format here
            .create();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getParameter("code");
        if (code == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        VoucherDTO voucherDTO = voucherServices.getDetail(code);
        resp.getWriter().println(gson.toJson(voucherDTO));
    }
}
