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
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="/public/commonLink.jsp"/>
    <link rel="stylesheet" href="<c:url value="/assets/css/shoppingCart.css"/>">
    <title>Giỏ hàng</title>
</head>
<body>
<c:import url="/public/header.jsp"/>
<main id="main">
    <!-- New update template -->
    <div class="promotion__modal">
        <div class="promotion__sidebar">
            <div class="promotion__header">
                <i class="fa-solid fa-arrow-left"></i>
                <span>Mã giảm giá</span>
            </div>
            <div class="promotion__content">
                <c:forEach items="${requestScope.listVouchers}" var="voucher">
                    <div class="promotion__item">
                        <div class="discount__percent">
                            <i class="fa-solid fa-fire"></i>
                            <span><fmt:formatNumber type="percent" value="${voucher.discountPercent}"/></span>
                        </div>
                        <div class="item__content">
                            <h1 class="promotion__text">NHẬP MÃ: ${voucher.code}</h1>
                            <p>HSD: <fmt:formatDate pattern="dd-MM-yyyy" value="${voucher.expiryDate}"/></p>
                            <p class="promotion__description">${voucher.description} <fmt:setLocale value="vi_VN"/>
                                <fmt:formatNumber type="currency" value="${voucher.minimumPrice}"/></p>
                            <button class="button__copy" data-code="${voucher.code}">Sao chép <i
                                    class="fa-solid fa-copy"></i></button>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <div class="promotion__footer">
                <button>Quay lại giỏ hàng</button>
            </div>
        </div>
    </div><!-- New update template -->

    <div class="container-xl">
        <h1 class="cart__title">Giỏ hàng</h1>
        <div class="cart__container row">
            <c:set var="userIdCart" value="${String.valueOf(sessionScope.auth.id)}"/>
            <c:choose>
                <c:when test="${sessionScope[userIdCart].getTotalItems() == 0 || sessionScope[userIdCart] == null}">
                    <div class="cart__container--empty">
                        <p>Không có sản phẩm nào trong giỏ hàng của bạn</p>
                        <a href="<c:url value="/public/product/productBuying.jsp" />">
                            <button>Tiếp tục mua sắm</button>
                        </a>
                        <img src="<c:url value="/assets/img/continueShopping.svg" />">
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="cart__content col">
                        <form class="shopping__cart--form" action="/ShoppingCart" method="post">
                            <table id="cart__table">
                                <thead class="cart__header">
                                <tr>
                                    <th>Sản phẩm</th>
                                    <th>Giá may</th>
                                    <th>Số lượng</th>
                                    <th>Thành tiền</th>
                                    <th>Xóa</th>
                                </tr>
                                </thead>
                                <tbody class="cart__items">
                                <%
                                    String userIdCart = String.valueOf(((User) session.getAttribute("auth")).getId());
                                    Map<Integer, List<AbstractCartProduct>> shoppingCartMap = ((ShoppingCart) request.getSession().getAttribute(userIdCart)).getShoppingCartMap();
                                    for (Integer productId : shoppingCartMap.keySet()) {
                                        for (AbstractCartProduct cartProduct : shoppingCartMap.get(productId)) {
                                            int cartProductIndex = shoppingCartMap.get(productId).indexOf(cartProduct);
                                %>

                                <tr class="cart__item" data-product-id="<%=productId%>"
                                    data-cart-product-index="<%=cartProductIndex%>">
                                    <td class="product__item">
                                        <div class="product__content">
                                            <a class="product__image"
                                               href="<c:url value="/showProductDetail"/>?id=<%=productId%>">
                                                <img src='<c:url value="/assets/img/product_img/" /><%=ProductFactory.getListImagesByProductId(productId).get(0).getNameImage()%>'>
                                            </a>
                                            <div class="order__product--info">
                                                <a href="#"
                                                   class="product__name"><%=cartProduct.getProduct().getName()%>
                                                </a>
                                                <p class="order__color">Màu
                                                    sắc: <%=cartProduct.getColor().getCodeColor()%>
                                                </p>
                                                <ul class="order__size--specification">
                                                    <%=cartProduct.makeSizeFormat()%>
                                                </ul>
                                            </div>
                                        </div>
                                    </td>
                                    <td class="unit__price"><%=cartProduct.sewingPriceFormat()%>
                                    </td>
                                    <td>
                                        <div class="quality__swapper">
                                            <button type="submit" class="minus__quality change__quality" name="action"
                                                    value="decreaseQuantity"><i class="fa-solid fa-minus"></i></button>
                                            <input type="number" name="quality__required" class="quality__required"
                                                   min="1" value="<%=cartProduct.getQuantity()%>">
                                            <button type="submit" class="plus__quality change__quality" name="action"
                                                    value="increaseQuantity"><i class="fa-solid fa-plus"></i></button>
                                        </div>
                                    </td>
                                    <td class="subtotal__item"><%=FormatCurrency.vietNamCurrency(cartProduct.getSubtotal())%>
                                    </td>
                                    <td class="remove__action">
                                        <button type="submit" name="action" value="removeCartProduct"
                                                class="remove__item"><i class="fa-solid fa-trash-can"></i></button>
                                    </td>
                                </tr>
                                <%

                                        }
                                    }
                                %>
                                </tbody>
                            </table>
                                <%--                            <!-- New update template -->--%>
                                <%--                            <div class="order__note">--%>
                                <%--                                <label for="area__note">Ghi chú đơn hàng</label>--%>
                                <%--                                <textarea id="area__note" rows="6" name="area__note" placeholder="Ghi chú">${sessionScope[userIdCart].noteOrder != null ? sessionScope[userIdCart].noteOrder : ""}</textarea>--%>
                                <%--                            </div><!-- New update template -->--%>
                        </form>
                    </div>
                    <div class="invoice__promotion col">
                        <c:set var="temporaryPrice" value="${sessionScope[userIdCart].getTemporaryPrice()}"/>
                        <div class="apply__promotion">
                            <h2>Khuyến mãi</h2>
                            <form id="promotion__form" action="/ShoppingCart" method="post">
                                <!-- New update template -->
                                <div class="promotion__all">
                                    <span><i class="fa-solid fa-ticket-simple"></i> Mã giảm giá</span>
                                    <span>Xem tất cả <i class="fa-solid fa-chevron-right"></i></span>
                                </div> <!-- New update template -->
                                <div>
                                    <input type="hidden" name="temporaryPrice" value="${temporaryPrice}">
                                    <input type="text" name="promotionCode" id="promotion__code"
                                           value="${sessionScope.promotionCode != null ? sessionScope.promotionCode : ""}">
                                    <button type="submit" name="action" value="applyVoucher" id="apply">Áp dụng</button>
                                </div>
                                <div class="apply__status">
                                        <%--                                    <fmt:setLocale value="vi_VN"/>--%>
                                    <c:if test="${sessionScope.successApplied != null}">
                                        <span class="apply__success"><i
                                                class="fa-solid fa-circle-check"></i> <span>${sessionScope.successApplied}</span></span>
                                    </c:if>
                                    <c:if test="${sessionScope.failedApply != null}">
                                        <span class="apply__failed"><i
                                                class="fa-solid fa-circle-exclamation"></i> <span>${sessionScope.failedApply}</span></span>
                                    </c:if>
                                </div>
                            </form>
                        </div>
                        <div class="summary__invoice">
                            <h2>Tổng đơn hàng</h2>
                            <div class="invoice__detail--info">
                                <ul class="price__items">
                                    <li class="price__item">
                                        <p class="price__text">Tạm tính (<span
                                                class="total__items">${sessionScope[userIdCart].getTotalItems()}</span>
                                            sp)</p>
                                        <p class="price__value">${sessionScope[userIdCart].temporaryPriceFormat()}</p>
                                    </li>
                                    <li class="price__item">
                                        <c:if test="${sessionScope[userIdCart].getDiscountPrice() != 0}">
                                            <p class="price__text">Giảm giá</p>
                                            <p class="price__value">${sessionScope[userIdCart].discountPriceFormat()}</p>
                                        </c:if>
                                    </li>

                                </ul>
                                <div class="price__total">
                                    <p class="price__text">Tổng cộng:</p>
                                    <div class="price__content">
                                        <p class="price__value--final">${sessionScope[userIdCart].totalPriceFormat(false)}</p>
                                        <p class="price__value--noted">(Đã bao gồm VAT nếu có)</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <span>Phí vận chuyển sẽ được tính ở trang thanh toán</span>
                        <div class="group__button--forward">
                            <a href="/public/user/checkout.jsp">
                                <button id="continue--checkout">Tiến hành thanh toán</button>
                            </a>
                            <a href="/public/product/productBuying.jsp">
                                <button id="continue--shopping">Tiến tục mua sắm</button>
                            </a>
                        </div>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</main>
<div class="popup__deletion"></div>
</body>
<script src="<c:url value="/js/base.js"/>"></script>
<script src="<c:url value="/js/validateContactForm.js"/>"></script>
<script>
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
<script src="<c:url value="/js/shoppingCart.js"/>"></script>
<script type="text/javascript">
    function increaseQuantityCartProduct() {
        $(document).ready(function () {
            $('.plus__quality').on('click', function (event) {
                event.preventDefault();
                let cartItem = $(this).closest('.cart__item');
                let productId = cartItem.data("productId");
                let cartProductIndex = cartItem.data("cartProductIndex");
                let cartForm = $(document).find('.shopping__cart--form');
                let action = $(this).val();
                $.ajax({
                    url: cartForm.attr('action'),
                    type: cartForm.attr('method'),
                    data: {
                        action: action,
                        productId: productId,
                        cartProductIndex: cartProductIndex
                    },
                    dataType: 'json',
                    success: function (response) {
                        let quantitySwapper = $(cartItem).find('.quality__swapper');
                        let quantityRequired = $(quantitySwapper).find('.quality__required');
                        quantityRequired.val(response.newQuantity);

                        let subtotalItem = $(cartItem).find('.subtotal__item');
                        subtotalItem.text(response.newSubtotalFormat);

                        let temporaryPrice = $(document).find('.price__item:first-child .price__value')
                        temporaryPrice.text(response.newTemporaryPriceFormat)

                        let totalPrice = $(document).find('.price__value--final')
                        totalPrice.text(response.newTotalPriceFormat);

                        const applyStatus = $(document).find('.apply__status')
                        if (response.successApplied) {
                            $(applyStatus).html(`<span class="apply__success"><i class="fa-solid fa-circle-check"></i><span>` + response.successApplied + `</span></span>`)
                            $(document).find('.price__items .price__item:last-child').html(`<p class="price__text">Giảm giá</p><p class="price__value">` + response.discountPriceFormat + `</p>`);
                            $(document).find('.price__value--final').text(response.newTotalPriceFormat)
                        } else if (response.failedApply) {
                            $(applyStatus).html(`<span class="apply__failed"><i class="fa-solid fa-circle-exclamation"></i><span>` + response.failedApply + `</span></span>`)
                        }
                    }
                })
            })
        });
    }

    increaseQuantityCartProduct();

    function decreaseQuantityCartProduct() {
        $(document).ready(function () {
            $('.minus__quality').on('click', function (event) {
                event.preventDefault();
                let cartItem = $(this).closest('.cart__item');
                let productId = cartItem.data("productId");
                let cartProductIndex = cartItem.data("cartProductIndex");
                let cartForm = $(document).find('.shopping__cart--form');
                let action = $(this).val();
                $.ajax({
                    url: cartForm.attr('action'),
                    type: cartForm.attr('method'),
                    data: {
                        action: action,
                        productId: productId,
                        cartProductIndex: cartProductIndex
                    },
                    dataType: 'json',
                    success: function (response) {
                        let quantitySwapper = $(cartItem).find('.quality__swapper');
                        let quantityRequired = $(quantitySwapper).find('.quality__required');
                        quantityRequired.val(response.newQuantity);

                        let subtotalItem = $(cartItem).find('.subtotal__item');
                        subtotalItem.text(response.newSubtotalFormat);

                        let temporaryPrice = $(document).find('.price__item:first-child .price__value')
                        temporaryPrice.text(response.newTemporaryPriceFormat)

                        let totalPrice = $(document).find('.price__value--final')
                        totalPrice.text(response.newTotalPriceFormat);

                        if (response.discountPrice !== 0) {
                            $(document).find('.price__items .price__item:last-child').html(`<p class="price__text">Giảm giá</p><p class="price__value">` + response.discountPriceFormat + `</p>`);
                        }

                        const applyStatus = $(document).find('.apply__status')
                        if (response.failedApply) {
                            $(applyStatus).html(`<span class="apply__failed"><i class="fa-solid fa-circle-exclamation"></i><span>` + response.failedApply + `</span></span>`)
                            $(document).find('.price__items .price__item:last-child').html("");
                        }
                    }
                })
            })
        });
    }

    decreaseQuantityCartProduct();

    function deleteCartProduct() {
        $(document).ready(function () {
            $('.remove__item').on('click', function (event) {
                event.preventDefault();
                let cartItem = $(this).closest('.cart__item');
                let productId = cartItem.data("productId");
                let cartProductIndex = cartItem.data("cartProductIndex");
                let cartForm = $(document).find('.shopping__cart--form');
                let action = $(this).val();

                const popupDeletion = $(document).find('.popup__deletion');
                popupDeletion.html(`<div class="popup__container">
                                        <div class="popup__content">
                                            <div class="title__header">
                                                <span class="title"><i class="fa-solid fa-triangle-exclamation"></i> Xóa sản phẩm khỏi giỏ hàng</span>
                                                <span class="subtitle">Bạn có muốn xóa sản phẩm đang chọn?</span>
                                            </div>
                                            <div class="button__control">
                                                <button class="agree__button">Xác nhận</button>
                                                <button class="cancel__button">Hủy</button>
                                            </div>
                                        </div>
                                    </div>`);
                $(popupDeletion).find('.cancel__button').on('click', function () {
                    $(popupDeletion).find('.popup__container').remove();
                })

                $(popupDeletion).find('.agree__button').on('click', function () {
                    $.ajax({
                        url: cartForm.attr('action'),
                        type: cartForm.attr('method'),
                        data: {
                            action: action,
                            productId: productId,
                            cartProductIndex: cartProductIndex
                        },
                        dataType: 'json',
                        success: function (response) {
                            $(popupDeletion).find('.popup__container').remove();
                            $(cartItem).remove();
                            $(document).find('.qlt__value').text(response.newTotalItems)
                            $(document).find('.total__items').text(response.newTotalItems)
                            if (response.newTotalItems === 0) {
                                $(document).find('.cart__container').html(`<div class="cart__container--empty">
                                                                                <p>Không có sản phẩm nào trong giỏ hàng của bạn</p>
                                                                                <a href="../product/productBuying.jsp"><button>Tiếp tục mua sắm</button></a>
                                                                                <img src="../../assets/img/continueShopping.svg">
                                                                            </div>`);
                            } else {
                                let temporaryPrice = $(document).find('.price__item:first-child .price__value')
                                temporaryPrice.text(response.newTemporaryPriceFormat)

                                let totalPrice = $(document).find('.price__value--final')
                                totalPrice.text(response.newTotalPriceFormat);

                                if (response.discountPrice !== 0) {
                                    $(document).find('.price__items .price__item:last-child').html(`<p class="price__text">Giảm giá</p><p class="price__value">` + response.discountPriceFormat + `</p>`);
                                }

                                const applyStatus = $(document).find('.apply__status')
                                if (response.failedApply) {
                                    $(applyStatus).html(`<span class="apply__failed"><i class="fa-solid fa-circle-exclamation"></i><span>` + response.failedApply + `</span></span>`)
                                    $(document).find('.price__items .price__item:last-child').html("");
                                }
                            }
                        }
                    })
                })
            })
        });
    }

    deleteCartProduct()

    function applyCodeVoucher() {
        $(document).ready(function () {
            $('#promotion__form').on('submit', function (event) {
                const promotionForm = $(this);
                const buttonApply = $(promotionForm).find('#apply');
                const promotionCodeInput = $(promotionForm).find('#promotion__code')
                const temporaryPriceInputHidden = $(promotionForm).find('input[type=hidden][name=temporaryPrice]')
                const action = buttonApply.val();
                let promotionCode = promotionCodeInput.val();
                let temporaryPrice = temporaryPriceInputHidden.val();
                event.preventDefault();
                $.ajax({
                    url: promotionForm.attr('action'),
                    type: promotionForm.attr('method'),
                    data: {
                        action: action,
                        promotionCode: promotionCode,
                        temporaryPrice: temporaryPrice
                    },
                    dataType: 'json',
                    success: function (response) {
                        const applyStatus = $(document).find('.apply__status')
                        if (response.successApplied) {
                            $(applyStatus).html(`<span class="apply__success"><i class="fa-solid fa-circle-check"></i><span>` + response.successApplied + `</span></span>`)
                            $(document).find('.price__items .price__item:last-child').html(`<p class="price__text">Giảm giá</p><p class="price__value">` + response.discountPriceFormat + `</p>`);
                            $(document).find('.price__value--final').text(response.newTotalPriceFormat)
                        } else if (response.failedApply) {
                            $(applyStatus).html(`<span class="apply__failed"><i class="fa-solid fa-circle-exclamation"></i><span>` + response.failedApply + `</span></span>`)
                        }
                    }
                });
            })
        })
    }

    applyCodeVoucher();
</script>
</html>