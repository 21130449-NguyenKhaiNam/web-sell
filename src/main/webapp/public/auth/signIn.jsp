<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <jsp:include page="/public/commonLink.jsp" />
        <link rel="stylesheet" href="<c:url value="/assets/css/logIn.css" />">
        <title>Đăng nhập</title>
    </head>
    <body>
        <div id="fb-root"></div>
        <script async defer crossorigin="anonymous" src="https://connect.facebook.net/en_GB/sdk.js#xfbml=1&version=v19.0&appId=797814205366191" nonce="rWVJged8"></script>
        <main class="main">
            <div class="frame">
                <div class="frame__media">
                </div>
                <article>
                    <form action="<c:url value="/signIn" />" class="form form--signUp" method="post">
                        <div class="form__block">
                            <label for="username" class="form__label">Tên đăng nhập</label>
                            <input id="username" name="username" type="text" class="form__input" value="">
                            <c:set value="${requestScope.usernameError}" var="usernameError" />
                            <c:if test="${requestScope.spam != null}">
                                ${requestScope.spam}
                            </c:if>
                            <p class="form__error"><c:if test="${usernameError != null}">${usernameError}</c:if></p>
                        </div>
                        <div class="form__block">
                            <label for="password" class="form__label">Mật khẩu</label>
                            <input id="password" name="password" type="password" class="form__input">
                            <c:set value="${requestScope.passwordError}" var="passwordError" />
                            <p class="form__error"><c:if test="${passwordError != null}">${passwordError}</c:if></p>
                        </div>
                        <div class="form__block">
                            <a href="<c:url value ="/public/auth/forgotPassword.jsp"/>" id="form__forget-password" class="form__link">
                                Quên mật khẩu
                            </a>
                        </div>
                        <p style="color: red">${requestScope.error}</p>
                        <button id="form__submit" type="submit" class="form__submit button button--hover">Đăng nhập
                        </button>
                    </form>
                    <a href="<c:url value="/public/auth/signUp.jsp" />" id="form__link--signUp" class="form__link ">Đăng
                                                                                                                    ký
                    </a>
                    <c:url var="google" value="https://accounts.google.com/o/oauth2/auth">
                        <c:param name="scope" value="email" />
                        <c:param name="redirect_uri" value="http://localhost:8080/signInGoogle" />
                        <c:param name="response_type" value="code" />
                        <c:param name="client_id" value="336186921669-k0vb5vt05phajcah47psl3v0t4h3eah0.apps.googleusercontent.com" />
                        <c:param name="approval_prompt" value="force" /> </c:url>
                    <a class="btn btn-primary" href="${google}">Đăng nhập với Google</a>
                    <c:url var="facebook" value="https://www.facebook.com/v19.0/dialog/oauth">
                        <c:param name="scope" value="email" />
                        <c:param name="redirect_uri" value="http://localhost:8080/signInFacebook" />
                        <c:param name="client_id" value="2825100177629702" /> </c:url>
                    <a class="btn btn-primary" href="${facebook}">Đăng nhập với Facebook</a>
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