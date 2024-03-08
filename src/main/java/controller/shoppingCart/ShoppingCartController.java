package controller.shoppingCart;

import config.ConfigPage;
import models.Voucher;
import services.ShoppingCartServices;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ShoppingCartController", value = "/ShoppingCart")
public class ShoppingCartController extends HttpServlet {

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        RequestDispatcher requestDispatcher;
        if (action != null) {
            String productId = request.getParameter("productId");
            String cartProductIndex = request.getParameter("cartProductIndex");
            request.setAttribute("productId", productId);
            request.setAttribute("cartProductIndex", cartProductIndex);


            switch (action) {
                case "increaseQuantity": {
                    requestDispatcher = request.getRequestDispatcher("IncreaseQuantity");
                    requestDispatcher.forward(request, response);
                    break;
                }
                case "decreaseQuantity": {
                    requestDispatcher = request.getRequestDispatcher("DecreaseQuantity");
                    requestDispatcher.forward(request, response);
                    break;
                }
                case "removeCartProduct": {
                    requestDispatcher = request.getRequestDispatcher("DeleteCartProduct");
                    requestDispatcher.forward(request, response);
                    break;
                }
                case "applyVoucher": {
                    String promotionCode = request.getParameter("promotionCode");
                    double temporaryPrice = Double.parseDouble(request.getParameter("temporaryPrice"));
                    request.setAttribute("promotionCode", promotionCode);
                    request.setAttribute("temporaryPrice", temporaryPrice);
                    requestDispatcher = request.getRequestDispatcher("ApplyVoucher");
                    requestDispatcher.forward(request, response);
                    break;
                }
            }
//            if (action.equals("increaseQuantity")) {
//                requestDispatcher = request.getRequestDispatcher("IncreaseQuantity");
//                requestDispatcher.forward(request, response);
//            } else if (action.equals("decreaseQuantity")) {
//                requestDispatcher = request.getRequestDispatcher("DecreaseQuantity");
//                requestDispatcher.forward(request, response);
//            } else if (action.equals("removeCartProduct")) {
//                requestDispatcher = request.getRequestDispatcher("DeleteCartProduct");
//                requestDispatcher.forward(request, response);
//            } else if (action.equals("applyVoucher")) {
//                String promotionCode = request.getParameter("promotionCode");
//                double temporaryPrice = Double.parseDouble(request.getParameter("temporaryPrice"));
//                request.setAttribute("promotionCode", promotionCode);
//                request.setAttribute("temporaryPrice", temporaryPrice);
//                requestDispatcher = request.getRequestDispatcher("ApplyVoucher");
//                requestDispatcher.forward(request, response);
//            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Voucher> listVouchers = ShoppingCartServices.getINSTANCE().getListVouchers();
        request.setAttribute("listVouchers", listVouchers);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(ConfigPage.USER_CART);
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}