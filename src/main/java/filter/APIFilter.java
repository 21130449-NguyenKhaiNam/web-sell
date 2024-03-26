package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebFilter(filterName = "APIFilter", urlPatterns = {"/api/*"})
public class APIFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding(StandardCharsets.UTF_8.name());
        request.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType("application/json");
        chain.doFilter(request, response);
    }
}
