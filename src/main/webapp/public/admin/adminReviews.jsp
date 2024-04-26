<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:useBean id="productFactory" class="utils.ProductFactory"/>
<jsp:useBean id="userFactory" class="utils.UserFactory"/>
<!doctype html>
<html lang="en">
<head>
    <jsp:include page="/public/commonLink.jsp"/>
    <link rel="stylesheet" href="<c:url value="/assets/css/admin/admin.css"/>">
    <link rel="stylesheet" href="<c:url value="/assets/css/admin/adminProducts.css"/>">
    <link rel="stylesheet" href="<c:url value="/assets/css/productBuying.css"/>">
    <link rel="stylesheet" href="<c:url value="/assets/css/admin/adminReviews.css" />">
    <title>Quản lý nhận xét</title>
</head>
<body>
<c:import url="/public/header.jsp"/>
<main id="main">
    <nav class="navbar">
        <div class="container-xl">
            <ul class="navbar__list">
                <li class="navbar__item">
                    <a href="<c:url value="/public/admin/adminProducts.jsp" />"
                       class="navbar__link button button button--hover hvr-grow-shadow">Sản
                        phẩm</a>
                </li>
                <li class="navbar__item">
                    <a href="<c:url value="/public/admin/adminOrders.jsp"/>"
                       class="navbar__link button button button--hover hvr-grow-shadow">Đơn hàng</a>
                </li>
                <li class="navbar__item">
                    <a href="<c:url value="/public/admin/adminUsers.jsp"/>"
                       class="navbar__link button button button--hover hvr-grow-shadow">Người
                        dùng</a>
                <li class="navbar__item">
                    <a href="<c:url value="/public/admin/adminReviews.jsp"/>"
                       class="navbar__link button button button--hover  navbar__link--clicked hvr-grow-shadow">Nhận
                        xét</a>
                </li>
                <li class="navbar__item">
                    <a href="<c:url value="/public/admin/adminCategories.jsp"/>"
                       class="navbar__link button button button--hover hvr-grow-shadow">Phân loại</a>
                </li>
                <li class="navbar__item">
                    <a href="<c:url value="/public/admin/dashboard.jsp" />"
                       class="navbar__link button button button--hover hvr-grow-shadow">Thống kê</a>
                </li>
            </ul>
        </div>
    </nav>
    <section class="content">
        <div class="container-xl">
            <div class="row">
                <div class="col-12">
                    <div>
                        <h1>Danh sách nhận xét</h1>
                    </div>

                    <div class="table__wrapper">
                        <table class="table">
                            <thead>
                                <tr class="table__row">
                                    <th class="table__head">Xem</th>
                                    <th class="table__head table__head-id">ID</th>
                                    <th class="table__head">Mã khách hàng</th>
                                    <th class="table__head">Tên sản phẩm</th>
                                    <th class="table__head">Mã đơn hàng</th>
                                    <th class="table__head">Số sao</th>
                                    <th class="table__head">Ngày tạo</th>
                                    <th class="table__head">Hiển thị</th>
                                </tr>
                            </thead>

                            <tbody class="body_table">
                            </tbody>
                        </table>
                    </div>
                    <ul class="paging"></ul>
                </div>
            </div>
        </div>
    </section>
</main>
<div id="dialog-review-read" class="modal">
    <article class="modal__content modal__product">
        <div>
            <h1>Nhận xét chi tiết</h1>
            <i class="modal__product-close  modal__review-close fa-solid fa-xmark"></i>
        </div>
        <iframe class="modal__product-iframe" src="adminReviewForm.jsp" frameborder="0"></iframe>
    </article>
    <div class="modal__blur"></div>
</div>

<script src="<c:url value="/js/admin/adminReviews.js" />"></script>
</body>
</html>