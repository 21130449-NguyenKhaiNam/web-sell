package controller.api.shoppingCart;

import models.Color;
import models.Size;
import models.User;
import models.shoppingCart.ShoppingCart;
import utils.ProductFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "AddToCartController", value = "/api/cart/add")
public class AddToCartController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        User userAuth = (User) session.getAttribute("auth");

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
        String sizeName = request.getParameter("size");


        if (colorCode == null) {
            colorCode = ProductFactory.getListColorsByProductId(productId).get(0).getCodeColor();
        }
        if (sizeName == null) {
            sizeName = ProductFactory.getListSizesByProductId(productId).get(0).getNameSize();
        }

        Size size = ProductFactory.getSizeByNameSizeWithProductId(sizeName, productId);
        Color color = ProductFactory.getColorByCodeColorWithProductId(colorCode, productId);
        cart.add(productId, quantityRequired, color, size);
        cartProductCount = cart.getTotalItems();
        session.setAttribute(userIdCart, cart);

        response.getWriter().write(String.valueOf(cartProductCount));

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