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
                <div class="service__list">
                    <a class="service__item" href="Account" >Chỉnh sửa tài khoảng</a>
                    <a class="service__item service__item--clicked">Đổi mật khẩu</a>
                    <a class="service__item" href="PurchaseHistory" >Lịch sử mua hàng</a>
                </div>
            </div>
            <div class="col-9">
                <section class="service__section service__section--show">
                    <c:set var="auth" value="${sessionScope.auth}"/>
                    <c:set var="errorOlddPass" value="${requestScope.errorOlddPass}"/>
                    <c:set var="errorNewPass" value="${requestScope.errorNewPass}"/>
                    <c:set var="errorConfirmPass" value="${requestScope.errorConfirmPass}"/>
                    <form action="ChangePassword" method="post">

                        <h1 class="title">Đổi mật khẩu</h1>

                        <input type="hidden" name="userId" value="<c:out value='${auth.getId()}' />">
                        <input type="hidden" name="userPass" value="<c:out value='${auth.getPasswordEncoding()}' />">

                        <div class="form contains">
                            <div class="info__oldPass info-compo">
                                <label class="lable__oldPass lable-compo" for="oldPassword">Mật khẩu cũ</label>
                                <div class="input__form">
                                    <input class="input__oldPass input-compo" type="password" id="oldPassword" name="oldPassword">
                                    <i class=" icon__eye icon__eye--close fa-regular fa-eye-slash"></i>
                                    <i class="icon__eye icon__eye--open fa-regular fa-eye"></i>
                                </div>
                                <c:if test="${errorOlddPass != null}">
                                    <p class="form__error">${errorOlddPass}</p>
                                </c:if>
                            </div>
                            <div class="info__newPass info-compo">
                                <label class="lable__newPass lable-compo" for="password">Mật khẩu mới</label>
                                <div class="input__form">
                                    <input type="password" id="password" name="newPassword" class="input__newPass input-compo">
                                    <i class=" icon__eye icon__eye--close fa-regular fa-eye-slash"></i>
                                    <i class="icon__eye icon__eye--open fa-regular fa-eye"></i>
                                </div>
                                <c:if test="${errorNewPass != null}">
                                    <p class="form__error">${errorNewPass}</p>
                                </c:if>
                            </div>
                            <div class="info__newPass--confirm info-compo">
                                <label class="lable__newPass--confirm lable-compo" for="confirm-password">Nhập lại mật khẩu
                                    mới</label>
                                <div class="input__form">
                                    <input type="password" id="confirm-password" name="confirmPassword"
                                           class="input__newPass--confirm input-compo">
                                    <i class=" icon__eye icon__eye--close fa-regular fa-eye-slash"></i>
                                    <i class="icon__eye icon__eye--open fa-regular fa-eye"></i>
                                </div>
                                <c:if test="${errorConfirmPass != null}">
                                    <p class="form__error">${errorConfirmPass}</p>
                                </c:if>
                            </div>
                            <div class="save save__changePass">
                                <button id="form__submit" type="submit" class=" form__submit button button--hover">Lưu thay
                                    đổi
                                </button>
                            </div>
                        </div>
                    </form>
                </section>
            </div>
        </div>
    </div>
</main>
<%@include file="footer.jsp" %>
<%--<script src="js/validateForm.js"></script>--%>
<%--<script src="js/data.js"></script>--%>
<%--<script src="js/account.js"></script>--%>
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
<%--<script>--%>

<%--    var listServiceBtn = document.querySelectorAll(".service__item");--%>
<%--    var listServiceSection = document.querySelectorAll(".service__section");--%>
<%--    console.log(listServiceSection)--%>
<%--    listServiceBtn.forEach(function (btn, index) {--%>
<%--        btn.onclick = function () {--%>
<%--            listServiceSection.forEach(function (section) {--%>
<%--                section.classList.remove("service__section--show");--%>
<%--            });--%>
<%--            listServiceBtn.forEach(function (btn) {--%>
<%--                btn.classList.remove("service__item--clicked");--%>
<%--            });--%>
<%--            listServiceSection[index].classList.add("service__section--show");--%>
<%--            btn.classList.add("service__item--clicked");--%>
<%--        }--%>
<%--    })--%>
<%--</script>--%>
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
<%--<script>--%>
<%--    //checkValidation--%>
<%--    var validation = new Validation({--%>
<%--        formSelector: ".form",--%>
<%--        formBlockClass: "info-compo",--%>
<%--        errorSelector: ".form__error",--%>
<%--        rules: [--%>
<%--            Validation.isRequired("#oldPassword"),--%>
<%--            Validation.isRequired("#password"),--%>
<%--            Validation.minLength("#password", 6),--%>
<%--            Validation.isRequired("#confirm-password"),--%>
<%--            Validation.isConfirm("#confirm-password", function () {--%>
<%--                return document.querySelector("#password").value;--%>
<%--            })--%>
<%--        ],--%>
<%--    })--%>
<%--</script>--%>
<script>
    var inputForm = document.querySelectorAll(".input__form");
    inputForm.forEach(function (input) {
        const eyeClose = input.querySelector(".icon__eye--close");
        const eyeOpen = input.querySelector(".icon__eye--open");
        const inputElement = input.querySelector("input");

        eyeClose.addEventListener("click", function (e) {
            e.preventDefault();
            inputElement.type = "text";
            eyeOpen.style.display = "block";
            eyeClose.style.display = "none";
        });

        eyeOpen.addEventListener("click", function (e) {
            e.preventDefault();
            inputElement.type = "password";
            eyeOpen.style.display = "none";
            eyeClose.style.display = "block";
        });
    });
</script>
</html>