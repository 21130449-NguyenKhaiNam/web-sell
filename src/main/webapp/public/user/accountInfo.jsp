<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <jsp:include page="/public/commonLink.jsp" />
        <link rel="stylesheet" href="/assets/css/user/account.css">
        <link rel="stylesheet" href="/assets/css/user/accountInfo.css">
        <title>Thông tin cá nhân</title>
    </head>
    <body>
        <c:set var="user" value="${requestScope.accountInfo}" />
        <div class="container-xl px-4 mt-4">

            <nav class="nav nav-borders">
                <a class="nav-link active ms-0" href="#">
                    Thông tin cá nhân
                </a>
                <a class="nav-link" href="/public/user/accountSecurity.jsp">
                    Bảo mật
                </a>
                <a class="nav-link" href="/public/user/accountOrder.jsp">
                    Đơn hàng
                </a>
            </nav>
            <hr class="mt-0 mb-4">
            <div class="row">
                <div class="col-xl-4">

                    <div class="card mb-4 mb-xl-0">
                        <div class="card-header">Ảnh đại diện</div>
                        <div class="card-body text-center">
                            <img id="preview-avatar" class="img-account-profile rounded-circle mb-2" src="/assets/img/user/userDefaultAvatar.jpg" alt>
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
                            <form>

                                <div class="mb-3">
                                    <label class="medium mb-1" for="inputUsername">Họ và tên</label>
                                    <input class="form-control" id="inputUsername" type="text" placeholder="Vui lòng nhập tên của bạn" value="${user.fullName}">
                                </div>

                                <div class="row gx-3 mb-3">
                                    <div class="col-md-6">
                                        <label class="medium mb-1" for="inputGender">Giới tính</label>
                                        <select id="inputGender" class="form-select" aria-label="Chọn">
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
                                    </div>

                                    <div class="col-md-6">
                                        <fmt:formatDate var="date" type="DATE" value="${user.birthDay}" pattern="dd-MM-yyy" />
                                        <label class="medium mb-1" for="inputDate">Ngày sinh</label>
                                        <input class="form-select" value="${date}" id="inputDate" type="text">
                                    </div>
                                </div>


                                <div class="row">
                                    <div class="medium mb-1">Địa chỉ</div>
                                </div>

                                <div class="row gx-3 mb-3 mt-2">
                                    <div class="col-md-4 col-sm-12">
                                        <label class="small  py-1" for="inputProvince">Tỉnh / Thành phố </label>
                                        <select id="inputProvince" class="form-select" aria-label="Chọn">
                                            <option value=""></option>
                                        </select>
                                    </div>

                                    <div class="col-md-4 col-sm-12">
                                        <label class="small py-1" for="inputDistrict"> Quận / Huyện </label>
                                        <select id="inputDistrict" class="form-select" aria-label="Chọn">
                                            <option value=""></option>
                                        </select>
                                    </div>
                                    <div class="col-md-4 col-sm-12">
                                        <label class="small py-1" for="inputWard">Phường</label>
                                        <select id="inputWard" class="form-select" aria-label="Chọn">
                                            <option value=""></option>
                                        </select>
                                    </div>
                                </div>
                                <div class="row gx-3 mb-3 mt-2 ">
                                    <div class="col-12">
                                        <label  class="small py-1" for="inputAddress"> Số nhà, đường </label>
                                        <textarea class="form-control" name="detailAddress" id="inputAddress"></textarea>
                                    </div>
                                </div>
                                <button class="btn btn-primary" type="button">Thay đổi</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div id="progress-container">
            <div id="progress"></div>
        </div>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.13.2/themes/base/jquery-ui.min.css" integrity="sha512-ELV+xyi8IhEApPS/pSj66+Jiw+sOT1Mqkzlh8ExXihe4zfqbWkxPRi8wptXIO9g73FSlhmquFlUOuMSoXz5IRw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"></script>
        <link href="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/css/select2.min.css" rel="stylesheet" />
        <script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/js/select2.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.13.2/jquery-ui.min.js" integrity="sha512-57oZ/vW8ANMjR/KQ6Be9v/+/h6bq9/l3f0Oc7vn6qMqyhvPd1cvKBRWWpzu0QoneImqr2SkmO4MSqU+RpHom3Q==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.20.0/jquery.validate.min.js" integrity="sha512-WMEKGZ7L5LWgaPeJtw9MBM4i5w5OSBlSjTjCtSnvFJGSVD26gE5+Td12qN5pvWXhuWaWcVwF++F7aqu9cvqP0A==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
        <script src="https://ajax.aspnetcdn.com/ajax/jquery.validate/1.15.0/additional-methods.js"></script>
        <script src="/js/user/accountInfo.js">
        </script>
    </body>
</html>
