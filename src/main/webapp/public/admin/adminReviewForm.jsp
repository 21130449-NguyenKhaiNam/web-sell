<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <!--Các thư viện hỗ trợ-->
    <!--Font Awesome-->
    <link rel="stylesheet" href="assets/fontIcon/fontawesome-free-6.4.2-web/css/all.min.css">
    <!--Bootstrap-->
    <link rel="stylesheet" href="assets/bootstrap/bootstrap-grid.min.css">
    <%--jquery--%>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js"
            integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g=="
            crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <!--CK Editor-->
    <script src="ckeditor/ckeditor.js"></script>
    <!--Ck Finder-->
    <script src="ckfinder/ckfinder.js"></script>
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
    <link rel="stylesheet" href="assets/css/admin/admin.css">
    <link rel="stylesheet" href="assets/css/admin/adminProducts.css">
    <link rel="stylesheet" href="assets/css/admin/adminProductDetail.css">
    <link rel="stylesheet" href="assets/css/review.css">
    <link rel="stylesheet" href="assets/css/admin/adminReviews.css">
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
<script src="js/admin/adminReviewForm.js"></script>
</body>
</html>

