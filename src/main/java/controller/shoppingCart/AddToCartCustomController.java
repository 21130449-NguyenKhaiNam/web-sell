package controller.shoppingCart;

//import cartShopping.ShoppingCart;
import models.Color;
import models.shoppingCart.ShoppingCart;
import models.User;
import utils.ProductFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AddToCartCustomController", value = "/AddToCartCustom")
public class AddToCartCustomController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        HttpSession session = request.getSession(true);
        User userAuth = (User) session.getAttribute("auth");

        if (userAuth == null) {
            response.sendRedirect("signIn.jsp");
        } else {
            int productId = 0;
            int quantityRequired = 0;
            try {
                productId = Integer.parseInt(request.getParameter("productId"));
                quantityRequired = Integer.parseInt(request.getParameter("quantity"));
            } catch (NumberFormatException exception) {
                exception.printStackTrace();
            }

            String userIdCart = String.valueOf(userAuth.getId());

            ShoppingCart cart = (ShoppingCart) session.getAttribute(userIdCart);
            int cartProductCount;
            if (cart == null) {
                cart = new ShoppingCart();
            }
            if (quantityRequired <= 0) {
                quantityRequired = 1;
            }
            String colorCode = request.getParameter("color");
            String sizeRequired = request.getParameter("size");

            if (colorCode == null) {
                colorCode = ProductFactory.getListColorsByProductId(productId).get(0).getCodeColor();
            }
            if (sizeRequired == null) {
                response.sendError(404);
                return;
            }

            Color color = ProductFactory.getColorByCodeColorWithProductId(colorCode, productId);

            cart.add(productId, quantityRequired, color, sizeRequired);
            cartProductCount = cart.getTotalItems();
            session.setAttribute(userIdCart, cart);
//            response.sendRedirect("index.jsp");

            response.getWriter().write(String.valueOf(cartProductCount));
        }
    }
}