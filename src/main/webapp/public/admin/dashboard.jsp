<%@ page import="java.util.List" %>
<%@ page import="models.User" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="en">
<head>
    <jsp:include page="/public/commonLink.jsp"/>
    <link rel="stylesheet" href="<c:url value="/assets/css/admin/admin.css"/>">
    <link rel="stylesheet" href="<c:url  value="/assets/css/admin/dashboard.css"/>">
    <title>Dashboard</title>
</head>
<body>
<c:import url="/public/header.jsp"/>
<main id="main">
    <nav class="navbar">
        <div class="container-xl">
            <ul class="navbar__list">
                <li class="navbar__item">
                    <a href="<c:url value="/public/admin/adminProducts.jsp"/>"
                       class="navbar__link button button button--hover hvr-grow-shadow">Sản
                        phẩm</a>
                </li>
                <li class="navbar__item">
                    <a href="<c:url value="/public/admin/adminOrders.jsp"/>"
                       class="navbar__link button button button--hover hvr-grow-shadow">Đơn hàng</a>
                </li>
                <li class="navbar__item">
                    <a href="<c:url value="/public/admin/adminUsers.jsp"/>"
                       class="navbar__link button button button--hover hvr-grow-shadow">Người dùng</a>
                </li>
                <li class="navbar__item">
                    <a href="<c:url value="/public/admin/adminReviews.jsp"/>"
                       class="navbar__link button button button--hover hvr-grow-shadow">Nhận xét</a>
                </li>
                <li class="navbar__item">
                    <a href="<c:url value="/public/admin/adminCategories.jsp"/>"
                       class="navbar__link button button button--hover hvr-grow-shadow">Phân loại</a>
                </li>
                <li class="navbar__item">
                    <a href="<c:url value="/public/admin/dashboard.jsp" />"
                       class="navbar__link button button button--hover navbar__link--clicked hvr-grow-shadow">Thống kê</a>
                </li>
            </ul>
        </div>
    </nav>
    <section class="content">
        <div class="container-xl">
            <div class="row">
                <div class="col-12">
                    <div class="dashbord--contain">
                        <h2 class="title">DASHBOARD</h2>
                        <div class="cards">
                            <div class="card hvr-float-shadow">
                                <div class="box">
                                    <h1 class="text-center">${user}</h1>
                                    <h3>Người dùng</h3>
                                </div>
                                <div class="icon-case">
                                    <img src="<c:url value="/assets/img/user.png" />" alt="">
                                </div>
                            </div>
                            <div class="card hvr-float-shadow">
                                <div class="box">
                                    <h1 class="text-center">${product}</h1>
                                    <h3>Sản phẩm</h3>
                                </div>
                                <div class="icon-case">
                                    <img src="<c:url value="/assets/img/product.png"/>" alt="">
                                </div>
                            </div>
                            <div class="card hvr-float-shadow">
                                <div class="box">
                                    <h1 class="text-center">${order}</h1>
                                    <h3>Đơn hàng</h3>
                                </div>
                                <div class="icon-case">
                                    <img src="<c:url value="/assets/img/orders.png" />" alt="">
                                </div>
                            </div>
                            <div class="card hvr-float-shadow">
                                <div class="box">
                                    <h1 class="text-center">${review}</h1>
                                    <h3>Review</h3>
                                </div>
                                <div class="icon-case">
                                    <img src="<c:url value="/assets/img/CountRView.png"/>" alt="">
                                </div>
                            </div>
                        </div>
                        <div class="charts">
                            <div class="charts__card">
                                <h2 class="chart__title">Sản phẩm nổi bật</h2>
                                <div id="bar__chart">

                                </div>
                            </div>
                            <div class="charts__card">
                                <h2 class="chart__title">Thống kê đơn hàng</h2>
                                <div id="area__chart">

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</main>
<script src="https://cdnjs.cloudflare.com/ajax/libs/apexcharts/3.45.1/apexcharts.min.js"></script>
</body>
<script>
    let top5NameProduct = "<%=request.getAttribute("nameTop5")%>";
    console.log(top5NameProduct)

    let top5QuantityProduct = "<%=request.getAttribute("QuantityTop5")%>";
    console.log(top5QuantityProduct)

    let totalQuantityByMonth = "<%=request.getAttribute("quantityOrderByMonth")%>";
    console.log(totalQuantityByMonth)

    let totalRevenueByMonth = "<%=request.getAttribute("revenueOrderByMonth")%>";
    console.log(totalRevenueByMonth)
</script>
<script src="<c:url value="/js/admin/dashboard.js" />"></script>
</html>