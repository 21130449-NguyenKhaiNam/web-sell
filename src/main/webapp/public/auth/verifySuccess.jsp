<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>

    <head>
        <jsp:include page="/public/commonLink.jsp" />
        <link rel="stylesheet" href="<c:url value="/assets/css/verify.css"/>">
    </head>

    <body>
        <main>
            <div class="verify">
                <i class="verify__icon fa-solid fa-check"></i>
            </div>
            <h1>Xác thực hành công</h1>
            <p>
                Chúc mừng tài khoản <b>${requestScope.username} </b> của bạn đã xác thực hành công! </p>
            <a href="<c:url value="/public/auth/signIn.jsp"/>" class="button button--hover">Tiến hành đăng nhập</a>
        </main>
    </body>

</html>