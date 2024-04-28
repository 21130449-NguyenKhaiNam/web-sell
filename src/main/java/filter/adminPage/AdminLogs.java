package filter.adminPage;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "AdminLogs", urlPatterns = {"/public/admin/adminLogs.jsp"})
public class AdminLogs implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest rq= (HttpServletRequest) request;
        HttpServletResponse rs = (HttpServletResponse) response;
        String url = rq.getServletPath();

        if (url.contains("adminLogs.jsp")) {
            rs.sendRedirect(rq.getContextPath() + "/AdminLog");
        }

        chain.doFilter(request, response);
    }
}
