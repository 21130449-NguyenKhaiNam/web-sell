<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="java.util.Calendar" %>


<%@ page import="models.User" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <!--Các thư viện hỗ trợ-->
    <!--Font Awesome-->
    <link rel="stylesheet" href="assets/fontIcon/fontawesome-free-6.4.2-web/css/all.min.css">
    <link rel="stylesheet" href="assets/bootstrap/bootstrap-grid.min.css">
    <!--Favicon-->
    <link rel="apple-touch-icon" sizes="180x180" href="assets/favicon/apple-touch-icon.png">
    <link rel="icon" type="image/png" sizes="32x32" href="assets/favicon/favicon-32x32.png">
    <link rel="icon" type="image/png" sizes="16x16" href="assets/favicon/favicon-16x16.png">
    <link rel="manifest" href="assets/favicon/site.webmanifest">
    <!--Web font-->
    <link rel="stylesheet" href="assets/font/webfonts/Montserrat.css">

    <link rel="stylesheet" href="assets/bootstrap/bootstrap-grid.min.css">
    <link rel="stylesheet" href="assets/fontIcon/fontawesome-free-6.4.2-web/css/all.min.css">
    <link rel="manifest" href="assets/favicon/site.webmanifest">
    <link rel="stylesheet" href="assets/font/webfonts/Montserrat.css">
    <link rel="stylesheet" href="assets/css/reset.css">
    <link rel="stylesheet" href="assets/css/base.css">

    <link rel="stylesheet" href="assets/css/account.css">
    <title>Tài khoản</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<main class="main">
    <div class="container-xl">
        <div class="row">
            <div class="col-3">
                <ul class="service__list">
                    <li class="service__item service__item--clicked ">Chỉnh sửa tài khoản</li>
                    <li class="service__item">Đổi mật khẩu</li>
                    <li class="service__item">Lịch sử mua hàng</li>
                </ul>
            </div>
            <div class="col-9">
                <section class="service__section service__section--show">
                    <c:set var="auth" value="${sessionScope.auth}"/>
                    <c:set var="accountInfo" value="${requestScope.accountInfo}" />
                    <c:set var="selectedGender" value="${accountInfo.gender}"/>
                    <c:set var="birthday" value="${accountInfo.birthDay}"/>
                    <c:set var="birthdayParts" value="${fn:split(birthday, '-')}"/>
                    <form action="UpdateAccount" method="post">
                        <h1 class="title">Chỉnh sửa tài khoản</h1>
                        <div class="user__maininfo block_info">
                            <div class="user__img user">
                                <img src="assets/img/product_img/product21.jpg" id="photo">
                                <input type="file" id="file">
                                <label for="file" id="uploadbtn" class="fas fa-camera"></label>
                            </div>
                            <div class="user__info user">
                                    <input type="hidden" name="userId" value="<c:out value='${auth.getId()}' />">
                                <!-- Ví dụ sử dụng c:out để in giá trị của các trường từ đối tượng accountInfo -->
<%--                                <input type="text" name="userId" value="<c:out value='${accountInfo.id}' />">--%>
<%--                                <input type="text" name="userName" value="<c:out value='${accountInfo.username}' />">--%>
<%--                                <input type="email" name="email" value="<c:out value='${accountInfo.email}' />">--%>
<%--                                <!-- Các trường thông tin khác -->--%>

<%--                                <!-- Hoặc sử dụng trực tiếp giá trị từ đối tượng accountInfo -->--%>
<%--                                <input type="text" name="userId" value="${accountInfo.id}">--%>
<%--                                <input type="text" name="userName" value="${accountInfo.username}">--%>
<%--                                <input type="email" name="email" value="${accountInfo.email}">--%>
                                <!-- Các trường thông tin khác -->

                                <div class="user__info--name info-compo">
                                    <div class="lable__name lable-compo">
                                        <label for="Username">Tên người dùng</label>
                                    </div>
                                    <input type="text" id="Username" class=" input-compo" name="userName" value="${accountInfo.username}">
                                </div>
                                <div class="user__info--email info-compo">
                                    <div class="lable__email lable-compo">
                                        <label for="Email">Email</label>
                                    </div>
                                    <input type="email" id="Email" class=" input-compo" name="email" value="${accountInfo.email}">
                                </div>
                                <div class="user__info--name info-compo">
                                    <div class="lable__name lable-compo">
                                        <label for="Username">Họ tên</label>
                                    </div>
                                    <input type="text" id="FullName" class=" input-compo" name="fullName" value="${accountInfo.fullName}">
                                </div>
                                <div class="user__info--email info-compo">
                                    <div class="lable__email lable-compo">
                                        <label for="Email">Số điện thoại</label>
                                    </div>
                                    <input type="text" id="Phone" class=" input-compo" name="phone" value="${accountInfo.phone}">
                                </div>
                                <div class="user__info--gender info-compo">
                                    <div class="lable__gender lable-compo">
                                        <label for="gender">Giới tính</label>
                                    </div>
                                    <div class="gender__option">
                                        <select  id="gender" name="gender" onchange="hideDefaultOption()">
                                            <c:choose>
                                                <c:when test="${not empty selectedGender}">
                                                    <option value="Nam" ${selectedGender eq 'Nam' ? 'selected' : ''}>Nam
                                                    </option>
                                                    <option value="Nữ" ${selectedGender eq 'Nữ' ? 'selected' : ''}>Nữ
                                                    </option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="" id="defaultOption">-- Chọn giới tính --</option>
                                                    <option value="Nam">Nam</option>
                                                    <option value="Nữ">Nữ</option>
                                                </c:otherwise>
                                            </c:choose>
                                        </select>
                                    </div>
                                </div>
                                <div class="user__info--birthday info-compo">
                                    <div class="lable__birthday lable-compo">
                                        <label for="day">Ngày sinh</label>
                                    </div>
                                    <div class="birthday__option">
                                        <select type="text"  class="input-compo" id="year" name="year">
                                            <c:forEach var="year" begin="1900" end="2100">
                                                <option value="${year}" ${year eq birthdayParts[0] ? 'selected' : ''}>${year}</option>
                                            </c:forEach>
                                        </select>

                                        <select  id="month" name="month">
                                            <c:forEach var="month" begin="1" end="12">
                                                <option value="${month}" ${month eq birthdayParts[1] ? 'selected' : ''}>Tháng ${month}</option>
                                            </c:forEach>
                                        </select>

                                        <select type="text" class="input-compo" id="day" name="day">
                                            <c:forEach var="day" begin="1" end="31">
                                                <option value="${day}" ${day eq birthdayParts[2] ? 'selected' : ''}>${day}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="user__info--address info-compo">
                                    <div class="lable__address lable-compo">
                                        <label for="Address">Địa chỉ</label>
                                    </div>
                                    <input type="text" class="input-compo" id="Address" name="address" value="<c:out value="${accountInfo.address}"/>">
                                </div>
                            </div>
                        </div>
                        <div class="save save__userInfo">
                            <button>Lưu thay đổi</button>
                        </div>
                    </form>
                </section>
                <section class="service__section">
                    <h1 class="title">Đổi mật khẩu</h1>
                    <div class="form contains">
                        <div class="info__oldPass info-compo">
                            <label class="lable__oldPass lable-compo" for="oldPassword">Mật khẩu cũ</label>
                            <div class="input__form">
                                <input class="input__oldPass input-compo" type="password" id="oldPassword">
                                <i class=" icon__eye icon__eye--close fa-regular fa-eye-slash"></i>
                                <i class="icon__eye icon__eye--open fa-regular fa-eye"></i>
                            </div>
                            <p class="form__error"></p>
                        </div>
                        <div class="info__newPass info-compo">
                            <label class="lable__newPass lable-compo" for="password">Mật khẩu mới</label>
                            <div class="input__form">
                                <input type="password" id="password" class="input__newPass input-compo">
                                <i class=" icon__eye icon__eye--close fa-regular fa-eye-slash"></i>
                                <i class="icon__eye icon__eye--open fa-regular fa-eye"></i>
                            </div>
                            <p class="form__error"></p>
                        </div>
                        <div class="info__newPass--confirm info-compo">
                            <label class="lable__newPass--confirm lable-compo" for="confirm-password">Nhập lại mật khẩu
                                mới</label>
                            <div class="input__form">
                                <input type="password" id="confirm-password"
                                       class="input__newPass--confirm input-compo">
                                <i class=" icon__eye icon__eye--close fa-regular fa-eye-slash"></i>
                                <i class="icon__eye icon__eye--open fa-regular fa-eye"></i>
                            </div>
                            <p class="form__error"></p>
                        </div>
                        <div class="save save__changePass">
                            <button id="form__submit" type="submit" class=" form__submit button button--hover">Lưu thay
                                đổi
                            </button>
                        </div>
                    </div>
                </section>
                <section class="service__section">
                    <h1 class="title">Lịch sử mua hàng</h1>
                    <div class="statusOrder">
                        <span class="status__list status__list--click">Tất cả</span>
                        <span class="status__list">Đơn hàng mới</span>
                        <span class="status__list">Chờ thanh toán</span>
                        <span class="status__list">Đã xác nhận</span>
                        <span class="status__list">Vận chuyển</span>
                        <span class="status__list">Hoàn thành</span>
                    </div>
                    <div class="service__order service__order--show">

                    </div>
                    <div class="service__order ">
                        <div class="block__product--history">
                            <div class="imgNoneProduct" f></div>
                            <h2>Chưa có đơn hàng</h2>
                        </div>
                    </div>
                    <div class="service__order ">
                        <div class="block__product--history">
                            <div class="imgNoneProduct"></div>
                            <h2>Chưa có đơn hàng</h2>
                        </div>
                    </div>
                    <div class="service__order ">
                        <div class="block__product--history">
                            <div class="imgNoneProduct"></div>
                            <h2>Chưa có đơn hàng</h2>
                        </div>
                    </div>
                    <div class="service__order ">
                        <div class="block__product--history">
                            <div class="imgNoneProduct"></div>
                            <h2>Chưa có đơn hàng</h2>
                        </div>
                    </div>
                    <div class="service__order service__order--done">

                    </div>
                </section>
            </div>
        </div>
    </div>
</main>
<%@include file="footer.jsp" %>
<script src="js/validateForm.js"></script>
<script src="js/data.js"></script>
<script src="js/account.js"></script>
<script>
    function hideDefaultOption() {
        var genderDropdown = document.getElementById("gender");
        var defaultOption = document.getElementById("defaultOption");

        if (genderDropdown.value !== "") {
            defaultOption.style.display = "none";
        } else {
            defaultOption.style.display = "block";
        }
    }
</script>
</body>
<script>

    var listServiceBtn = document.querySelectorAll(".service__item");
    var listServiceSection = document.querySelectorAll(".service__section");
    console.log(listServiceSection)
    listServiceBtn.forEach(function (btn, index) {
        btn.onclick = function () {
            listServiceSection.forEach(function (section) {
                section.classList.remove("service__section--show");
            });
            listServiceBtn.forEach(function (btn) {
                btn.classList.remove("service__item--clicked");
            });
            listServiceSection[index].classList.add("service__section--show");
            btn.classList.add("service__item--clicked");
        }
    })
</script>
<script>
    var listStatus = document.querySelectorAll(".status__list");
    var listServiceOrder = document.querySelectorAll(".service__order");
    console.log(listServiceOrder)
    listStatus.forEach(function (btn, index) {
        btn.onclick = function () {
            listServiceOrder.forEach(function (section) {
                section.classList.remove("service__order--show");
            });
            listStatus.forEach(function (btn) {
                btn.classList.remove("status__list--click");
            });
            listServiceOrder[index].classList.add("service__order--show");
            btn.classList.add("status__list--click");
        }
    })
</script>
<script>
    //checkValidation
    var validation = new Validation({
        formSelector: ".form",
        formBlockClass: "info-compo",
        errorSelector: ".form__error",
        rules: [
            Validation.isRequired("#oldPassword"),
            Validation.isRequired("#password"),
            Validation.minLength("#password", 6),
            Validation.isRequired("#confirm-password"),
            Validation.isConfirm("#confirm-password", function () {
                return document.querySelector("#password").value;
            })
        ],
    })
</script>

</html>