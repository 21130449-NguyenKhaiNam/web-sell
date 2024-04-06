package filter.authorization;

import models.User;
import properties.PathProperties;
import properties.RoleProperties;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

//@WebFilter(filterName = "SecurityFilter", urlPatterns = {"/*"})
public class SecurityFilter implements Filter {
    List<String> listUrlAdmin = PathProperties.getINSTANCE().getPathAdmin();
    List<String> listUrlMod = PathProperties.getINSTANCE().getPathMod();
    List<String> listUrlGuest = PathProperties.getINSTANCE().getPathGuest();

    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpSession session = httpServletRequest.getSession();
        String url = httpServletRequest.getRequestURL().toString();
        String role;
        if (preventAll(url)) {
            httpServletResponse.sendError(404);
            return;
        }

        try {
            role = ((User) session.getAttribute("auth")).getRole();
            System.out.println("[/Filter-SecurityFilter: doFilter] >> " + role);
        } catch (NullPointerException e) {
            if (!preventNoLogin(url)) {
                chain.doFilter(request, response);
            } else {
                httpServletResponse.sendError(403);
            }
            return;
        }
//        System.out.println("Admin: " + isAdmin(url, role));
//        System.out.println("Mod: " + isMod(url, role));
//        System.out.println("Guest: : " + isGuest(url, role));
        if (isAdmin(url, role) || isMod(url, role) || isGuest(url, role)) {
            chain.doFilter(request, response);
            return;
        }
        httpServletResponse.sendError(403);
    }

    private boolean contain(String url, List<String> list) {
        for (String s : list) {
            if (url.contains(s)) {
                return true;
            }
        }
        return false;
    }

    //    true = prevent/ false = allow
    private boolean preventNoLogin(String url) {
        return contain(url, listUrlAdmin) ||
                contain(url, listUrlMod) ||
                contain(url, listUrlGuest);
    }

    private boolean isAdmin(String url, String role) {
        List<String> listUrlAllow = PathProperties.getINSTANCE().getPathAdmin();
        return !contain(url, listUrlAllow) || role.equals(RoleProperties.getINSTANCE().getAdmin());
    }

    private boolean isGuest(String url, String role) {
        List<String> listUrlAllow = PathProperties.getINSTANCE().getPathGuest();
        return !contain(url, listUrlAllow) || role.equals(RoleProperties.getINSTANCE().getGuest());
    }

    private boolean isMod(String url, String role) {
        List<String> listUrlAllow = PathProperties.getINSTANCE().getPathMod();
        return !contain(url, listUrlAllow) || role.equals(RoleProperties.getINSTANCE().getMod());
    }

    private boolean preventAll(String url) {
        List<String> listUrlAllow = PathProperties.getINSTANCE().getPreventAll();
        return contain(url, listUrlAllow);
    }
}