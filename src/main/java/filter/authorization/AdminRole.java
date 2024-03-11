package filter.authorization;

import models.User;
import properties.RoleProperties;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "admin", urlPatterns = {"/public/admin/*"})
public class AdminRole implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpSession session = httpServletRequest.getSession();
        User user = (User) session.getAttribute("auth");
        if (user == null) {
            httpServletResponse.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }
        boolean isAdmin = user.getRole().equals("2");
        if (isAdmin) {
            chain.doFilter(request, response);
        } else {
            httpServletResponse.sendError(HttpServletResponse.SC_FORBIDDEN);
        }
    }
}
 
