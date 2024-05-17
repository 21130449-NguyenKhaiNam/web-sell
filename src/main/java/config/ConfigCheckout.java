package config;

import models.DeliveryMethod;
import models.PaymentMethod;
import services.CheckoutServices;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.List;

@WebListener
public class ConfigCheckout implements ServletContextListener {

    public ConfigCheckout() {
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        List<DeliveryMethod> listDeliveryMethod = CheckoutServices.getINSTANCE().getAllInformationDeliveryMethod();
        List<PaymentMethod> listPaymentMethod = CheckoutServices.getINSTANCE().getAllPaymentMethod();
        context.setAttribute("listDeliveryMethod", listDeliveryMethod);
        context.setAttribute("listPaymentMethod", listPaymentMethod);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }


}