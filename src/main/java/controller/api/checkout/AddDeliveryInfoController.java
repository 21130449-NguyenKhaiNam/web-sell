package controller.api.checkout;

import models.DeliveryInfo;
import models.DeliveryInfoStorage;
import org.json.JSONObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "AddDeliveryInfoController", value = "/AddDeliveryInfo")
public class AddDeliveryInfoController extends HttpServlet {

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String fullName = (String) request.getAttribute("fullName");
        String email = (String) request.getAttribute("email");
        String phone = (String) request.getAttribute("phone");
        String address = (String) request.getAttribute("address");

        JSONObject errorFields = new JSONObject();

        HttpSession session = request.getSession(true);
        DeliveryInfoStorage deliveryInfoStorage = (DeliveryInfoStorage) session.getAttribute("deliveryInfoStorage");

        boolean isRegisterValid = deliveryInfoStorage.checkAllValidationDeliveryInfo(errorFields, fullName, email, phone, address);

        UUID uuid = UUID.randomUUID();
        String deliveryInfoKey = uuid.toString();
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("isRegisterValid", isRegisterValid);
        jsonObject.put("errorFields", errorFields);

        if (isRegisterValid) {
            DeliveryInfo deliveryInfo = new DeliveryInfo(fullName, email, phone, address);
            boolean addDeliveryInfo = deliveryInfoStorage.add(deliveryInfoKey, deliveryInfo);
            System.out.println(addDeliveryInfo);
            if (!addDeliveryInfo) {
                jsonObject.put("duplicateError", "Thông tin giao hàng đã tồn tại vui lòng bạn nhập thông tin khác hoặc thực hiện chỉnh sửa hoặc xóa");
            } else {
                jsonObject.put("deliInfoKey", deliveryInfoKey);
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

//    private boolean isValidEmail(String email) {
//        Pattern patternEmail = Pattern.compile("^\\w+@\\w+\\.[A-Za-z]+$");
//        Matcher matcherEmail = patternEmail.matcher(email);
//        return matcherEmail.matches();
//    }
//
//    private boolean isValidPhone(String phone) {
//        Pattern patternPhone = Pattern.compile("^\\+?(?:\\d\\s?){9,13}$");
//        Matcher matcherPhone = patternPhone.matcher(phone);
//        return matcherPhone.matches();
//    }
}