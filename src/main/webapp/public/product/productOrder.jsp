<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="vi_VN"/>
<c:set var="product" value="${requestScope.product}"/>
<c:set var="category" value="${requestScope.category}"/>
<c:set var="listParameter" value="${requestScope.listParameter}"/>
<jsp:useBean id="productFactory" class="utils.ProductFactory" scope="session"/>
<!doctype html>
<html lang="en">
<head>
    <jsp:include page="/public/commonLink.jsp"/>
    <link rel="stylesheet" href="<c:url value="/assets/css/productDetail.css" />">
    <link rel="stylesheet" href="<c:url value="/assets/css/productOrder.css" />">
    <title>Orther sản phẩm</title>
</head>
<body>
<c:import url="/public/header.jsp"/>
<form class="form" id="form__product">
    <div class="container-xl order__log-padding">
        <%--So do --%>
        <div class="col-12">
            <h2 class="order__heading">Bảng số đo</h2>
            <div class="order__guide-size">
                <img src="<c:url value="assets/img/size_table/${category.sizeTableImage}" />" alt="">
            </div>
        </div>
        <c:forEach var="parameter" items="${listParameter}" varStatus="loop">
            <%--Parameter--%>
            <div class="col-12">
                <div class="separate"></div>
            </div>
            <div class="col-12">
                <h2 class="order__heading">${parameter.name}</h2>
                <input type="text" name="nameParameter" hidden="hidden" value="${parameter.name}"/>
                <div class="row align-items-center">
                    <div class="col-5">
                        <label class="order__group">
                            <label class="order__parameter">
                                <input id="parameter${loop.index}" class="order__input" type="text"
                                       name="valueParameter"
                                       placeholder="Hãy nhập số đo của ${fn:toLowerCase(parameter.name)}">
                                <c:set var="unit" value="${parameter.unit}"/>
                                <span class="order__unit">${unit}</span>
                            </label>
                            <p class="order__parameter-valid">
                                Giá trị khả dụng chấp nhận từ <strong><span id="minValueParameter${loop.index}"
                                                                            class="order__parameter-min">${parameter.minValue}</span> ${unit}
                            </strong> đến
                                <strong><span id="maxValueParameter${loop.index}"
                                              class="order__parameter-max">${parameter.maxValue}</span> ${unit}</strong>
                            </p>
                            <p class="order__error"></p>
                        </label>
                    </div>
                        <%--Image--%>
                    <div class="offset-1 col-6">
                        <div class="order__guide-parameter">
                            <img src="<c:url value="assets/img/parameter_guide/${parameter.guideImg}"/>" alt="">
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
    <div class="order__log">
        <div class="order__info">
            <div class="order__media">
                <c:set var="firstImage"
                       value="${productFactory.getListImagesByProductId(product.id).get(0).nameImage}"/>
                <img src="<c:url value="assets/img/product_img/${firstImage}" />" alt="${firstImage}"/>
            </div>
            <div class="order__content">
                <h1 class="order__name">${product.name}</h1>
                <input type="text" value="${product.id}" hidden="hidden" name="productId">
                <div class=" form__block order__group">
                    <c:set var="colors" value="${productFactory.getListColorsByProductId(product.id)}"/>
                    <div class="form__choose-color">
                        <p class="form__title">Màu sắc</p>
                        <c:forEach var="color" items="${colors}">
                            <label class="form__color-check" style="background-color: ${color.codeColor}">
                                <input type="radio" name="color" hidden="hidden" value="${color.codeColor}">
                            </label>
                        </c:forEach>
                    </div>
                    <p class="order__error"></p>
                </div>

                <div class="form__block order__group">
                    <div class="form__quantity-wrapper">
                        <p class="form__title">Số lượng</p>
                        <div class="form__quantity">
                            <div class="form__quantity-inner">
                                <div class="form___quantity-btn form___quantity--decrease"></div>
                                <input id="quantity" type="text" name="quantity" value="1" readonly />
                                <div class="form___quantity-btn form___quantity--increase"></div>
                            </div>
                        </div>
                    </div>
                    <p class="order__error"></p>
                </div>
            </div>
        </div>
        <button id="order__submit" type="submit" class="order__submit-cart button--hover button">
            Thêm vào giỏ hàng
        </button>
    </div>
</form>
<script src="<c:url value="/js/validateForm.js"/>"></script>
<script>
    const isLogin = "<%=session.getAttribute("auth") != null%>";
</script>
<script src="<c:url value="/js/productOrder.js"/>"></script>
</body>
</html>