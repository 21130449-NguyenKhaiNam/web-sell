package services.admin;

import dao.admin.*;
import models.Order;
import models.OrderDetail;
import models.Product;

import java.time.LocalDate;
import java.util.List;

public class DashboardService {
    private static DashboardService INSTANCE;
    private IDashboardUserDAO dashboardUserDAO;
    private IDashboardProductDAO dashboardProductDAO;
    private IDashboardOrderDAO dashboardOrderDAO;

    private DashboardService() {
        dashboardUserDAO = new DashboardUserDAOImp();
        dashboardProductDAO = new DashboardProductDAOImp();
        dashboardOrderDAO = new DashboardOrderDAOImp();
    }

    public static DashboardService getINSTANCE() {
        if (INSTANCE == null)
            INSTANCE = new DashboardService();
        return INSTANCE;
    }

    public int countUser() {
        return dashboardUserDAO.countUser();
    }

    public int countProduct() {
        return dashboardProductDAO.countProduct();
    }

    public int countOrder() {
        return dashboardOrderDAO.countOrder();
    }

    public int countReview() {
        return dashboardProductDAO.countReview();
    }

    public List<Product> getTop5Product() {
        return dashboardOrderDAO.getTopNProduct(5);
    }

    public List<Order> getOrderByDate(LocalDate date) {
        return dashboardOrderDAO.getOrderByDate(date);
    }

    public List<OrderDetail> getOrderByOrderId(String orderId) {
        return dashboardOrderDAO.selectById(orderId);
    }
}
