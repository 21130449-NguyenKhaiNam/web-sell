<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <!--Các thư viện hỗ trợ-->
    <!--Font Awesome-->
    <link rel="stylesheet" href="assets/fontIcon/fontawesome-free-6.4.2-web/css/all.min.css">
    <!--Bootstrap-->
    <link rel="stylesheet" href="assets/bootstrap/bootstrap-grid.min.css">
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
    <link rel="stylesheet" href="assets/css/productBuying.css">
    <title>Gian hàng</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<main class="main">
    <section class="products">
        <div class="container-xl">
            <div class="row">
                <div class="col-3">
                    <aside class="categories">
                        <ul id="category-list" class="category__list">
                            <!--                            <li class="category__item">Ao khoac</li>-->
                            <!--                            <li class="category__item">Quan kaki</li>-->
                            <!--                            <li class="category__item">Ao so mi</li>-->
                            <!--                            <li class="category__item">Ao tay ngan</li>-->
                            <!--                            <li class="category__item">AO tay dai</li>-->
                        </ul>
                    </aside>
                </div>
                <div class="col-9">
                    <form action="#!" id="search-form" class="search__form">
                        <input type="text" autocomplete="off" id="search-input" class="search__input"
                               placeholder="Nhập sản phẩm bạn muốn tìm tại đây.">
                        <button type="button" id="search-button" class="search__button button button--hover"><i
                                class="search__icon fa-solid fa-magnifying-glass"></i>Tìm kiếm
                        </button>
                        <div class="result-box"></div>
                    </form>
                </div>
            </div>
            <div class="row ">
                <div class="product__list"></div>
            </div>
            <ul class="paging">
            </ul>
        </div>
    </section>
</main>
<%@include file="footer.jsp" %>
<script src="js/base.js"></script>
<script src="js/data.js"></script>
<script src="js/paging.js"></script>
<script src="js/productBuying.js"></script>
</body>
</html>