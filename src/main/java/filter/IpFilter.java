package filter;

import services.LogService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class IpFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        LogService.getINSTANCE().setIp(request.getRemoteAddr());
        chain.doFilter(request, response);
    }
}
