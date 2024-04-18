package services.admin;

import dao.admin.*;
import models.Order;
import models.OrderDetail;
import models.Product;
import services.LogService;

import java.time.LocalDate;
import java.util.List;

public class DashboardService {
    private static DashboardService INSTANCE;
    private IDashboardUserDAO dashboardUserDAO;
    private IDashboardProductDAO dashboardProductDAO;
    private IDashboardOrderDAO dashboardOrderDAO;
    private IDashboardReviewDAO dashboardReviewDAO;

    private DashboardService() {
        dashboardUserDAO = LogService.getINSTANCE().createProxy(new DashboardUserDAOImp());
        dashboardProductDAO = LogService.getINSTANCE().createProxy(new DashboardProductDAOImp());
        dashboardOrderDAO = LogService.getINSTANCE().createProxy(new DashboardOrderDAOImp());
        dashboardReviewDAO = LogService.getINSTANCE().createProxy(new DashboardReviewDAOImp());
    }

    public static DashboardService getINSTANCE() {
        if (INSTANCE == null)
            INSTANCE = new DashboardService();
        return INSTANCE;
    }

    public int countUser() {
        return dashboardUserDAO.total();
    }

    public int countProduct() {
        return dashboardProductDAO.total();
    }

    public int countOrder() {
        return dashboardOrderDAO.total();
    }

    public int countReview() {
        return dashboardReviewDAO.total();
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
