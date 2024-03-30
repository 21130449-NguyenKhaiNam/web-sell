<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <jsp:include page="/public/commonLink.jsp" />
        <link rel="stylesheet" href="/assets/css/user/account.css">
        <link rel="stylesheet" href="/assets/css/user/accountOrder.css">
        <title>Lịch sử mua hàng</title>
    </head>
    <body>
        <div class="container-xl px-4 mt-4">
            <nav class="nav nav-borders">
                <a class="nav-link " href="/public/user/accountInfo.jsp">
                    Thông tin cá nhân
                </a>
                <a class="nav-link " href="/public/user/accountSecurity.jsp">
                    Bảo mật
                </a>
                <a class="nav-link active ms-0" href="#">
                    Đơn hàng
                </a>
            </nav>
            <hr class="mt-0 mb-4">
        </div>
    </body>
</html>
