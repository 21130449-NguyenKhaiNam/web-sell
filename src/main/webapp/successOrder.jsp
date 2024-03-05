<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
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
    <link rel="stylesheet" href="./assets/font/webfonts/Montserrat.css">
    <link rel="stylesheet" href="./assets/css/reset.css">
    <link rel="stylesheet" href="./assets/css/base.css">
    <!--Web font-->
    <title>Đặt hàng thành công</title>
    <style>
        .container{
            display: grid;
            text-align: center;
            gap: 18px;
            margin: 50px 0px;
        }

        .notification__success{
            display: grid;
            gap: 24px;
        }
        .icon__success{
            font-size: 120px;
            color: #5ee95e;
        }

        .icon__success i{
            position: relative;
            width: fit-content;
        }

        .icon__success i::after{
            font-family: 'Font Awesome 6 Free';
            width: fit-content;
            height: fit-content;
            content: "\f058";
            position: absolute;
            font-size: 50px;
            top: 0;
            left: 50%;
            right: 0;
            background-color: white;
            padding: 6px;
            border-radius: 50%;
            transform: translate(-36%,-36%);
        }

        .text__success{
            font-weight: 700;
            font-size: 30px;
            color: var(--blue-color-high);
        }

        .navigation__target{
            display: flex;
            gap: 8px;
            justify-content: center;
        }

        .navigation__target a{
            padding: 14px 16px;
            border-radius: 4px;
            border: 1px solid var(--blue-color-high);
            font-weight: 600;
        }

        .order__detail{
            background-color: var(--blue-color-high);
            color: white;
        }

        .continue__shopping{
            color: var(--blue-color-high);
        }

        .countdown__second{
            font-weight: 600;
            color: var(--blue-color-high);
        }

        .guide__next, .back__home{
            width: 420px;
            margin: auto;
            line-height: 22px;
            font-size: 14px;
        }

        p{
            margin: unset;
        }

        .order__tag{
            display: grid;
            gap: 10px;
            padding: 14px 0px;
            color: white;
            background-color: var(--blue-color-high);
            width: 100%;
            border-radius: 4px;
            margin: auto;
        }

        .order__no{
            font-size: 30px;
            font-weight: 600;
        }

        .order__swapper{
            display: grid;
            width: fit-content;
            margin: auto;
            gap: 16px;
        }

        .my__company{
            color: var(--blue-color-high);
            font-weight: 600;
        }
    </style>
</head>
<body>
    <c:import url="header.jsp" charEncoding="UTF-8"/>
    <div class="container">
        <div class="notification__success">
            <span class="icon__success"><i class="fa-solid fa-cart-shopping"></i></span>
            <strong class="text__success">ĐẶT HÀNG THÀNH CÔNG</strong>
        </div>
        <p>Cảm ơn quý khách đã mua sắm tại <strong class="my__company">YOURSTYLE</strong></p>
        <p class="guide__next">Để kiểm tra tất cả chi tiết về đơn hàng đã đặt vui lòng chọn "Chi tiết đơn hàng" hoặc xem trong email thông tin mua hàng và để khám phá thêm các sản phẩm khác vui lòng chọn "Tiếp tục mua sắm" </p>
        <div class="order__swapper">
            <div class="order__tag">
                <p>Mã số đơn hàng của quý khách là:</p>
                <strong class="order__no">${requestScope.invoiceNo}</strong>
            </div>
            <div class="navigation__target">
                <a class="order__detail">Chi tiết đơn hàng</a>
                <a class="continue__shopping" href="productBuying.jsp">Tiếp tục mua hàng</a>
            </div>
        </div>
        <p class="back__home">Website sẽ tự động quay về trang chủ sau <span class="countdown__second"></span></p>
    </div>
    <%@include file="footer.jsp" %>
</body>
<script>
    const timerDisplay = document.querySelector(".countdown__second");
    let countdownSeconds = 20;
    timerDisplay.innerText = countdownSeconds + "s";

    function startTimer() {

        const countdown = setInterval(() => {
            countdownSeconds--;
            timerDisplay.innerText = countdownSeconds + "s";

            if (countdownSeconds <= 0) {
                clearInterval(countdown);
                window.location.href = 'index.jsp';
            }
        }, 1000);
    }
    startTimer();
</script>
</script>
</html>
