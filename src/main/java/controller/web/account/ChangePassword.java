package controller.web.account;

import config.ConfigPage;
import models.User;
import services.UserServices;
import utils.Encoding;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ChangePassword", value = "/ChangePassword")
public class ChangePassword extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(ConfigPage.USER_CHANGE_PASSWORD).forward(request, response);
//        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User auth = (User) session.getAttribute("auth");

        String errorOlddPass = "Mật khẩu không chính xác";

        String errorNewPassMinlength = "Mật khẩu phải tối thiểu 6 kí tự";
        String errorNewPassUpercase = "Mật khẩu phải có ít nhất 1 kí tự in hoa";
        String errorNewPassSpecialSymbol = "Mật khẩu phải có ít nhất 1 kí tự đăt biệt";
        String errorNewPassNumber = "Mật khẩu phải có ít nhất 1 số";

        String errorConfirmPass = "Mật khẩu nhập lại không chính xác";

        int countError=0;

        String idString = request.getParameter("userId");

        String oldPass = request.getParameter("oldPassword");
        String newPass = request.getParameter("newPassword");
        String confirmPass = request.getParameter("confirmPassword");

        if (!Encoding.getINSTANCE().toSHA1(oldPass).equals(auth.getPasswordEncoding())){
            request.setAttribute("errorOlddPass", errorOlddPass);
            countError++;
        }
        if (newPass.length() < 6) {
            request.setAttribute("errorNewPass", errorNewPassMinlength);
            countError++;
        } else if (!hasUpperCase(newPass)) {
            request.setAttribute("errorNewPass", errorNewPassUpercase);
            countError++;
        } else if (!hasSpecialSymbol(newPass)) {
            request.setAttribute("errorNewPass", errorNewPassSpecialSymbol);
            countError++;
        } else if (!hasNumber(newPass)) {
            request.setAttribute("errorNewPass", errorNewPassNumber);
            countError++;
        }
        if (!newPass.equals(confirmPass)){
            request.setAttribute("errorConfirmPass", errorConfirmPass);
            countError++;
        }
        request.getRequestDispatcher(ConfigPage.USER_CHANGE_PASSWORD).forward(request, response);

        if (countError == 0){
            try {
                int id = Integer.parseInt(idString);
                UserServices.getINSTANCE().updateUserPassword(id, Encoding.getINSTANCE().toSHA1(newPass));
                response.sendRedirect(request.getContextPath() + "/ChangePassword");
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean hasUpperCase(String word){
        for (char c:word.toCharArray()) {
            if (Character.isUpperCase(c)){
                return true;
            }
        }
        return  false;
    }

    private boolean hasSpecialSymbol(String word) {
        String specialSymbols = "!@#$%^&*()_-+=<>,.?/~`:";
        for (char c : word.toCharArray()) {
            if (specialSymbols.contains(String.valueOf(c))) {
                return true;
            }
        }
        return false;
    }


    private boolean hasNumber(String word) {
        boolean result = false;
        for (char c:word.toCharArray()) {
            if (Character.isDigit(c)){
                result = true;
            }else{
                result = false;
            }
        }
        return result;
    }

}