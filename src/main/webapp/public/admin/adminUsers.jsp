<%@ page import="java.util.List" %>
<%@ page import="models.User" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="en">
<head>
    <jsp:include page="/public/admin/adminLink.jsp"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/assets/css/admin/adminUsers.css" />">
    <title>Quản lí người dùng</title>
</head>
<body>
<!--Header-->
<c:import url="/public/header.jsp"/>
<main id="main">
    <!--Navigate-->
    <c:import url="/public/admin/adminNavigator.jsp"/>
    <section class="content">
        <div class="container-xl">
            <div class="row">
                <div class="col-12">
                    <form action="/exportExcelUser" method="POST">
                        <button class="btn_export">
                            <i class="fa-solid fa-file-export"></i>
                            Xuất file excel
                        </button>
                    </form>
                    <div>
                        <h1>Danh sách người dùng</h1>
                        <article class="form__search-block filler__block">
                            <i class="search__icon fa-solid fa-magnifying-glass"></i>
                            <form id="form-search" method="get">
                                <input id="search-input" type="text" name="search"
                                       placeholder="Tìm kiếm theo username hoặc email người dùng">
                            </form>
                        </article>
                        <button id="button-add-user" class="button button__delete">
                            <i class="fa-solid fa-add"></i>Thêm người dùng
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
                                <th class="table__head ">Vai trò</th>
                            </tr>
                            </thead>
                            <tbody>
                            </tbody>
                        </table>
                    </div>

                    <div class="pagination">
                    </div>
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
<%--&lt;%&ndash;DialogUpdateUser&ndash;%&gt;--%>
<%--<div id="update-user-dialog" class="modal">--%>
<%--    <div class="modal__content">--%>
<%--        <div class="modal__header">--%>
<%--            <h1>Chỉnh sửa thông tin người dùng</h1>--%>
<%--            <i id="close-update-user-dialog" class="fa-solid fa-xmark"></i>--%>
<%--        </div>--%>
<%--        <div class="modal__body">--%>
<%--            <form id="update-user-form" action="UpdateUser" method="post">--%>
<%--                <label for="username">Username:</label>--%>
<%--                <input type="text" id="username--Update" name="username" value="${user.username}">--%>

<%--                <label for="fullName">Họ tên:</label>--%>
<%--                <input type="text" id="fullName--Update" name="fullName" value="${user.fullName}">--%>

<%--                <label for="gender">Giới tính:</label>--%>
<%--                <select id="gender--Update" name="gender">--%>
<%--                    <option value="Nam" ${user.gender eq 'Nam' ? 'selected' : ''}>Nam</option>--%>
<%--                    <option value="Nữ" ${user.gender eq 'Nữ' ? 'selected' : ''}>Nữ</option>--%>
<%--                </select>--%>

<%--                <label for="email">Email:</label>--%>
<%--                <input type="email" id="email--Update" name="email" value="${user.email}" required>--%>

<%--                <label for="phone">Số điện thoại:</label>--%>
<%--                <input type="number" id="phone--Update" name="phone" value="${user.phone}" required>--%>

<%--                <label for="address">Địa chỉ:</label>--%>
<%--                <input type="text" id="address--Update" name="address" value="${user.address}" required>--%>

<%--                <label for="birthDay">Ngày sinh:</label>--%>
<%--                <input type="date" id="birthDay--Update" name="birthDay" value="${user.birthDay}" required>--%>

<%--                <input type="hidden" id="id--Update" name="userID" value="${user.id}">--%>

<%--                <label for="role--Update">Vai trò</label>--%>
<%--                <select id="role--Update" name="role">--%>
<%--                    <option value="0" ${user.role eq '0' ? 'selected' : ''}>Khách hàng</option>--%>
<%--                    <option value="1" ${user.role eq '1' ? 'selected' : ''}>Mod</option>--%>
<%--                    <option value="2" ${user.role eq '2' ? 'selected' : ''}>Admin</option>--%>
<%--                </select>--%>

<%--                <div class="modal__footer">--%>
<%--                    <button type="button" id="cancel-update-user" class="button button__cancel">Hủy bỏ</button>--%>
<%--                    <button type="submit" class="button button__update-user">--%>
<%--                        <i class="fa-solid fa-user-plus"></i>--%>
<%--                        Chỉnh sửa--%>
<%--                    </button>--%>
<%--                </div>--%>
<%--            </form>--%>

<%--        </div>--%>
<%--    </div>--%>
<%--</div>--%>
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
                <input type="number" id="phone" name="phone" required>

                <label for="address">Địa chỉ:</label>
                <input type="text" id="address" name="address" required>

                <label for="birthDay">Ngày sinh:</label>
                <input type="date" id="birthDay" name="birthDay" required>

                <label for="role">Vai trò</label>
                <select id="role" name="role">
                    <option value="0">Khách hàng</option>
                    <option value="1">Mod</option>
                    <option value="2">Admin</option>
                </select>

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

<!-- Modal update user-->
<div class="modal fade" id="modal-update" data-bs-keyboard="false" tabindex="-1"
     aria-labelledby="staticBackdropLabel" aria-hidden="true">
    <div class="modal-dialog" style="max-width: 80%">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="staticBackdropLabel">Cập nhập thông tin ngừoi dùng</h5>
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
                                <input type="text" readonly class="form-control" disabled id="staticEmail"
                                       value="email@example.com">
                            </div>
                            <div class="col-6">
                                <label for="staticUsername" class="form-label">Tên đăng nhập</label>
                                <input type="text" readonly class="form-control" disabled id="staticUsername"
                                       value="email@example.com">
                            </div>
                            <div class="col-6 mt-2">
                                <label for="staticFullName" class="form-label">Họ và tên</label>
                                <input type="text" readonly class="form-control" disabled id="staticFullName"
                                       value="email@example.com">
                            </div>

                            <div class="col-6 mt-2">
                                <label for="staticPhone" class="form-label"> Số điện thoại </label>
                                <input type="text" readonly class="form-control" disabled id="staticPhone"
                                       value="email@example.com">
                            </div>
                            <div class="col-6 mt-2">
                                <label for="staticDate" class="form-label text-nowrap"> Ngày tháng năm sinh</label>
                                <input type="text" readonly class="form-control" disabled id="staticDate"
                                       value="email@example.com">
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
                <button type="button" class="btn btn-primary">Cập nhập</button>
            </div>
        </div>
    </div>
</div>
<script src="<c:url value="/js/admin/adminUsers.js" />"></script>
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