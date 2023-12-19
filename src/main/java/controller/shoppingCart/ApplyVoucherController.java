package controller.shoppingCart;

import models.Voucher;
import services.ShoppingCartServices;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ApplyVoucherController", value = "/ApplyVoucher")
public class ApplyVoucherController extends HttpServlet {

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        List<String> listCodeOfVouchers = ShoppingCartServices.getINSTANCE().getListCodeOfVouchers();
        String code = (String) request.getAttribute("code");
        double temporaryPrice = (double) request.getAttribute("temporaryPrice");
        if(listCodeOfVouchers.contains(code)){
            Voucher voucher = ShoppingCartServices.getINSTANCE().getDiscountPercentByCode(temporaryPrice, code);
            if(voucher != null){
                double discountPercent = voucher.getDiscountPercent();
                double newTotalPrice = temporaryPrice * (1 - discountPercent);
                String successApplied = "Bạn đá áp dụng mã " + code + " thành công";
                request.setAttribute("newTotalPrice", newTotalPrice);
                request.setAttribute("successApplied", successApplied);
            }else {
                double minPriceToApply = voucher.getMinimumPrice();
                request.setAttribute("failedApply", "Bạn chưa đủ điều kiện để áp dụng mã " + code + ". Hãy mua thêm " + (minPriceToApply - temporaryPrice));
            }
        }else{
            request.setAttribute("failedApply", "Mã " + code + " mà bạn nhập không tồn tại");
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("shoppingCart.jsp");
        requestDispatcher.forward(request, response);
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