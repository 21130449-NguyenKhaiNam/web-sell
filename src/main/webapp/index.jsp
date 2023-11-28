<%@ page import="models.User" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <!--Các thư viện hỗ trợ-->
    <!--Font Awesome-->
    <link rel="stylesheet" href="./assets/fontIcon/fontawesome-free-6.4.2-web/css/all.min.css">

    <!--Bootstrap-->
    <link rel="stylesheet" href="./assets/bootstrap/bootstrap-grid.min.css">
    <!--Favicon-->
    <link rel="apple-touch-icon" sizes="180x180" href="./assets/favicon/apple-touch-icon.png">
    <link rel="icon" type="image/png" sizes="32x32" href="./assets/favicon/favicon-32x32.png">
    <link rel="icon" type="image/png" sizes="16x16" href="./assets/favicon/favicon-16x16.png">
    <link rel="manifest" href="./assets/favicon/site.webmanifest">
    <!--Web font-->

    <link rel="stylesheet" href="./assets/font/webfonts/Montserrat.css">

    <!--CSS-->
    <link rel="stylesheet" href="./assets/css/reset.css">
    <link rel="stylesheet" href="./assets/css/base.css">
    <link rel="stylesheet" href="./assets/css/home.css">

    <title>Trang chủ</title>
</head>
<body>
<!--Header-->
<jsp:include page="header.jsp"></jsp:include>
<!--Main: chứa nội dung chính, các section như giới thiệu sản phầm, các cổ đông,...-->
<main id="main">
    <!--Hero-->
    <div class="hero">
        <img src="./assets/img/hero__img.png" alt="" class="hero__img">
        <div class="hero__slogan">
            <h1>Change Your Styles Now</h1>
            <p>Cùng chúng tôi tạo nên thiết kế khác biệt cho quần áo của bạn</p>
            <a href="productBuying.jsp" class="hero__button button button--hover">Bắt đầu đặt may</a>
        </div>
    </div>

    <div class="container-xl">
        <div id="slider__category--section">
            <div class="slider__container">
                <div class="slider__items">
                    <img class="slider__item" src="./assets/img/slider/T-Shirt.png">
                    <img class="slider__item" src="./assets/img/slider/Hoodie.png">
                    <img class="slider__item" src="./assets/img/slider/Vest.png">
                    <img class="slider__item" src="./assets/img/slider/Pants.png">
                </div>
                <div class="navigation__button nav__prev">
                    <i class="fa-solid fa-chevron-left"></i>
                </div>

                <div class="navigation__button nav__next">
                    <i class="fa-solid fa-chevron-right"></i>
                </div>

                <div class="carousel__indicators">
                    <div class="indicator active"></div>
                    <div class="indicator"></div>
                    <div class="indicator"></div>
                    <div class="indicator"></div>
                </div>
            </div>
            <div class="category__container category__items">
                <div class="category__item">
                    <p class="item__text">Áo thun</p>
                    <img class="item__image" src="./assets/img/category/T-shirt.png">
                </div>
                <div class="category__item">
                    <p class="item__text">Áo hoodie</p>
                    <img class="item__image" src="./assets/img/category/hoodie.png">
                </div>
                <div class="category__item">
                    <p class="item__text">Balo</p>
                    <img class="item__image" src="./assets/img/category/backpack.png">
                </div>
                <div class="category__item">
                    <p class="item__text">Quần dài</p>
                    <img class="item__image" src="./assets/img/category/pants.png">
                </div>
                <div class="category__item">
                    <p class="item__text">Nón</p>
                    <img class="item__image" src="./assets/img/category/hat.png">
                </div>
            </div>
        </div>
    </div>
    <div class="popular__section container-xl">
        <h2 class="section__title">Mẫu đồ phổ biến</h2>
        <div class="product__container">
            <div class="product__items">

            </div>
            <button id="left__button"><i class="fa-solid fa-arrow-left"></i></button>
            <button id="right__button"><i class="fa-solid fa-arrow-right"></i></button>
        </div>
    </div>
    <div id="discovery__us--section" class="container-xl">
        <div class="discovery__container">
            <h2 class="section__title">Bạn có thể khám phá được điều gì ở chúng tôi?</h2>
            <div class="discovery__content">
                <div class="disco_thing">
                    <p>50+</p>
                    <p>Mẫu đồ bạn có thể đặt may</p>
                </div>
                <div class="disco_thing">
                    <p>1,000+</p>
                    <p>Khách hàng yêu cầu đặt may mỗi tháng</p>
                </div>

                <div class="disco_thing">
                    <p>50+</p>
                    <p>Mẫu đồ được gia công liên tục</p>
                </div>
            </div>
        </div>
    </div>

    <div class="step__guide--section container-xl">
        <h2 class="section__title">Cách bước để bạn có thể đặt may một mẫu đồ</h2>
        <div class="guide__content row">
            <div class="col">
                <div class="step__item">
                    <img src="assets/img/step_guide/choose.png">
                    <div class="description_step">
                        <span>Bước 1</span>
                        <p>Chọn một mẫu đồ mà bạn ưng ý</p>
                    </div>
                </div>
            </div>
            <div class="col">
                <div class="step__item">
                    <img src="./assets/img/step_guide/customize.png">
                    <div class="description_step">
                        <span>Bước 2</span>
                        <p>Tùy chọn size và kiểu dáng phù hợp với nhu cầu của bạn</p>
                    </div>
                </div>
            </div>
            <div class="col">
                <div class="step__item">
                    <img src="./assets/img/step_guide/checkout.png">
                    <div class="description_step">
                        <span>Bước 3</span>
                        <p>Tiến hành đặt may và thanh toán</p>
                    </div>
                </div>
            </div>
            <div class="col">
                <div class="step__item">
                    <img src="./assets/img/step_guide/receive.png">
                    <div class="description_step">
                        <span>Bước 4</span>
                        <p>Chờ nhận hàng sau khi bạn đã thanh toán thành công</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>
<!--Footer-->
<%@include file="footer.jsp" %>
<script src="./js/home.js"></script>
</body>
</html>