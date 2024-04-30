package controller.api.checkout;

import models.DeliveryInfoStorage;
import org.json.JSONObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "EditDeliveryInfoController", value = "/api/checkout/delivery/edit")
public class EditDeliveryInfoController extends HttpServlet {

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String deliveryInfoKey = (String) request.getAttribute("deliveryInfoKey");
        String fullName = (String) request.getAttribute("fullName");
        String email = (String) request.getAttribute("email");
        String phone = (String) request.getAttribute("phone");
        String address = (String) request.getAttribute("address");
        HttpSession session = request.getSession(true);
        DeliveryInfoStorage deliveryInfoStorage = (DeliveryInfoStorage) session.getAttribute("deliveryInfoStorage");

        JSONObject errorFields = new JSONObject();
        boolean isUpdateValid = deliveryInfoStorage.checkAllValidationDeliveryInfo(errorFields, fullName, email, phone, address);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("errorFields", errorFields);
        jsonObject.put("isUpdateValid", isUpdateValid);

        if(isUpdateValid){
            boolean updateDelivery = deliveryInfoStorage.updateDeliveryInfo(fullName, email, phone, address, deliveryInfoKey);
            if(!updateDelivery){
                jsonObject.put("duplicateError", "Thông tin giao hàng đã tồn tại vui lòng bạn nhập thông tin khác hoặc thực hiện chỉnh sửa hoặc xóa");
            }else {
                jsonObject.put("newFullName", fullName);
                jsonObject.put("newEmail", email);
                jsonObject.put("newPhone", phone);
                jsonObject.put("newAddress", address);
                session.setAttribute("deliveryInfoStorage", deliveryInfoStorage);
            }
        }
        PrintWriter printWriter = response.getWriter();
        printWriter.print(jsonObject);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}