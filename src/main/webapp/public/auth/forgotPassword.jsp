<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">


<head>
    <jsp:include page="/public/commonLink.jsp"/>
    <link rel="stylesheet" href="<c:url value="/assets/css/logIn.css" />">
    <link rel="stylesheet" href="<c:url value="/assets/css/forgetPassword.css" />">
    <title>Quên mật khẩu</title>
</head>

<body>
<main class="main">
    <div class="frame">
        <article>
            <span class="text-center mb-3 d-flex justify-content-center hvr-bob">
                <a href="${initParam.contextPath}/public/index.jsp" class="logo"></a>
            </span>
            <form action="<c:url value="/forgetPassword" />" class="form form--forget-password mb-5" method="post">
                <h1 class="heading">Vui lòng nhập email của bạn để lấy lại mật khẩu</h1>
                <div class="form__block">
                    <label for="email" class="form__label">Email</label>
                    <input id="email" name="email" type="email" class="form__input p-2">
                    <c:set value="${requestScope.emailError}" var="emailError"/>
                    <p class="form__error">
                        <c:choose>
                            <c:when test="${emailError != null}">
                                ${emailError}
                            </c:when>
                            <c:otherwise>
                                <c:set var="sendMail" value="${requestScope.sendMail}"/>
                                <c:if test="${sendMail != null}">
                                    ${sendMail}
                                </c:if>
                            </c:otherwise>
                        </c:choose>
                    </p>
                    <%--                    <p class="form__error">--%>
                    <%--                        <c:if test="${emailError != null}">${emailError}</c:if>--%>
                    <%--                    </p>--%>
                    <%--                    <c:if test="${sendMail != null}">--%>
                    <%--                        <p class="form__error">${sendMail}</p>--%>
                    <%--                    </c:if>--%>
                </div>
                <button id="form__submit" type="submit" class="form__submit button button--hover">
                    Lấy lại mật khẩu
                </button>
            </form>
            <a href="signIn.jsp" id="form__link--signIn" class="form__link hvr-float-shadow">Đăng nhập</a>
        </article>
        <input type="checkbox" id="modal__hide" class="modal__hide" hidden="hidden" checked>
        <div class="modal">
            <div class="modal__notify modal__content">
                <i class="model__checked-icon fa-regular fa-circle-check"></i>
                <p class="modal__text">Vui lòng kiểm tra email bạn đã đăng kí.</p>
                <a href="signIn.jsp" class="button modal__button button--hover">Đăng nhập</a>
                <p class="modal__resend ">Nếu bạn chưa nhận được email xác nhận, hãy <span>nhấn vào
                                    đây.</span></p>
            </div>
            <label for="modal__hide" class="modal__blur"></label>
        </div>
    </div>
</main>
<!--JS validate-->
<script src="<c:url value="/js/validateForm.js"/>"></script>
<script>
    $(document).ready(() => {
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

        var validation = new Validation({
            formSelector: ".form",
            formBlockClass: "form__block",
            errorSelector: ".form__error",
            rules: [
                Validation.isRequired("#email"),
                Validation.isEmail("#email")
            ],
            submitSelector: "#form__submit",
        })
    })
</script>
</body>

</html>