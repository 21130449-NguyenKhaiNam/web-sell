package config;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ConfigPage implements ServletContextListener {
    public static String DOMAIN;
//    Auth
    public static String SIGN_IN, SIGN_UP, RESET_PASSWORD, VERIFY, FORGET_PASSWORD;
//    Product
    public static String HOME, PRODUCT_BUYING, PRODUCT_DETAIL, PRODUCT_ORDER, PRODUCT_NEW, PRODUCT_TRENDING;
//    Admin/Product
    public static String ADMIN_PRODUCT;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContextListener.super.contextInitialized(sce);
        ServletContext servletContext = sce.getServletContext();
        DOMAIN = servletContext.getInitParameter("contextPath");
        init();
    }

    private void init() {
        HOME = DOMAIN;
//        Config path /public/user/
        String folderUser = "/public/user/";
        SIGN_IN = folderUser + "signIn.jsp";
        SIGN_UP = folderUser + "signUp.jsp";
        RESET_PASSWORD = folderUser + "resetPassword.jsp";
        FORGET_PASSWORD = folderUser + "forgetPassword.jsp";
        VERIFY = folderUser + "verifySuccess.jsp";

//        Config path /public/product/
        String folderProduct = "/public/product/";
        PRODUCT_BUYING = folderProduct + "productBuying.jsp";
        PRODUCT_DETAIL = folderUser + "productDetail.jsp";
        PRODUCT_ORDER = folderProduct + "productOrder.jsp";
        PRODUCT_TRENDING = folderProduct+"productTrending.jsp";
        PRODUCT_NEW = folderProduct+"productNew.jsp";

//        Config path /public/admin/
//        Config path /public/admin/product/
        String folderAdminProduct = "/public/admin/product/";
        ADMIN_PRODUCT = folderAdminProduct + "adminProduct.jsp";

    }
}
