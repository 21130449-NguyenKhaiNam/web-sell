<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <c:import url="${initParam.contextPath}/public/commonLink.jsp"/>
    <link rel="stylesheet" href="${initParam.contextPath}/assets/css/logIn.css">
    <link rel="stylesheet" href="${initParam.contextPath}/assets/css/forgetPassword.css">
    <title>Quên mật khẩu</title>
</head>
<body>
<main class="main">
    <div class="frame">
        <article>
            <form action="forgetPassword" class="form form--forget-password" method="post">
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