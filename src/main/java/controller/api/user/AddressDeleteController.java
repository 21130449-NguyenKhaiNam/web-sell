package controller.api.user;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dao.IAddressDAO;
import models.Address;
import models.User;
import services.AddressServices;
import session.SessionManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AddressDelete", value = "/api/user/address/delete")
public class AddressDeleteController extends HttpServlet {
    private final AddressServices addressServices = AddressServices.getINSTANCE();
    Gson gson = new Gson();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JsonObject jsonObject = new JsonObject();
        String id = req.getParameter("id");
        if (id == null) {
            jsonObject.addProperty("status", false);
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write(gson.toJson(jsonObject));
            return;
        }

        try {
            Integer addressId = Integer.parseInt(id);
            SessionManager sessionManager = SessionManager.getInstance(req, resp);
            User user = sessionManager.getUser();
            if (user == null) {
                jsonObject.addProperty("status", false);
                resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                resp.getWriter().write(gson.toJson(jsonObject));
                return;
            }
            if (addressServices.deleteAddress(addressId, user.getId())) {
                jsonObject.addProperty("status", true);
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.getWriter().write(gson.toJson(jsonObject));
                return;
            }
            jsonObject.addProperty("status", false);
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write(gson.toJson(jsonObject));
        } catch (Exception e) {
            jsonObject.addProperty("status", false);
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write(gson.toJson(jsonObject));
        }
    }
}
