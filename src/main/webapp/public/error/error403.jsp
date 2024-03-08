<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <!--Các thư viện hỗ trợ-->
    <!--Font Awesome-->
    <link rel="stylesheet" href="assets/fontIcon/fontawesome-free-6.4.2-web/css/all.min.css">
    <!--Bootstrap-->
    <link rel="stylesheet" href="../../assets/bootstrap/bootstrap-grid.min.css">
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
    <link rel="stylesheet" href="../../assets/css/error404.css">
    <link rel="stylesheet" href="../../assets/css/error403.css">
    <title>403</title>
</head>
<body>
<main>
    <div class="container-xl">
        <div class="row align-items-center">
            <div class="col-6">
                <h1>Warning...</h1>
                <p class="error__desc">Tài nguyên bị hạn chế đối với tài khoản của bạn</p>
                <p class="error__detail">Vui lòng quay lại trang chủ</p>
                <a href="../index.jsp" class="button button--hover error__button">
                    <i class="fa-solid fa-arrow-left"></i>
                    Trang chủ
                </a>
            </div>
            <div class="col-6" >
                <img class="error__img" src="assets/img/error403.png" alt="">
            </div>
        </div>
    </div>
</main>
</body>
</html>
