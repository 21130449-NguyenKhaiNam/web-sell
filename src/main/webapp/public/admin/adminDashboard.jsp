<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="en">
<head>
    <jsp:include page="/public/admin/adminLink.jsp"/>

    <link rel="stylesheet" href="<c:url value="/assets/css/admin/admin.css"/>">
    <link rel="stylesheet" href="<c:url  value="/assets/css/admin/dashboard.css"/>">
    <title>Thống kê</title>
</head>
<body>
<!--Header-->
<c:import url="/public/header.jsp"/>
<main id="main">
    <!--Navigate-->
    <c:import url="/public/admin/adminNavigator.jsp"/>
    <section class="content">
        <div class="container-xl">
            <div class="row">
                <div class="col-12">
                    <div class="dashboard--contain">
                        <h1 class="h3">Thống kê</h1>
                        <div class="cards">
                            <div class="card hvr-float-shadow">
                                <div class="box">
                                    <h1 class="text-center">${requestScope.user}</h1>
                                    <h3>Người dùng</h3>
                                </div>
                                <div class="icon-case">
                                    <img src="<c:url value="/assets/img/user.png" />" alt="">
                                </div>
                            </div>
                            <div class="card hvr-float-shadow">
                                <div class="box">
                                    <h1 class="text-center">${requestScope.product}</h1>
                                    <h3>Sản phẩm</h3>
                                </div>
                                <div class="icon-case">
                                    <img src="<c:url value="/assets/img/product.png"/>" alt="">
                                </div>
                            </div>
                            <div class="card hvr-float-shadow">
                                <div class="box">
                                    <h1 class="text-center">${requestScope.order}</h1>
                                    <h3>Đơn hàng</h3>
                                </div>
                                <div class="icon-case">
                                    <img src="<c:url value="/assets/img/orders.png" />" alt="">
                                </div>
                            </div>
                            <div class="card hvr-float-shadow">
                                <div class="box">
                                    <h1 class="text-center">${requestScope.review}</h1>
                                    <h3>Review</h3>
                                </div>
                                <div class="icon-case">
                                    <img src="<c:url value="/assets/img/CountRView.png"/>" alt="">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-12 py-3">

                </div>
                <div class="col-12 d-flex gap-4">
                    <div class="cards ">
                        <div class="card hvr-float-shadow p-2">
                            <h3 class="h4">Doanh thu trong tháng</h3>
                            <div class="d-flex align-items-center py-2 justify-content-between">
                                <i class="fa-solid fa-money-bill icon-case"></i>
                                <h1 class="text-center" id="revenue"></h1>
                            </div>
                        </div>
                    </div>
                    <div class="cards ">
                        <div class="card hvr-float-shadow p-2">
                            <h3 class="h4">Số đơn hàng đặt thành công</h3>
                            <div class="d-flex align-items-center py-2 justify-content-between">
                                <i class="fa-regular fa-circle-check icon-case"></i>
                                <h1 class="text-center" id="order-success"></h1>
                            </div>
                        </div>
                    </div>
                    <div class="cards ">
                        <div class="card hvr-float-shadow p-2">
                            <h3 class="h4">Số đơn hàng đặt thất bại</h3>
                            <div class="d-flex align-items-center py-2 justify-content-between">
                                <i class="fa-regular fa-circle-xmark icon-case"></i>
                                <h1 class="text-center" id="order-failed"></h1>
                            </div>
                        </div>
                    </div>
                    <div style="flex: 1">
                        <form id="form-filter" class="d-flex flex-column gap-2 align-items-center">
                            <select id="month" aria-label="Default select example">
                            </select>
                            <select id="year" aria-label="Default select example"></select>
                            <button type="submit" class="btn btn-primary w-100">Lọc</button>
                        </form>
                    </div>
                </div>
                <div class="col-12">
                    <canvas id="product-popular-charts"></canvas>
                    <canvas id="product-not-popular-charts"></canvas>
                </div>
            </div>
        </div>
    </section>
</main>
<%--<script src="https://cdnjs.cloudflare.com/ajax/libs/apexcharts/3.45.1/apexcharts.min.js"></script>--%>
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script type="module" src="<c:url value="/js/admin/dashboard.js" />"></script>
</body>
<%--<script>--%>
<%--    let top5NameProduct = "<%=request.getAttribute("nameTop5")%>";--%>
<%--    console.log(top5NameProduct)--%>

<%--    let top5QuantityProduct = "<%=request.getAttribute("QuantityTop5")%>";--%>
<%--    console.log(top5QuantityProduct)--%>

<%--    let totalQuantityByMonth = "<%=request.getAttribute("quantityOrderByMonth")%>";--%>
<%--    console.log(totalQuantityByMonth)--%>

<%--    let totalRevenueByMonth = "<%=request.getAttribute("revenueOrderByMonth")%>";--%>
<%--    console.log(totalRevenueByMonth)--%>
<%--</script>--%>

</html>