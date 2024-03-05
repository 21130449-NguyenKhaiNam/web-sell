package filter.authorization;

import models.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
//
//@WebFilter(filterName = "user",
//        urlPatterns = {
//                "/adminProductForm.jsp",
//                "/adminProducts.jsp",
//                "/adminUsers.jsp",
//                "/adminOrders.jsp",
//                "/account.jsp",
//                "/checkout.jsp"
//        })
//User:
//- Chưa đăng nhập:
//+ Các trang admin (/admin*) -> trả về signIn
//+ Thêm sản phẩm (/checkout) -> trả về signIn
//- Đã đăng nhập:
//+ Các trang admin (/admin*) -> 403 page
public class UserRole implements Filter {
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
        String url = ((HttpServletRequest) request).getServletPath();
//        Chưa đăng nhập
        if (user == null)
            httpServletResponse.sendRedirect("signIn.jsp");
        else
            chain.doFilter(request, response);
    }
}
 
