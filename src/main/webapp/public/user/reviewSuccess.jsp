<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <jsp:include page="${initParam.contextPath}/public/commonLink.jsp"/>
    <link rel="stylesheet" href="${initParam.contextPath}/assets/css/verify.css">
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
    <a href="${initParam.contextPath}/public/product/productBuying.jsp" class="button button--hover">Quay lại gian hàng</a>
</main>
</body>
</html>
