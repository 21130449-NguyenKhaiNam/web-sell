<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="models.User" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:useBean id="shoppingCart" class="models.ShoppingCart" scope="session"/>
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
                        <!--Account show (After log in success)-->
                        <div class="account__wrapper">
                            <!--Giỏ hàng-->
                            <c:if test="${auth.role == false}">
                                <a href="shoppingCart.jsp" class="cart">
                                    <span class="cart__content"><i class="cart__icon fa-solid fa-cart-shopping"></i> Giỏ hàng</span>
                                    <span class="qlt__swapper">
                                        <c:if test="${sessionScope.cart == null}">
                                            <jsp:useBean id="cart" class="models.ShoppingCart" scope="session" />
                                        </c:if>
                                        <span class="qlt__value">${shoppingCart.getTotalItems()}</span>
                                    </span>
                                </a>
                            </c:if>
                            <div class="account">
                                <i class="account__icon fa-regular fa-user"></i>
                                <div class="setting__list">
                                    <div class="setting__item"><a href="#!" class="setting__link">
                                        <div class="account__info">
                                            <i class="account__icon fa-regular fa-user"></i>
                                            <p class="account__name">
                                                <c:out value="${auth.getUsername()}"/>
                                            </p>
                                        </div>
                                    </a></div>
                                    <div class="setting__item"><a href="account.jsp" class="setting__link">Tài
                                        khoản
                                        của
                                        tôi</a>
                                    </div>
                                    <div class="setting__item "><a href="signOut" class="setting__link setting__logOut">Đăng
                                        xuất</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </nav>
</header>