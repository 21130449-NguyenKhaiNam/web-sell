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

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws  ServletException, IOException{
//        String action = request.getParameter("action");
//        RequestDispatcher requestDispatcher;
//        if (action != null){
//            String productId = request.getParameter("productId");
//            String cartProductIndex = request.getParameter("cartProductItem");
//            request.setAttribute("productId", productId);
//            request.setAttribute("cartProductIndex", cartProductIndex);
//
//            if (action.equals("increaseQuantity")){
//                requestDispatcher = request.getRequestDispatcher("IncreaseQuantity");
//                requestDispatcher.forward(request, response);
//            } else if (action.equals("decreaseQuantity")) {
//                requestDispatcher = request.getRequestDispatcher("DecreaseQuantity");
//                requestDispatcher.forward(request, response);
//            }else if(action.equals("removeCartProduct")){
//                requestDispatcher = request.getRequestDispatcher("DeleteCartProduct");
//                requestDispatcher.forward(request, response);
//            }else if(action.equals("applyVoucher")){
//                String code = request.getParameter("promotion__code");
//                double temporaryPrice = Double.parseDouble(request.getParameter("tempPrice"));
//                request.setAttribute("code", code);
//                request.setAttribute("temporaryPrice", temporaryPrice);
//                requestDispatcher = request.getRequestDispatcher("ApplyVoucher");
//                requestDispatcher.forward(request, response);
//            }
//        }

        String action = request.getParameter("action");
        RequestDispatcher requestDispatcher;
        if (action != null){
            String productId = request.getParameter("productId");
            String cartProductIndex = request.getParameter("cartProductIndex");
            request.setAttribute("productId", productId);
            request.setAttribute("cartProductIndex", cartProductIndex);

            if (action.equals("increaseQuantity")){
                requestDispatcher = request.getRequestDispatcher("IncreaseQuantity");
                requestDispatcher.forward(request, response);
            }else if (action.equals("decreaseQuantity")) {
                requestDispatcher = request.getRequestDispatcher("DecreaseQuantity");
                requestDispatcher.forward(request, response);
            }else if(action.equals("removeCartProduct")){
                requestDispatcher = request.getRequestDispatcher("DeleteCartProduct");
                requestDispatcher.forward(request, response);
            } else if(action.equals("applyVoucher")){
                String promotionCode = request.getParameter("promotionCode");
                double temporaryPrice = Double.parseDouble(request.getParameter("temporaryPrice"));
                request.setAttribute("promotionCode", promotionCode);
                request.setAttribute("temporaryPrice", temporaryPrice);
                requestDispatcher = request.getRequestDispatcher("ApplyVoucher");
                requestDispatcher.forward(request, response);
            }
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        HttpSession session = request.getSession(true);
//        session.setAttribute("listVouchers", listVouchers);
//        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
//        cart.setVoucherApplied(null);
//        session.setAttribute("cart", cart);
        List<Voucher> listVouchers = ShoppingCartServices.getINSTANCE().getListVouchers();
        request.setAttribute("listVouchers", listVouchers);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("shoppingCart.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}