package filter.adminPage;

import models.*;
import services.admin.AdminOrderServices;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebFilter(filterName = "AdminOrdersFilter", urlPatterns = {"/public/admin/adminOrders.jsp", "/AdminOrders", "/SearchFilterOrderAdmin"}, servletNames = {"AdminOrders"})
public class AdminOrders implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

//        Về sau sẽ chuyển phần này sang context listener
        List<OrderStatus> listAllOrderStatus = AdminOrderServices.getINSTANCE().getListAllOrderStatus();
        request.setAttribute("listAllOrderStatus", listAllOrderStatus);

        List<TransactionStatus> listAllTransactionStatus = AdminOrderServices.getINSTANCE().getListAllTransactionStatus();
        request.setAttribute("listAllTransactionStatus", listAllTransactionStatus);

        List<DeliveryMethod> listAllDeliveryMethodManage = AdminOrderServices.getINSTANCE().getListAllDeliveryMethodManage();
        request.setAttribute("listAllDeliveryMethodManage", listAllDeliveryMethodManage);

        List<PaymentMethod> listAllPaymentMethodManage = AdminOrderServices.getINSTANCE().getListAllPaymentMethodManage();
        request.setAttribute("listAllPaymentMethodManage", listAllPaymentMethodManage);

        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
