<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

    <!--Favicon-->
    <link rel="apple-touch-icon" sizes="180x180" href="assets/favicon/apple-touch-icon.png">
    <link rel="icon" type="image/png" sizes="32x32" href="assets/favicon/favicon-32x32.png">
    <link rel="icon" type="image/png" sizes="16x16" href="assets/favicon/favicon-16x16.png">
    <link rel="manifest" href="assets/favicon/site.webmanifest">
    <!--Web font-->
    <link rel="stylesheet" href="assets/font/webfonts/Montserrat.css">

    <!--CSS-->
<<<<<<< HEAD:htmls/resetPassword.html
    <link rel="stylesheet" href="../assets/css/reset.css">
    <link rel="stylesheet" href="../assets/css/base.css">
    <link rel="stylesheet" href="../assets/css/logIn.css">
    <link rel="stylesheet" href="../assets/css/forgetPassword.css">
=======
    <link rel="stylesheet" href="assets/css/reset.css">
    <link rel="stylesheet" href="assets/css/base.css">
    <link rel="stylesheet" href="assets/css/logIn.css">
    <link rel="stylesheet" href="assets/css/forgetPassword.css">
>>>>>>> 5bf36662aff7a4ce66429d123762f688bf9b24a9:src/main/webapp/resetPassword.jsp
    <title>Đặt lại mật khẩu</title>
</head>
<body>
<div class="frame">
    <article>
        <form action="updatePassword" class="form form--forget-password" method="post">
            <c:set var="email" value="${requestScope.email}"/>
            <input name="email" type="text" value="${email}" hidden="hidden">

            <h1 class="heading">Đặt lại mật khẩu</h1>
            <div class="form__block">
                <label for="password" class="form__label">Mật khẩu</label>
                <input name="password" id="password" type="password" class="form__input">
                <c:set var="errorPassword" value="${requestScope.errorPassword}"/>
                <c:if test="${errorPassword != null}">
                    <p class="form__error">${errorPassword}</p>
                </c:if>
            </div>
            <div class="form__block">
                <label for="confirmPassword" class="form__label">Nhập lại mật khẩu</label>
                <input name="confirmPassword" id="confirmPassword" type="password" class="form__input">
                <c:set var="errorConfirmPassword" value="${requestScope.errorConfirmPassword}"/>
                <c:if test="${errorConfirmPassword != null}">
                    <p class="form__error">${errorConfirmPassword}</p>
                </c:if>
            </div>
            <button id="form__submit" type="submit" class="form__submit button button--hover">Đặt lại mật khẩu
            </button>
        </form>
    </article>
    <c:set var="updateSuccess" value="${requestScope.updateSuccess}"/>
    <c:if test="${updateSuccess != null}">
        <div class="modal">
            <div class="modal__notify modal__content">
                <i class="model__checked-icon fa-regular fa-circle-check"></i>
                <p class="modal__text">${updateSuccess}</p>
                <a href="signIn.jsp" class="button modal__button button--hover">Đăng nhập</a>
            </div>
            <label class="modal__blur"></label>
        </div>
    </c:if>
</div>

</body>
</html>