<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <jsp:include page="/public/commonLink.jsp"/>
    <!--CK Editor-->
    <script src="<c:url value="/ckeditor/ckeditor.js"/>"></script>
    <!--Ck Finder-->
    <script src="<c:url value="/ckfinder/ckfinder.js"/>"></script>
    <link rel="stylesheet" href="<c:url value="/assets/css/admin/admin.css" />">
    <link rel="stylesheet" href="<c:url value="/assets/css/admin/adminProducts.css"/>">
    <link rel="stylesheet" href="<c:url value="/assets/css/admin/adminCategories.css"/>">

    <title>Quản lý phân loại</title>
</head>
<body>
<c:set var="listCategory" value="${requestScope.listCategory}"/>
<c:import url="/public/header.jsp"/>
<main class="main">
    <nav class="navbar">
        <div class="container-xl">
            <ul class="navbar__list">
                <li
                        class="navbar__item"><a href="<c:url value="/public/admin/adminProducts.jsp" />"
                                                class="navbar__link button button button--hover ">Sản
                    phẩm</a>
                </li>
                <li class="navbar__item"><a href="<c:url value="/public/admin/adminOrders.jsp"/>"
                                            class="navbar__link button button button--hover ">Đơn hàng</a>
                </li>
                <li class="navbar__item"><a href="<c:url value="/public/admin/adminUsers.jsp"/>"
                                            class="navbar__link button button button--hover ">Người dùng</a>
                </li>
                <li class="navbar__item"><a href="<c:url value="/public/admin/adminReviews.jsp"/>"
                                            class="navbar__link button button button--hover ">Nhận xét</a>
                </li>
                <li class="navbar__item"><a href="<c:url value="/public/admin/adminCategories.jsp"/>"
                                            class="navbar__link button button button--hover navbar__link--clicked">Phân loại</a>
                </li>
                <li class="navbar__item"><a href="<c:url value="/public/admin/dashboard.jsp" />"
                                            class="navbar__link button button button--hover ">Thống kê</a>
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
            <i class="modal__product-close modal__review-close fa-solid fa-xmark"></i>
        </div>
        <iframe class="modal__product-iframe" src="adminCategoryForm.jsp" frameborder="0"></iframe>
    </article>
    <div class="modal__blur"></div>
</div>
<div id="dialog-category-read" class="modal">
    <article class="modal__content modal__product">
        <div>
            <h1>Chỉnh sửa phân loại</h1>
            <i class="modal__product-close modal__review-close fa-solid fa-xmark"></i>
        </div>
        <iframe class="modal__product-iframe" src="adminCategoryForm.jsp" frameborder="0"></iframe>
    </article>
    <div class="modal__blur"></div>
</div>
<script src="<c:url value="/js/admin/adminCategory.js" />"></script>
</body>
</html>
