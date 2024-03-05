package controller.checkout;

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
//        boolean isRegisterValid = true;

        HttpSession session = request.getSession(true);
        DeliveryInfoStorage deliveryInfoStorage = (DeliveryInfoStorage) session.getAttribute("deliveryInfoStorage");
//        if(deliveryInfoStorage == null){
//            deliveryInfoStorage = new DeliveryInfoStorage();
//            session.setAttribute("deliveryInfoStorage", deliveryInfoStorage);
//        }

//        if (fullName.isEmpty() || address.isEmpty()) {
//            isRegisterValid = false;
//            if (fullName.isEmpty()) {
//                errorFields.put("fullNameError", "Vui lòng bạn nhập họ và tên");
//            }
//
//            if (address.isEmpty()) {
//                errorFields.put("addressError", "Vui lòng bạn nhập địa chỉ giao hàng");
//            }
//        }
//
//        if (email.isEmpty() || !isValidEmail(email)) {
//            isRegisterValid = false;
//            if (email.isEmpty()) {
//                errorFields.put("emailError", "Vui lòng bạn nhập email");
//            } else if (!isValidEmail(email)) {
//                errorFields.put("emailError", "Vui lòng bạn nhập email hơp lệ (Cấu trúc email: tên_email@tên_miền)\nVí dụ: yourname@example.com");
//            }
//        }
//
//        if (phone.isEmpty() || !isValidPhone(phone)) {
//            isRegisterValid = false;
//            if (phone.isEmpty()) {
//                errorFields.put("phoneError", "Vui lòng bạn nhập số điện thoại");
//            } else if (!isValidPhone(phone)) {
//                errorFields.put("phoneError", "Vui lòng bạn nhập số điện thoại hợp lệ (Số điện thoại gồm 10 bắt đầu bằng số 0)");
//            }
//        }

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