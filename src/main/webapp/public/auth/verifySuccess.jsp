<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>
    <c:import url="${initParam.contextPath}/public/commonLink.jsp"/>
    <link rel="stylesheet" href="${initParam.contextPath}/assets/css/verify.css">
</head>
<body>
<main>
    <div class="verify">
        <i class="verify__icon fa-solid fa-check"></i>
    </div>
    <h1>Xác thực hành công</h1>
    <p>
        Chúc mừng tài khoản
        <b><%= request.getAttribute("username") %></b>
         của bạn đã xác thực hành công!
    </p>
    <a href="signIn.jsp" class="button button--hover">Tiến hành đăng nhập</a>
</main>
</body>
</html>
