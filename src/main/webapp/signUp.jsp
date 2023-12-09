<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
    <!--Bootstrap-->
    <link rel="stylesheet" href="assets/bootstrap/bootstrap-grid.min.css">
    <!--JQuery-->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
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
    <link rel="stylesheet" href="assets/css/logIn.css">

    <title>Đăng ký</title>
</head>
<body>
<main class="main">
    <div class="frame">
        <div class="frame__media">

        </div>
        <article>
            <form action="signUp" method="post" class="form form--signUp">
                <div class="form__block">
                    <label for="username" class="form__label">Tên đăng nhập</label>
                    <input id="username" name="username" type="text" class="form__input">
                    <c:set value="${requestScope.usernameError}" var="usernameError"/>
                    <p class="form__error"><c:if test="${usernameError != null}">${usernameError}</c:if></p>
                </div>
                <div class="form__block">
                    <label for="email" class="form__label">Email</label>
                    <input id="email" name="email" type="email" class="form__input">
                    <c:set value="${requestScope.emailError}" var="emailError"/>
                    <p class="form__error"><c:if test="${emailError != null}">${emailError}</c:if></p>
                </div>
                <div class="form__block">
                    <label for="password" class="form__label">Mật khẩu</label>
                    <input id="password" name="password" type="password" class="form__input">
                    <c:set value="${requestScope.passwordError}" var="passwordError"/>
                    <p class="form__error"><c:if test="${passwordError != null}">${passwordError}</c:if></p>
                </div>
                <div class="form__password-error">
                    <h3>Mật khẩu cần phải có:</h3>
                    <div class="error__list">
                        <c:set var="charMinLength" value="${requestScope.charMinLength}"/>
                        <p id="minLength"
                           class="error__item <c:if test="${charMinLength != null}">error__item--correct</c:if> ">
                            <i class="error__icon fa-solid fa-check"></i>
                            <span>Có ít nhất 8 kí tự.</span>
                        </p>
                        <c:set var="charNumber" value="${requestScope.charNumber}"/>
                        <p id="atLeast1Digit"
                           class="error__item <c:if test="${charNumber != null}">error__item--correct</c:if> ">
                            <i class="error__icon fa-solid fa-check"></i>
                            <span>Có ít nhất 1 chữ số như 1, 2, 3,...</span>
                        </p>
                        <c:set var="charUpper" value="${requestScope.charUpper}"/>
                        <p id="atLeast1WordUpper"
                           class="error__item <c:if test="${charUpper != null}">error__item--correct</c:if> ">
                            <i class="error__icon fa-solid fa-check"></i>
                            <span>Có ít nhất 1 chữ hoa như: A, B, C,...</span>
                        </p>
                        <c:set var="charLower" value="${requestScope.charLower}"/>
                        <p id="atLeast1WordLower"
                           class="error__item <c:if test="${charLower != null}">error__item--correct</c:if> ">
                            <i class="error__icon fa-solid fa-check"></i>
                            <span>Có ít nhất 1 chữ thường như a, b, c,...</span>
                        </p>
                        <c:set var="noSpace" value="${requestScope.noSpace}"/>
                        <p id="noSpace"
                           class="error__item <c:if test="${noSpace != null}">error__item--correct</c:if> ">
                            <i class="error__icon fa-solid fa-check"></i>
                            <span>Mật khẩu không được có khoảng trắng.</span>
                        </p>
                        <c:set var="charSpecial" value="${requestScope.charSpecial}"/>
                        <p id="atLeast1SpecialCharacter"
                           class="error__item <c:if test="${charSpecial != null}">error__item--correct</c:if>">
                            <i class="error__icon fa-solid fa-check"></i>
                            <span>Có ít nhất 1 kí tự đặc biệt như "@", ".", ":",....</span>
                        </p>
                    </div>
                </div>
                <div class="form__block">
                    <label for="confirm-password" class="form__label">Xác nhận lại mật khẩu</label>
                    <input id="confirm-password" name="confirm-password" type="password" class="form__input">
                    <c:set value="${requestScope.passwordConfirmError}" var="passwordConfirmError"/>
                    <p class="form__error"><c:if test="${passwordError != null}">${passwordConfirmError}</c:if></p>
                </div>
                <button type="submit" id="form__submit" class="form__submit button button--hover">Đăng ký
                </button>
            </form>
            <a href="signIn.jsp" id="form__link--signIn" class="form__link ">Đăng nhập</a>
        </article>
    </div>

    <c:if test="${requestScope.sendMail != null}">
        <!--Modal -->
        <input type="checkbox" id="modal__hide" hidden="hidden">
        <div class="modal">
            <div class="modal__notify">
                <i class="model__checked-icon fa-regular fa-circle-check"></i>
                <p class="modal__text">Đăng kí thành công, vui lòng kiểm tra email bạn đã đăng kí.</p>
                <a href="signIn.jsp" class="button modal__button button--hover">Đăng nhập</a>
                <p class="modal__resend ">Nếu bạn chưa nhận được email xác nhận, hãy <span>nhấn vào đây.</span></p>
                <label for="modal__hide" class="modal__close"><i class="fa-solid fa-xmark"></i></label>
            </div>
            <label for="modal__hide" class="modal__blur"></label>
        </div>
    </c:if>
</main>
<!--JS validate-->
<script src="js/validateForm.js"></script>
<script>
    var validation = new Validation({
        formSelector: ".form",
        formBlockClass: "form__block",
        errorSelector: ".form__error",
        rules: [
            Validation.isRequired("#username"),
            Validation.isRequired("#email"),
            Validation.isEmail("#email"),
            Validation.isRequired("#password"),
            Validation.isUnique("#password"),
            Validation.isRequired("#confirm-password"),
            Validation.isConfirm("#confirm-password", function () {
                return document.querySelector("#password").value;
            })
        ],
        submitSelector: "#form__submit",

    })
</script>
</body>
</html>