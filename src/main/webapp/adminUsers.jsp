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
    <link rel="stylesheet" type="text/css" href="assets/css/admin/adminUsers.css">
    <title>Quản lí người dùng</title>
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
                                            class="navbar__link button button button--hover navbar__link--clicked">Khách
                    hàng</a></li>
            </ul>
        </div>
    </nav>
    <section class="content">
        <div class="container-xl">
            <div class="row">
                <div class="col-12">
                    <div>
                        <h1>Danh sách người dùng</h1>
                        <article action="#!" class="form__search-block filler__block">
                            <i class="search__icon fa-solid fa-magnifying-glass"></i>
                            <form action="AdminUser" method="get">
                                <input id="search-input" type="text" name="search" placeholder="Tìm kiếm người dùng">
                            </form>
                        </article>
                        <button id="button-remove-product" class="button button__delete">
                            <i class="fa-solid fa-add"></i>
                            Thêm người dùng
                        </button>
                    </div>
                    <div class="table__wrapper">
                        <table class="table ">
                            <thead>
                            <tr class="table__row">
                                <th class="table__head table__id">Mã người dùng</th>
                                <th class="table__head table__username">Tên người dùng</th>
                                <th class="table__head table__email">Email</th>
                                <th class="table__head table__fullname">Họ tên</th>
                                <th class="table__head table__gender">Giới tính</th>
                                <th class="table__head table__birthday">Ngày sinh</th>
                                <th class="table__head table__phone">Số điện thoại</th>
                                <th class="table__head table__address">Địa chỉ</th>
                                <th class="table__head table__address">Xóa</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${requestScope.lists}" var="user">
                                <tr class="table__row">
                                    <td class="table__data">
                                        <p class="table__cell"><c:out value="${user.id}"/></p>
                                    </td>
                                    <td class="table__data">
                                        <p class="table__cell"><c:out value="${user.username}"/></p>
                                    </td>

                                    <td class="table__data">
                                        <p class="table__cell"><c:out value="${user.email}"/></p>
                                    </td>
                                    <td class="table__data">
                                        <p class="table__cell table__data--fullname"><c:out
                                                value="${user.fullName}"/></p>
                                    </td>
                                    <td class="table__data">
                                        <p class="table__cell"><c:out value="${user.gender}"/></p>
                                    </td>
                                    <td class="table__data table__data--birthday">
                                        <p class="table__cell"><c:out value="${user.birthDay}"/></p>
                                    </td>
                                    <td class="table__data">
                                        <p class="table__cell"><c:out value="${user.phone}"/></p>
                                    </td>
                                    <td class="table__data">
                                        <p class="table__cell"><c:out value="${user.address}"/></p>
                                    </td>
                                    <td class="table__data">
                                        <a id="deleteUserLink" onclick="openDeleteDialog(${user.id})">
                                            <i class="fa-solid fa-trash-can"></i>
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <ul class="paging"></ul>
                </div>
            </div>
        </div>
    </section>
</main>
<!-- Dialog -->
<div id="delete-dialog" class="modal">
    <div class="modal__content">
        <div class="modal__header">
            <h1>Xác nhận xóa</h1>
            <i id="close-dialog" class="fa-solid fa-xmark"></i>
        </div>
        <p class="modal__body">Bạn có chắc chắn muốn xóa người dùng này?</p>
        <div class="modal__footer">
            <button id="cancel-delete" class="button button__cancel">Hủy bỏ</button>
            <a id="confirm-delete" href="delete?userId=${user.id}" class="button button__delete--dialog">
                <i class="fa-solid fa-trash-can"></i>
                Xác nhận xóa
            </a>
        </div>
    </div>
</div>

<%--<script src="js/data.js"></script>--%>
<%--<script src="js/paging.js"></script>--%>
<script src="js/admin/adminUsers.js"></script>
</body>

</html>