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
                                <input id="search-input" type="text" name="search" placeholder="Tìm kiếm tên người dùng">
                            </form>
                        </article>
                        <button id="button-add-user" class="button button__delete">
                            <i class="fa-solid fa-add"></i>
                            Thêm người dùng
                        </button>
                    </div>
                    <div class="table__wrapper">
                        <table class="table ">
                            <thead>
                            <tr class="table__row">
                                <th class="table__head table__id">Chỉnh sửa</th>
                                <th class="table__head table__id">Mã người dùng</th>
                                <th class="table__head table__username">Tên người dùng</th>
                                <th class="table__head table__email">Email</th>
                                <th class="table__head table__fullname">Họ tên</th>
                                <th class="table__head table__gender">Giới tính</th>
                                <th class="table__head table__birthday">Ngày sinh</th>
                                <th class="table__head table__phone">Số điện thoại</th>
                                <th class="table__head table__address">Địa chỉ</th>
                                <th class="table__head table__address">Vai trò</th>
                                <th class="table__head table__address">Xóa</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${requestScope.lists}" var="user">
                                <tr class="table__row">
                                    <td class="table__data">
                                        <a id="updateUser" onclick="openUpdateDialog(${user.id}, '${user.username}', '${user.fullName}', '${user.gender}', '${user.email}', '${user.phone}', '${user.address}', '${user.birthDay}')">
                                            <i class="fa-solid fa-pen-to-square"></i>
                                        </a>
                                    </td>
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
                                        <p class="table__cell table__data--fullname"><c:out value="${user.fullName}"/></p>
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
                                        <p class="table__cell">
                                            <%--  <c:out value="${user.role}"/>--%>
                                            <c:if test="${user.role == '0'}">admin</c:if>
                                            <c:if test="${user.role == '1'}">mod</c:if>
                                            <c:if test="${user.role == '2'}">khách</c:if>
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

<%--                    <c:set value="${requestScope.page}" var="page"/>--%>
<%--                    <div class="pagination">--%>
<%--                        <c:if test="${page > 1}">--%>
<%--                            <c:url var="prevURLPage" value="newProducts">--%>
<%--                                <c:param name="page" value="${page - 1}"/>--%>
<%--                            </c:url>--%>
<%--                            <a href="${prevURLPage}" class="previous__page"><i class="fa-solid fa-chevron-left"></i></a>--%>
<%--                        </c:if>--%>
<%--                        <c:forEach begin="${1}" end="${requestScope.totalPage}" var="i">--%>
<%--                            <c:url var="trURLPage" value="newProducts">--%>
<%--                                <c:param name="page" value="${i}"/>--%>
<%--                            </c:url>--%>
<%--                            <a class="${i == page ? "active" : "page__forward"}" href="${trURLPage}">${i}</a>--%>
<%--                        </c:forEach>--%>
<%--                        <c:if test="${page < requestScope.totalPage}">--%>
<%--                            <c:url var="nextURLPage" value="newProducts">--%>
<%--                                <c:param name="page" value="${page + 1}"/>--%>
<%--                            </c:url>--%>
<%--                            <a href="${nextURLPage}" class="next__page"><i class="fa-solid fa-chevron-right"></i></a>--%>
<%--                        </c:if>--%>
<%--                    </div>--%>

                </div>
            </div>
        </div>
    </section>
</main>
<!-- DialogDeleteUser -->
<div id="delete-dialog" class="modal">
    <div class="modal__content">
        <div class="modal__header">
            <h1>Xác nhận xóa</h1>
            <i id="close-dialog" class="fa-solid fa-xmark"></i>
        </div>
        <p class="modal__body">Bạn có chắc chắn muốn xóa người dùng này?</p>
        <div class="modal__footer">
            <button id="cancel-delete" class="button button__cancel">Hủy bỏ</button>
            <a id="confirm-delete" href="Delete?userId=${user.id}" class="button button__delete--dialog">
                <i class="fa-solid fa-trash-can"></i>
                Xác nhận xóa
            </a>
        </div>
    </div>
</div>
<%--DialogUpdateUser--%>
<div id="update-user-dialog" class="modal">
    <div class="modal__content">
        <div class="modal__header">
            <h1>Chỉnh sửa thông tin người dùng</h1>
            <i id="close-update-user-dialog" class="fa-solid fa-xmark"></i>
        </div>
        <div class="modal__body">
            <form id="update-user-form" action="UpdateUser" method="post">
                <label for="username">Username:</label>
                <input type="text" id="username--Update" name="username" value="${user.username}">

                <label for="fullName">Họ tên:</label>
                <input type="text" id="fullName--Update" name="fullName" value="${user.fullName}">

                <label for="gender">Giới tính:</label>
                <select id="gender--Update" name="gender">
                    <option value="Nam" ${user.gender eq 'Nam' ? 'selected' : ''}>Nam</option>
                    <option value="Nữ" ${user.gender eq 'Nữ' ? 'selected' : ''}>Nữ</option>
                </select>

                <label for="email">Email:</label>
                <input type="email" id="email--Update" name="email" value="${user.email}" required>

                <label for="phone">Số điện thoại:</label>
                <input type="tel" id="phone--Update" name="phone" value="${user.phone}" required>

                <label for="address">Địa chỉ:</label>
                <input type="text" id="address--Update" name="address" value="${user.address}" required>

                <label for="birthDay">Ngày sinh:</label>
                <input type="date" id="birthDay--Update" name="birthDay" value="${user.birthDay}" required>

                <input type="hidden" id="id--Update" name="userID" value="${user.id}">

                <div class="modal__footer">
                    <button type="button" id="cancel-update-user" class="button button__cancel">Hủy bỏ</button>
                    <button type="submit" class="button button__update-user">
                        <i class="fa-solid fa-user-plus"></i>
                        Chỉnh sửa
                    </button>
                </div>
            </form>

        </div>
    </div>
</div>
<!-- DialogAddUser-->
<div id="add-user-dialog" class="modal">
    <div class="modal__content">
        <div class="modal__header">
            <h1>Thêm người dùng</h1>
            <i id="close-add-user-dialog" class="fa-solid fa-xmark"></i>
        </div>
        <div class="modal__body">
            <form id="add-user-form" action="AddUser" method="post">
                <label for="username">Username:</label>
                <input type="text" id="username" name="username" required>

                <label for="username">Mật khẩu:</label>
                <input type="text" id="passwordEncoding" name="passwordEncoding" required>

                <label for="fullName">Họ tên:</label>
                <input type="text" id="fullName" name="fullName" required>

                <label for="gender">Giới tính:</label>
                <select id="gender" name="gender">
                    <option value="Nam">Nam</option>
                    <option value="Nữ">Nữ</option>
                </select>

                <label for="email">Email:</label>
                <input type="email" id="email" name="email" required>

                <label for="phone">Số điện thoại:</label>
                <input type="tel" id="phone" name="phone" required>

                <label for="address">Địa chỉ:</label>
                <input type="text" id="address" name="address" required>

                <label for="birthDay">Ngày sinh:</label>
                <input type="date" id="birthDay" name="birthDay" required>

                <div class="modal__footer">
                    <button type="button" id="cancel-add-user" class="button button__cancel">Hủy bỏ</button>
                    <button type="submit" class="button button__add-user">
                        <i class="fa-solid fa-user-plus"></i>
                        Thêm người dùng
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<%--<script src="js/data.js"></script>--%>
<%--<script src="js/paging.js"></script>--%>
<script src="js/admin/adminUsers.js"></script>
</body>

<script>
    var addUserButton = document.getElementById('button-add-user');
    var addUserDialog = document.getElementById('add-user-dialog');
    var closeAddUserDialog = document.getElementById('close-add-user-dialog');
    var cancelAddUserButton = document.getElementById('cancel-add-user');

    addUserButton.addEventListener('click', function () {
        addUserDialog.style.display = 'block';
    });
    closeAddUserDialog.addEventListener('click', function () {
        addUserDialog.style.display = 'none';
    });
    cancelAddUserButton.addEventListener('click', function () {
        addUserDialog.style.display = 'none';
    });
</script>
</html>