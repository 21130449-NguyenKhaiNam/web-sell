package controller.api.shoppingCart;

import config.ConfigPage;
import models.Color;
import models.User;
import models.shoppingCart.ShoppingCart;
import session.SessionManager;
import utils.ProductFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "AddToCartCustomController", value = "/api/cart/add/custom")
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
        User userAuth = SessionManager.getInstance(request, response).getUser();
        if (userAuth == null) {
            response.sendRedirect(ConfigPage.SIGN_IN);
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
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                return;
            }

            Color color = ProductFactory.getColorByCodeColorWithProductId(colorCode, productId);

            cart.add(productId, quantityRequired, color, sizeRequired);
            cartProductCount = cart.getTotalItems();
            session.setAttribute(userIdCart, cart);
            response.getWriter().write(String.valueOf(cartProductCount));
        }
    }
}