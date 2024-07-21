package filter.checkout;

import models.DeliveryInfoStorage;
import models.User;
import session.SessionManager;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "checkoutFilter", urlPatterns = {"/public/user/checkout.jsp", "/api/checkout/*"})
public class CheckoutFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();

        User userAuth = SessionManager.getInstance(request, response).getUser();

//        String userIdCart = String.valueOf(userAuth.getId());

        String fullName = userAuth.getFullName();
        String email = userAuth.getEmail();
        String phone = userAuth.getPhone();
        String address = userAuth.getAddress();

//        DeliveryInfo deliveryInfoAuth = new DeliveryInfo(fullName, email, phone, address);

        DeliveryInfoStorage deliveryInfoStorage = (DeliveryInfoStorage) session.getAttribute("deliveryInfoStorage");
        if (deliveryInfoStorage == null) {
            deliveryInfoStorage = new DeliveryInfoStorage();
            session.setAttribute("deliveryInfoStorage", deliveryInfoStorage);
        }

//            if(cart.getDeliveryInfo() == null){
//                deliveryInfoStorage.add("defaultDeliveryInfo", deliveryInfoAuth);
//                cart.setDeliveryInfo(deliveryInfoAuth);
//                session.setAttribute("deliveryInfoStorage", deliveryInfoStorage);
//                session.setAttribute(userIdCart, cart);
//            }

        String url = request.getServletPath();
        if (url.contains("checkout.jsp") && !url.contains("error404.jsp")) {
            request.getRequestDispatcher("/api/checkout").forward(request, response);
        } else {
            filterChain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
