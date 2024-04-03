package controller.api.admin.user;

import models.Address;
import models.User;
import services.UserServices;
import session.SessionManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URISyntaxException;

@WebServlet(name = "UpdateInfo", value = "/api/user/address")
public class UpdateAddress extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String province = request.getParameter("province");
        String district = request.getParameter("district");
        String ward = request.getParameter("ward");

        String provinceName = request.getParameter("provinceName");
        String districtName = request.getParameter("districtName");
        String wardName = request.getParameter("wardName");
        String detail = request.getParameter("detail");
        if (province == null || district == null || ward == null || provinceName == null || districtName == null || wardName == null || detail == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        try {
            Address address = new Address();
            address.setProvince(Integer.parseInt(province));
            address.setDistrict(Integer.parseInt(district));
            address.setWard(Integer.parseInt(ward));
            address.setProvinceName(provinceName);
            address.setDistrictName(districtName);
            address.setWardName(wardName);
            address.setDetail(detail);

            User user = SessionManager.getInstance(request, response).getUser();
            UserServices.getINSTANCE().updateAddress(user.getId(), address);
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        response.setStatus(HttpServletResponse.SC_OK);

    }
}