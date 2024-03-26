package filter.adminPage;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "AdminUsers", urlPatterns = {"/public/admin/adminUsers.jsp"})
public class AdminUsers implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest rq= (HttpServletRequest) request;
        HttpServletResponse rs = (HttpServletResponse) response;
        String url = rq.getServletPath();

        if (url.contains("adminUsers.jsp")) {
            rs.sendRedirect(rq.getContextPath() + "/AdminUser");
        }

        chain.doFilter(request, response);
    }
}