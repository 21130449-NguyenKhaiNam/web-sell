package controller.shoppingCart;

import models.Color;
import models.ShoppingCart;
import models.Size;
import models.User;
import utils.ProductFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet(name = "AddToCartController", value = "/AddToCart")
public class AddToCartController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession(true);
        User userAuth = (User) session.getAttribute("auth");

        if(userAuth == null){
            response.sendRedirect("signIn.jsp");
        }else{
            int productId = 0;
            int quantityRequired = 0;
            try {
                productId = Integer.parseInt(request.getParameter("productId"));
                quantityRequired = Integer.parseInt(request.getParameter("quantity"));
            }catch (NumberFormatException exception){
                exception.printStackTrace();
            }

            String userIdCart = String.valueOf(userAuth.getId());

            ShoppingCart cart = (ShoppingCart) session.getAttribute(userIdCart);
            int cartProductCount;
            if(cart == null){
                cart = new ShoppingCart();
            }
            if(quantityRequired <= 0){
                quantityRequired = 1;
            }
            String colorCode = request.getParameter("color");
            String sizeName = request.getParameter("size");



            if(colorCode == null){
                colorCode = ProductFactory.getListColorsByProductId(productId).get(0).getCodeColor();
            }
            if(sizeName == null){
                sizeName = ProductFactory.getListSizesByProductId(productId).get(0).getNameSize();
            }

            Size size = ProductFactory.getSizeByNameSizeWithProductId(sizeName, productId);
            Color color = ProductFactory.getColorByCodeColorWithProductId(colorCode, productId);
            cart.add(productId, quantityRequired, color, size);
            cartProductCount = cart.getTotalItems();
            session.setAttribute(userIdCart, cart);
//            response.sendRedirect("index.jsp");

            response.getWriter().write(String.valueOf(cartProductCount));
        }
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