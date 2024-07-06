<%--
  Created by IntelliJ IDEA.
  User: AD
  Date: 6/16/2024
  Time: 9:41 AM
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
</head>
<body>
<nav class="navbar d-block p-4" style="height: 100vh; min-height: 800px">
    <div class="wrapper_navigator">
        <ul class="navbar__list">
            <li class="navbar__item">
                <a href="<c:url value="/public/user/accountInfo.jsp"/>"
                   class="navbar__link button button button--hover hvr-grow-shadow" data-index="1"> Thông tin cá nhân</a>
            </li>
            <li class="navbar__item">
                <a href="<c:url value="/public/user/accountSecurity.jsp"/>"
                   class="navbar__link button button button--hover hvr-grow-shadow" data-index="2"> Bảo mật</a>
            </li>
            <li class="navbar__item">
                <a href="<c:url value="/public/user/accountOrder.jsp"/>"
                   class="navbar__link button button button--hover hvr-grow-shadow" data-index="3"> Đơn hàng
                </a>
            </li>
        </ul>
    </div>
</nav>
</body>
</html>
