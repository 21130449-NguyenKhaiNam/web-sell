<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <jsp:include page="/public/commonLink.jsp"/>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/select2-bootstrap-5-theme@1.3.0/dist/select2-bootstrap-5-theme.min.css"/>
    <link rel="stylesheet" href="<c:url value="/assets/css/admin/admin.css"/>">
    <link rel="stylesheet" href="<c:url value="/assets/css/user/account.css"/>">
    <link rel="stylesheet" href="<c:url value="/assets/css/user/accountInfo.css"/>">
    <title>Thông tin cá nhân</title>
</head>
<body>
<c:set var="user" value="${requestScope.accountInfo}"/>
<div id="main">
    <nav class="navbar">
        <div class="container-xl">
            <ul class="navbar__list">
                <li class="navbar__item">
                    <a href="<c:url value="/"/>"
                       class="navbar__link button button button--hover hvr-grow-shadow">Trang
                        chủ</a>
                </li>
                <li class="navbar__item">
                    <a href="#"
                       class="navbar__link button button button--hover navbar__link--clicked hvr-grow-shadow"> Thông tin
                        cá
                        nhân</a>
                </li>
                <li class="navbar__item">
                    <a href="<c:url value="/public/user/accountSecurity.jsp"/>"
                       class="navbar__link button button button--hover  hvr-grow-shadow"> Bảo mật</a>
                </li>
                <li class="navbar__item">
                    <a href="<c:url value="/public/user/accountOrder.jsp"/>"
                       class="navbar__link button button button--hover hvr-grow-shadow"> Đơn hàng
                    </a>
                </li>
            </ul>
        </div>
    </nav>
    <div class="container-xl px-4 mt-4">
        <div class="row">
            <div class="col-xl-4">
                <div class="card mb-4 mb-xl-0">
                    <div class="card-header">Ảnh đại diện</div>
                    <div class="card-body text-center">

                        <img id="preview-avatar"
                             class="img-account-profile object-fit-cover rounded-circle overflow-hidden  mb-2"
                             src="${not empty user.avatar ? avatar : '/assets/img/user/userDefaultAvatar.png'}"
                             alt="">
                        <div id="username" class="medium  text-muted mb-2">${user.username}</div>
                        <div id="email" class="small  text-muted mb-4">${user.email}</div>
                        <div id="open-form" class="btn btn-primary ">Thay đổi ảnh</div>

                        <form id="form-avatar" enctype="multipart/form-data">
                            <input id="avatar" name="avatar" type="file" class="form-control" accept="image/png">
                            <div class="mt-2 small">
                            </div>
                            <button type="submit" class="btn btn-primary w-100 mt-2">Cập nhập ảnh</button>
                        </form>
                    </div>
                </div>
            </div>
            <div class="col-xl-8">
                <div class="card mb-4">
                    <div class="card-header">Thông tin cá nhân</div>
                    <div class="card-body">
                        <form id="form-personal">
                            <div class="row gx-3 mb-3">
                                <div class="col-md-6">
                                    <div class="">
                                        <label class="medium mb-1" for="inputUsername">Họ và tên</label>
                                        <input name="fullName" class="form-control" id="inputUsername" type="text"
                                               placeholder="Vui lòng nhập tên của bạn" value="${user.fullName}">
                                        <div class="valid-feedback">

                                        </div>
                                    </div>
                                </div>

                                <div class="col-md-6">
                                    <label class="medium mb-1" for="inputGender">Giới tính</label>
                                    <select id="inputGender" name="gender" class="form-select" aria-label="Chọn">
                                        <c:choose> <c:when test="${not empty user.gender}">
                                            <option value="Nam" ${user.gender eq 'Nam'
                                                    ? 'selected' : '' }>Nam
                                            </option>
                                            <option value="Nữ" ${user.gender eq 'Nữ'
                                                    ? 'selected' : '' }>Nữ
                                            </option>
                                        </c:when> <c:otherwise>
                                            <option value="" id="defaultOption">-- Chọn giới tính --
                                            </option>
                                            <option value="Nam">Nam</option>
                                            <option value="Nữ">Nữ</option>
                                        </c:otherwise> </c:choose>
                                    </select>
                                    <div class="valid-feedback">

                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6">
                                    <fmt:formatDate var="date" type="DATE" value="${user.birthDay}"
                                                    pattern="dd-MM-yyy"/>
                                    <label class="medium mb-1" for="inputDate">Ngày sinh</label>
                                    <input class="form-select" name="birthDay" value="${date}" id="inputDate"
                                           type="text">
                                    <div class="valid-feedback">

                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <label class="medium mb-1" for="inputPhone"> Số điện thoại</label>
                                    <input class="form-select" name="phone" value="${user.phone}" id="inputPhone"
                                           type="text">
                                    <div class="valid-feedback">

                                    </div>
                                </div>
                            </div>
                            <button class="btn btn-primary mt-2" type="submit">Thay đổi</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <div class="row mt-4">
            <div class="col-12">
                <div class="card mb-4">
                    <div class="card-header">Sổ địa chỉ</div>
                    <div class="card-body">
                        <div class="d-flex justify-content-end mb-2">
                            <button class="btn btn-primary btn__address-create" data-bs-toggle="modal"
                                    data-bs-target="#modal">
                                Thêm địa chỉ
                            </button>
                        </div>
                        <table id="addressList" class="table table-bordered table-hover table-striped text-center">
                            <thead>
                            <tr>
                                <th>#</th>
                                <th>Tỉnh/Thành phố</th>
                                <th>Quận/Huyện</th>
                                <th>Xã/Phường</th>
                                <th>Địa chỉ chi tiết</th>
                            </tr>
                            </thead>
                            <tbody>

                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="modal fade text-black" id="modal" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
    <div id="model" class="modal-dialog modal-dialog-scrollable modal-dialog-centered" style="max-width: 80%">
        <form id="form-address" class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="staticBackdropLabel">Sổ địa chỉ</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <div class="row gx-3 mb-3 mt-2">
                    <div class="col-md-4 col-sm-12">
                        <label class="small  py-1" for="inputProvince">Tỉnh / Thành phố </label>
                        <select name="province" id="inputProvince" class="form-select " aria-label="Chọn">
                            <option value=""></option>
                        </select>
                        <div class="valid-feedback">

                        </div>
                    </div>

                    <div class="col-md-4 col-sm-12">
                        <label class="small py-1" for="inputDistrict"> Quận / Huyện </label>
                        <select name="district" id="inputDistrict" class="form-select " aria-label="Chọn">
                            <option value=""></option>
                        </select>
                        <div class="valid-feedback">

                        </div>
                    </div>
                    <div class="col-md-4 col-sm-12">
                        <label class="small py-1" for="inputWard">Phường</label>
                        <select name="ward" id="inputWard" class="form-select " aria-label="Chọn">
                            <option value=""></option>
                        </select>
                        <div class="valid-feedback">

                        </div>
                    </div>
                    <div class="col-12">
                        <label class="small py-1" for="inputAddress"> Số nhà, đường </label>
                        <textarea class="form-control" name="detail" id="inputAddress"></textarea>
                        <div class="valid-feedback">

                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="submit" class="btn btn-primary">Lưu</button>
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
            </div>
        </form>
    </div>
</div>
<div class='d-none loader__wrapper position-fixed top-0 start-0 end-0 bottom-0'
     style="background-color: rgba(0,0,0,0.5)">
    <span class='position-absolute top-50 start-50 translate-middle loader'></span>
</div>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.13.2/themes/base/jquery-ui.min.css"
      integrity="sha512-ELV+xyi8IhEApPS/pSj66+Jiw+sOT1Mqkzlh8ExXihe4zfqbWkxPRi8wptXIO9g73FSlhmquFlUOuMSoXz5IRw=="
      crossorigin="anonymous" referrerpolicy="no-referrer"/>
<script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"></script>
<!--Select 2 jquery-->
<link href="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/css/select2.min.css" rel="stylesheet"/>
<script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/js/select2.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.13.2/jquery-ui.min.js"
        integrity="sha512-57oZ/vW8ANMjR/KQ6Be9v/+/h6bq9/l3f0Oc7vn6qMqyhvPd1cvKBRWWpzu0QoneImqr2SkmO4MSqU+RpHom3Q=="
        crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<!--jQuery validator-->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.20.0/jquery.validate.min.js"
        integrity="sha512-WMEKGZ7L5LWgaPeJtw9MBM4i5w5OSBlSjTjCtSnvFJGSVD26gE5+Td12qN5pvWXhuWaWcVwF++F7aqu9cvqP0A=="
        crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script src="https://ajax.aspnetcdn.com/ajax/jquery.validate/1.15.0/additional-methods.js"></script>
<!---Sweet Alert 2--->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/sweetalert2/11.10.7/sweetalert2.min.css"
      integrity="sha512-OWGg8FcHstyYFwtjfkiCoYHW2hG3PDWwdtczPAPUcETobBJOVCouKig8rqED0NMLcT9GtE4jw6IT1CSrwY87uw=="
      crossorigin="anonymous" referrerpolicy="no-referrer"/>
<script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert2/11.10.7/sweetalert2.min.js"
        integrity="sha512-csaTzpLFmF+Zl81hRtaZMsMhaeQDHO8E3gBkN3y3sCX9B1QSut68NxqcrxXH60BXPUQ/GB3LZzzIq9ZrxPAMTg=="
        crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script type="module" src="<c:url value="/js/user/accountInfo.js"/>">
</script>
</body>
</html>