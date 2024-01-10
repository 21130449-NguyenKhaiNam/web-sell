<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
    <link rel="stylesheet" href="assets/css/productBuying.css">
    <link rel="stylesheet" href="assets/css/admin/adminProducts.css">
    <title>Nhận xét</title>
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
                <li class="navbar__item"><a href="adminReview.jsp"
                                            class="navbar__link button button button--hover navbar__link--clicked">Nhận
                                                                                                                   xét</a>
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
                        <span class="reload__btn">
                            <i class="reload__icon fa-solid fa-rotate"></i>
                        </span>
                        <span id="button-create-product" class="button button__add">
                            <i class="fa-solid fa-plus"></i>
                            Thêm sản phẩm
                        </span>
                        <div id="button-remove-product" class="button button__delete">
                            <i class="fa-solid fa-minus"></i>
                            Xóa sản phẩm
                        </div>
                    </div>
                    <div class="table__wrapper">
                        <table class="table">
                            <thead>
                            <tr class="table__row">
                                <th class="table__head">Mã khách hàng</th>
                                <th class="table__head">Tên sản phẩm
                                </th>
                                <th class="table__head">Mã đơn hàng</th>
                                <th class="table__head">Số sao</th>
                                <th class="table__head">Số sao</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:set var="list" value="${requestScope.productCardList}"/>
                            <c:forEach var="item" items="${list}">
                                <tr class="table__row">
                                    <td class="table__data-view">
                                        <label>
                                            <i class="fa-solid fa-eye"></i>
                                        </label>
                                    </td>
                                    <td class="table__data-edit">
                                        <label>
                                            <i class="fa-solid fa-pen-to-square"></i>
                                        </label>
                                    </td>
                                    <td class="table__data table__data-id">
                                        <p class="table__cell">${item.id}</p>
                                    </td>
                                    <td class="table__data">
                                        <p class="table__cell line-clamp line-1">${item.name}</p>
                                    </td>
                                    <td class="table__data">
                                        <p class="table__cell">${productFactory.getNameCategoryById(item.id)}</p>
                                    </td>
                                    <fmt:formatNumber value="${item.originalPrice}" type="currency" currencyCode="VND"
                                                      var="originalPrice"/>
                                    <fmt:formatNumber value="${item.salePrice}" type="currency" currencyCode="VND"
                                                      var="salePrice"/>
                                    <td class="table__data">
                                        <p class="table__cell">${salePrice}</p>
                                    </td>
                                    <td class="table__data">
                                        <p class="table__cell">${originalPrice}</p>
                                    </td>
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
</body>
</html>