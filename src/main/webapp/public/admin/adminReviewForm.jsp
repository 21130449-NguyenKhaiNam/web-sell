<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!doctype html>
<html lang="en">
<head>
    <jsp:include page="/public/commonLink.jsp"/>
    <!--CK Editor-->
    <script src="<c:url value="/ckeditor/ckeditor.js"/>"></script>
    <!--Ck Finder-->
    <script src="<c:url value="/ckfinder/ckfinder.js"/>"></script>
    <link rel="stylesheet" href="<c:url value="/assets/css/admin/admin.css"/>">
    <link rel="stylesheet" href="<c:url value="/assets/css/admin/adminProducts.css"/>">
    <link rel="stylesheet" href="<c:url value="/assets/css/admin/adminProductDetail.css"/>">
    <link rel="stylesheet" href="<c:url value="/assets/css/review.css" />">
    <link rel="stylesheet" href="<c:url value="/assets/css/admin/adminReviews.css"/>">
    <title></title>
</head>
<body>
<main>
    <div class="container">
        <section class="review">
            <div class="row">
                <div class="col-6">
                    <article class="product">
                        <div class="product__img">
                            <img id="image" src=""
                                 alt="">
                        </div>
                        <div class="product__info">
                            <h2 class="product__name" id="name"></h2>
                            <p class="product__category">Phân loại: <span id="category"></span></p>

                            <p class="product__color">Màu
                                <span class="color__code" id="color"></span>
                            </p>
                            <div id="size"></div>
                            <p class="product__quantity">
                                Số lượng: <span id="quantity"></span>
                            </p>
                        </div>
                    </article>
                </div>
                <div class="col-6">
                    <div class="review__form">
                        <!--Rating-->
                        <div class="form__block">
                            <div class="review__rating">
                                <p>Chất lượng sản phẩm</p>
                                <ul class="review__stars">
                                </ul>
                            </div>
                        </div>

                        <!--Desc-->
                        <div class="form__block">
                            <label class="review__desc">
                                Đánh giá
                                <textarea readonly name="desc" id="feedback"></textarea>
                            </label>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </div>
</main>
<script src="<c:url value="/js/admin/adminReviewForm.js"/>"></script>
</body>
</html>

