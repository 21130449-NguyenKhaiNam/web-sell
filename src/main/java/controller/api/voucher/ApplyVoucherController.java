package controller.api.voucher;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dao.VoucherDAO;
import dto.VoucherDTO;
import models.Voucher;
import services.voucher.VoucherState;
import models.User;
import services.voucher.VoucherServices;
import session.SessionManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


@WebServlet(value = "/api/voucher/apply")
public class ApplyVoucherController extends HttpServlet {
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd") // Customize date format here
            .create();
    VoucherDAO voucherDAO = new VoucherDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JsonObject jsonObject = new JsonObject();
        try {
            String code = req.getParameter("code");
            String[] idsParam = req.getParameterValues("id[]");
            User user = SessionManager.getInstance(req, resp).getUser();
            if (idsParam != null) {
                List<Integer> ids = List.of(idsParam).stream().map(Integer::parseInt).collect(Collectors.toList());
                VoucherDTO voucherDTO = VoucherServices.getINSTANCE().canApply(user, code, ids);
                JsonElement jsonElement = gson.toJsonTree(voucherDTO);
                jsonObject.add("result", jsonElement);
            }
            jsonObject.addProperty("success", true);
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            jsonObject.addProperty("success", false);
        }
        resp.getWriter().write(gson.toJson(jsonObject));
    }
}
