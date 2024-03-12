<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="productFactory" class="utils.ProductFactory" scope="session" />
<!DOCTYPE html>
<html lang="en">

    <head>
        <jsp:include page="/public/commonLink.jsp" />
        <link rel="stylesheet" href="<c:url value="/assets/css/account.css" />">
        <title>Tài khoản</title>
    </head>

    <body>
        <c:import url="/public/header.jsp" charEncoding="UTF-8" />
        <main class="main">
            <div class="container-xl">
                <div class="row">
                    <div class="col-3">
                        <div class="service__list">
                            <a class="service__item hvr-shutter-in-vertical" href="<c:url value="/Account" />">Chỉnh sửa
                                                                                                               tài khoản
                            </a>
                            <a class="service__item hvr-shutter-in-vertical" href="<c:url value="/ChangePassword" />">
                                Đổi mật khẩu
                            </a>
                            <a class="service__item service__item--clicked">Lịch sử mua hàng</a>
                        </div>
                    </div>
                    <div class="col-9">
                        <section class="service__section service__section--show">
                            <c:set var="auth" value="${sessionScope.auth}" />
                            <input type="hidden" name="userId" value="<c:out value='${auth.getId()}'/>">
                            <h1 class="title">Lịch sử mua hàng</h1>
                            <div class="statusOrder">
                                <a class="${tag==" TẤT CẢ"?"status__list status__list--click":"status__list"}" href="<c:url value="/PurchaseHistory?status=TẤT CẢ" />">
                                    Tất cả
                                </a>
                                <a class="${tag==" 1"?"status__list status__list--click":"status__list"}" href="<c:url value="/PurchaseHistory?status=1" />">
                                    Chờ xác nhận
                                </a>
                                <a class="${tag==" 2"?"status__list status__list--click":"status__list"}" href="<c:url value="/PurchaseHistory?status=2" />">
                                    Đã xác nhận
                                </a>
                                <a class="${tag==" 3"?"status__list status__list--click":"status__list"}" href="<c:url value="/PurchaseHistory?status=3" />">
                                    Đang vận chuyển
                                </a>
                                <a class="${tag==" 4"?"status__list status__list--click":"status__list"}" href="<c:url value="/PurchaseHistory?status=4" />">
                                    Hoàn thành
                                </a>
                                <a class="${tag==" 5"?"status__list status__list--click":"status__list"}" href="<c:url value="/PurchaseHistory?status=5" />">
                                    Đã hủy
                                </a>
                            </div>
                            <div id="serviceOrderContainer" class="service__order service__order--show">
                                <c:set var="listPurchaseHistory" value="${requestScope.listPurchaseHistory}" />
                                <c:choose> <c:when test="${empty listPurchaseHistory }">
                                    <div class="block__product">
                                        <div class="block__product--history">
                                            <div class="imgNoneProduct"></div>
                                            <h2>Chưa có đơn hàng</h2>
                                        </div>
                                    </div>
                                </c:when> <c:otherwise> <c:forEach items="${listPurchaseHistory}" var="item">
                                    <c:set var="product" value="${productFactory.getProductById(item.productId)}" />
                                    <div class="block__product">
                                        <c:set var="image" value="${productFactory.getListImagesByProductId(product.id).get(0)}" />
                                        <img class="img__product block__img" src="<c:url value="
                                                                /assets/img/product_img/${image.nameImage}" />">
                                        <div class="block__info">
                                            <p class="info__product info__product--name"> ${product.name}</p>
                                            <p class="info__product">Phân
                                                                     loại: ${productFactory.getNameCategoryById(product.categoryId)}
                                            </p>
                                            <p class="info__product">Số lượng: ${item.getQuantityRequired()}</p>
                                            <fmt:formatNumber value="${item.getQuantityRequired() * item.getPrice()}" type="currency" currencyCode="VND" var="price" />
                                            <p class="info__product">Giá: ${price}</p>
                                        </div>
                                        <c:if test="${requestScope.tag eq '4' and requestScope.OrderDetailNotReview != null}">
                                            <c:set var="checkHasReview" value="false" />
                                            <c:forEach items="${requestScope.OrderDetailNotReview}" var="OrderDetailNotReview">
                                                <c:if test="${OrderDetailNotReview.id == item.id}">
                                                    <c:set var="checkHasReview" value="true" /> </c:if> </c:forEach>
                                            <c:if test="${checkHasReview == true}">
                                                <a class="btn" href="<c:url value="review?orderDetailId=${item.id}" />">
                                                    Đánh giá
                                                </a>
                                            </c:if> </c:if>
                                    </div>
                                </c:forEach> </c:otherwise> </c:choose>
                            </div>
                        </section>
                    </div>
                </div>
            </div>
        </main>
        <jsp:include page="/public/footer.jsp" />
    </body>
</html>