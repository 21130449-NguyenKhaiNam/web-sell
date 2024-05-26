package controller.api.voucher;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import models.Voucher;
import services.voucher.VoucherServices;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(value = "/api/voucher/getAll")
public class LoadVoucherController extends HttpServlet {
    VoucherServices voucherService = new VoucherServices();
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd") // Customize date format here
            .create();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JsonObject jsonObject = new JsonObject();
        try {
            String[] idsParam = req.getParameterValues("id[]");
            if (idsParam != null) {
                List<Integer> ids = List.of(idsParam).stream().map(Integer::parseInt).collect(Collectors.toList());
                List<Voucher> vouchers = voucherService.getAll(ids);
                JsonArray jsonArray = gson.toJsonTree(vouchers).getAsJsonArray();
                jsonObject.add("vouchers", jsonArray);
            }
            jsonObject.addProperty("success", true);
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            jsonObject.addProperty("success", false);
        }
        resp.getWriter().write(gson.toJson(jsonObject));
    }
}
