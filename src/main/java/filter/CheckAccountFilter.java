package filter;

import com.mysql.cj.Session;
import models.User;
import models.shoppingCart.ShoppingCart;
import session.SessionManager;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class CheckAccountFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        User user = SessionManager.getInstance((HttpServletRequest) request, (HttpServletResponse) response).getUser();
        if(user != null) {
            HttpSession session = ((HttpServletRequest) request).getSession();
            if(session.getAttribute(user.getId() + "") == null) {
                session.setAttribute(user.getId() + "", new ShoppingCart());
            }
        }
        chain.doFilter(request, response);
    }
}
