<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="models.User" %>
<%@ page import="models.UserSessionAccess" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!--Header-->
<header id="header">
    <nav class="nav">
        <div class="container-xl">
            <div class="nav__inner">
                <a href="index.jsp" class="logo">
                </a>
                <ul class="nav__list">
                    <li class="nav__item">
                        <a href="index.jsp" class="nav__link">Trang chủ</a>
                    </li>
                    <li class="nav__item">
                        <a href="productBuying.jsp" class="nav__link">Gian hàng</a>
                    </li>
                    <li class="nav__item">
                        <a href="contact.jsp" class="nav__link">Liên hệ</a>
                    </li>
                    <li class="nav__item">
                        <a href="about.jsp" class="nav__link">Về chúng tôi</a>
                    </li>
                </ul>

<%--                <c:forEach items="${sessionScope}" var="sessionAttribute">--%>
<%--&lt;%&ndash;                    ${sessionAttribute.value['class'].simpleName}&ndash;%&gt;--%>
<%--                    <c:if test="${sessionAttribute.value.getClass().simpleName eq 'User'}">--%>
<%--                        <c:if test="${sessionAttribute.key eq UserSessionAccess.getINSTANCE().getUserSessionId()}">--%>
<%--                            <c:set var="auth" value="${sessionAttribute.value}"/>--%>
<%--                        </c:if>--%>
<%--                    </c:if>--%>
<%--                </c:forEach>--%>
                <c:set var="auth" value="${sessionScope.auth}"/>
                <c:choose>
                    <c:when test="${auth == null}">
                        <!--cta == call to action-->
                        <div class="nav__cta">
                            <a href="signIn.jsp" class="nav__button nav__button--signIn">Đăng nhập</a>
                            <a href="signUp.jsp"
                               class="nav__button nav__button--signUp button button button--hover">Đăng ký</a>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <% System.out.println(session.getId()); %>
                        <!--Account show (After log in success)-->
                        <div class="account__wrapper">
                            <!--Giỏ hàng-->

                                <div class="cart__wrapper">
                                    <a href="shoppingCart.jsp" class="cart">
                                        <span class="cart__content"><i class="cart__icon fa-solid fa-cart-shopping"></i> Giỏ hàng</span>
                                        <span class="qlt__swapper">
                                            <span class="qlt__value">
                                                <c:set var="userIdCart" value="${String.valueOf(auth.id)}"/>
                                                <c:choose>
                                                    <c:when test="${sessionScope[userIdCart] == null}">0</c:when>
                                                    <c:otherwise>${sessionScope[userIdCart].getTotalItems()}</c:otherwise>
                                                </c:choose>
                                            </span>
                                        </span>
                                    </a>
                                </div>

                            <div class="account">
                                <i class="account__icon fa-regular fa-user"></i>
                                <div class="setting__list">
                                    <a href="Account" class="setting__item">
                                        <div class="setting__link">
                                            <div class="account__info">
                                                <i class="account__icon fa-regular fa-user"></i>
                                                <p class="account__name">
                                                    <c:out value="${auth.getUsername()}"/>
                                                </p>
                                            </div>
                                        </div>
                                    </a>
                                    <a href="Account" class="setting__item">
                                        <div class="setting__link">Tài khoản của tôi
                                        </div>
                                    </a>
                                    <c:if test="${auth.role == 2 || auth.role == 1}">
                                    <a href="adminProducts.jsp" class="setting__item">
                                        <div class="setting__link">Quản
                                                                   lý
                                        </div>
                                    </c:if>
                                        <a href="signOut" class="setting__item ">
                                            <div class="setting__link setting__logOut">Đăng
                                                                                       xuất
                                            </div>
                                        </a>
                                </div>
                            </div>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </nav>
</header>