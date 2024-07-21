package services.admin;

import dao.DashboardDAO;

import dto.DashBoardDetail;

public class DashboardServices {
    private static DashboardServices INSTANCE;
    private DashboardDAO dashboardDAO;

    private DashboardServices() {
        this.dashboardDAO = new DashboardDAO();
    }

    public static DashboardServices getINSTANCE() {
        if (INSTANCE == null)
            INSTANCE = new DashboardServices();
        return INSTANCE;
    }

    public int countUser() {
        return dashboardDAO.countUser();
    }

    public int countProduct() {
        return dashboardDAO.countProduct();
    }

    public int countOrder() {
        return dashboardDAO.countOrder();
    }

    public int countReview() {
        return dashboardDAO.countReview();
    }

    public DashBoardDetail getDashBoardDetail(String month, String year) {
        DashBoardDetail dashBoardDetail = new DashBoardDetail();
//        Các đơn hàng giao thành công trong tháng
        dashBoardDetail.setOrderFailed(dashboardDAO.quantityOrderSuccess(month, year));
//        Các đơn hàng giao thất bại trong tháng
        dashBoardDetail.setOrderSuccess(dashboardDAO.quantityOrderFailed(month, year));
//        Sản phẩm bán chạy nhất trong tháng
        dashBoardDetail.setProductPopular(dashboardDAO.getListProductPopular(month, year));
//        Sản phẩm bán chậm nhất trong tháng
        dashBoardDetail.setProductNotPopular(dashboardDAO.getListProductNotPopular(month, year));
//        Doanh thu trong tháng
        dashBoardDetail.setRevenue(dashboardDAO.getRevenueByMonth(month, year));
        return dashBoardDetail;
    }
}
