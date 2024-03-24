<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>

    <head>
        <jsp:include page="/public/commonLink.jsp" />
        <link rel="stylesheet" href="<c:url value="/assets/css/successOrder.css" />">
        <title>Đặt hàng thành công</title>
        <style>

        </style>
    </head>

    <body>
        <c:import url="/public/header.jsp" />
        <div class="container">
            <div class="notification__success">
                <span class="icon__success"><i class="fa-solid fa-cart-shopping"></i></span>
                <strong class="text__success">ĐẶT HÀNG THÀNH CÔNG</strong>
            </div>
            <p>Cảm ơn quý khách đã mua sắm tại <strong class="my__company">YOURSTYLE</strong></p>
            <p class="guide__next">Để kiểm tra tất cả chi tiết về đơn hàng đã đặt vui lòng chọn "Chi tiết đơn hàng" hoặc
                                   xem trong email thông tin mua hàng và để khám phá thêm các sản phẩm khác vui lòng
                                   chọn "Tiếp tục mua sắm" </p>
            <div class="order__swapper">
                <div class="order__tag">
                    <p>Mã số đơn hàng của quý khách là:</p>
                    <strong class="order__no">${requestScope.invoiceNo}</strong>
                </div>
                <div class="navigation__target">
                    <a class="order__detail">Chi tiết đơn hàng</a>
                    <a class="continue__shopping" href="../product/productBuying.jsp">Tiếp tục mua hàng</a>
                </div>
            </div>
            <p class="back__home">Website sẽ tự động quay về trang chủ sau <span class="countdown__second"></span></p>
        </div>
        <jsp:include page="${initParam.contextPath}/public/footer.jsp" />
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
                    window.location.href = '${initParam.contextPath}/public/index.jsp';
                }
            }, 1000);
        }

        startTimer();
    </script>

</html>