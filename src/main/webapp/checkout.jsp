<%@ page import="services.CheckoutServices" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <!--Các thư viện hỗ trợ-->
    <!--Font Awesome-->
    <link rel="stylesheet" href="assets/fontIcon/fontawesome-free-6.4.2-web/css/all.min.css">

    <!--Bootstrap-->
    <link rel="stylesheet" href="assets/bootstrap/bootstrap-grid.min.css">
    <!--Favicon-->
    <link rel="apple-touch-icon" sizes="180x180" href="assets/favicon/apple-touch-icon.png">
    <link rel="icon" type="image/png" sizes="32x32" href="assets/favicon/favicon-32x32.png">
    <link rel="icon" type="image/png" sizes="16x16" href="assets/favicon/favicon-16x16.png">
    <link rel="manifest" href="assets/favicon/site.webmanifest">
    <!--Web font-->
    <link rel="stylesheet" href="assets/font/webfonts/Montserrat.css">

    <!--CSS-->
    <link rel="stylesheet" href="assets/css/reset.css">
    <link rel="stylesheet" href="assets/css/base.css">
    <link rel="stylesheet" href="assets/css/checkout.css">

    <title>Thanh toán</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<main id="main">
    <div class="container-xl">
        <div class="checkout__container row">
            <div class="checkout__info--left col">
                <div class="delivery__info--container">
                    <h1 class="checkout__title">Thanh toán</h1>
                    <h2 class="checkout__subtitle">Thông tin giao hàng</h2>
                    <%--                    <form id="delivery__info--form" class="radio__section">--%>
                    <%--                        <div>--%>
                    <%--                            <div class="delivery__default section__info--selection">--%>
                    <%--                                <input type="radio" name="typeSelection" class="radio__button"--%>
                    <%--                                       value="defaultInfo"--%>
                    <%--                                       id="default__selection">--%>
                    <%--                                <label class="label__selection" for="default__selection">Sử dụng thông tin mặc định khi--%>
                    <%--                                    nhận hàng</label>--%>
                    <%--                            </div>--%>

                    <%--                            <ul class="info__items">--%>
                    <%--                                <li class="info__item"><span>Họ và tên:</span><span>${sessionScope.auth.fullName}</span>--%>
                    <%--                                </li>--%>
                    <%--                                <li class="info__item"><span>Email:</span><span>${sessionScope.auth.email}</span></li>--%>
                    <%--                                <li class="info__item">--%>
                    <%--                                    <span>Số điện thoại:</span><span>${sessionScope.auth.phone}</span></li>--%>
                    <%--                                <li class="info__item"><span>Địa chỉ:</span><span>${sessionScope.auth.address}</span>--%>
                    <%--                                </li>--%>
                    <%--                            </ul>--%>
                    <%--                        </div>--%>
                    <%--                        <div class="delivery__other">--%>
                    <%--                            <div class="section__info--selection">--%>
                    <%--                                <input type="radio" name="typeSelection" class="radio__button"--%>
                    <%--                                       value="customizeInfo"--%>
                    <%--                                       id="other__selection">--%>
                    <%--                                <label class="label__selection" for="other__selection">Sử dụng thông tin khác khi nhận--%>
                    <%--                                    hàng</label>--%>
                    <%--                            </div>--%>
                    <%--                        </div>--%>
                    <%--                    </form>--%>

                    <form id="delivery__info--form">
                        <%--                        <div id="default__info" class="delivery__info">--%>
                        <%--                            <input type="hidden" name="deliveryInfoKey" value="defaultDeliveryInfo">--%>
                        <%--                            <div class="info__header">--%>
                        <%--                                <h3>Giao tới <i class="fa-solid fa-turn-down"></i></h3>--%>
                        <%--                                <a href="">Chỉnh sửa</a>--%>
                        <%--                            </div>--%>
                        <%--                            <ul class="info__items">--%>
                        <%--                                <li class="info__item customer__name">${sessionScope.auth.fullName}<span class="default__tag">Mặc định</span></li>--%>
                        <%--                                <li class="info__item">Email: ${sessionScope.auth.email}</li>--%>
                        <%--                                <li class="info__item">Số điện thoại: ${sessionScope.auth.phone}</li>--%>
                        <%--                                <li class="info__item">Địa chỉ: ${sessionScope.auth.address}</li>--%>
                        <%--                            </ul>--%>
                        <%--                            <button class="button__choice" name="typeEdit" value="choiceDeliveryInfo">Đã chọn</button>--%>
                        <%--                        </div>--%>
                        <c:set var="userIdCart" value="${String.valueOf(sessionScope.auth.id)}"/>
                        <c:if test="${sessionScope.deliveryInfoStorage != null}">
                            <c:forEach items="${sessionScope.deliveryInfoStorage.deliveryInfoMap.keySet()}" var="deliveryInfoKey">
                                <div <c:if test="${deliveryInfoKey eq 'defaultDeliveryInfo'}">id="default__info"</c:if> class="delivery__info">
                                    <c:set var="deliveryInfo" value="${sessionScope.deliveryInfoStorage.getDeliveryInfoByKey(deliveryInfoKey)}"/>
                                    <input data-customer-name="${deliveryInfo.fullName}"
                                           data-customer-email="${deliveryInfo.email}"
                                           data-customer-phone="${deliveryInfo.phone}"
                                           data-customer-address="${deliveryInfo.address}"
                                           type="hidden"
                                           name="deliveryInfoKey" value="${deliveryInfoKey}">
                                    <div class="info__header">
                                        <h3>Giao tới <i class="fa-solid fa-turn-down"></i></h3>
                                        <span class="edit__delivery" onclick="showCustomizeDeliveryInfoForm(this, 'Chỉnh sửa thông tin giao hàng')">Chỉnh sửa</span>
                                    </div>
                                    <ul class="info__items">
                                        <li class="info__item customer__name">${deliveryInfo.fullName}<c:if test="${deliveryInfoKey eq 'defaultDeliveryInfo'}"><span class="default__tag">Mặc định</span></c:if></li>
                                        <li class="info__item">Email: ${deliveryInfo.email}</li>
                                        <li class="info__item">Số điện thoại: ${deliveryInfo.phone}</li>
                                        <li class="info__item">Địa chỉ: ${deliveryInfo.address}</li>
                                    </ul>

                                    <c:choose>
                                        <c:when test="${deliveryInfo eq sessionScope[userIdCart].deliveryInfo}">
                                            <c:set var="statusChoice" value="Đã chọn"/>
                                        </c:when>
                                        <c:otherwise>
                                            <c:set var="statusChoice" value="Chọn"/>
                                        </c:otherwise>
                                    </c:choose>
                                    <div class="choice__remove">
                                        <button type="submit" class="button__choice" name="typeEdit" value="choiceDeliveryInfo">${statusChoice}</button>
                                        <c:if test="${deliveryInfoKey ne 'defaultDeliveryInfo'}">
                                            <button type="submit" class="button__remove" name="typeEdit" value="removeDeliveryInfo">Xóa</button>
                                        </c:if>
                                    </div>
                                </div>
                            </c:forEach>
                        </c:if>
                    </form>
                    <p class="other__info">Bạn muốn giao hàng đến địa chỉ khác?
                        <span onclick="showCustomizeDeliveryInfoForm(this, 'Thêm thông tin giao hàng')" class="add__delivery">Thêm thông tin giao hàng mới</span></p>
                    <div class="popup__bg">
                        <div class="popup__form">
                            <div class="form__header">
                                <h2 class="form__title"></h2>
                                <span class="button__close"><i class="fa-solid fa-xmark"></i></span>
                            </div>
                            <form id="customize__info--form">
                                <input type="hidden" name="deliveryInfoTarget">
                                <div class="customize__item">
                                    <label for="fullName" class="input__text">Họ và tên <span
                                            class="compulsory">*</span></label>
                                    <input type="text" name="fullName" class="input__content field__content"
                                           id="fullName" placeholder="Họ và tên của bạn">
                                    <span id="fullNameError" class="error__notice"></span>
                                </div>
                                <div class="customize__item">
                                    <label for="email" class="input__text">Email <span
                                            class="compulsory">*</span></label>
                                    <input type="text" name="email" class="input__content field__content" id="email"
                                           placeholder="Email của bạn">
                                    <span id="emailError" class="error__notice"></span>
                                </div>
                                <div class="customize__item">
                                    <label for="phone" class="input__text">Số điện thoại <span
                                            class="compulsory">*</span></label>
                                    <input type="text" name="phone" class="input__content field__content" id="phone"
                                           placeholder="Số điện thoại của bạn">
                                    <span id="phoneError" class="error__notice"></span>
                                </div>
                                <div class="customize__item">
                                    <label for="address" class="input__text">Địa chỉ <span
                                            class="compulsory">*</span></label>
                                    <textarea class="textarea__content field__content" name="address" id="address"
                                              rows="6" placeholder="Địa chỉ của bạn"></textarea>
                                    <span id="addressError" class="error__notice"></span>
                                </div>
                                <%--                        <input type="submit" class="button__custom" value="Đăng ký địa chỉ giao hàng">--%>
                                <div class="button__forward">
                                    <button type="button" class="button__cancel">Hủy bỏ</button>
                                    <button type="submit" class="button__custom" name="action"></button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>

                <!-- New update template -->
                <c:set var="freeShip" value="5000000"/>
                <c:choose>
                    <c:when test="${sessionScope[userIdCart].getTotalPrice(false) >= freeShip}">
                        <p class="free__ship"><i class="fa-solid fa-circle-check"></i>Miễn phí vận chuyển cho hóa đơn từ
                            <fmt:setLocale value="vi_VN"/><fmt:formatNumber value="${freeShip}" type="currency"/></p>
                    </c:when>
                    <c:otherwise>
                        <div class="delivery__method--container">
                            <h2 class="checkout__subtitle">Phương thức vận chuyển</h2>
                                <%--                            action="Checkout" method="post"--%>
                            <form id="delivery__method--form" class="radio__section">
                                <input type="hidden" name="action" value="choiceDeliveryMethod">
                                    <%--                                <c:forEach items="${requestScope.listInfoDeliveryMethod}" var="infoDeliveryMethod">--%>
                                    <%--                                    <div class="method__content">--%>
                                    <%--                                        <div class="method__item section__info--selection ${sessionScope.deliveryMethodSelected eq infoDeliveryMethod.id ? "selected" : ""}">--%>
                                    <%--                                            <input type="radio" name="delivery__method" class="radio__button"--%>
                                    <%--                                                   value="${infoDeliveryMethod.id}"--%>
                                    <%--                                                   id="delivery__method${infoDeliveryMethod.id}" <c:if test="${sessionScope.deliveryMethodSelected eq infoDeliveryMethod.id}">checked</c:if>>--%>
                                    <%--                                            <label class="label__selection" for="delivery__method${infoDeliveryMethod.id}">--%>
                                    <%--                                                <span>${infoDeliveryMethod.typeShipping}</span>--%>
                                    <%--                                                <span><fmt:setLocale value="vi_VN"/><fmt:formatNumber type="currency"--%>
                                    <%--                                                                                                      value="${infoDeliveryMethod.shippingFee}"/></span>--%>
                                    <%--                                            </label>--%>
                                    <%--                                        </div>--%>
                                    <%--                                        <span><p>${infoDeliveryMethod.description}</p></span>--%>
                                    <%--                                    </div>--%>
                                    <%--                                </c:forEach>--%>
                                <c:forEach items="${requestScope.listDeliveryMethod}" var="deliveryMethod">
                                    <div class="method__content">
                                        <div class="method__item section__info--selection">
                                            <input <c:if test="${deliveryMethod eq sessionScope[userIdCart].deliveryMethod}">checked</c:if> type="radio" name="delivery__method" class="radio__button"
                                                   value="${deliveryMethod.id}"
                                                   id="delivery__method${deliveryMethod.id}">
                                            <label class="label__selection" for="delivery__method${deliveryMethod.id}">
                                                <span>${deliveryMethod.typeShipping}</span>
                                                <span><fmt:setLocale value="vi_VN"/><fmt:formatNumber type="currency"
                                                                                                      value="${deliveryMethod.shippingFee}"/></span>
                                            </label>
                                        </div>
                                        <span class="description__method"><p>${deliveryMethod.description}</p></span>
                                    </div>
                                </c:forEach>
                                    <%--                        <div class="method__content">--%>
                                    <%--                            <div class="method__item section__info--selection">--%>
                                    <%--                                <input type="radio" name="delivery__method" class="radio__button" value="uban__suburbs"--%>
                                    <%--                                       id="uban__suburbs">--%>
                                    <%--                                <label class="label__selection" for="uban__suburbs">--%>
                                    <%--                                    <span>Nội thành / ngoại thành </span>--%>
                                    <%--                                    <span>22.000 ₫</span>--%>
                                    <%--                                </label>--%>
                                    <%--                            </div>--%>
                                    <%--                            <span>--%>
                                    <%--                                <ul>--%>
                                    <%--                                    <li>Nội thành TP.HCM bao gồm: Quận 1, Quận 3, Quận 4, Quận 5, Quận 6, Quận 7, Quận 8, Quận 10, Quận 11, Quận Tân Bình, Quận Gò Vấp, Quận Bình Thạnh, Quận Tân Phú, Quận Phú Nhuận</li>--%>
                                    <%--                                    <li>Ngoại thành TP.HCM bao gồm: Thủ Đức, Quận 12, Quận Bình Tân</li>--%>
                                    <%--                                </ul>--%>
                                    <%--                            </span>--%>
                                    <%--                        </div>--%>

                                    <%--                        <div class="method__content">--%>
                                    <%--                            <div class="method__item section__info--selection">--%>
                                    <%--                                <input type="radio" name="delivery__method" class="radio__button" value="district__commune" id="district__commune">--%>
                                    <%--                                <label class="label__selection" for="district__commune">--%>
                                    <%--                                    <span>Huyện, xã</span>--%>
                                    <%--                                    <span>30.000 ₫</span>--%>
                                    <%--                                </label>--%>
                                    <%--                            </div>--%>
                                    <%--                            <span><p>Huyện, xã TP.HCM bao gồm: Huyện Nhà Bè, Huyện Bình Chánh, Huyện Hóc Môn, Huyện Củ Chi</p></span>--%>
                                    <%--                        </div>--%>
                                    <%--                        <div class="method__content">--%>
                                    <%--                            <div class="method__item section__info--selection">--%>
                                    <%--                                <input type="radio" name="delivery__method" class="radio__button" value="other__district"--%>
                                    <%--                                       id="other__district">--%>
                                    <%--                                <label class="label__selection" for="other__district">--%>
                                    <%--                                    <span>Các tỉnh thành khác</span>--%>
                                    <%--                                    <span>37.000 ₫</span>--%>
                                    <%--                                </label>--%>
                                    <%--                            </div>--%>
                                    <%--                            <span><p>Các tỉnh thành khác: Cần Thơ, Đà Nẵng, Bến Tre,...</p></span>--%>
                                    <%--                        </div>--%>
                            </form>
                        </div>
                    </c:otherwise>
                </c:choose>
                <!-- New update template -->
                <div class="payment__method--container">
                    <h2 class="checkout__subtitle">Phương thức thanh toán</h2>
                    <form id="payment__method--form" class="radio__section">
                        <input type="hidden" name="action" value="choicePaymentMethod">
                        <c:forEach items="${requestScope.listPaymentMethod}" var="paymentMethod">
                            <div class="method__content">
                                <div class="method__item section__info--selection">
                                    <input <c:if test="${paymentMethod eq sessionScope[userIdCart].paymentMethod}">checked</c:if> type="radio" name="payment__method" class="radio__button"
                                           value="${paymentMethod.id}"
                                           id="payment__method${paymentMethod.id}">
                                    <label class="label__selection"
                                           for="payment__method${paymentMethod.id}">${paymentMethod.typePayment}</label>
                                </div>
                                <div class="description__method information__transaction">
                                    <c:choose>
                                        <c:when test="${paymentMethod.id > 1}">
                                            <c:set value="${CheckoutServices.getINSTANCE().getPaymentOwnerByPaymentMethodId(paymentMethod.id)}" var="paymentOwner"/>
                                            <table class="table__transaction">
                                                <tbody>
                                                <tr class="owner__name">
                                                    <td>Chủ tài khoản</td>
                                                    <td><span>${paymentOwner.ownerName}</span></td>
                                                </tr>
                                                <tr class="account__number">
                                                    <td>Số tài khoản</td>
                                                    <td>
                                                        <div>
                                                            <span>${paymentOwner.accountNumber}</span>
                                                            <span class="copy__button"><i class="fa-solid fa-copy"></i> Sao chép</span>
                                                        </div>
                                                    </td>
                                                </tr>
                                                <tr class="payment__platform">
                                                    <c:if test="${paymentMethod.id eq 2}">
                                                        <c:set var="qrImage" value="bank.png"/>
                                                        <td>Ngân hàng</td>
                                                    </c:if>
                                                    <c:if test="${paymentMethod.id eq 3}">
                                                        <c:set var="qrImage" value="e-wallet.png"/>
                                                        <td>Ví điện tử</td>
                                                    </c:if>
                                                    <td><span>${paymentOwner.paymentPlatform}</span></td>
                                                </tr>
                                                <tr class="amount__pay">
                                                    <td>Số tiền</td>
                                                    <td>
                                                        <div>
                                                            <span class="amount">${sessionScope[userIdCart].totalPriceFormat(true)}</span>
                                                            <span class="copy__button"><i class="fa-solid fa-copy"></i> Sao chép</span>
                                                        </div>
                                                    </td>
                                                </tr>
                                                <tr class="transaction__content">
                                                    <td>Nội dung giao dịch</td>
                                                    <td>
                                                        <div>
                                                            <span class="content">${sessionScope.contentForPay}</span>
                                                            <span class="copy__button"><i class="fa-solid fa-copy"></i> Sao chép</span>
                                                        </div>
                                                    </td>
                                                </tr>
                                                </tbody>
                                            </table>
                                            <div class="payment__qr">
                                                <img class="qr__code" src="assets/img/paymentQR/${qrImage}">
                                                <div>
                                                    <span>Hoặc bạn có thể quét QR code bên cạnh để tiến hành thanh toán một cách nhanh chóng và chính xác hơn</span>
                                                    <span><strong style="font-weight: 500">* Lưu ý:</strong> Trước khi thanh toán vui lòng kiểm tra thật kỹ số tiền cần thanh toán và nội dung chuyển khoản. Trong trường hợp chuyển khoản sai nội dung hoặc thanh toán với số tiền không đúng thì chúng tôi hoàn toàn không chịu trách nhiệm với số tiền bạn đã chuyển và đơn hàng không thể đóng gói đến bạn</span>
                                                </div>
                                            </div>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="cod">Khi chọn phương thức trả tiền mặt khi nhận hàng (COD), vui lòng bạn chuẩn bị đầy đủ số tiền cần thanh toán cho nhà vận chuyển khi nhận hàng</span>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                        </c:forEach>
                        <%--                        <div class="method__item section__info--selection">--%>
                        <%--                            <input type="radio" name="payment__method" class="radio__button" value="payment__method"--%>
                        <%--                                   id="cash">--%>
                        <%--                            <label class="label__selection" for="cash">Thanh toán tiền mặt khi nhận hàng</label>--%>
                        <%--                        </div>--%>
                        <%--                        <div class="method__item section__info--selection">--%>
                        <%--                            <input type="radio" name="payment__method" class="radio__button" value="bank" id="bank">--%>
                        <%--                            <label class="label__selection" for="bank">Thanh toán qua atm nội địa / internet--%>
                        <%--                                banking</label>--%>
                        <%--                        </div>--%>
                        <%--                        <div class="method__item section__info--selection">--%>
                        <%--                            <input type="radio" name="payment__method" class="radio__button" value="e-wallet"--%>
                        <%--                                   id="e-wallet">--%>
                        <%--                            <label class="label__selection" for="e-wallet">Thanh toán qua ví điện tử</label>--%>
                        <%--                        </div>--%>
                    </form>
                </div>
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
                        <c:forEach items="${sessionScope[userIdCart].shoppingCartMap.keySet()}" var="productId">
                            <c:forEach items="${sessionScope[userIdCart].shoppingCartMap.get(productId)}" var="cartProduct">
                                <tr class="row__content">
                                    <td class="td__item">
                                        <div class="product__item">
                                            <c:set var="listImagesProduct"
                                                   value="${productFactory.getListImagesByProductId(productId)}"/>
                                            <img src='assets/img/product_img/${listImagesProduct.get(0).nameImage}'>
                                            <div class="order__product--info">
                                                <p class="product__name">${cartProduct.product.name}</p>
                                                <p class="order__color">Màu sắc: ${cartProduct.color.codeColor}</p>
                                                <p class="order__size">${cartProduct.makeSizeFormat()}</p>
                                            </div>
                                        </div>
                                    </td>
                                    <td class="td__item">${cartProduct.quantity}</td>
                                    <td class="td__item">${cartProduct.sewingPriceFormat()}</td>
                                </tr>
                            </c:forEach>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="invoice--final">
                    <div class="invoice__content">
                        <div class="price__item--detail">
                            <div class="temporary__container">
                                <span>Tạm tính (${sessionScope[userIdCart].getTotalItems()} sản phẩm)</span>
                                <span>${sessionScope[userIdCart].temporaryPriceFormat()}</span>
                            </div>
                            <c:if test="${sessionScope[userIdCart].voucherApplied != null}">
                                <div class="discount__container">
                                    <span>Giảm giá</span>
                                    <span>${sessionScope[userIdCart].discountPriceFormat()}</span>
                                </div>
                            </c:if>
                            <c:if test="${sessionScope[userIdCart].deliveryMethod != null}">
                                <div class="shipping__container">
                                    <span>Phí vận chuyển</span>
                                    <span><fmt:setLocale value="vi_VN"/><fmt:formatNumber type="currency"
                                                                                          value="${sessionScope[userIdCart].deliveryMethod.shippingFee}"/></span>
                                </div>
                            </c:if>
                        </div>
                        <div class="total__price--final">
                            <span class="total__label">Tổng tiền</span>
                            <span class="total__value">${sessionScope[userIdCart].totalPriceFormat(true)}</span>
                        </div>
                    </div>
                    <div class="ground__button--forward">
                        <button class="place__order">Đặt hàng</button>
                        <a href="shoppingCart.jsp">
                            <button class="back--shopping__cart">Quay lại giỏ hàng</button>
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>
<div class="popup__deletion"></div>
<%@include file="footer.jsp" %>
</body>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"
        integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script src="js/base.js"></script>
<script src="js/checkout.js"></script>
<%--<script>--%>
<%--    ValidatorCustomizeDeliveryForm({--%>
<%--        form: '#customize__info--form',--%>
<%--        formBlockSelector: '.customize__item',--%>
<%--        errorSelector: '.error__notice',--%>
<%--        rules: [--%>
<%--            ValidatorCustomizeDeliveryForm.isRequired('#fullName'),--%>
<%--            ValidatorCustomizeDeliveryForm.isRequired('#phone'),--%>
<%--            ValidatorCustomizeDeliveryForm.isRequired('#address'),--%>
<%--            ValidatorCustomizeDeliveryForm.isRequired('#email'),--%>
<%--        ],--%>
<%--        onSubmit: function (dataInput) {--%>
<%--            &lt;%&ndash;const otherDeliveryLabelElement = document.querySelector('label[for="other__selection"]');&ndash;%&gt;--%>
<%--            &lt;%&ndash;otherDeliveryLabelElement.innerHTML = `<ul class="info__items">&ndash;%&gt;--%>
<%--            &lt;%&ndash;                                          <li class="info__item">Họ và tên: ${dataInput['full__name']}</li>&ndash;%&gt;--%>
<%--            &lt;%&ndash;                                            <li class="info__item">Email: ${dataInput['email']}</li>&ndash;%&gt;--%>
<%--            &lt;%&ndash;                                            <li class="info__item">Số điện thoại: ${dataInput['phone']}</li>&ndash;%&gt;--%>
<%--            &lt;%&ndash;                                            <li class="info__item">Địa chỉ: ${dataInput['address']}</li>&ndash;%&gt;--%>
<%--            &lt;%&ndash;                                       </ul>`;&ndash;%&gt;--%>
<%--        }--%>
<%--    });--%>
<%--</script>--%>
<script type="text/javascript">

    function handleChoiceDeliveryMethod() {
        $(document).ready(function () {
            $('input[name="delivery__method"]').change(function () {
                let action = $('#delivery__method--form input[type=hidden][name="action"]').val();
                let deliveryMethodId = $(this).val();
                $.ajax({
                    type: 'POST',
                    url: 'Checkout',
                    data: {
                        action: action,
                        deliveryMethodId: deliveryMethodId
                    },
                    dataType: 'json',
                    success: function (response) {
                        $(this).prop('checked', true);
                        $('.total__price--final .total__value').text(response.newTotalPrice);
                        $('.shipping__container span:last-child').text(response.shippingFee);
                        $('.amount__pay .amount').text(response.newTotalPrice);
                    }
                })
            })
        })
    }
    handleChoiceDeliveryMethod();

    function handleChoicePaymentMethod() {
        $(document).ready(function () {
            $('input[name="payment__method"]').change(function () {
                let action = $('#payment__method--form input[type=hidden][name="action"]').val();
                let paymentMethodId = $(this).val();
                $.ajax({
                    type: 'POST',
                    url: 'Checkout',
                    data: {
                        action: action,
                        paymentMethodId: paymentMethodId
                    },
                    dataType: 'json',
                    success: function (response) {
                        $('.transaction__content .content').text(response.contentForPay);
                    }
                })
            })
        })
    }
    handleChoicePaymentMethod();

    function handleChoiceDeliveryInfo() {
        $(document).ready(function () {
            $('#delivery__info--form').on('click', '.button__choice', function (event) {
                event.preventDefault();
                let buttonChoiceClicked = $(this);
                if (buttonChoiceClicked.text() === 'Chọn') {
                    let deliveryInfo = buttonChoiceClicked.closest('.delivery__info');
                    let deliveryInfoKey = deliveryInfo.find('input[type=hidden][name=deliveryInfoKey]').val();
                    let typeEdit = buttonChoiceClicked.val();
                    $.ajax({
                        type: 'POST',
                        url: 'Checkout',
                        data: {
                            typeEdit: typeEdit,
                            deliveryInfoKey: deliveryInfoKey
                        },
                        success: function (response) {
                            buttonChoiceClicked.text(response)
                            $('.button__choice').not(buttonChoiceClicked).text("Chọn")
                        }
                    })
                }
            })
        })
    }
    handleChoiceDeliveryInfo();

    function handleRemoveDeliveryInfo() {
        $(document).ready(function () {
            $('#delivery__info--form').on('click', '.button__remove', function (event) {
                event.preventDefault();
                let buttonRemoveClicked = $(this);
                let deliveryInfo = buttonRemoveClicked.closest('.delivery__info');
                let deliveryInfoKey = deliveryInfo.find('input[type=hidden][name=deliveryInfoKey]').val();
                let typeEdit = buttonRemoveClicked.val();

                let buttonChoice = deliveryInfo.find('.button__choice');
                let statusChoice = buttonChoice.text();

                const popupDeletion = $(document).find('.popup__deletion');
                popupDeletion.html(`<div class="popup__container">
                                        <div class="popup__content">
                                            <div class="title__header">
                                                <span class="title"><i class="fa-solid fa-triangle-exclamation"></i> Xóa thông tin giao hàng</span>
                                                <span class="subtitle">Bạn có muốn xóa thông tin giao hàng đang chọn?</span>
                                            </div>
                                            <div class="button__control">
                                                <button class="agree__button">Xác nhận</button>
                                                <button class="cancel__button">Hủy</button>
                                            </div>
                                        </div>
                                    </div>`);
                $(popupDeletion).find('.cancel__button').on('click', function (){
                    $(popupDeletion).find('.popup__container').remove();
                })

                $(popupDeletion).find('.agree__button').on('click', function (){
                    $.ajax({
                        type: 'POST',
                        url: 'Checkout',
                        data: {
                            typeEdit: typeEdit,
                            deliveryInfoKey: deliveryInfoKey,
                            statusChoice: statusChoice
                        },
                        success: function (response) {
                            $(popupDeletion).find('.popup__container').remove();
                            deliveryInfo.remove();
                            if (statusChoice === "Đã chọn") {
                                $('#default__info').find('.button__choice').text("Đã chọn")
                            }
                        }
                    })
                })
            })
        })
    }
    handleRemoveDeliveryInfo();

    function handlePlaceOrder(){
        $('#delivery__method--form input[class=radio__button][name=delivery__method]').change(function (){
            $('#payment__method--form input[class=radio__button][name=payment__method]').prop('disabled', false);
        })

        $('.place__order').on('click', function (){
            $.ajax({
                type: 'POST',
                url: 'PlaceOrder',
                data: {},
                dataType: 'json',
                success: function (response){
                    const popupOrder = `<div class="popup__order">
                                            <div class="bar__loading"></div>
                                            <p class="message__process">Hệ thống đang xử lý, vui lòng quý khách chờ trong vài giây và không đóng tab này. Trong trường hợp tab bị đóng thì quá trình hiện đang được xử lý sẽ thất bại</p>
                                        </div>`
                    $('.place__order').parent().append(popupOrder)
                    $(document).find('.ground__button--forward a').addClass('disabled-link')
                    $(document).find('.place__order').css('cursor', 'not-allowed').prop('disabled', 'false')
                    $(document).find('.radio__button').each(function (index){
                        $(this).css('cursor', 'not-allowed').prop('disabled', 'false')
                    })
                    $(document).find('.popup__order').css('display', 'block')
                    setTimeout(function() {
                        $(document).find('.popup__order').addClass('active');
                    }, 100);

                    setTimeout(function() {
                        let invoiceNo = response.invoiceNo;
                        let dateOrder = response.dateOrder;
                        window.location.href = "SuccessOrder?invoiceNo="+invoiceNo;

                        // $(document).find('.message__process').html(response);
                        // $(document).find('.loading__order').removeClass('loading__order').html(`<i class="fa-regular fa-circle-check"></i>`).css({'margin': '16px auto', 'fontSize': '80px'});
                        // $(document).find('.popup__order').append(`<a href="" style="padding: 14px 16px; background-color: #5ee95e; font-weight: 600; display: inline-block; margin: 16px 0px; border-radius: 6px">Đến trang đánh giá</a>`)
                    }, 3000);
                }
            })

            // console.log(1)
            // const radioSections = $(document).find('.radio__section');

            // let allSectionsSelected = true;
            // radioSections.each(function () {
            //     let radioSection = $(this);
            //     const radioButtons = radioSection.find('.radio__button');
            //     const errorMessage = radioSection.parent().find('.non__selected');
            //     let sectionSelected = false;
            //
            //     radioButtons.each(function (index) {
            //         let radioButton = $(this);
            //         if (radioButton.prop("checked")) {
            //             sectionSelected = true;
            //         }
            //     });
            //
            //     if (!sectionSelected) {
            //         allSectionsSelected = false;
            //         errorMessage.css('display', 'block');
            //     } else {
            //         errorMessage.css('display', 'none');
            //     }
            // });

            // if (allSectionsSelected) {
            //
            //
            // }
        })
        // const radioButtons = $(document).find('.radio__button');
        // radioButtons.change(function (){
        //     let radioButton = $(this);
        //     const radioSection = radioButton.closest(".radio__section");
        //     const errorMessage = radioSection.parent().find('.non__selected');
        //     errorMessage.css('display', 'none')
        // })
    }
    handlePlaceOrder();
</script>
</html>

