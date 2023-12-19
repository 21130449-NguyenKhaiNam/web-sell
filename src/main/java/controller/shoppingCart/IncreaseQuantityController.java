package controller.shoppingCart;

import models.ShoppingCart;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "IncreaseQuantityController", value = "/IncreaseQuantity")
public class IncreaseQuantityController extends HttpServlet {

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productId = 0;
        int cartProductIndex = 0;
        HttpSession session = request.getSession(true);
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        try {
            productId = Integer.parseInt(request.getParameter("productId"));
            cartProductIndex = Integer.parseInt(request.getParameter("cartProductIndex"));
        }catch (NumberFormatException exception){
            exception.printStackTrace();
        }

        cart.increase(productId, cartProductIndex);
        session.setAttribute("cart", cart);
//        int quantityIncreased = cart.getShoppingCartMap().get(productId).get(cartProductIndex).getQuantity();
//        response.getWriter().write(String.valueOf(quantityIncreased));
        response.sendRedirect("shoppingCart.jsp");
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