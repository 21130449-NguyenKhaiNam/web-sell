<%@ page import="services.CheckoutServices" %>
<%@ page import="models.Image" %>
<%@ page import="java.util.List" %>
<%@ page import="services.image.CloudinaryUploadServices" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="/public/commonLink.jsp"/>
    <link rel="stylesheet" href="<c:url value="/assets/css/checkout.css" />">
    <title>Thanh toán</title>
</head>

<body>
<c:import url="/public/header.jsp"/>
<main id="main">
    <form id="form-checkout" class="needs-validation">
        <div class="container-xl">
            <div class="checkout__container row">
                <div class="checkout__info--left col">
                    <div class="delivery__info--container">
                        <h1 class="checkout__title">Thanh toán</h1>
                        <h2 class="checkout__subtitle">Thông tin giao hàng</h2>
                        <div id="delivery__info--form ">
                            <div class="form__group ">
                                <div class="form-floating mb-3 ">
                                    <input type="text" class="form-control  " id="fullName" name="fullName"
                                           placeholder="name@example.com" required>
                                    <label for="fullName">Họ và tên</label>
                                    <div class="form-message ">
                                    </div>
                                </div>
                            </div>
                            <div class="form__group">
                                <div class=" form-floating mb-3">
                                    <input type="email" class="form-control " id="email" name="email"
                                           placeholder="name@example.com">
                                    <label for="email">Email</label>
                                    <div class="form-message ">
                                    </div>
                                </div>
                            </div>
                            <div class="form__group">
                                <div class="form-floating mb-3">
                                    <input type="tel" class="form-control " id="phone" name="phone"
                                           placeholder="099000">
                                    <label for="phone">Số điện thoại</label>
                                    <div class=" form-message ">
                                    </div>
                                </div>
                            </div>
                            <div class="w-100 form__group">
                                <h2 class="checkout__subtitle">Sổ địa chỉ </h2>
                                <input id="addressId-$" type="radio" name="addressId" class="btn-check" value="1">
                                <label for="addressId-$"
                                       class="form__group btn w-100 d-block btn-outline-primary text-black  rounded ">
                                    <div class="row">
                                        <div class="col">
                                            <p class="address__detail text-start">adasdasdasdasd</p>
                                        </div>
                                    </div>
                                    <div class="row mt-2">
                                        <div class="col ">
                                            <p class="text-start"><span class="ward">TP HCM</span>
                                                <span class="district">Thu Duc</span>
                                                <span class="province">Linh Trung</span></p>
                                        </div>
                                    </div>
                                </label>
                                <input id="addressId-2" type="radio" name="addressId" class="btn-check" value="2">
                                <label for="addressId-2"
                                       class="form__group btn w-100 d-block btn-outline-primary text-black rounded mt-2">
                                    <div class="row">
                                        <div class="col">
                                            <p class="address__detail text-start">adasdasdasdasd</p>
                                        </div>
                                    </div>
                                    <div class="row mt-2">
                                        <div class="col ">
                                            <p class="text-start"><span class="ward">TP HCM</span>
                                                <span class="district">Thu Duc</span>
                                                <span class="province">Linh Trung</span></p>
                                        </div>
                                    </div>
                                </label>
                                <div class=" form-message ">
                                </div>
                            </div>
                            <%--                        Load từ bảng địa chỉ --%>
                            <%--                        <c:forEach items="${sessionScope.deliveryInfoStorage.deliveryInfoMap.keySet()}"--%>
                            <%--                                   var="deliveryInfoKey">--%>
                            <%--                            <div--%>
                            <%--                                    <c:if test="${deliveryInfoKey eq 'defaultDeliveryInfo'}"> id="default__info" </c:if>--%>
                            <%--                                    class="delivery__info">--%>
                            <%--                                <c:set var="deliveryInfo"--%>
                            <%--                                       value="${sessionScope.deliveryInfoStorage.getDeliveryInfoByKey(deliveryInfoKey)}"/>--%>
                            <%--                                <input data-customer-name="${deliveryInfo.fullName}"--%>
                            <%--                                       data-customer-email="${deliveryInfo.email}"--%>
                            <%--                                       data-customer-phone="${deliveryInfo.phone}"--%>
                            <%--                                       data-customer-address="${deliveryInfo.address}" type="hidden"--%>
                            <%--                                       name="deliveryInfoKey" value="${deliveryInfoKey}">--%>
                            <%--                                <div class="info__header">--%>
                            <%--                                    <h3>Giao tới <i class="fa-solid fa-turn-down"></i></h3>--%>
                            <%--                                    <span class="edit__delivery"--%>
                            <%--                                          onclick="showCustomizeDeliveryInfoForm(this, 'Chỉnh sửa thông tin giao hàng')">Chỉnh--%>
                            <%--                                                                            sửa</span>--%>
                            <%--                                </div>--%>
                            <%--                                <ul class="info__items">--%>
                            <%--                                    <li class="info__item customer__name">${deliveryInfo.fullName}--%>
                            <%--                                        <c:if test="${deliveryInfoKey eq 'defaultDeliveryInfo'}"><span--%>
                            <%--                                                class="default__tag">Mặc định</span></c:if>--%>
                            <%--                                    </li>--%>
                            <%--                                    <li class="info__item">Email: ${deliveryInfo.email}</li>--%>
                            <%--                                    <li class="info__item">Số điện thoại: ${deliveryInfo.phone}</li>--%>
                            <%--                                    <li class="info__item">Địa chỉ: ${deliveryInfo.address}</li>--%>
                            <%--                                </ul>--%>

                            <%--                                <c:choose>--%>
                            <%--                                    <c:when test="${deliveryInfo eq sessionScope[userIdCart].deliveryInfo}">--%>
                            <%--                                        <c:set var="statusChoice" value="Đã chọn"/> </c:when> <c:otherwise>--%>
                            <%--                                    <c:set var="statusChoice" value="Chọn"/> </c:otherwise> </c:choose>--%>
                            <%--                                <div class="choice__remove">--%>
                            <%--                                    <button type="submit" class="button__choice" name="typeEdit"--%>
                            <%--                                            value="choiceDeliveryInfo">${statusChoice}</button>--%>
                            <%--                                    <c:if test="${deliveryInfoKey ne 'defaultDeliveryInfo'}">--%>
                            <%--                                        <button type="submit" class="button__remove" name="typeEdit"--%>
                            <%--                                                value="removeDeliveryInfo">--%>
                            <%--                                            Xóa--%>
                            <%--                                        </button>--%>
                            <%--                                    </c:if>--%>
                            <%--                                </div>--%>
                            <%--                            </div>--%>
                            <%--                        </c:forEach>--%>
                            <div class="w-100 mt-3 form__group">
                                <h2 class="checkout__subtitle">Phương thức thanh toán</h2>
                                <input type="radio" name="paymentId" class="btn-check"
                                       value="1"
                                       hidden="hidden"
                                       id="payment__method-1">
                                <label class="w-100 btn btn-outline-primary rounded p-3 text-start"
                                       for="payment__method-1">Thanh toán VNPAY</label>
                                <input type="radio" name="paymentId"
                                       value="2" class="btn-check"
                                       id="payment__method-2">
                                <label class="w-100 btn btn-outline-primary rounded p-3 text-start mt-2"
                                       for="payment__method-2">Thanh toán COD</label>
                                <div class=" form-message ">
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- New update template -->
                </div>
                <div class="checkout__info--right col">
                    <span class="summary__cart">Tóm tắt giỏ hàng</span>
                    <div class="order__detail--info">
                        <table class="order__table">
                            <thead>
                            <tr class="row__header">
                                <th class="thead__item">Sản phầm</th>
                                <th class="thead__item">Số lượng</th>
                                <th class="thead__item">Đơn giá</th>
                            </tr>
                            </thead>
                            <tbody>
                            <%--                        <c:forEach items="${sessionScope[userIdCart].shoppingCartMap.keySet()}" var="productId">--%>
                            <%--                            <c:forEach items="${sessionScope[userIdCart].shoppingCartMap.get(productId)}"--%>
                            <%--                                       var="cartProduct">--%>
                            <%--                                <tr class="row__content">--%>
                            <%--                                    <td class="td__item">--%>
                            <%--                                        <div class="product__item">--%>
                            <%--                                            <c:set var="listImagesProduct"--%>
                            <%--                                                   value="${productFactory.getListImagesByProductId(productId)}"/>--%>
                            <%--                                            <%--%>
                            <%--                                                String nameImage = ((List<Image>) pageContext.getAttribute("listImagesProduct")).get(0).getNameImage();--%>
                            <%--                                            %>--%>
                            <%--                                            <img src='<%=CloudinaryUploadServices.getINSTANCE().getImage("product_img", nameImage)%>'>--%>
                            <%--                                            <div class="order__product--info">--%>
                            <%--                                                <p class="product__name">${cartProduct.product.name}</p>--%>
                            <%--                                                <p class="order__color">Màu sắc: ${cartProduct.color.codeColor}</p>--%>
                            <%--                                                <p class="order__size">${cartProduct.makeSizeFormat()}</p>--%>
                            <%--                                            </div>--%>
                            <%--                                        </div>--%>
                            <%--                                    </td>--%>
                            <%--                                    <td class="td__item">${cartProduct.quantity}</td>--%>
                            <%--                                    <td class="td__item">${cartProduct.sewingPriceFormat()}</td>--%>
                            <%--                                </tr>--%>
                            <%--                            </c:forEach>--%>
                            <%--                        </c:forEach>--%>
                            </tbody>
                        </table>
                    </div>
                    <div class="invoice--final">
                        <div class="invoice__content">
                            <div class="price__item--detail">
                                <div class="temporary__container">
                                    <%--                                <span>Tạm tính (${sessionScope[userIdCart].getTotalItems()} sản phẩm)</span>--%>
                                    <%--                                <span>${sessionScope[userIdCart].temporaryPriceFormat()}</span>--%>
                                </div>
                                <div class="discount__container">
                                    <span>Giảm giá</span>
                                    <span> </span>
                                </div>
                                <div class="shipping__container">
                                    <span>Phí vận chuyển</span>
                                    <%--                                    chèn api shipping--%>
                                    <span></span>
                                </div>
                            </div>
                            <div class="total__price--final">
                                <span class="total__label">Tổng tiền</span>
                                <span class="total__value">${sessionScope[userIdCart].totalPriceFormat(true)}</span>
                            </div>
                        </div>
                        <div class="ground__button--forward">
                            <button class="place__order" type="submit">Đặt hàng</button>
                            <a href="<c:url value="/public/user/shoppingCart.jsp" />">
                                <button class="back--shopping__cart">Quay lại giỏ hàng</button>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>
</main>
<div class="popup__deletion"></div>
</body>
<!--jQuery validator-->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.20.0/jquery.validate.min.js"
        integrity="sha512-WMEKGZ7L5LWgaPeJtw9MBM4i5w5OSBlSjTjCtSnvFJGSVD26gE5+Td12qN5pvWXhuWaWcVwF++F7aqu9cvqP0A=="
        crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script src="https://ajax.aspnetcdn.com/ajax/jquery.validate/1.15.0/additional-methods.js"></script>
<!---Sweet Alert 2--->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/sweetalert2/11.10.7/sweetalert2.min.css"
      integrity="sha512-OWGg8FcHstyYFwtjfkiCoYHW2hG3PDWwdtczPAPUcETobBJOVCouKig8rqED0NMLcT9GtE4jw6IT1CSrwY87uw=="
      crossorigin="anonymous" referrerpolicy="no-referrer"/>
<script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert2/11.10.7/sweetalert2.min.js"
        integrity="sha512-csaTzpLFmF+Zl81hRtaZMsMhaeQDHO8E3gBkN3y3sCX9B1QSut68NxqcrxXH60BXPUQ/GB3LZzzIq9ZrxPAMTg=="
        crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script src="<c:url value="/js/base.js"/>"></script>
<script type="module" src="<c:url value="/js/user/checkout.js"/>"></script>
</html>