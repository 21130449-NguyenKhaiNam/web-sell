<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>Thông tin cá nhân</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <jsp:include page="/public/commonLink.jsp" />
        <style type="text/css">
            body {
                margin-top: 20px;
                background-color: #f2f6fc;
                color: #69707a;
            }

            .img-account-profile {
                height: 10rem;
            }

            .rounded-circle {
                border-radius: 50% !important;
            }

            .card {
                box-shadow: 0 0.15rem 1.75rem 0 rgb(33 40 50 / 15%);
            }

            .card .card-header {
                font-weight: 500;
            }

            .card-header:first-child {
                border-radius: 0.35rem 0.35rem 0 0;
            }

            .card-header {
                padding: 1rem 1.35rem;
                margin-bottom: 0;
                background-color: rgba(33, 40, 50, 0.03);
                border-bottom: 1px solid rgba(33, 40, 50, 0.125);
            }

            .form-control, .dataTable-input {
                display: block;
                width: 100%;
                padding: 0.875rem 1.125rem;
                font-size: 0.875rem;
                font-weight: 400;
                line-height: 1;
                color: #69707a;
                background-color: #fff;
                background-clip: padding-box;
                border: 1px solid #c5ccd6;
                -webkit-appearance: none;
                -moz-appearance: none;
                appearance: none;
                border-radius: 0.35rem;
                transition: border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
            }

            .nav-borders .nav-link.active {
                color: #0061f2;
                border-bottom-color: #0061f2;
            }

            .nav-borders .nav-link {
                color: #69707a;
                border-bottom-width: 0.125rem;
                border-bottom-style: solid;
                border-bottom-color: transparent;
                padding-top: 0.5rem;
                padding-bottom: 0.5rem;
                padding-left: 0;
                padding-right: 0;
                margin-left: 1rem;
                margin-right: 1rem;
            }
        </style>
    </head>
    <body>
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

                            <img class="img-account-profile rounded-circle mb-2" src="/assets/img/user/userDefaultAvatar.jpg" alt>

                            <div class="small font-italic text-muted mb-4"></div>

                            <form action="">
                                <button class="btn btn-primary" type="button">Cập nhập ảnh</button>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="col-xl-8">

                    <div class="card mb-4">
                        <div class="card-header">Thông tin cá nhân</div>
                        <div class="card-body">
                            <form>
                                <c:set var="user" value="${requestScope.accountInfo}" />
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


                                <div class="medium mb-1">Địa chỉ</div>

                                <div class="row gx-3 mb-3 mt-2">

                                    <div class="col-md-4">
                                        <label class="small mb-1" for="inputProvince">Tỉnh / Thành phố </label>
                                        <select id="inputProvince" class="form-select" aria-label="Chọn">
                                            <option value=""></option>
                                        </select>
                                    </div>

                                    <div class="col-md-4">
                                        <label class="small mb-1" for="inputDistrict"> Quận / Huyện </label>
                                        <select id="inputDistrict" class="form-select" aria-label="Chọn">
                                            <option value=""></option>
                                        </select>
                                    </div>
                                    <div class="col-md-4">
                                        <label class="small mb-1" for="inputWard">Phường</label>
                                        <select id="inputWard" class="form-select" aria-label="Chọn">
                                            <option value=""></option>
                                        </select>
                                    </div>
                                </div>

                                <button class="btn btn-primary" type="button">Thay đổi</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"></script>
        <link href="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/css/select2.min.css" rel="stylesheet" />
        <script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/js/select2.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.13.2/jquery-ui.min.js" integrity="sha512-57oZ/vW8ANMjR/KQ6Be9v/+/h6bq9/l3f0Oc7vn6qMqyhvPd1cvKBRWWpzu0QoneImqr2SkmO4MSqU+RpHom3Q==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.13.2/themes/base/jquery-ui.min.css" integrity="sha512-ELV+xyi8IhEApPS/pSj66+Jiw+sOT1Mqkzlh8ExXihe4zfqbWkxPRi8wptXIO9g73FSlhmquFlUOuMSoXz5IRw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <script src="/js/accountInfo.js">
        </script>
    </body>
</html>
