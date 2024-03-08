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
    //    User
    public static String USER_ACCOUNT, USER_CHANGE_PASSWORD, USER_PURCHASE_HISTORY, USER_CART, USER_CHECKOUT;
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
//        Config path /public/auth/
        String folderAuth = "/public/auth/";
        SIGN_IN = folderAuth + "signIn.jsp";
        SIGN_UP = folderAuth + "signUp.jsp";
        RESET_PASSWORD = folderAuth + "resetPassword.jsp";
        FORGET_PASSWORD = folderAuth + "forgetPassword.jsp";
        VERIFY = folderAuth + "verifySuccess.jsp";

//        Config path /public/user
        String folderUser = "/public/user/";
        USER_ACCOUNT = folderUser + "account.jsp";
        USER_CHANGE_PASSWORD = folderUser + "changePassword.jsp";
        USER_PURCHASE_HISTORY = folderUser + "purchaseHistory.jsp";
        USER_CART = folderUser + "shoppingCart.jsp";
        USER_CHECKOUT = folderUser + "checkout.jsp";

//        Config path /public/product/
        String folderProduct = "/public/product/";
        PRODUCT_BUYING = folderProduct + "productBuying.jsp";
        PRODUCT_DETAIL = folderProduct + "productDetail.jsp";
        PRODUCT_ORDER = folderProduct + "productOrder.jsp";
        PRODUCT_TRENDING = folderProduct + "productTrending.jsp";
        PRODUCT_NEW = folderProduct + "productNew.jsp";

//        Config path /public/admin/
//        Config path /public/admin/product/
        String folderAdminProduct = "/public/admin/product/";
        ADMIN_PRODUCT = folderAdminProduct + "adminProduct.jsp";

    }
}
