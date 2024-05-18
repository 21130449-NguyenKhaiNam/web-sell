package controller.api.voucher;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import services.VoucherServices;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

@SuppressWarnings("ALL")
@WebServlet(value = "/api/voucher/apply")
public class ApplyVoucherController extends HttpServlet {
    Gson gson = new GsonBuilder().create();
    VoucherServices voucherServices = new VoucherServices();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String code = req.getParameter("voucherId");
            List<Integer> cartItems;
            Type cartListType = new TypeToken<List<Integer>>() {
            }.getType();
            cartItems = gson.fromJson(req.getParameter("cartItems"), cartListType);
//            voucherServices.canApply(code, cartItems);
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
