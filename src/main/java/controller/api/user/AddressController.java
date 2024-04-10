package controller.api.user;

import models.Address;
import models.User;
import org.json.JSONObject;
import services.UserServices;
import session.SessionManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URISyntaxException;

@WebServlet(name = "UpdateAddress", value = "/api/user/address")
public class AddressController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = SessionManager.getInstance(request, response).getUser().getId();
        Address address = UserServices.getINSTANCE().getAddress(userId);
        JSONObject jsonObject = new JSONObject();
        if (address == null) {
            jsonObject.put("status", HttpServletResponse.SC_NOT_FOUND);
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        jsonObject.put("status", HttpServletResponse.SC_OK);
        response.setStatus(HttpServletResponse.SC_OK);
        jsonObject.put("provinceId", address.getProvinceId());
        jsonObject.put("districtId", address.getDistrictId());
        jsonObject.put("wardId", address.getWardId());
        jsonObject.put("detail", address.getDetail());
        response.getWriter().write(jsonObject.toString());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String provinceId = request.getParameter("province");
        String districtId = request.getParameter("district");
        String wardId = request.getParameter("ward");

        String provinceName = request.getParameter("provinceName");
        String districtName = request.getParameter("districtName");
        String wardName = request.getParameter("wardName");
        String detail = request.getParameter("detail");
        if (provinceId == null || districtId == null || wardId == null || provinceName == null || districtName == null || wardName == null || detail == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        try {
            User user = SessionManager.getInstance(request, response).getUser();
            Address address = new Address();
            address.setProvinceId(provinceId);
            address.setDistrictId(districtId);
            address.setWardId(wardId);
            address.setProvinceName(provinceName);
            address.setDistrictName(districtName);
            address.setWardName(wardName);
            address.setDetail(detail);
            address.setUserId(user.getId());
            UserServices.getINSTANCE().updateAddress(address);
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        response.setStatus(HttpServletResponse.SC_OK);

    }
}