<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <jsp:include page="/public/commonLink.jsp" />
        <link rel="stylesheet" href="<c:url value="/assets/css/logIn.css"/>">
        <link rel="stylesheet" href="<c:url value="/assets/css/forgetPassword.css"/>">
        <title>Đặt lại mật khẩu</title>
    </head>

    <body>
        <div class="frame">
            <article>
                <form action="<c:url value="/updatePassword"/>" class="form form--forget-password" method="post">
                    <c:set var="email" value="${requestScope.email}" />
                    <input name="email" type="text" value="${email}" hidden="hidden">

                    <h1 class="heading">Đặt lại mật khẩu</h1>
                    <div class="form__block">
                        <label for="password" class="form__label">Mật khẩu</label>
                        <input name="password" id="password" type="password" class="form__input">
                        <c:set var="errorPassword" value="${requestScope.errorPassword}" />
                        <c:if test="${errorPassword != null}">
                            <p class="form__error">${errorPassword}</p>
                        </c:if>
                    </div>
                    <div class="form__block">
                        <label for="confirmPassword" class="form__label">Nhập lại mật khẩu</label>
                        <input name="confirmPassword" id="confirmPassword" type="password" class="form__input">
                        <c:set var="errorConfirmPassword" value="${requestScope.errorConfirmPassword}" />
                        <c:if test="${errorConfirmPassword != null}">
                            <p class="form__error">${errorConfirmPassword}</p>
                        </c:if>
                    </div>
                    <button id="form__submit" type="submit" class="form__submit button button--hover">Đặt lại mật khẩu
                    </button>
                </form>
            </article>
            <c:set var="updateSuccess" value="${requestScope.updateSuccess}" /> <c:if test="${updateSuccess != null}">
            <div class="modal">
                <div class="modal__notify modal__content">
                    <i class="model__checked-icon fa-regular fa-circle-check"></i>
                    <p class="modal__text">${updateSuccess}</p>
                    <a href="<c:url value="/public/auth/signIn.jsp"/>" class="button modal__button
                            button--hover">Đăng nhập
                    </a>
                </div>
                <label class="modal__blur"></label>
            </div>
        </c:if>
        </div>
    </body>

</html>