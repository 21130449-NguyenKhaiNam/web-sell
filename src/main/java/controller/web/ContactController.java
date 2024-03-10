package controller.web;

import models.SubjectContact;
import models.User;
import org.json.JSONObject;
import services.ContactServices;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "ContactController", value = "/Contact")
public class ContactController extends HttpServlet implements Serializable {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<SubjectContact> listContactSubjects = ContactServices.getINSTANCE().getListContactSubjects();
        request.setCharacterEncoding("UTF-8");
        request.setAttribute("listContactSubjects", listContactSubjects);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("contact.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String fullName = request.getParameter("fullName");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String subject = request.getParameter("subject");
        String message = request.getParameter("message");

        JSONObject jsonObject = new JSONObject();
        JSONObject errorFields = new JSONObject();

        boolean isContactValid = checkAllValidationContact(errorFields, fullName, email, phone);
        jsonObject.put("isContactValid", isContactValid);
        jsonObject.put("errorFields", errorFields);

        if(isContactValid){
            int subjectId = ContactServices.getINSTANCE().getIdContactSubjectByName(subject);
            User userAuth = (User) request.getSession(true).getAttribute("auth");
            Integer userAuthId;

            if(userAuth != null){
                userAuthId = userAuth.getId();
            }else{
                userAuthId = null;
            }
            ContactServices.getINSTANCE().addNewRecordUserContact(userAuthId, fullName, phone, email, subjectId, message);
            String succeedContact = "<p><i class=\"fa-solid fa-circle-check\"></i> Bạn đã gửi liên hệ thành công</p>";
            request.setAttribute("succeedContact", succeedContact);
            jsonObject.put("succeedContact", request.getAttribute("succeedContact"));
        }

        response.getWriter().print(jsonObject);
    }

//    private boolean validationFullName(HttpServletRequest request, String fullName)  throws ServletException, IOException{
//        if(fullName == null || fullName.trim().isEmpty()){
//            request.setAttribute("fullNameError", "Vui lòng bạn nhập họ và tên");
//            return false;
//        }
//        return true;
//    }

    public boolean checkAllValidationContact(JSONObject errorFields, String fullName, String email, String phone){
        boolean isAllValid = true;
        if (fullName.isEmpty()) {
            errorFields.put("fullNameError", "Vui lòng bạn nhập họ và tên");
        }

        if (email.isEmpty() || !isValidEmail(email)) {
            isAllValid = false;
            if (email.isEmpty()) {
                errorFields.put("emailError", "Vui lòng bạn nhập email");
            } else if (!isValidEmail(email)) {
                errorFields.put("emailError", "Vui lòng bạn nhập email hơp lệ (Cấu trúc email: tên_email@tên_miền)\nVí dụ: yourname@example.com");
            }
        }

        if (phone.isEmpty() || !isValidPhone(phone)) {
            isAllValid = false;
            if (phone.isEmpty()) {
                errorFields.put("phoneError", "Vui lòng bạn nhập số điện thoại");
            } else if (!isValidPhone(phone)) {
                errorFields.put("phoneError", "Vui lòng bạn nhập số điện thoại hợp lệ (Số điện thoại gồm 10 bắt đầu bằng số 0)");
            }
        }
        return isAllValid;
    }

    private boolean isValidEmail(String email) {
        Pattern patternEmail = Pattern.compile("^\\w+@\\w+\\.[A-Za-z]+$");
        Matcher matcherEmail = patternEmail.matcher(email);
        return matcherEmail.matches();
    }

    private boolean isValidPhone(String phone) {
        Pattern patternPhone = Pattern.compile("^\\+?(?:\\d\\s?){9,13}$");
        Matcher matcherPhone = patternPhone.matcher(phone);
        return matcherPhone.matches();
    }

//    private boolean validationPhone(HttpServletRequest request, String phone) throws ServletException, IOException{
//        if(phone == null || phone.trim().isEmpty()){
//            request.setAttribute("phoneError", "Vui lòng bạn nhập số điện thoại");
//            return false;
//        }else{
//            Pattern  patternPhone = Pattern.compile("^\\+?(?:\\d\\s?){9,13}$");
//            Matcher matcherPhone = patternPhone.matcher(phone);
//            if(!matcherPhone.matches()){
//                request.setAttribute("phoneError", "Vui lòng bạn nhập số điện thoại hợp lệ (Số điện thoại gồm 10 bắt đầu bằng số 0)");
//                return false;
//            }else{
//                return true;
//            }
//        }
//    }
//    private boolean validationEmail(HttpServletRequest request, String email) throws ServletException, IOException{
////        String demoRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\\\.[A-Za-z]+$";
//        if(email == null || email.trim().isEmpty()){
//            request.setAttribute("emailError", "Vui lòng bạn nhập email");
//            return false;
//        }else{
//            Pattern patternEmail = Pattern.compile("^\\w+@\\w+\\.[A-Za-z]+$");
//            Matcher matcherEmail = patternEmail.matcher(email);
//            if(!matcherEmail.matches()){
//                request.setAttribute("emailError", " Vui lòng bạn nhập email hơp lệ (Cấu trúc email: tên_email@tên_miền) <br/> Ví dụ: yourname@example.com");
//                return false;
//            }else{
//                return true;
//            }
//        }
//    }
}