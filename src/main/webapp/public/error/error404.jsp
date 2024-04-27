<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/public/commonLink.jsp"/>
    <link rel="stylesheet" href="<c:url value="/assets/css/error404.css"/>">
    <title>404</title>
</head>
<body>
<main>
    <main>
        <div class="container-xl">
            <div class="row align-items-center">
                <div class="col-6">
                    <h1>Oops...</h1>
                    <p class="error__desc">Không tìm thấy tài nguyên này.</p>
                    <p class="error__detail">Tài nguyên này có thể đã bị xóa hoặc không tồn tại, bạn nên quay trở lại
                        trang
                        chủ.</p>
                    <a href="<c:url value="/public/index.jsp"/>" class="button button--hover error__button">
                        <i class="fa-solid fa-arrow-left"></i>
                        Trang chủ
                    </a>
                </div>
                <div class="col-6">
                    <img src="<c:url value="/assets/img/error404.png"/>" alt="">
                </div>
            </div>
        </div>
    </main>
</main>
</body>
</html>
