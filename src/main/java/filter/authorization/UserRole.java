package filter.authorization;

import config.ConfigPage;
import models.User;
import session.SessionManager;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "user", urlPatterns = {"/public/user/*", "/api/cart/*", "/api/checkout/*", "/api/user/*"})
public class UserRole implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        User user = SessionManager.getInstance(httpServletRequest, httpServletResponse).getUser();
//        Chưa đăng nhập
        if (user == null) {
            httpServletResponse.sendRedirect(ConfigPage.SIGN_IN);
        } else {
            chain.doFilter(request, response);
        }
    }
}
 
