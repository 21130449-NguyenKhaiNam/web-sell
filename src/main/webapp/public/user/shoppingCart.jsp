<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="models.shoppingCart.AbstractCartProduct" %>
<%@ page import="utils.ProductFactory" %>
<%@ page import="models.shoppingCart.CartProduct" %>
<%@ page import="models.shoppingCart.CartProductCustom" %>
<%@ page import="models.User" %>
<%@ page import="models.shoppingCart.ShoppingCart" %>
<%@ page import="org.json.JSONObject" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="utils.FormatCurrency" %>
<%@ page import="services.image.CloudinaryUploadServices" %>
<%@ page import="session.SessionManager" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"
           uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="session.SessionManager" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <jsp:include page="/public/commonLink.jsp"/>
    <link rel="stylesheet" href="<c:url value="/assets/css/shoppingCart.css" />">
    <title>Giỏ hàng</title>
</head>

<body>
<c:import url="/public/header.jsp"/>
<main id="main">
    <!-- New update template -->
    <div class="promotion__modal">
        <div class="promotion__sidebar">
            <div class="promotion__header">
                <i
                        class="fa-solid fa-arrow-left"></i>
                <span>Mã giảm giá</span>
            </div>
            <div class="promotion__content">
                <c:forEach
                        items="${requestScope.listVouchers}"
                        var="voucher">
                    <div class="promotion__item">
                        <div class="discount__percent">
                            <i class="fa-solid fa-fire"></i>
                            <span>
<%--                                <fmt:formatNumber--%>
<%--                                        type="percent"--%>
<%--                                        value="${voucher.discountPercent}"/>--%>
                            </span>
                        </div>
                        <div class="item__content">
                            <h1 class="promotion__text">
                                NHẬP MÃ: ${voucher.code}
                            </h1>
                            <p>HSD:
                                <fmt:formatDate
                                        pattern="dd-MM-yyyy"
                                        value="${voucher.expiryDate}"/>
                            </p>
                            <p class="promotion__description">
                                    ${voucher.description}
                                <fmt:setLocale
                                        value="vi_VN"/>
                                <fmt:formatNumber
                                        type="currency"
                                        value="${voucher.minimumPrice}"/>
                            </p>
                            <button
                                    class="button__copy"
                                    data-code="${voucher.code}">Sao
                                chép
                                <i class="fa-solid fa-copy"></i></button>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <div class="promotion__footer">
                <button>Quay lại giỏ
                    hàng
                </button>
            </div>
        </div>
    </div>
    <!-- New update template -->

    <div class="container-xl">
        <h1 class="cart__title">Giỏ hàng</h1>
        <div class="cart__container row">
            <c:set var="sessionId"
                   value="${cookie['sessionId'].value}"/>
            <c:set var="auth"
                   value="${sessionScope.sessionUser[sessionId]}"/>
            <c:set var="userIdCart"
                   value="${String.valueOf(auth.id)}"/>
            <c:choose>
                <c:when
                        test="${sessionScope[userIdCart].getTotalItems() == 0 || sessionScope[userIdCart] == null}">
                    <div class="cart__container--empty">
                        <p>Không có sản phẩm nào
                            trong giỏ hàng của
                            bạn</p>
                        <a href="<c:url value="/public/product/productBuying.jsp" />">
                            <button>Tiếp tục mua
                                sắm
                            </button>
                        </a>
                        <img src="<c:url value="/assets/img/continueShopping.svg" />">
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="cart__content col">
                        <form
                                class="shopping__cart--form"
                                action="<c:url value="/api/cart"/>"
                                method="post">
                            <div
                                    class="d-flex w-100 check__header">
                                <input
                                        type="radio"
                                        id="check__pay-all"
                                        name="check-high"
                                        class="check-high"/>
                                <label
                                        for="check__pay-all"
                                        class="check__title">Lựa
                                    chọn toàn
                                    bộ</label>
                                <input
                                        type="radio"
                                        id="remove__pay-all"
                                        name="check-high"
                                        class="check-high"
                                        checked/>
                                <label
                                        for="remove__pay-all"
                                        class="check__title">Hủy chọn tất cả</label>
                            </div>
                            <table
                                    id="cart__table">
                                <thead
                                        class="cart__header">
                                <tr>
                                    <th>Lựa
                                        chọn
                                    </th>
                                    <th>Sản
                                        phẩm
                                    </th>
                                    <th>Giá
                                        may
                                    </th>
                                    <th>Số
                                        lượng
                                    </th>
                                    <th>Thành
                                        tiền
                                    </th>
                                    <th>Xóa
                                    </th>
                                </tr>
                                </thead>
                                <tbody class="cart__items">
                                <% String
                                        userIdCart = String.valueOf(SessionManager.getInstance(request,
                                        response).getUser().getId());
                                    Map<Integer,
                                            List<AbstractCartProduct>
                                            >
                                            shoppingCartMap
                                            =
                                            ((ShoppingCart)
                                                    request.getSession().getAttribute(userIdCart)).getShoppingCartMap();
                                    for
                                    (Integer
                                            productId
                                            :
                                            shoppingCartMap.keySet()) {
                                        for
                                        (AbstractCartProduct
                                                cartProduct
                                                :
                                                shoppingCartMap.get(productId)) {
                                            int
                                                    cartProductIndex
                                                    =
                                                    shoppingCartMap.get(productId).indexOf(cartProduct);%>

                                <tr class="cart__item"
                                    data-product-id="<%=productId%>"
                                    data-cart-product-index="<%=cartProductIndex%>">
                                    <td class="container__check__pay">
                                        <input
                                                name="product"
                                                value="<%=productId%>"
                                                type="checkbox"
                                                class="check__pay"/>
                                    </td>
                                    <td class="product__item">
                                        <div class="product__content">
                                            <a class="product__image"
                                               href="<c:url value="/showProductDetail" />?id=<%=productId%>">
                                                <img src='<%=ProductFactory.getListImagesByProductId(productId).get(0).getNameImage()%>'>
                                            </a>
                                            <div class="order__product--info">
                                                <a href="#"
                                                   class="product__name">
                                                    <%=cartProduct.getProduct().getName()%>
                                                </a>
                                                <span>
                                                    Màu sắc:
                                                    <p class="order__color d-inline">
                                                        <%=cartProduct.getColor().getCodeColor()%>
                                                    </p>
                                                </span>
                                                <span class="d-flex align-content-center">
                                                    Mã màu:
                                                    <p class=" d-inline-block ms-2"
                                                       style="background:  <%=cartProduct.getColor().getCodeColor()%>; width: 30px; height: 100%">
                                                    </p>
                                                </span>
                                                <ul class="d-block">
                                                    <span>
                                                        Kích thước:
                                                        <p class="order__size--specification d-inline">
                                                            <%=cartProduct.makeSizeFormat()%>
                                                        </p>
                                                    </span>
                                                </ul>
                                            </div>
                                        </div>
                                    </td>
                                    <td class="unit__price" data-price=" <%=cartProduct.getSewingPrice()%>">
                                        <%=cartProduct.sewingPriceFormat()%>
                                    </td>
                                    <td>
                                        <div class="quality__swapper">
                                            <button
                                                    type="submit"
                                                    class="minus__quality change__quality"
                                                    name="action"
                                                    value="decreaseQuantity">
                                                <i class="fa-solid fa-minus"></i></button>
                                            <input
                                                    type="number"
                                                    name="quality__required"
                                                    class="quality__required"
                                                    min="1"
                                                    value="<%=cartProduct.getQuantity()%>">
                                            <button
                                                    type="submit"
                                                    class="plus__quality change__quality"
                                                    name="action"
                                                    value="increaseQuantity">
                                                <i class="fa-solid fa-plus"></i></button>
                                        </div>
                                    </td>
                                    <td class="subtotal__item">
                                        <%=FormatCurrency.vietNamCurrency(cartProduct.getSubtotal())%>
                                    </td>
                                    <td class="remove__action">
                                        <button
                                                type="submit"
                                                name="action"
                                                value="removeCartProduct"
                                                class="remove__item">
                                            <i class="fa-solid fa-trash-can"></i></button>
                                    </td>
                                </tr>
                                <% }
                                }%>
                                </tbody>
                            </table>
                        </form>
                    </div>
                    <div
                            class="invoice__promotion col">
                        <c:set
                                var="temporaryPrice"
                                value="${sessionScope[userIdCart].getTemporaryPrice()}"/>
                        <div class="apply__promotion">
                            <h2>Khuyến mãi </h2>
                            <form
                                    id="promotion__form"
                                    action="<c:url value="/api/voucher/apply"/>"
                                    method="post">
                                <!-- New update template -->
                                <div class="promotion__all">
                                     <span>
                                         <i class="fa-solid fa-ticket-simple"></i>
                                            Mã giảm giá
                                     </span>
                                    <span>
                                        Xem tất cả
                                       <i class="fa-solid fa-chevron-right"></i>
                                    </span>
                                </div>
                                <!-- New update template -->
                                <div>
                                    <input
                                            type="hidden"
                                            name="temporaryPrice"
                                            value="">
                                    <input
                                            type="text"
                                            name="promotionCode"
                                            id="promotion__code"
                                            value="" required>
                                    <button
                                            type="submit"
                                            name="action"
                                            value="applyVoucher"
                                            id="apply">
                                        Áp dụng
                                    </button>
                                </div>
                                <div class="mt-2 w-100 d-block">
                                    <div id="apply__status">
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div
                                class="summary__invoice">
                            <h2>Tổng đơn hàng
                            </h2>
                            <div class="invoice__detail--info">
                                <ul class="price__items">
                                    <li class="price__item">
                                        <p class="price__text">
                                            Tạm tính
                                            (<span class="total__items" id="total__items">0</span>
                                            sp)
                                        </p>
                                        <p class="price__value" id="price__total">
                                            0 ₫
                                        </p>
                                    </li>
                                    <li class="price__item ">
                                        Của voucher
                                        <p class="price__text">
                                            Giảm giá
                                        </p>
                                        <p class="price__value " id="price__voucher">

                                        </p>
                                    </li>

                                </ul>
                                <div class="price__total">
                                    <p class="price__text">
                                        Tổng cộng:
                                    </p>
                                    <div class="price__content">
                                        <p class="price__value--final price__final" id="price__final">
                                            0 ₫
                                        </p>
                                        <p class="price__value--noted">
                                            (Đã bao gồm VAT nếu có)
                                        </p>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="group__button--forward">
                            <a id="continue--directional" href="<c:url value="/public/user/checkout.jsp" />">
                                <button id="continue--checkout">
                                    Tiến hành thanh toán
                                </button>
                            </a>
                            <a href="<c:url value="/public/product/productBuying.jsp" />">
                                <button id="continue--shopping">
                                    Tiến tục mua sắm
                                </button>
                            </a>
                        </div>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</main>
<c:import url="/public/footer.jsp"/>
</body>
<script src="<c:url value="/js/validateContactForm.js"/>">
    ValidatorContactForm({
        form: '#contact_us-form',
        formBlockSelector: '.form__block',
        errorSelector: '.error-notice',
        rules: [
            ValidatorContactForm.isRequired('#fullname'),
            ValidatorContactForm.isRequired('#email'),
            ValidatorContactForm.isEmail('#email')
        ],
        onSubmit: function (data) {
            console.log(data)
        }
    });
</script>
<script type="module" src="<c:url value="/js/shoppingCart1.js" />"></script>
<%--<script type="text/javascript">--%>
<%--    $(document).ready(function () {--%>
<%--        var voucherApply = {--%>
<%--            state: {},--%>
<%--            voucher: {},--%>
<%--            listIdProduct: [],--%>
<%--        };--%>
<%--        // Chuyển tiếp đến trang thanh toán--%>
<%--        $('#continue--directional').on('click', function (event) {--%>
<%--            event.preventDefault()--%>

<%--            let checked = false--%>
<%--            let data = []--%>
<%--            const checks = $('.check__pay')--%>
<%--            checks.each(function () {--%>
<%--                const check = $(this)--%>
<%--                const parent = $(this).parents("tr")--%>
<%--                if (check.prop('checked')) {--%>
<%--                    if (!checked)--%>
<%--                        checked = true--%>
<%--                    let obj = {--%>
<%--                        id: check.val(),--%>
<%--                        ind: parent.data("cartProductIndex"),--%>
<%--                        name: parent.find("a.product__name").text().trim(),--%>
<%--                        color: parent.find("p.order__color").text().trim(),--%>
<%--                        size: parent.find("p.order__size--specification").text().trim(),--%>
<%--                        count: parent.find("input.quality__required").val(),--%>
<%--                        price: parent.find("td.subtotal__item").text().replace('₫', '').replace('.', '').trim(),--%>
<%--                        voucher: voucherApply.voucher--%>
<%--                    }--%>
<%--                    data.push({--%>
<%--                        name: check.attr('name'),--%>
<%--                        value: JSON.stringify(obj),--%>
<%--                    });--%>
<%--                }--%>
<%--            })--%>

<%--            if (checked) {--%>
<%--                // Đã lựa chọn hàng--%>
<%--                let formData = $.param(data);--%>
<%--                let url = this.href--%>
<%--                $.ajax({--%>
<%--                    url: url,--%>
<%--                    data: formData,--%>
<%--                    processData: false,--%>
<%--                    contentType: false,--%>
<%--                    success: (data) => {--%>
<%--                        // Ghi lại toàn bộ nội dung của document--%>
<%--                        document.open();--%>
<%--                        document.write(data);--%>
<%--                        history.pushState(null, null, url);--%>
<%--                        document.close();--%>
<%--                    },--%>
<%--                    error: (err) => {--%>
<%--                        console.log(err)--%>
<%--                    }--%>
<%--                })--%>
<%--            } else {--%>
<%--                Swal.fire({--%>
<%--                    icon: "error",--%>
<%--                    title: "Oops...",--%>
<%--                    text: "Vui lòng lựa chọn món hàng muốn thanh toán",--%>
<%--                });--%>
<%--            }--%>

<%--        })--%>

<%--        function checkPayHigh(isAll = false) {--%>
<%--            if (isAll)--%>
<%--                $('.check__pay').prop('checked', true)--%>

<%--            $('#check__pay-all').on('click', function () {--%>
<%--                $('.check__pay').prop('checked', true)--%>
<%--                let comTotalItem = $('.total__items')[0]--%>
<%--                comTotalItem.innerText = $('.check__pay').length--%>
<%--                updatePrice();--%>
<%--            })--%>

<%--            $('#remove__pay-all').on('click', function () {--%>
<%--                $('.check__pay').prop('checked', false)--%>
<%--                let comTotalItem = $('.total__items')[0]--%>
<%--                comTotalItem.innerText = 0--%>
<%--                let comPriceTotal = $('.price__value')[0]--%>
<%--                comPriceTotal.innerText = 0 + '₫'--%>
<%--                updatePrice();--%>
<%--            })--%>
<%--        }--%>

<%--        checkPayHigh()--%>

<%--        function handelPay() {--%>
<%--            let isClick = false;--%>

<%--            $('.check__pay').on('click', function () {--%>
<%--                isClick = true--%>
<%--                let myCom = $(this)--%>
<%--                let checkPay = myCom.prop('checked')--%>
<%--                let comTotalItem = $('.total__items')[0]--%>
<%--                let totalItem = parseInt(comTotalItem.innerText) || 0--%>
<%--                if (checkPay) {--%>
<%--                    // Thêm sản phẩm--%>
<%--                    comTotalItem.innerText = totalItem + 1--%>
<%--                } else {--%>
<%--                    // Hủy bỏ sản phẩm--%>
<%--                    comTotalItem.innerText = totalItem - 1--%>
<%--                }--%>
<%--                myCom.prop('checked', checkPay);--%>
<%--                updatePrice();--%>
<%--            })--%>

<%--            $('.container__check__pay').on('click', function () {--%>
<%--                const checkbox = $(this).find('.check__pay');--%>
<%--                if (!isClick) {--%>
<%--                    checkbox.prop('checked', !checkbox.prop('checked'));--%>
<%--                }--%>
<%--                isClick = false--%>
<%--                updatePrice();--%>
<%--            });--%>
<%--        }--%>

<%--        handelPay()--%>

<%--        function increaseQuantityCartProduct() {--%>
<%--            $(document).ready(function () {--%>
<%--                $('.plus__quality').on('click', function (event) {--%>
<%--                    event.preventDefault();--%>
<%--                    let cartItem = $(this).closest('.cart__item');--%>
<%--                    let productId = cartItem.data("productId");--%>
<%--                    let cartProductIndex = cartItem.data("cartProductIndex");--%>
<%--                    let cartForm = $(document).find('.shopping__cart--form');--%>
<%--                    let action = $(this).val();--%>
<%--                    $.ajax({--%>
<%--                        url: cartForm.attr('action'),--%>
<%--                        type: cartForm.attr('method'),--%>
<%--                        data: {--%>
<%--                            action: action,--%>
<%--                            productId: productId,--%>
<%--                            cartProductIndex: cartProductIndex--%>
<%--                        },--%>
<%--                        dataType: 'json',--%>
<%--                        success: function (response) {--%>
<%--                            let quantitySwapper = $(cartItem).find('.quality__swapper');--%>
<%--                            let quantityRequired = $(quantitySwapper).find('.quality__required');--%>
<%--                            quantityRequired.val(response.newQuantity);--%>
<%--                            //--%>
<%--                            // let subtotalItem = $(cartItem).find('.subtotal__item');--%>
<%--                            // subtotalItem.text(response.newSubtotalFormat);--%>
<%--                            //--%>
<%--                            // let temporaryPrice = $(document).find('.price__item:first-child .price__value')--%>
<%--                            // temporaryPrice.text(response.newTemporaryPriceFormat)--%>
<%--                            //--%>
<%--                            // let totalPrice = $(document).find('.price__value--final')--%>
<%--                            // totalPrice.text(response.newTotalPriceFormat);--%>
<%--                            //--%>
<%--                            // const applyStatus = $(document).find('.apply__status')--%>
<%--                            // if (response.successApplied) {--%>
<%--                            //     $(applyStatus).html(`<span class="apply__success"><i class="fa-solid fa-circle-check"></i><span>` + response.successApplied + `</span></span>`)--%>
<%--                            //     $(document).find('.price__items .price__item:last-child').html(`<p class="price__text">Giảm giá</p><p class="price__value">` + response.discountPriceFormat + `</p>`);--%>
<%--                            //     $(document).find('.price__value--final').text(response.newTotalPriceFormat)--%>
<%--                            // } else if (response.failedApply) {--%>
<%--                            //     $(applyStatus).html(`<span class="apply__failed"><i class="fa-solid fa-circle-exclamation"></i><span>` + response.failedApply + `</span></span>`)--%>
<%--                            // }--%>
<%--                            updatePrice()--%>
<%--                        }--%>
<%--                    })--%>
<%--                })--%>
<%--            });--%>
<%--        }--%>

<%--        increaseQuantityCartProduct();--%>

<%--        function decreaseQuantityCartProduct() {--%>
<%--            $(document).ready(function () {--%>
<%--                $('.minus__quality').on('click', function (event) {--%>
<%--                    event.preventDefault();--%>
<%--                    let cartItem = $(this).closest('.cart__item');--%>
<%--                    let productId = cartItem.data("productId");--%>
<%--                    let cartProductIndex = cartItem.data("cartProductIndex");--%>
<%--                    let cartForm = $(document).find('.shopping__cart--form');--%>
<%--                    let action = $(this).val();--%>
<%--                    $.ajax({--%>
<%--                        url: cartForm.attr('action'),--%>
<%--                        type: cartForm.attr('method'),--%>
<%--                        data: {--%>
<%--                            action: action,--%>
<%--                            productId: productId,--%>
<%--                            cartProductIndex: cartProductIndex--%>
<%--                        },--%>
<%--                        dataType: 'json',--%>
<%--                        success: function (response) {--%>
<%--                            let quantitySwapper = $(cartItem).find('.quality__swapper');--%>
<%--                            let quantityRequired = $(quantitySwapper).find('.quality__required');--%>
<%--                            quantityRequired.val(response.newQuantity);--%>

<%--                            // let subtotalItem = $(cartItem).find('.subtotal__item');--%>
<%--                            // subtotalItem.text(response.newSubtotalFormat);--%>
<%--                            //--%>
<%--                            // let temporaryPrice = $(document).find('.price__item:first-child .price__value')--%>
<%--                            // temporaryPrice.text(response.newTemporaryPriceFormat)--%>
<%--                            //--%>
<%--                            // let totalPrice = $(document).find('.price__value--final')--%>
<%--                            // totalPrice.text(response.newTotalPriceFormat);--%>
<%--                            //--%>
<%--                            // if (response.discountPrice !== 0) {--%>
<%--                            //     $(document).find('.price__items .price__item:last-child').html(`<p class="price__text">Giảm giá</p><p class="price__value">` + response.discountPriceFormat + `</p>`);--%>
<%--                            // }--%>
<%--                            //--%>
<%--                            // const applyStatus = $(document).find('.apply__status')--%>
<%--                            // if (response.failedApply) {--%>
<%--                            //     $(applyStatus).html(`<span class="apply__failed"><i class="fa-solid fa-circle-exclamation"></i><span>` + response.failedApply + `</span></span>`)--%>
<%--                            //     $(document).find('.price__items .price__item:last-child').html("");--%>
<%--                            // }--%>
<%--                            updatePrice();--%>
<%--                        }--%>
<%--                    })--%>
<%--                })--%>
<%--            });--%>
<%--        }--%>

<%--        decreaseQuantityCartProduct();--%>

<%--        function deleteCartProduct() {--%>
<%--            $(document).ready(function () {--%>
<%--                $('.remove__item').on('click', function (event) {--%>
<%--                    event.preventDefault();--%>
<%--                    let cartItem = $(this).closest('.cart__item');--%>
<%--                    let productId = cartItem.data("productId");--%>
<%--                    let cartProductIndex = cartItem.data("cartProductIndex");--%>
<%--                    let cartForm = $(document).find('.shopping__cart--form');--%>
<%--                    let action = $(this).val();--%>

<%--                    const popupDeletion = $(document).find('.popup__deletion');--%>
<%--                    popupDeletion.html(`<div class="popup__container">--%>
<%--                                        <div class="popup__content">--%>
<%--                                            <div class="title__header">--%>
<%--                                                <span class="title"><i class="fa-solid fa-triangle-exclamation"></i> Xóa sản phẩm khỏi giỏ hàng</span>--%>
<%--                                                <span class="subtitle">Bạn có muốn xóa sản phẩm đang chọn?</span>--%>
<%--                                            </div>--%>
<%--                                            <div class="button__control">--%>
<%--                                                <button class="agree__button">Xác nhận</button>--%>
<%--                                                <button class="cancel__button">Hủy</button>--%>
<%--                                            </div>--%>
<%--                                        </div>--%>
<%--                                    </div>`);--%>
<%--                    $(popupDeletion).find('.cancel__button').on('click', function () {--%>
<%--                        $(popupDeletion).find('.popup__container').remove();--%>
<%--                    })--%>

<%--                    $(popupDeletion).find('.agree__button').on('click', function () {--%>
<%--                        $.ajax({--%>
<%--                            url: cartForm.attr('action'),--%>
<%--                            type: cartForm.attr('method'),--%>
<%--                            data: {--%>
<%--                                action: action,--%>
<%--                                productId: productId,--%>
<%--                                cartProductIndex: cartProductIndex--%>
<%--                            },--%>
<%--                            dataType: 'json',--%>
<%--                            success: function (response) {--%>
<%--                                $(popupDeletion).find('.popup__container').remove();--%>
<%--                                $(cartItem).remove();--%>
<%--                                // $(document).find('.qlt__value').text(response.newTotalItems)--%>
<%--                                // $(document).find('.total__items').text(response.newTotalItems)--%>
<%--                                if (response.newTotalItems === 0) {--%>
<%--                                    $(document).find('.cart__container').html(`<div class="cart__container--empty">--%>
<%--                                                                                <p>Không có sản phẩm nào trong giỏ hàng của bạn</p>--%>
<%--                                                                                <a href="../product/productBuying.jsp"><button>Tiếp tục mua sắm</button></a>--%>
<%--                                                                                <img src="../../assets/img/continueShopping.svg">--%>
<%--                                                                            </div>`);--%>
<%--                                } else {--%>
<%--                                    // let temporaryPrice = $(document).find('.price__item:first-child .price__value')--%>
<%--                                    // temporaryPrice.text(response.newTemporaryPriceFormat)--%>
<%--                                    //--%>
<%--                                    // let totalPrice = $(document).find('.price__value--final')--%>
<%--                                    // totalPrice.text(response.newTotalPriceFormat);--%>

<%--                                    // if (response.discountPrice !== 0) {--%>
<%--                                    //     $(document).find('.price__items .price__item:last-child').html(`<p class="price__text">Giảm giá</p><p class="price__value">` + response.discountPriceFormat + `</p>`);--%>
<%--                                    // }--%>

<%--                                    // const applyStatus = $(document).find('.apply__status')--%>
<%--                                    // if (response.failedApply) {--%>
<%--                                    //     $(applyStatus).html(`<span class="apply__failed"><i class="fa-solid fa-circle-exclamation"></i><span>` + response.failedApply + `</span></span>`)--%>
<%--                                    //     $(document).find('.price__items .price__item:last-child').html("");--%>
<%--                                    // }--%>
<%--                                }--%>
<%--                                updatePrice();--%>
<%--                            },--%>
<%--                            error: function (err) {--%>
<%--                                console.log(err)--%>
<%--                            }--%>
<%--                        })--%>
<%--                    })--%>
<%--                })--%>
<%--            });--%>
<%--        }--%>

<%--        deleteCartProduct()--%>

<%--        function applyCodeVoucher() {--%>
<%--            $(document).ready(function () {--%>
<%--                $('#promotion__form').on('submit', function (event) {--%>
<%--                    const promotionForm = $(this);--%>
<%--                    // const buttonApply = $(promotionForm).find('#apply');--%>
<%--                    const promotionCodeInput = $(promotionForm).find('#promotion__code')--%>
<%--                    // const temporaryPriceInputHidden = $(promotionForm).find('input[type=hidden][name=temporaryPrice]')--%>
<%--                    // const action = buttonApply.val();--%>
<%--                    // let promotionCode = promotionCodeInput.val();--%>
<%--                    // let temporaryPrice = temporaryPriceInputHidden.val();--%>
<%--                    event.preventDefault();--%>
<%--                    $.ajax({--%>
<%--                        url: promotionForm.attr('action'),--%>
<%--                        type: promotionForm.attr('method'),--%>
<%--                        data: {--%>
<%--                            code: promotionCodeInput.val(),--%>
<%--                            id: getProductListId(),--%>
<%--                        },--%>
<%--                        dataType: 'json',--%>
<%--                        success: function (response) {--%>
<%--                            if (!response.success) {--%>
<%--                                voucherApply.state = getVoucherState(5);--%>
<%--                            } else {--%>
<%--                                const state = getVoucherState(response.result.state);--%>
<%--                                voucherApply.state = state;--%>
<%--                                voucherApply.voucher = response.result.voucher;--%>
<%--                                voucherApply.listIdProduct = response.result.listIdProduct;// Lưu lại danh sách id sản phẩm có thể áp dụng voucher--%>
<%--                            }--%>
<%--                            updatePrice();--%>
<%--                        }--%>
<%--                    });--%>
<%--                })--%>
<%--            })--%>
<%--        }--%>

<%--        applyCodeVoucher();--%>

<%--        // Cập nhập giá khi tăng, giảm, xóa sản phẩm, khi áp dụng voucher--%>
<%--        function updatePrice() {--%>
<%--            console.log("listIdProduct: ", voucherApply.listIdProduct)--%>
<%--            const totalItem = $(".cart__item:has(input.check__pay:checked)").length;--%>
<%--            const cartItemElements = document.querySelectorAll(".cart__item:has(input.check__pay:checked)");--%>
<%--            const totalPriceCanApplyVoucher = [...cartItemElements].map((item) => {--%>
<%--                const productId = $(item).data("product-id");--%>
<%--                if (!voucherApply.listIdProduct?.includes(productId)) {--%>
<%--                    return 0;--%>
<%--                }--%>
<%--                const quantityProduct = $(item).find(".quality__required").val();--%>
<%--                const priceUnit = convertToNumber($(item).find(".unit__price").text());--%>
<%--                return quantityProduct * priceUnit;--%>
<%--            }).reduce((acc, cur) => acc + cur, 0);--%>
<%--            if (voucherApply.voucher) {--%>
<%--                if (totalPriceCanApplyVoucher < voucherApply.voucher.minimumPrice)--%>
<%--                    updateVoucherState(getVoucherState(6));--%>
<%--                else--%>
<%--                    updateVoucherState(voucherApply.state);--%>
<%--            }--%>
<%--            const totalPrice = [...cartItemElements].map((item) => {--%>
<%--                const quantityProduct = $(item).find(".quality__required").val();--%>
<%--                const priceUnit = convertToNumber($(item).find(".unit__price").text());--%>
<%--                return quantityProduct * priceUnit;--%>
<%--            }).reduce((acc, cur) => acc + cur, 0);--%>

<%--            const priceVoucher = voucherApply.voucher?.discountPercent ? voucherApply.voucher.discountPercent * totalPrice : 0;--%>
<%--            const finalPrice = totalPrice - priceVoucher;--%>
<%--            $("#total__items").text(totalItem);--%>
<%--            $("#price__total").text(formatCurrencyVND(totalPrice));--%>
<%--            $("#price__voucher").text(priceVoucher ? formatCurrencyVND(priceVoucher) : "");--%>
<%--            $("#price__final").text(formatCurrencyVND(finalPrice));--%>
<%--        }--%>

<%--        function convertToNumber(currency) {--%>
<%--            let withoutCurrencySymbol = currency.replace("₫", "").trim();--%>

<%--            let cleanedString = withoutCurrencySymbol.replace(/\./g, "");--%>

<%--            return Number(cleanedString);--%>
<%--        }--%>

<%--        function formatCurrencyVND(amount) {--%>
<%--            // Create a NumberFormat object with Vietnamese locale and currency style--%>
<%--            const formatter = new Intl.NumberFormat('vi-VN', {--%>
<%--                style: 'currency',--%>
<%--                currency: 'VND'--%>
<%--            });--%>

<%--            // Format the amount using the NumberFormat object--%>
<%--            return formatter.format(amount);--%>
<%--        }--%>

<%--        handleOpenSidebarVoucher();--%>

<%--        // xử lý cho sidebar voucher--%>
<%--        function handleOpenSidebarVoucher() {--%>
<%--            const promotionSidebar = document.querySelector(".promotion__sidebar")--%>
<%--            const promotionDisplayAll = document.querySelector(".promotion__all span:last-child");--%>
<%--            const iconBackShoppingCart = document.querySelector(".promotion__header i");--%>
<%--            const buttonBackShoppingCart = document.querySelector(".promotion__footer button")--%>
<%--            const promotionContent = $(".promotion__content");--%>

<%--            promotionDisplayAll.addEventListener("click", () => {--%>
<%--                promotionSidebar.classList.add("visible");--%>
<%--                const listIdProduct = getProductListId();--%>
<%--                handleGetVouchers(listIdProduct);--%>
<%--            })--%>

<%--            iconBackShoppingCart.addEventListener("click", () => {--%>
<%--                promotionSidebar.classList.remove("visible")--%>
<%--                promotionContent.html("");--%>
<%--            })--%>

<%--            buttonBackShoppingCart.addEventListener("click", () => {--%>
<%--                promotionSidebar.classList.remove("visible")--%>
<%--                promotionContent.html("");--%>
<%--            })--%>
<%--        }--%>

<%--        function getProductListId() {--%>
<%--            const selectorCartItems = "[data-product-id]:has(input.check__pay:checked)";--%>
<%--            return Array.from(document.querySelectorAll(selectorCartItems)).map(productItem => productItem.getAttribute("data-product-id"));--%>
<%--        }--%>

<%--        // Lấy danh sách voucher--%>
<%--        function handleGetVouchers(listIdProduct) {--%>
<%--            $.ajax({--%>
<%--                url: "/api/voucher/getAll",--%>
<%--                type: "GET",--%>
<%--                data: {--%>
<%--                    id: listIdProduct,--%>
<%--                },--%>
<%--                success: function (data) {--%>
<%--                    if (data.success && data.vouchers) {--%>
<%--                        const promotionContent = $(".promotion__content");--%>
<%--                        console.log(data.vouchers)--%>
<%--                        promotionContent.html(loadVoucher(data.vouchers));--%>
<%--                        handleCopyDiscountCode();--%>
<%--                    }--%>
<%--                },--%>
<%--            })--%>
<%--        }--%>

<%--        function handleCopyDiscountCode() {--%>
<%--            const copyButtonElements = document.querySelectorAll(".button__copy");--%>
<%--            copyButtonElements.forEach(copyButtonElement => {--%>
<%--                let originalContent = copyButtonElement.innerHTML;--%>
<%--                copyButtonElement.addEventListener('click', () => {--%>
<%--                    copyButtonElement.innerHTML = `Đã sao chép <i class="fa-solid fa-copy"></i>`;--%>
<%--                    setTimeout(() => {--%>
<%--                        copyButtonElement.innerHTML = originalContent;--%>
<%--                    }, 1000);--%>

<%--                    const codeToCopy = copyButtonElement.getAttribute('data-code');--%>
<%--                    copyToClipboard(codeToCopy)--%>
<%--                        .then(() => {--%>
<%--                            console.log(codeToCopy);--%>
<%--                        })--%>
<%--                        .catch(error => {--%>
<%--                            console.error("Không thể sao chép: ", error);--%>
<%--                        });--%>
<%--                })--%>
<%--            })--%>

<%--            async function copyToClipboard(text) {--%>
<%--                try {--%>
<%--                    await navigator.clipboard.writeText(text);--%>
<%--                } catch (error) {--%>
<%--                    throw new Error("Không thể sao chép vào clipboard: ", error);--%>
<%--                }--%>
<%--            }--%>
<%--        }--%>

<%--        function loadVoucher(listVoucher) {--%>
<%--            return listVoucher.map(voucher => {--%>
<%--                return ` <div class="promotion__item">--%>
<%--                        <div class="discount__percent">--%>
<%--                            <i class="fa-solid fa-fire"></i>--%>
<%--                            <span>--%>
<%--                                \${voucher.discountPercent}--%>
<%--                            </span>--%>
<%--                        </div>--%>
<%--                        <div class="item__content">--%>
<%--                            <h1 class="promotion__text">--%>
<%--                                NHẬP MÃ:--%>
<%--                                    \${voucher.code}--%>
<%--                            </h1>--%>
<%--                            <p>HSD:  \${voucher.expiryDate}--%>
<%--                            </p>--%>
<%--                            <p class="promotion__description">--%>
<%--                                    \${voucher.description}--%>
<%--                           \${formatCurrencyVND(voucher.minimumPrice)}--%>
<%--                            </p>--%>
<%--                            <button class="button__copy"--%>
<%--                                    data-code="\${voucher.code}">Sao--%>
<%--                                chép--%>
<%--                                <i class="fa-solid fa-copy"></i></button>--%>
<%--                        </div>--%>
<%--                    </div>`--%>
<%--            })--%>
<%--        }--%>

<%--        function getVoucherState(state) {--%>
<%--            const voucherState = [--%>
<%--                {--%>
<%--                    state: 1,--%>
<%--                    className: "success",--%>
<%--                    message: "Áp dụng mã giảm giá thành công",--%>
<%--                }, {--%>
<%--                    state: 2,--%>
<%--                    className: "warning",--%>
<%--                    message: "Mã giảm giá không tìm thấy",--%>
<%--                }, {--%>
<%--                    state: 3,--%>
<%--                    className: "warning",--%>
<%--                    message: "Hết lượt sử dụng mã giảm giá",--%>
<%--                }, {--%>
<%--                    state: 4,--%>
<%--                    className: "warning",--%>
<%--                    message: "Mã giảm giá đã hết hạn",--%>
<%--                }, {--%>
<%--                    state: 5,--%>
<%--                    className: "danger",--%>
<%--                    message: "Mã giảm giá không áp dụng cho đơn hàng này",--%>
<%--                },--%>
<%--                {--%>
<%--                    state: 6,--%>
<%--                    className: "danger",--%>
<%--                    message: "Số tiền đơn hàng không đủ để áp dụng mã giảm giá",--%>
<%--                },--%>
<%--            ];--%>
<%--            return voucherState.find(voucher => voucher.state == state);--%>
<%--        }--%>

<%--        function updateVoucherState(voucherState) {--%>
<%--            if (!voucherState) return;--%>
<%--            $("#apply__status").removeClass();--%>
<%--            $("#apply__status").text("");--%>
<%--            $("#apply__status").addClass("alert alert-" + voucherState.className).text(voucherState.message);--%>
<%--        }--%>
<%--    })--%>
<%--</script>--%>

</html>