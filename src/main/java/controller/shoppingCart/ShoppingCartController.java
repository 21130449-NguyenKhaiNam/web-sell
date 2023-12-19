package controller.shoppingCart;

import models.Voucher;
import services.ShoppingCartServices;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ShoppingCartController", value = "/ShoppingCart")
public class ShoppingCartController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Voucher> listVouchers = ShoppingCartServices.getINSTANCE().getListVouchers();
        HttpSession session = request.getSession(true);
        session.setAttribute("listVouchers", listVouchers);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("shoppingCart.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("promotion__code");
        double temporaryPrice = Double.parseDouble(request.getParameter("tempPrice"));
        request.setAttribute("code", code);
        request.setAttribute("temporaryPrice", temporaryPrice);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("ApplyVoucher");
        requestDispatcher.forward(request, response);
    }
}