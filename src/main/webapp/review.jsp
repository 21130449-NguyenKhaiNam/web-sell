<%@page contentType  = "text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <!--Các thư viện hỗ trợ-->
    <!--Font Awesome-->
    <link rel="stylesheet" href="assets/bootstrap/bootstrap-grid.min.css">
    <link rel="stylesheet" href="assets/fontIcon/fontawesome-free-6.4.2-web/css/all.min.css">
    <!--Favicon-->
    <link rel="apple-touch-icon" sizes="180x180" href="assets/favicon/apple-touch-icon.png">
    <link rel="icon" type="image/png" sizes="32x32" href="assets/favicon/favicon-32x32.png">
    <link rel="icon" type="image/png" sizes="16x16" href="assets/favicon/favicon-16x16.png">
    <link rel="manifest" href="assets/favicon/site.webmanifest">
    <!--Web font-->
    <link rel="stylesheet" href="assets/font/webfonts/Montserrat.css">
    <!--CSS-->
    <link rel="stylesheet" href="assets/css/reset.css">
    <link rel="stylesheet" href="assets/css/base.css">
    <link rel="stylesheet" href="assets/css/review.css">
    <title>Đánh giá</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<main>
    <div class="container">
        <section class="review">
            <h1>Đánh giá sản phẩm</h1>
            <div class="row">
                <div class="col-6">
                    <article class="product">
                        <div class="product__img">
                            <img src="assets/img/product_img/product21.jpg" alt="">
                        </div>
                        <div class="product__info">
                            <h2 class="product__name"></h2>
                            <p class="product__category">Phân loại: </p>
                            <p class="product__color">Màu
                                <span class="color__code"></span>
                            </p>
                            <p class="product__size">Kích thước:
                                <span class="product__size--default"></span></p>
                            <ul class="product__size--custom">
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
                            <p class="product__quantity">
                                Số lượng:
                            </p>
                        </div>
                    </article>
                </div>
                <div class="col-6">
                    <form class="review__form">
                        <!--                        <h2 class="review__name">-->
                        <!--                            &lt;!&ndash;User info: name&ndash;&gt;Lê Anh Đức-->
                        <!--                        </h2>-->

                        <!--Rating-->
                        <div class="review__rating">
                            <p>Chất lượng sản phẩm</p>
                            <ul class="review__stars">
                                <li class="review__star review__star--choose"></li>
                                <li class="review__star review__star--choose"></li>
                                <li class="review__star review__star--choose"></li>
                                <li class="review__star review__star--choose"></li>
                                <li class="review__star review__star--choose"></li>
                            </ul>
                            <p>Tệ</p>
                        </div>

                        <!--Desc-->
                        <label class="review__desc">
                            Đánh giá
                            <textarea></textarea>
                        </label>
                        <button class="review__submit button button--hover">
                            Hoàn thành
                        </button>
                    </form>
                </div>
            </div>
        </section>
    </div>
</main>
<%@include file="header.jsp"%>
<script src="js/base.js"></script>
<script src="js/data.js"></script>
<script src="js/review.js"></script>
</body>
</html>