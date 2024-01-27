<%@ page import="java.util.List" %>
<%@ page import="models.User" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <!--Các thư viện hỗ trợ-->
    <!--Font Awesome-->
    <link rel="stylesheet" href="assets/fontIcon/fontawesome-free-6.4.2-web/css/all.min.css">
    <!--Bootstrap-->
    <link rel="stylesheet" href="assets/bootstrap/bootstrap-grid.min.css">
    <!--Favicon-->
    <link rel="apple-touch-icon" sizes="180x180" href="assets/favicon/apple-touch-icon.png">
    <link rel="icon" type="image/png" sizes="32x32" href="assets/favicon/favicon-32x32.png">
    <link rel="icon" type="image/png" sizes="16x16" href="assets/favicon/favicon-16x16.png">
    <link rel="manifest" href="assets/favicon/site.webmanifest">
    <!--Web font-->
    <link rel="stylesheet" href="assets/font/webfonts/Montserrat.css">
    <!--CSS-->
    <link rel="stylesheet" href="assets/css/reset.css">
    <link rel="stylesheet" href="assets/css/base.css">
    <link rel="stylesheet" href="assets/css/admin/admin.css">
    <link rel="stylesheet" href="assets/css/admin/dashboard.css">
    <title>Dashboard</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<main id="main">
    <nav class="navbar">
        <div class="container-xl">
            <ul class="navbar__list">
                <li class="navbar__item"><a href="adminProducts.jsp" class="navbar__link button button button--hover ">Sản
                    phẩm</a></li>
                <li class="navbar__item"><a href="adminOrders.jsp" class="navbar__link button button button--hover ">Đơn
                    hàng</a></li>
                <li class="navbar__item"><a href="AdminUser"
                                            class="navbar__link button button button--hover ">Khách
                    hàng</a></li>
                <li class="navbar__item"><a href="Dashboard"
                                            class="navbar__link button button button--hover navbar__link--clicked">Dashboard</a></li>
                <li class="navbar__item"><a href="adminReviews.jsp"
                                            class="navbar__link button button button--hover ">Nhận xét</a>
                </li>
                <li class="navbar__item"><a href="adminCategories.jsp"
                                            class="navbar__link button button button--hover ">Phân loại</a>
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
                            <div class="card">
                                <div class="box">
                                    <h1>${user}</h1>
                                    <h3>Người dùng</h3>
                                </div>
                                <div class="icon-case">
                                    <img src="./assets/img/user.png" alt="">
                                </div>
                            </div>
                            <div class="card">
                                <div class="box">
                                    <h1>${product}</h1>
                                    <h3>Sản phẩm</h3>
                                </div>
                                <div class="icon-case">
                                    <img src="./assets/img/product.png" alt="">
                                </div>
                            </div>
                            <div class="card">
                                <div class="box">
                                    <h1>${order}</h1>
                                    <h3>Đơn hàng</h3>
                                </div>
                                <div class="icon-case">
                                    <img src="./assets/img/orders.png" alt="">
                                </div>
                            </div>
                            <div class="card">
                                <div class="box">
                                    <h1>${review}</h1>
                                    <h3>Review</h3>
                                </div>
                                <div class="icon-case">
                                    <img src="assets/img/CountRView.png" alt="">
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
    let top5NameProduct ="<%=request.getAttribute("nameTop5")%>";
            console.log(top5NameProduct)

    let top5QuantityProduct ="<%=request.getAttribute("QuantityTop5")%>";
    console.log(top5QuantityProduct)

    let totalQuantityByMonth ="<%=request.getAttribute("quantityOrderByMonth")%>";
    console.log(totalQuantityByMonth)

    let totalRevenueByMonth ="<%=request.getAttribute("revenueOrderByMonth")%>";
    console.log(totalRevenueByMonth)
</script>
<script src="js/admin/dashboard.js"></script>



</html>