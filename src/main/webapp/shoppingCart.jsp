<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">

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
    <link rel="stylesheet" href="assets/css/shoppingCart.css">
    <title>Giỏ hàng</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<main id="main">
    <!-- New update template -->
    <div class="promotion__modal">
        <div class="promotion__sidebar">
            <div class="promotion__header">
                <i class="fa-solid fa-arrow-left"></i>
                <span>Mã giảm giá</span>
            </div>
            <div class="promotion__content">
                <c:forEach items="${sessionScope.listVouchers}" var="voucher">
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
            <c:choose>
                <c:when test="${sessionScope.cart.shoppingCartMap.size() == 0}">
                    <div class="cart__container--empty">
                        <p>Không có sản phẩm nào trong giỏ hàng của bạn</p>
                        <a href="productBuying.jsp">
                            <button>Tiếp tục mua sắm</button>
                        </a>
                        <img src="./assets/img/continueShopping.svg">
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="cart__content col">
                        <div>
                            <!-- New remove  -->
                            <!-- <div id="cart__header" class="grid__cart--template">
                                <span>Sản phẩm</span>
                                <span>Giá may</span>
                                <span>Số lượng</span>
                                <span>Thành tiền</span>
                                <span>Xóa</span>
                            </div>
                            <div class="cart__items">

                            </div> --> <!-- New remove  -->

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
                                    <%--                                    <c:forEach items="${sessionScope.cart.shoppingCartMap}" var="shoppingCartMap">--%>
                                    <%--                                        <tr class="cart__item">--%>
                                    <%--                                            <fmt:setLocale value="vi_VN"/>--%>
                                    <%--                                            <c:set var="productId" value="${shoppingCartMap.key}"/>--%>
                                    <%--                                            <c:set var="cartProduct" value="${shoppingCartMap.value}"/>--%>
                                    <%--                                            <td class="product__item">--%>
                                    <%--                                                <div class="product__content">--%>
                                    <%--                                                    <a class="product__image" href="#"> <!-- Back-to-detail_product-page-->--%>
                                    <%--                                                        <c:set var="listImagesProduct" value="${productFactory.getListImagesByProductId(productId)}" />--%>
                                    <%--                                                        <img src='assets/img/product_img/${listImagesProduct.get(0).nameImage}'>--%>
                                    <%--                                                    </a>--%>
                                    <%--                                                    <div class="order__product--info">--%>
                                    <%--                                                        <a href="#" class="product__name">${cartProduct.product.name}</a> <!-- Back-to-detail_product-page-->--%>
                                    <%--                                                        <c:set var="listColorsProduct" value="${productFactory.getListColorsByProductId(productId)}"/>--%>
                                    <%--                                                        <p class="order__color">--%>
                                    <%--                                                            <c:set var="codeColor" value="${listColorsProduct.get(0).codeColor}"/>--%>
                                    <%--                                                            Màu sắc: ${codeColor}--%>
                                    <%--&lt;%&ndash;                                                            <jsp:setProperty name="cartProd" property="color" value="${codeColor}"/>&ndash;%&gt;--%>
                                    <%--                                                        </p>--%>
                                    <%--                                                        <ul class="order__size--specification">--%>
                                    <%--                                                            Kích thước:--%>
                                    <%--                                                        </ul>--%>
                                    <%--                                                    </div>--%>
                                    <%--                                                </div>--%>
                                    <%--                                            </td>--%>
                                    <%--                                            <td class="unit__price">--%>

                                    <%--                                                <c:choose>--%>
                                    <%--                                                    <c:when test="${cartProduct.product.salePrice != null}">--%>
                                    <%--                                                        <c:set var="prorityPrice" value="${cartProduct.product.salePrice}"/>--%>
                                    <%--&lt;%&ndash;                                                        <jsp:setProperty name="cartProd" property="priorityPrice" value="${salePrice}"/>&ndash;%&gt;--%>
                                    <%--                                                    </c:when>--%>
                                    <%--                                                    <c:otherwise>--%>
                                    <%--                                                        <c:set var="prorityPrice" value="${cartProduct.product.originalPrice}"/>--%>
                                    <%--&lt;%&ndash;                                                        <jsp:setProperty name="cartProd" property="priorityPrice" value="${originalPrice}"/>&ndash;%&gt;--%>
                                    <%--                                                    </c:otherwise>--%>
                                    <%--                                                </c:choose>--%>
                                    <%--                                                <fmt:formatNumber value="${prorityPrice}" type="currency"/>--%>
                                    <%--                                            </td>--%>
                                    <%--                                            <td>--%>
                                    <%--                                                <div class="quality__swapper">--%>
                                    <%--                                                    <button class="minus__quality change__quality"><i class="fa-solid fa-minus"></i></button>--%>
                                    <%--                                                    <input type="number" name="quality__required" class="quality__required" min="1" value="${cartProduct.quantity}">--%>
                                    <%--                                                    <button class="plus__quality change__quality"><i class="fa-solid fa-plus"></i></button>--%>
                                    <%--                                                    <span class="quality__status"></span>--%>
                                    <%--                                                </div>--%>
                                    <%--                                            </td>--%>
                                    <%--                                            <td class="subtotal__item">--%>
                                    <%--                                                <fmt:formatNumber value="${prorityPrice * cartProduct.quantity}" type="currency"/>--%>
                                    <%--                                            </td>--%>
                                    <%--                                            <td class="remove__action"><i class="fa-solid fa-trash-can"></i></td>--%>
                                    <%--                                        </tr>--%>
                                    <%--                                    </c:forEach>--%>
                                    <c:set value="0" var="temporaryPrice"/>
                                    <c:forEach items="${sessionScope.cart.shoppingCartMap.keySet()}" var="productId">
                                        <c:forEach items="${sessionScope.cart.shoppingCartMap.get(productId)}" var="cartProduct">
                                            <c:set value="${sessionScope.cart.shoppingCartMap.get(productId).indexOf(cartProduct)}" var="cartProductItem"/>
                                            <tr class="cart__item">
                                                <fmt:setLocale value="vi_VN"/>
                                                <td class="product__item">
                                                    <div class="product__content">
                                                        <a class="product__image" href="#"> <!-- Back-to-detail_product-page-->
                                                            <c:set var="listImagesProduct" value="${productFactory.getListImagesByProductId(productId)}"/>
                                                            <img src='assets/img/product_img/${listImagesProduct.get(0).nameImage}'>
                                                        </a>
                                                        <div class="order__product--info">
                                                            <a href="#" class="product__name">${cartProduct.product.name}</a>
                                                            <!-- Back-to-detail_product-page-->
<%--                                                            <c:set var="listColorsProduct"--%>
<%--                                                                   value="${productFactory.getListColorsByProductId(productId)}"/>--%>
                                                            <p class="order__color">
<%--                                                                <c:set var="codeColor"--%>
<%--                                                                       value="${listColorsProduct.get(0).codeColor}"/>--%>
<%--                                                                Màu sắc: ${codeColor}--%>
                                                                Màu sắc: ${cartProduct.color}
                                                                    <%--                                                            <jsp:setProperty name="cartProd" property="color" value="${codeColor}"/>--%>
                                                            </p>
                                                            <ul class="order__size--specification">
                                                                <c:set value="${productFactory.getPriceSizeByName(cartProduct.size, productId)}" var="sizePrice"/>
                                                                Kích thước: ${cartProduct.size} (Giá kích thứớc: <fmt:formatNumber type="currency" value="${sizePrice}"/>)
                                                            </ul>
                                                        </div>
                                                    </div>
                                                </td>
                                                <td class="unit__price">
                                                    <c:choose>
                                                        <c:when test="${cartProduct.product.salePrice != null}">
                                                            <c:set var="prorityPrice" value="${cartProduct.product.salePrice}"/>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <c:set var="prorityPrice" value="${cartProduct.product.originalPrice}"/>
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <c:set var="sewingPrice" value="${prorityPrice + sizePrice}"/>
                                                    <fmt:formatNumber value="${sewingPrice}" type="currency"/>
                                                </td>
                                                <td>
                                                    <c:url var="increaseQTY" value="IncreaseQuantity">
                                                        <c:param name="productId" value="${productId}"/>
                                                        <c:param name="cartProdIndex" value="${cartProductItem}"/>
                                                    </c:url>

                                                    <c:url var="decreaseQTY" value="DecreaseQuantity">
                                                        <c:param name="productId" value="${productId}"/>
                                                        <c:param name="cartProdIndex" value="${cartProductItem}"/>
                                                    </c:url>
                                                    <div class="quality__swapper">
                                                        <a class="minus__quality change__quality" href="${decreaseQTY}"><i
                                                                class="fa-solid fa-minus"></i></a>
                                                        <input type="number" name="quality__required" class="quality__required"
                                                               min="1" value="${cartProduct.quantity}">
                                                        <a class="plus__quality change__quality" href="${increaseQTY}"><i
                                                                class="fa-solid fa-plus"></i></a>
                                                        <span class="quality__status"></span>
                                                    </div>
                                                </td>
                                                <td class="subtotal__item">
                                                    <fmt:formatNumber value="${sewingPrice * cartProduct.quantity}" type="currency"/>
                                                    <c:set var="temporaryPrice" value="${temporaryPrice + sewingPrice * cartProduct.quantity}"/>
                                                </td>
                                                <td class="remove__action">
                                                    <c:url var="rmCartProduct" value="DeleteCartProduct">
                                                        <c:param name="productId" value="${productId}"/>
                                                        <c:param name="cartProdIndex" value="${cartProductItem}"/>
                                                    </c:url>
                                                    <a href="${rmCartProduct}"><i class="fa-solid fa-trash-can"></i></a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <!-- New update template -->
                        <div class="order__note">
                            <label for="area__note">Ghi chú đơn hàng</label>
                            <textarea id="area__note" rows="6" name="area__note" placeholder="Ghi chú"></textarea>
                        </div><!-- New update template -->
                    </div>
                    <div class="invoice__promotion col">
                        <div class="apply__promotion">
                            <h2>Khuyến mãi</h2>

                            <form id="promotion__form" action="ShoppingCart" method="post">
                                <!-- New update template -->
                                <div class="promotion__all">
                                    <span><i class="fa-solid fa-ticket-simple"></i> Mã giảm giá</span>
                                    <span>Xem tất cả <i class="fa-solid fa-chevron-right"></i></span>
                                </div> <!-- New update template -->
                                <div>
                                    <input type="hidden" name="tempPrice" value="${temporaryPrice}">
                                    <input type="text" name="promotion__code" id="promotion_code">
                                    <input type="submit" name="apply" value="Áp dụng" id="apply">
                                </div>
                                <div class="apply__status">
                                    <c:if test="${requestScope.successApplied != null}">
                                        ${requestScope.successApplied}
                                    </c:if>
                                    <c:if test="${requestScope.failedApply != null}">
                                        ${requestScope.failedApply}
                                    </c:if>
                                </div>
                            </form>
                        </div>
                        <div class="summary__invoice">
                            <h2>Tổng đơn hàng</h2>
                            <div class="invoice__detail--info">
                                <ul class="price__items">
                                    <li class="price__item">
                                        <fmt:setLocale value="vi_VN"/>
                                        <p class="price__text">Tạm tính (${shoppingCart.getTotalItems()} sp)</p>
                                        <p class="price__value"><fmt:formatNumber value="${temporaryPrice}" type="currency"/></p>
                                    </li>
                                    <li class="price__item">
                                        <p class="price__text">Giảm giá <i
                                                class="icon__info fa-solid fa-circle-info"></i> <span
                                                class="discount__note"></span></p>
                                        <p class="price__value"></p>
                                    </li>
                                </ul>
                                <div class="price__total">
                                    <p class="price__text">Tổng tiền</p>
                                    <div class="price__content">
                                        <p class="price__value--final">
                                            <c:choose>
                                                <c:when test="${requestScope.newTotalPrice != null}">
                                                    ${requestScope.newTotalPrice}
                                                </c:when>
                                                <c:otherwise>
                                                    ${temporaryPrice}
                                                </c:otherwise>
                                            </c:choose>

                                        </p>
                                        <p class="price__value--noted">(Đã bao gồm VAT nếu có)</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <span>Phí vận chuyển sẽ được tính ở trang thanh toán</span>
                        <div class="group__button--forward">
                            <a href="checkout.jsp">
                                <button id="continue--checkout">Tiến hành thanh toán</button>
                            </a>
                            <a href="productBuying.jsp">
                                <button id="continue--shopping">Tiến tục mua sắm</button>
                            </a>
                        </div>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</main>
<%@include file="footer.jsp" %>
</body>
<script src="js/base.js"></script>
<script src="js/validateContactForm.js"></script>
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
<script src="js/data.js"></script>
<script src="js/checkout.js"></script>
<script src="js/shoppingCart.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js"
        integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g=="
        crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script type="text/javascript">

    <%--function increaseQuantityCartProduct() {--%>
    <%--    $(document).ready(function () {--%>
    <%--        $('.plus__quality').each(function (index, plusQuantityButton) {--%>
    <%--            $(plusQuantityButton).on('click', function (event) {--%>
    <%--                event.preventDefault();--%>
    <%--                // let increaseQuantityElement = $(plusQuantityButton);--%>
    <%--                <c:url var="increaseQTY" value="IncreaseQuantity">--%>
    <%--                    <c:param name="productId" value="${pid}"/>--%>
    <%--                    <c:param name="cartProductIndex" value="${listCartProduct.indexOf(cartProd)}"/>--%>
    <%--                </c:url>--%>
    <%--                window.location.href = "${increaseQTY}"--%>
    <%--                &lt;%&ndash;$.ajax({&ndash;%&gt;--%>
    <%--                &lt;%&ndash;    type: "POST",&ndash;%&gt;--%>
    <%--                &lt;%&ndash;    url: "${increaseQTY}",&ndash;%&gt;--%>
    <%--                &lt;%&ndash;    data: {},&ndash;%&gt;--%>
    <%--                &lt;%&ndash;    success: function (response) {&ndash;%&gt;--%>

    <%--                &lt;%&ndash;    },&ndash;%&gt;--%>
    <%--                &lt;%&ndash;    error: function (error) {&ndash;%&gt;--%>
    <%--                &lt;%&ndash;        console.error("Không thể tăng số lượng: ", error);&ndash;%&gt;--%>
    <%--                &lt;%&ndash;    }&ndash;%&gt;--%>
    <%--                &lt;%&ndash;})&ndash;%&gt;--%>
    <%--            })--%>
    <%--        })--%>
    <%--    })--%>
    <%--}--%>
    <%--increaseQuantityCartProduct();--%>

    <%--function decreaseQuantityCartProduct() {--%>
    <%--    $(document).ready(function () {--%>
    <%--        $('.minus__quality').each(function (index, minusQuantityButton) {--%>
    <%--            $(minusQuantityButton).on('click', function (event) {--%>
    <%--                event.preventDefault();--%>
    <%--                // let decreaseQuantityElement = $(minusQuantityButton);--%>
    <%--                <c:url var="decreaseQTY" value="DecreaseQuantity">--%>
    <%--                    <c:param name="productId" value="${pid}"/>--%>
    <%--                    <c:param name="cartProductIndex" value="${cartProdIndex}"/>--%>
    <%--                </c:url>--%>
    <%--                window.location.href = "${decreaseQTY}"--%>
    <%--                &lt;%&ndash;$.ajax({&ndash;%&gt;--%>
    <%--                &lt;%&ndash;    type: "POST",&ndash;%&gt;--%>
    <%--                &lt;%&ndash;    url: "${decreaseQTY}",&ndash;%&gt;--%>
    <%--                &lt;%&ndash;    data: {},&ndash;%&gt;--%>
    <%--                &lt;%&ndash;    success: function (response) {&ndash;%&gt;--%>
    <%--                &lt;%&ndash;        &ndash;%&gt;--%>
    <%--                &lt;%&ndash;    },&ndash;%&gt;--%>
    <%--                &lt;%&ndash;    error: function (error) {&ndash;%&gt;--%>
    <%--                &lt;%&ndash;        console.error("Không thể giảm số lượng: ", error);&ndash;%&gt;--%>
    <%--                &lt;%&ndash;    }&ndash;%&gt;--%>
    <%--                &lt;%&ndash;})&ndash;%&gt;--%>
    <%--            })--%>
    <%--        })--%>
    <%--    })--%>
    <%--}--%>
    <%--decreaseQuantityCartProduct();--%>

    <%--function applyCodeVoucher(){--%>
    <%--    $(document).ready(function (){--%>
    <%--        $('#apply').click(function (event){--%>
    <%--            event.preventDefault();--%>
    <%--            let promotionCode = $('#promotion_code').val();--%>
    <%--            <c:url value="ApplyVoucher" var="applyVoucher">--%>
    <%--                <c:param name="tempPrice" value="${temporaryPrice}"/>--%>
    <%--            </c:url>--%>
    <%--            $.ajax({--%>
    <%--                type: 'POST',--%>
    <%--                url: '${applyVoucher}',--%>
    <%--                data: {--%>
    <%--                    promotionCode: promotionCode--%>
    <%--                },--%>
    <%--                success: function (response){--%>
    <%--                    if(response.successMessage !== null){--%>
    <%--                        console.log(response.successMessage);--%>
    <%--                        $('.apply__status').text(response.successMessage);--%>
    <%--                        $('.price__value--final').text(response.newTotal)--%>
    <%--                    }else if(response.errorMessage !== null){--%>
    <%--                        $('.apply__status').text(response.errorMessage);--%>
    <%--                    }--%>
    <%--                },--%>
    <%--                error: function() {--%>
    <%--                    console.log('Có lỗi xảy ra khi xử lý mã giảm giá.');--%>
    <%--                }--%>
    <%--            })--%>
    <%--        })--%>
    <%--    })--%>
    <%--}--%>
    <%--applyCodeVoucher();--%>
</script>
</html>