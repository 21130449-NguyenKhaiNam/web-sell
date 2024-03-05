<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<jsp:useBean id="productFactory" class="utils.ProductFactory" scope="session"/>
<c:set var="productId" value="${requestScope.productId}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <!--Các thư viện hỗ trợ-->
    <!--Font Awesome-->
    <link rel="stylesheet" href="../../assets/bootstrap/bootstrap-grid.min.css">
    <link rel="stylesheet" href="assets/fontIcon/fontawesome-free-6.4.2-web/css/all.min.css">
    <!--Favicon-->
    <link rel="apple-touch-icon" sizes="180x180" href="../../assets/favicon/apple-touch-icon.png">
    <link rel="icon" type="image/png" sizes="32x32" href="../../assets/favicon/favicon-32x32.png">
    <link rel="icon" type="image/png" sizes="16x16" href="../../assets/favicon/favicon-16x16.png">
    <link rel="manifest" href="../../assets/favicon/site.webmanifest">
    <!--Web font-->
    <link rel="stylesheet" href="../../assets/font/webfonts/Montserrat.css">
    <!--CSS-->
    <link rel="stylesheet" href="../../assets/css/reset.css">
    <link rel="stylesheet" href="../../assets/css/base.css">
    <link rel="stylesheet" href="../../assets/css/review.css">
    <title>Đánh giá</title>
</head>
<body>
<jsp:include page="public/header/header.jsp"></jsp:include>
<main>
    <div class="container">
        <section class="review">
            <h1>Đánh giá sản phẩm</h1>
            <div class="row">
                <div class="col-6">
                    <article class="product">
                        <div class="product__img">
                            <img src="assets/img/product_img/${productFactory.getListImagesByProductId(productId).get(0).nameImage}"
                                 alt="${productFactory.getListImagesByProductId(productId).get(0).nameImage}">
                        </div>
                        <div class="product__info">
                            <h2 class="product__name">${requestScope.nameProduct}</h2>
                            <p class="product__category">Phân loại: ${productFactory.getNameCategoryById(productId)}</p>
                            <c:set var="color" value="${requestScope.color}"/>
                            <p class="product__color">Màu
                                <span class="color__code" style="background-color: ${color}"></span>
                            </p>
                            <c:set var="sizes" value="${requestScope.sizes}"/>
                            <c:choose>
                                <c:when test="${fn:length(sizes) == 1}">
                                    <p class="product__size">Kích thước:
                                        <c:forEach var="size" items="${sizes}">
                                            <span class="product__size--default">${size}</span>
                                        </c:forEach>
                                    </p>
                                </c:when>
                                <c:otherwise>
                                    <c:set var="listParameter" value="${requestScope.listParameter}"/>
                                    <ul class="product__size--custom">
                                        Kích thước:
                                        <c:forEach var="size" items="${sizes}">
                                            <li>${size}
                                            </li>
                                        </c:forEach>
                                        <!--                                <li>-->
                                        <!--                                    Dài áo:-->
                                        <!--                                </li>-->
                                        <!--                                <li>-->
                                        <!--                                    Ngang ngực:-->
                                        <!--                                </li>-->
                                        <!--                                <li>-->
                                        <!--                                    Dài tay:-->
                                        <!--                                </li>-->
                                        <!--                                <li>-->
                                        <!--                                    Rộng vai:-->
                                        <!--                                </li>-->
                                    </ul>
                                </c:otherwise>
                            </c:choose>

                            <p class="product__quantity">
                                Số lượng: ${requestScope.quantity}
                            </p>
                        </div>
                    </article>
                </div>
                <div class="col-6">
                    <form class="review__form" method="post" action="createReview">
                        <input type="text" hidden="hidden" readonly name="orderProductId"
                               value="${requestScope.orderDetailId}">
                        <!--Rating-->
                        <div class="form__block">
                            <div class="review__rating">
                                <p>Chất lượng sản phẩm</p>
                                <ul class="review__stars">
                                    <c:forEach varStatus="loop" begin="1" end="5">
                                        <c:choose>
                                            <c:when test="${loop.index ==1}">
                                                <label class="review__star review__star--choose">
                                                    <input type="radio" name="ratingStar" checked hidden="hidden"
                                                           value="${loop.index}">
                                                </label>
                                            </c:when>
                                            <c:otherwise>
                                                <label class="review__star ">
                                                    <input type="radio" name="ratingStar" hidden="hidden"
                                                           value="${loop.index}">
                                                </label>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </ul>
                                <p>Tệ</p>
                            </div>
                        </div>

                        <!--Desc-->
                        <div class="form__block">
                            <label class="review__desc">
                                Đánh giá
                                <textarea name="desc"></textarea>
                            </label>
                            <p class="form__error"></p>
                        </div>
                        <button class="review__submit button button--hover">
                            Hoàn thành
                        </button>
                    </form>
                </div>
            </div>
        </section>
    </div>
</main>

<script src="../../js/base.js"></script>
<script src="../../js/validateForm.js"></script>
<script src="../../js/review.js"></script>
</body>
</html>