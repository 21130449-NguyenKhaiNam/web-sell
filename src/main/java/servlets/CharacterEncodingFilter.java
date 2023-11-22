package servlets;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

public class CharacterEncodingFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding("utf8");
        response.setCharacterEncoding("utf8");
        response.setContentType("text/html; charset=UTF-8");
        System.out.println("Filter is being applied to URL: " + ((HttpServletRequest) request).getRequestURI());
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        System.out.println("destroy");
    }
}