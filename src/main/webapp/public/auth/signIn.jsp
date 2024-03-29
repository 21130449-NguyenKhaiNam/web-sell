<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <jsp:include page="/public/commonLink.jsp" />
        <link rel="stylesheet" href="<c:url value="/assets/css/logIn.css"/>">
        <title>Đăng nhập</title>
    </head>

    <body>
        <main class="main">
            <div class="frame">
                <div class="frame__media"></div>

                <article>
                        <span class="text-cetner mb-3 d-flex justify-content-center hvr-bob">
                            <a href="${initParam.contextPath}/public/index.jsp" class="logo"></a>
                        </span>
                    <form action="<c:url value="/signIn"/>" class="form form--signUp" method="post">
                        <div class="form__block">
                            <label for="username" class="form__label">Tên đăng nhập</label>
                            <input id="username" name="username" type="text" class="form__input">
                            <c:set value="${requestScope.usernameError}" var="usernameError" />
                            <p class="form__error">
                                <c:if test="${usernameError != null}">${usernameError}</c:if>
                            </p>
                        </div>
                        <div class="form__block">
                            <label for="password" class="form__label">Mật khẩu</label>
                            <input id="password" name="password" type="password" class="form__input">
                            <c:set value="${requestScope.passwordError}" var="passwordError" />
                            <p class="form__error">
                                <c:if test="${passwordError != null}">${passwordError}</c:if>
                            </p>
                        </div>
                        <div class="form__block">
                            <a href="<c:url value ="/public/auth/forgotPassword.jsp"/>" id="form__forget-password" class="form__link">
                                Quên mật khẩu
                            </a>
                        </div>
                        <button id="form__submit" type="submit" class="form__submit button button--hover">Đăng nhập
                        </button>
                    </form>
                    <a href="<c:url value="/public/auth/signUp.jsp"/>" id="form__link--signUp" class="form__link
                        hvr-float-shadow">Đăng ký
                    </a>
                </article>
            </div>
        </main>
        <!--JS validate-->
        <script src="<c:url value="/js/validateForm.js"/>"></script>
        <script>
            var validation = new Validation({
                formSelector: ".form",
                formBlockClass: "form__block",
                errorSelector: ".form__error",
                rules: [
                    Validation.isRequired("#username"),
                    Validation.isRequired("#password"),
                ],
                submitSelector: "#form__submit",
            })
        </script>
    </body>

</html>