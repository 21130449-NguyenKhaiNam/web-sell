<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="productFactory" class="utils.ProductFactory" scope="session"/>
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
    <link rel="stylesheet" href="assets/bootstrap/bootstrap-grid.min.css">
    <!--Favicon-->
    <link rel="apple-touch-icon" sizes="180x180" href="assets/favicon/apple-touch-icon.png">
    <link rel="icon" type="image/png" sizes="32x32" href="assets/favicon/favicon-32x32.png">
    <link rel="icon" type="image/png" sizes="16x16" href="assets/favicon/favicon-16x16.png">
    <link rel="manifest" href="assets/favicon/site.webmanifest">
    <!--Web font-->
    <link rel="stylesheet" href="assets/font/webfonts/Montserrat.css">

    <link rel="stylesheet" href="assets/bootstrap/bootstrap-grid.min.css">
    <link rel="stylesheet" href="assets/fontIcon/fontawesome-free-6.4.2-web/css/all.min.css">
    <link rel="manifest" href="assets/favicon/site.webmanifest">
    <link rel="stylesheet" href="assets/font/webfonts/Montserrat.css">
    <link rel="stylesheet" href="assets/css/reset.css">
    <link rel="stylesheet" href="assets/css/base.css">

    <link rel="stylesheet" href="assets/css/account.css">
    <title>Tài khoản</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<main class="main">
    <div class="container-xl">
        <div class="row">
            <div class="col-3">
                <div class="service__list">
                    <a class="service__item" href="Account">Chỉnh sửa tài khoản</a>
                    <a class="service__item" href="ChangePassword">Đổi mật khẩu</a>
                    <a class="service__item service__item--clicked">Lịch sử mua hàng</a>
                </div>
            </div>
            <div class="col-9">
                <section class="service__section service__section--show">
                    <c:set var="auth" value="${sessionScope.auth}"/>
                    <input type="hidden" name="userId" value="<c:out value='${auth.getId()}'/>">
                    <h1 class="title">Lịch sử mua hàng</h1>
                    <div class="statusOrder">
                        <a class="${tag=="TẤT CẢ"?"status__list status__list--click":"status__list"}"
                           href="PurchaseHistory?status=TẤT CẢ">Tất cả</a>
                        <a class="${tag=="ĐƠN HÀNG MỚI"?"status__list status__list--click":"status__list"}"
                           href="PurchaseHistory?status=ĐƠN HÀNG MỚI">Đơn hàng mới</a>
                        <a class="${tag=="ĐÃ XÁC NHẬN"?"status__list status__list--click":"status__list"}"
                           href="PurchaseHistory?status=ĐÃ XÁC NHẬN">Đã xác nhận</a>
                        <a class="${tag=="ĐANG VẬN CHUYỂN"?"status__list status__list--click":"status__list"}"
                           href="PurchaseHistory?status=ĐANG VẬN CHUYỂN">Đang vận chuyển</a>
                        <a class="${tag=="HOÀN THÀNH"?"status__list status__list--click":"status__list"}"
                           href="PurchaseHistory?status=HOÀN THÀNH">Hoàn thành</a>
                        <a class="${tag=="ĐÃ HỦY"?"status__list status__list--click":"status__list"}"
                           href="PurchaseHistory?status=ĐÃ HỦY">Đã hủy</a>
                    </div>
                    <div id="serviceOrderContainer" class="service__order service__order--show">
                        <c:set var="listPurchaseHistory" value="${requestScope.listPurchaseHistory}"/>
                        <c:choose>
                            <c:when test="${empty listPurchaseHistory }">
                                <div class="block__product">
                                    <div class="block__product--history">
                                        <div class="imgNoneProduct"></div>
                                        <h2>Chưa có đơn hàng</h2>
                                    </div>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <c:forEach items="${listPurchaseHistory}" var="item">
                                    <c:set var="product" value="${productFactory.getProductById(item.productId)}"/>
                                    <div class="block__product">
                                        <c:set var="image"
                                               value="${productFactory.getListImagesByProductId(product.id).get(0)}"/>
                                        <img class="img__product block__img"
                                             src="./assets/img/product_img/${image.nameImage}">
                                        <div class="block__info">
                                            <p class="info__product info__product--name">${product.name}</p>
                                            <p class="info__product">Phân
                                                loại: ${productFactory.getNameCategoryById(product.categoryId)}</p>
                                            <p class="info__product">Số lượng: ${item.getQuantityRequired()}</p>
                                            <fmt:formatNumber value="${item.getQuantityRequired() * item.getPrice()}"
                                                              type="currency" currencyCode="VND" var="price"/>
                                            <p class="info__product">Giá: ${price}</p>
                                        </div>
                                        <c:if test="${requestScope.tag eq 'HOÀN THÀNH' and requestScope.OrderDetailNotReview != null}">
                                            <c:set var="checkHasReview" value="false"/>
                                            <c:forEach items="${requestScope.OrderDetailNotReview}"
                                                       var="OrderDetailNotReview">
                                                <c:if test="${OrderDetailNotReview.id == item.id}">
                                                    <c:set var="checkHasReview" value="true"/>
                                                </c:if>
                                            </c:forEach>
                                            <c:if test="${checkHasReview == true}">
                                                <button class="btn">
                                                    <a href="review?orderDetailId=${item.id}"> Đánh giá</a>
                                                </button>
                                            </c:if>
                                        </c:if>
                                    </div>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </section>
            </div>
        </div>
    </div>
</main>
<%@include file="footer.jsp" %>

</body>


</html>