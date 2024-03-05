<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
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
    <link rel="stylesheet" href="../../assets/css/verify.css">
    <title>Đánh giá</title>
</head>
<body>
<main>
    <div class="verify">
        <i class="verify__icon fa-solid fa-check"></i>
    </div>
    <h1> Đánh giá thành công </h1>
    <p>
        Bạn đã đánh giá cho sản phẩm  <%=request.getAttribute("productName")%>  thành công. Cảm ơn bạn đã đóng góp ý
        kiến
        cho chúng tôi.
    </p>
    <a href="../product/productBuying.jsp" class="button button--hover">Quay lại gian hàng</a>
</main>
</body>
</html>
