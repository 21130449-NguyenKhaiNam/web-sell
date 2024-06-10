package controller.api.admin.voucher;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import services.voucher.VoucherServices;
import services.voucher.VoucherState;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/api/admin/voucher/visible")
public class VisibleVoucherController extends HttpServlet {
    VoucherServices voucherServices = VoucherServices.getINSTANCE();
    Gson gson = new GsonBuilder().create();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getParameter("code");
        String type = req.getParameter("type");
        JsonObject jsonObject = new JsonObject();
        if (code == null) {
            jsonObject.addProperty("success", false);
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().println(gson.toJson(jsonObject));
            return;
        }
        VoucherState state;
        try {
            state = VoucherState.valueOf(type.toUpperCase());
            voucherServices.changeState(code, state);
            jsonObject.addProperty("success", true);
            resp.setStatus(HttpServletResponse.SC_OK);
        } catch (IllegalArgumentException e) {
            jsonObject.addProperty("success", false);
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        resp.getWriter().println(gson.toJson(jsonObject));
    }
}
