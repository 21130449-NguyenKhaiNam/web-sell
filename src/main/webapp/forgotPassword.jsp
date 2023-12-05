<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <link rel="stylesheet" href="assets/css/forgetPassword.css">
    <title>Quên mật khẩu</title>
</head>
<body>
<main class="main">
    <div class="frame">
        <article>
            <form action="forgetPassword" class="form form--forget-password">
                <h1 class="heading">Vui lòng nhập email của bạn để lấy lại mật khẩu</h1>
                <div class="form__block">
                    <label for="email" class="form__label">Email</label>
                    <input id="email" name="email" type="email" class="form__input">
                    <c:set var="emailError" value="${requestScope.emailError}"/>
                    <c:if test="${emailError != null}">
                        <p class="form__error">${emailError}</p>
                    </c:if>
                    <c:set var="sendMail" value="${requestScope.sendMail}"/>
                    <c:if test="${sendMail != null}">
                        <p class="form__error">${sendMail}</p>
                    </c:if>
                </div>
                <button id="form__submit" type="submit" class="form__submit button button--hover">Lấy lại mật
                                                                                                  khẩu
                </button>
            </form>
        </article>
        <input type="checkbox" id="modal__hide" class="modal__hide" hidden="hidden" checked>
        <div class="modal">
            <div class="modal__notify modal__content">
                <i class="model__checked-icon fa-regular fa-circle-check"></i>
                <p class="modal__text">Vui lòng kiểm tra email bạn đã đăng kí.</p>
                <a href="signIn.jsp" class="button modal__button button--hover">Đăng nhập</a>
                <p class="modal__resend ">Nếu bạn chưa nhận được email xác nhận, hãy <span>nhấn vào đây.</span></p>
            </div>
            <label for="modal__hide" class="modal__blur"></label>
        </div>
    </div>
</main>
</body>
</html>