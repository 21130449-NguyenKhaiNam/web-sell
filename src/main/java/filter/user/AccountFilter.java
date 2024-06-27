package filter.user;

import models.Address;
import models.User;
import models.shoppingCart.ShoppingCart;
import services.image.CloudinaryUploadServices;
import session.SessionManager;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "AccountFilter", urlPatterns = {"/public/user/accountInfo.jsp*", "/api/user/*"})
public class AccountFilter implements Filter {
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
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        User user = SessionManager.getInstance(req, resp).getUser();
        request.setAttribute("accountInfo", user);
        String avatarLink = CloudinaryUploadServices.getINSTANCE().getImage("user/", user.getAvatar());
        request.setAttribute("avatarLink", avatarLink);
        chain.doFilter(request, response);
    }
}
