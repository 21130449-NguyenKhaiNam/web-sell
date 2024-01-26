<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
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
    <%--jquery--%>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js"
            integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g=="
            crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <!--CK Editor-->
    <script src="ckeditor/ckeditor.js"></script>
    <!--Ck Finder-->
    <script src="ckfinder/ckfinder.js"></script>
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
    <link rel="stylesheet" href="assets/css/admin/adminProducts.css">
    <link rel="stylesheet" href="assets/css/admin/adminCategories.css">

    <title>Quản lý phân loại</title>
</head>
<body>
<c:set var="listCategory" value="${requestScope.listCategory}"/>
<jsp:include page="header.jsp"></jsp:include>
<main class="main">
    <nav class="navbar">
        <div class="container-xl">
            <ul class="navbar__list">
                <li
                        class="navbar__item"><a href="adminProducts.jsp"
                                                class="navbar__link button button button--hover">Sản phẩm</a>
                </li>
                <li class="navbar__item"><a href="adminOrders.jsp"
                                            class="navbar__link button button button--hover ">Đơn hàng</a>
                </li>
                <li class="navbar__item"><a href="AdminUser"
                                            class="navbar__link button button button--hover ">Khách
                                                                                              hàng</a>
                </li>
                <li class="navbar__item"><a href="adminReviews.jsp"
                                            class="navbar__link button button button--hover ">Nhận xét</a>
                </li>
                <li class="navbar__item"><a href="adminCategories.jsp"
                                            class="navbar__link button button button--hover navbar__link--clicked">Phân
                                                                                                                   loại</a>
                </li>
            </ul>
        </div>
    </nav>
    <div class="container-xl">
        <div class="row">
            <div class="col-12">
                <div>
                    <h1>Danh sách phân loại</h1>
                    <span class="reload__btn">
                        <i class="reload__icon fa-solid fa-rotate"></i>
                    </span>
                    <span id="button-create-category" class="button button__add">
                        <i class="fa-solid fa-plus"></i>
                      Thêm phân loại
                    </span>
                </div>
            </div>
            <div class="col-12">
                <div class="category__list">
                    <c:forEach var="category" items="${listCategory}">
                        <div class="category__item" category-id="${category.id}">
                            <h3 class="category__name">${category.nameType}</h3>
                            <div class="category__icon-detail"></div>
                            <i class="category__icon-edit fa-solid fa-pen-to-square"></i>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</main>
<div id="dialog-category-create" class="modal">
    <article class="modal__content modal__product">
        <div>
            <h1>Tạo phân loại</h1>
            <i class="modal__product-close  modal__review-close fa-solid fa-xmark"></i>
        </div>
        <iframe class="modal__product-iframe" src="adminCategoryForm.jsp" frameborder="0"></iframe>
    </article>
    <div class="modal__blur"></div>
</div>
<div id="dialog-category-read" class="modal">
    <article class="modal__content modal__product">
        <div>
            <h1>Chỉnh sửa phân loại</h1>
            <i class="modal__product-close  modal__review-close fa-solid fa-xmark"></i>
        </div>
        <iframe class="modal__product-iframe" src="adminCategoryForm.jsp" frameborder="0"></iframe>
    </article>
    <div class="modal__blur"></div>
</div>
<script src="js/admin/adminCategory.js"></script>
</body>
</html>
