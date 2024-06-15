<%--
  Created by IntelliJ IDEA.
  User: ducvui2003
  Date: 15/06/2024
  Time: 13:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!--Font Awesome-->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css"
          integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <link rel="stylesheet" href="/assets/css/modal.css">
    <title>Modal</title>
</head>
<body>
<label for="toggle-myModal">
    Open
</label>
<input type="checkbox" id="toggle-myModal" hidden="hidden">
<div class="myModal">
    <label for="toggle-myModal" class="myModal__blur">

    </label>
    <div class="myModal__body">
        <header class="myModal__header">
            <h2>Modal</h2>
            <label for="toggle-myModal" class="myModal__close">
                <i class="fa-solid fa-xmark"></i>
            </label>
        </header>
        <div class="myModal__content"></div>
    </div>
</div>
</body>
</html>
