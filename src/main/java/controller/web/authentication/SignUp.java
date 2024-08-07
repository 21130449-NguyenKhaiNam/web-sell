package controller.web.authentication;

import config.ConfigPage;
import models.User;
import services.authentication.AuthenticateServices;
import utils.Validation;


import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Date;
import java.util.Map;


@WebServlet(name = "signUp", value = "/signUp")
public class SignUp extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        // Chưa check và thêm giới tính
        String gender = request.getParameter("gender");
        String password = request.getParameter("password");
        String fullName = request.getParameter("fullname");
        String phone = request.getParameter("phone");
        String birthday = request.getParameter("birthday");
        String confirmPassword = request.getParameter("confirm-password");
        User user = User.builder()
                .username(username)
                .email(email)
                .gender(gender)
                .passwordEncoding(password)
                .fullName(fullName)
                .phone(phone).birthDay(Date.valueOf(birthday)).build();
        Validation validation = AuthenticateServices.getINSTANCE().checkSignUp(user, confirmPassword);

        Map<String, String> mapErrorPassword = AuthenticateServices.getINSTANCE().checkPasswordTemplate(password);
        Map<String, Integer> managerIp = (Map<String, Integer>) request.getAttribute("managerIp");
        // Đăng nhập thành công khi: mapErrorPassword == null và validation.getObjReturn() != null
        if (validation.getObjReturn() != null && mapErrorPassword == null) {
            User newUser = (User) validation.getObjReturn();
            AuthenticateServices.getINSTANCE().createUser(newUser);
            request.setAttribute("sendMail", "Send Mail Success");
            if(managerIp != null)
                managerIp.put(request.getRemoteAddr(), 0);
        } else {
            request.setAttribute("usernameError", validation.getFieldUsername());
            request.setAttribute("emailError", validation.getFieldEmail());
            request.setAttribute("passwordError", validation.getFieldPassword());
            request.setAttribute("passwordConfirmError", validation.getFieldConfirmPassword());

            // Mật khẩu thỏa nhưng tài khoản có trong csdl? -> mapErrorPassword != null
            if (mapErrorPassword != null) {
                request.setAttribute("charUpper", mapErrorPassword.get("char-upper"));
                request.setAttribute("charMinLength", mapErrorPassword.get("char-min-length"));
                request.setAttribute("charLower", mapErrorPassword.get("char-lower"));
                request.setAttribute("charNumber", mapErrorPassword.get("char-number"));
                request.setAttribute("charSpecial", mapErrorPassword.get("char-special"));
                request.setAttribute("noSpace", mapErrorPassword.get("no-space"));
            }
            if(managerIp != null)
                managerIp.put(request.getRemoteAddr(), managerIp.get(request.getRemoteAddr()));
        }
        request.getRequestDispatcher(ConfigPage.SIGN_UP).forward(request, response);
    }
}