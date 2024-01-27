<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:useBean id="productFactory" class="utils.ProductFactory" />
<jsp:useBean id="userFactory" class="utils.UserFactory" />
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
    <%--jquery--%>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js"
            integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g=="
            crossorigin="anonymous" referrerpolicy="no-referrer"></script>
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
    <link rel="stylesheet" href="assets/css/productBuying.css">
    <link rel="stylesheet" href="assets/css/admin/adminProducts.css">
    <link rel="stylesheet" href="assets/css/admin/adminReviews.css">
    <title>Quản lý nhận xét</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<main id="main">
    <nav class="navbar">
        <div class="container-xl">
            <ul class="navbar__list">
                <li
                        class="navbar__item"><a href="adminProducts.jsp"
                                                class="navbar__link button button button--hover ">Sản
                                                                                                  phẩm</a>
                </li>
                <li class="navbar__item"><a href="adminOrders.jsp"
                                            class="navbar__link button button button--hover ">Đơn hàng</a>
                </li>
                <li class="navbar__item"><a href="adminUsers.jsp"
                                            class="navbar__link button button button--hover ">Khách hàng</a>
                </li>
                <li class="navbar__item"><a href="adminReviews.jsp"
                                            class="navbar__link button button button--hover navbar__link--clicked">Nhận
                                                                                                                   xét</a>
                </li>
                <li class="navbar__item"><a href="adminCategories.jsp"
                                            class="navbar__link button button button--hover ">Phân loại</a>
                </li>
                <li class="navbar__item"><a href="Dashboard"
                                            class="navbar__link button button button--hover ">Thống kê</a>
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
                                <th class="table__head table__head-id">Id</th>
                                <th class="table__head">Mã khách hàng</th>
                                <th class="table__head">Tên sản phẩm
                                </th>
                                <th class="table__head">Mã đơn hàng</th>
                                <th class="table__head">Số sao</th>
                                <th class="table__head">Ngày tạo</th>
                                <th class="table__head">Hiển thị</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:set var="list" value="${requestScope.listReview}"/>
                            <c:forEach var="item" items="${list}">
                                <c:set var="user" value="${userFactory.getUserByIdProductDetail(item.orderDetailId)}"/>
                                <tr class="table__row">
                                    <td class="table__data-view">
                                        <label>
                                            <i class="fa-solid fa-eye"></i>
                                        </label>
                                    </td>
                                    <td class="table__data table__data-id">
                                        <p class="table__cell">${item.id}</p>
                                    </td>
                                    <td class="table__data">
                                        <p class="table__cell">${user.id}</p>
                                    </td>
                                    <td class="table__data">
                                        <p class="table__cell ">${productFactory.getNameProductByIdOrderDetail(item.orderDetailId)}</p>
                                    </td>
                                    <td class="table__data">
                                        <p class="table__cell">${item.orderDetailId}</p>
                                    </td>
                                    <td class="table__data">
                                        <p class="table__cell">${item.ratingStar}</p>
                                    </td>
                                    <fmt:formatDate var="dateReview" pattern="dd-MM-yyyy" value="${item.reviewDate}"/>
                                    <td class="table__data">
                                        <p class="table__cell">${dateReview}</p>
                                    </td>
                                    <c:choose>
                                        <c:when test="${item.visibility==true}">
                                            <td class="table__data table__data-visibility table__data-hide">
                                                <div class="button button--hover button__hide">Ẩn</div>
                                            </td>
                                        </c:when>
                                        <c:otherwise>
                                            <td class="table__data table__data-visibility table__data-un-hide">
                                                <div class="button button--hover button__un-hide">Bỏ ẩn</div>
                                            </td>
                                        </c:otherwise>
                                    </c:choose>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>

                    <ul class="paging">
                        <c:if test="${requestScope.quantityPage != 0}">
                            <c:forEach var="pageNumber" begin="1" end="${requestScope.quantityPage}">
                                <c:url var="linkPaing" value="${requestScope.requestURL}">
                                    <c:param name="page" value="${pageNumber}"/>
                                </c:url>
                                <c:choose>
                                    <c:when test="${pageNumber == requestScope.currentPage}">
                                        <a class="page page--current" href="${linkPaing}">${pageNumber}</a>
                                    </c:when>
                                    <c:otherwise>
                                        <a class="page" href="${linkPaing}">${pageNumber}</a>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </c:if>
                    </ul>
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
<script src="js/admin/adminReviews.js"></script>
</body>
</html>