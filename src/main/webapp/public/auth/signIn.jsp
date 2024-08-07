<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <jsp:include page="/public/commonLink.jsp"/>
    <link rel="stylesheet" href="<c:url value="/assets/css/logIn.css"/>">
    <script src="https://www.google.com/recaptcha/api.js?render=6LdSQu4pAAAAAJ0LbzMt-kVSfKqzVZYQtmPo3mHD"></script>
    <title>Đăng nhập</title>
</head>

<body>
<main class="main">
    <div class="frame">
        <div class="frame__media"></div>
        <article>
            <span class="text-center mb-3 d-flex justify-content-center hvr-bob">
                <a href="${initParam.contextPath}/public/index.jsp" class="logo"></a>
            </span>
            <form action="<c:url value="/signIn"/>" class="form form--signUp" method="post">
                <div class="form__block">
                    <label for="username" class="form__label">Tên đăng nhập</label>
                    <input id="username" name="username" type="text" class="form__input p-2">
                    <c:set value="${requestScope.usernameError}" var="usernameError"/>
                    <p class="form__error">
                        <c:if test="${usernameError != null}">${usernameError}</c:if>
                    </p>
                </div>
                <div class="form__block">
                    <label for="password" class="form__label">Mật khẩu</label>
                    <input id="password" name="password" type="password" class="form__input p-2">
                    <c:set value="${requestScope.passwordError}" var="passwordError"/>
                    <p class="form__error">
                        <c:if test="${passwordError != null}">${passwordError}</c:if>
                    </p>
                </div>
                <div class="form__block hvr-forward">
                    <a href="<c:url value ="/public/auth/forgotPassword.jsp"/>" id="form__forget-password"
                       class="form__link">
                        Quên mật khẩu
                    </a>
                </div>
                <input type="hidden" id="recaptchaToken" name="g-recaptcha-response">
<%--                <c:set var="errorReCaptcha" value="${requestScope.errorReCaptcha}"/>--%>
<%--                <c:if test="${param.errorReCaptcha eq 'true'}">${errorReCaptcha}--%>
<%--                    <p class="alert alert-danger" role="alert">Mã recaptcha không hợp lệ, vui lòng thử đăng nhập--%>
<%--                        lại </p>--%>
<%--                </c:if>--%>
                <c:set var="errorReCaptcha" value="${requestScope.errorReCaptcha}"/>
                <c:if test="${errorReCaptcha eq 'true'}">
                    <script>
                        Swal.fire({
                            title: "Khoan đã",
                            text: "Liệu bạn có phải là con người!",
                            icon: "info"
                        });
                    </script>
                </c:if>
                <button id="form__submit" type="submit" class="form__submit button button--hover">Đăng nhập
                </button>
                <c:url var="google" value="https://accounts.google.com/o/oauth2/auth">
                    <c:param name="scope" value="email"/>
                    <c:param name="redirect_uri" value="http://localhost:8080/signInGoogle"/>
                    <c:param name="response_type" value="code"/>
                    <c:param name="client_id"
                             value="336186921669-k0vb5vt05phajcah47psl3v0t4h3eah0.apps.googleusercontent.com"/>
                    <c:param name="approval_prompt" value="force"/> </c:url>

                <div class="d-flex justify-content-around mt-4">
                    <a class="btn btn-primary" data-btn-style="google" href="${google}">Đăng nhập với Google</a>
                    <c:url var="facebook" value="https://www.facebook.com/v19.0/dialog/oauth">
                        <c:param name="scope" value="email"/>
                        <c:param name="redirect_uri" value="http://localhost:8080/signInFacebook"/>
                        <c:param name="client_id" value="2825100177629702"/> </c:url>
                    <a class="btn btn-primary" data-btn-style="facebook" href="${facebook}">Đăng nhập với Facebook</a>
                </div>
            </form>
            <a href="<c:url value="/public/auth/signUp.jsp"/>" id="form__link--signUp" class="form__link
                        hvr-float-shadow p-2">Đăng ký
            </a>

        </article>
    </div>
</main>
<!--JS validate-->
<script src="<c:url value="/js/validateForm.js"/>"></script>
<script>
    // Check nhập không phù hợp
    $("input.form__input").on({
        keydown: function (e) {
            if (e.which === 32)
                return false;
        },
        change: function () {
            this.value = this.value.replace(/\s/g, "");
        }
    });
    grecaptcha.ready(function () {
        grecaptcha.execute('6LdSQu4pAAAAAJ0LbzMt-kVSfKqzVZYQtmPo3mHD', {action: 'submit'}).then(function (token) {
            document.getElementById('recaptchaToken').value = token;
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
        });
    });

</script>
</body>
</html>