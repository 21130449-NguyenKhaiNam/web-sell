package filter.authorization;

import models.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//@WebFilter(filterName = "Mod", urlPatterns = {"/*"
//})
public class ModRole implements Filter {
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

        boolean isLogin = user != null;
        boolean isMod = user.getRole().equals("mod");

        if (!isLogin) {
            chain.doFilter(request, response);
            return;
        }

//        if (!isLogin) {
//            httpServletResponse.sendRedirect("signIn.jsp");
//            return;
//        }

//        if (isAdmin) {
//            chain.doFilter(request, response);
//        } else {
//            httpServletResponse.sendError(403);
//        }
    }
}
 
