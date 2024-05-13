<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/public/commonLink.jsp"/>
    <link rel="stylesheet" href="<c:url value="/assets/css/error404.css"/>">
    <link rel="stylesheet" href="<c:url value="/assets/css/error403.css"/>">
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
                <a href="<c:url value="/public/index.jsp" />" class="button button--hover error__button">
                    <i class="fa-solid fa-arrow-left"></i>
                    Trang chủ
                </a>
            </div>
            <div class="col-6">
                <img class="error__img" src="<c:url value="/assets/img/error403.png"/>" alt="">
            </div>
        </div>
    </div>
</main>
</body>
</html>
