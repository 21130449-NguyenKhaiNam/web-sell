<%@ page import="java.util.List" %>
<%@ page import="models.User" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="en">
<head>
    <jsp:include page="/public/commonLink.jsp"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/assets/css/admin/adminUsers.css" />">
    <title>Quản lí người dùng</title>
</head>
<body>
<c:import url="/public/header.jsp"/>
<main id="main">
    <nav class="navbar">
        <div class="container-xl">
            <ul class="navbar__list">
                <li
                        class="navbar__item"><a href="<c:url value="/public/admin/adminProducts.jsp" />"
                                                class="navbar__link button button button--hover">Sản
                    phẩm</a>
                </li>
                <li class="navbar__item"><a href="<c:url value="/public/admin/adminOrders.jsp"/>"
                                            class="navbar__link button button button--hover ">Đơn hàng</a>
                </li>
                <li class="navbar__item"><a href="<c:url value="/public/admin/adminUsers.jsp"/>"
                                            class="navbar__link button button button--hover navbar__link--clicked">Người
                    dùng</a>
                <li class="navbar__item"><a href="<c:url value="/public/admin/adminReviews.jsp"/>"
                                            class="navbar__link button button button--hover ">Nhận xét</a>
                </li>
                <li class="navbar__item"><a href="<c:url value="/public/admin/adminCategories.jsp"/>"
                                            class="navbar__link button button button--hover ">Phân loại</a>
                </li>
                <li class="navbar__item"><a href="<c:url value="/public/admin/dashboard.jsp" />"
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
                        <h1>Danh sách người dùng</h1>
                        <article action="#!" class="form__search-block filler__block">
                            <i class="search__icon fa-solid fa-magnifying-glass"></i>
                            <form action="/AdminUser" method="get">
                                <input id="search-input" type="text" name="search"
                                       placeholder="Tìm kiếm theo username hoặc email người dùng">
                            </form>
                        </article>
                    </div>
                    <div class="table__wrapper">
                        <table class="table table-bordered ">
                            <thead>
                            <tr class="table__row">
                                <th class="table__head table__id">Chỉnh sửa</th>
                                <th class="table__head table__username">Tên người dùng</th>
                                <th class="table__head table__email">Email</th>
                                <th class="table__head table__fullname">Họ tên</th>
                                <th class="table__head table__gender">Giới tính</th>
                                <th class="table__head ">Vai trò</th>
                                <th class="table__head ">Xóa</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${requestScope.lists}" var="user">
                                <tr class="table__row">
                                    <td class="table__data">
                                        <div class="icon-link icon-link-hover admin__detail" data-id="${user.id}" data-bs-toggle="modal" data-bs-target="#staticBackdrop">
                                            <i class="fa-solid fa-pen-to-square"></i>
                                        </div>
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
                                    <td class="table__data">
                                        <p class="table__cell">
                                            <c:if test="${user.role == '2'}">Admin</c:if>
                                            <c:if test="${user.role == '1'}">Mod</c:if>
                                            <c:if test="${user.role == '0'}">Khách</c:if>
                                        </p>
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

                    <c:set value="${requestScope.page}" var="page"/>
                    <div class="pagination">
                        <c:if test="${page > 1}">
                            <c:url var="prevURLPage" value="/AdminUser">
                                <c:param name="page" value="${page - 1}"/>
                            </c:url>
                            <a href="${prevURLPage}" class="previous__page"><i class="fa-solid fa-chevron-left"></i></a>
                        </c:if>
                        <c:forEach begin="${1}" end="${requestScope.totalPage}" var="i">
                            <c:url var="trURLPage" value="/AdminUser">
                                <c:param name="page" value="${i}"/>
                            </c:url>
                            <a class="${i == page ? "active" : "page__forward"}" href="${trURLPage}">${i}</a>
                        </c:forEach>
                        <c:if test="${page < requestScope.totalPage}">
                            <c:url var="nextURLPage" value="/AdminUser">
                                <c:param name="page" value="${page + 1}"/>
                            </c:url>
                            <a href="${nextURLPage}" class="next__page"><i class="fa-solid fa-chevron-right"></i></a>
                        </c:if>
                    </div>

                </div>
            </div>
        </div>
    </section>
</main>
<div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
        <div id="model" class="modal-dialog modal-dialog-scrollable" style="max-width: 80%" >
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="staticBackdropLabel">Thông tin người dùng</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div class="row">
                        <div class="col-4">
                            <img id="staticAvatar" src="" class="img-thumbnail rounded" alt="...">
                        </div>
                        <div class="col-8">
                            <div class="row">
                                <div class="col-6">
                                    <label for="staticEmail" class="form-label">Email</label>
                                    <input type="text"  readonly class="form-control" disabled id="staticEmail" value="email@example.com">
                                </div>
                                <div class="col-6">
                                    <label for="staticUsername" class="form-label">Tên đăng nhập</label>
                                    <input type="text" readonly class="form-control" disabled  id="staticUsername" value="email@example.com">
                                </div>
                                <div class="col-6 mt-2">
                                    <label for="staticFullName" class="form-label">Họ và tên</label>
                                    <input type="text" readonly class="form-control" disabled  id="staticFullName" value="email@example.com">
                                </div>

                                <div class="col-6 mt-2">
                                    <label for="staticPhone" class="form-label"> Số điện thoại </label>
                                    <input type="text" readonly class="form-control" disabled id="staticPhone" value="email@example.com">
                                </div>
                                <div class="col-6 mt-2">
                                    <label for="staticDate" class="form-label text-nowrap"> Ngày tháng năm sinh</label>
                                    <input type="text" readonly class="form-control" disabled id="staticDate" value="email@example.com">
                                </div>
                                <div class="col-6 mt-2">
                                    <div class="form-label text-nowrap">Vai trò</div>
                                    <select class="form-select" aria-label="">
                                        <option value="2">Admin</option>
                                        <option value="1">Mod</option>
                                        <option value="0" selected>User</option>
                                        <option value="3">Khóa</option>
                                    </select>
                                </div>
                                <div class="col-12 mt-2">
                                    <label class="form-label">Địa chỉ</label>
                                    <div id="staticAddress" readonly class="form-control">
                                    </div>
                                </div>
                            </div>
                        </div>
                </div>
            </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
                    <button type="button" class="btn btn-primary">Thay đổi</button>
                </div>
            </div>
        </div>
<%--<script src="<c:url value="/js/admin/adminUsers.js" />"></script>--%>
    <script src="/js/admin/adminUsers.js"></script>
</body>

</html>