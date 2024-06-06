package filter;

import logging.ThreadSharing;
import models.Log;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Date;

@WebFilter(urlPatterns = "/*")
public class IpFilter implements Filter {
    @Override

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String ip = request.getRemoteHost();
        Date dateCreated = new Date(System.currentTimeMillis());
        String address = ((HttpServletRequest) request).getServletPath();
        Log log = Log.builder().ip(ip).dateCreated(dateCreated).resource(address).build();
        ThreadSharing.set(log);
        chain.doFilter(request, response);
        ThreadSharing.remove();
    }
}
