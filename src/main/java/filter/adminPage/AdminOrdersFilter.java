package filter.adminPage;

import models.*;
import services.AdminOrderServices;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebFilter(filterName = "AdminOrdersFilter", urlPatterns = {"/adminOrders.jsp", "/AdminOrders", "/SearchFilterOrderAdmin"})
public class AdminOrdersFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession();
        User userAuth = (User) session.getAttribute("auth");

        if(userAuth == null){
            response.sendRedirect("signIn.jsp");
        }else {
            String url = request.getServletPath();
            if(url.contains("adminOrders.jsp")){
                response.sendRedirect("AdminOrders");
            }else{
                List<OrderStatus> listAllOrderStatus = AdminOrderServices.getINSTANCE().getListAllOrderStatus();
                request.setAttribute("listAllOrderStatus", listAllOrderStatus);

                List<TransactionStatus> listAllTransactionStatus = AdminOrderServices.getINSTANCE().getListAllTransactionStatus();
                request.setAttribute("listAllTransactionStatus", listAllTransactionStatus);

                List<DeliveryMethod> listAllDeliveryMethodManage = AdminOrderServices.getINSTANCE().getListAllDeliveryMethodManage();
                request.setAttribute("listAllDeliveryMethodManage", listAllDeliveryMethodManage);

                List<PaymentMethod> listAllPaymentMethodManage = AdminOrderServices.getINSTANCE().getListAllPaymentMethodManage();
                request.setAttribute("listAllPaymentMethodManage", listAllPaymentMethodManage);
            }
            filterChain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
