<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="java.util.Calendar" %>

<%@ page import="models.User" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <jsp:include page="/public/commonLink.jsp" />
        <link rel="stylesheet" href="<c:url value="/assets/css/logIn.css" />">
        <link rel="stylesheet" href="<c:url value="/assets/css/account.css" />">
        <title>Tài khoản</title>
    </head>

    <body>
        <c:import url="/public/header.jsp" />
        <main class="main">
            <div class="container-xl">
                <div class="row">
                    <div class="col-3">
                        <div class="service__list">
                            <a class="service__item hvr-shutter-in-vertical" href="<c:url value="/Account" />">Chỉnh sửa
                                tài khoảng
                            </a>
                            <a class="service__item service__item--clicked">Đổi mật khẩu</a>
                            <a class="service__item hvr-shutter-in-vertical" href="<c:url value="/PurchaseHistory" />">
                                Lịch sử mua hàng
                            </a>
                        </div>
                    </div>
                    <div class="col-9">
                        <section class="service__section service__section--show">
                            <c:set var="auth" value="${sessionScope.auth}" />
                            <c:set var="errorOlddPass" value="${requestScope.errorOlddPass}" />
                            <c:set var="errorNewPass" value="${requestScope.errorNewPass}" />
                            <c:set var="errorConfirmPass" value="${requestScope.errorConfirmPass}" />
                            <form action="<c:url value="/ChangePassword" />" method="post">

                                <h1 class="title">Đổi mật khẩu</h1>

                                <input type="hidden" name="userId" value=${auth.getId()}>

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
                                        <label class="lable__newPass--confirm lable-compo" for="confirm-password">Nhập
                                            lại mật khẩu mới</label>
                                        <div class="input__form">
                                            <input type="password" id="confirm-password" name="confirmPassword" class="input__newPass--confirm input-compo">
                                            <i class=" icon__eye icon__eye--close fa-regular fa-eye-slash"></i>
                                            <i class="icon__eye icon__eye--open fa-regular fa-eye"></i>
                                        </div>
                                        <c:if test="${errorConfirmPass != null}">
                                            <p class="form__error">${errorConfirmPass}</p>
                                        </c:if>
                                    </div>
                                    <div class="save save__changePass">
                                        <button id="form__submit" type="submit" class=" form__submit button button--hover">
                                            Lưu thay đổi
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </section>
                    </div>
                </div>
            </div>
        </main>
        <c:import url="${initParam.contextPath}/public/footer.jsp" />
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