<%@ page import="services.CheckoutServices" %>
<%@ page import="java.lang.String" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="/assets/css/user/account.css">
    <link rel="stylesheet" href="/assets/css/user/accountInfo.css">
    <jsp:include page="/public/commonLink.jsp"/>
    <link rel="stylesheet" href="<c:url value="/assets/css/checkout.css" />">
    <title>Thanh toán</title>
</head>

<body>
<c:import url="/public/header.jsp"/>
<main id="main">
    <div class="container-xl">
        <div class="checkout__container row">
            <div class="checkout__info--left col">
                <div class="delivery__info--container">
                    <h1 class="checkout__title">Thanh toán</h1>
                    <h2 class="checkout__subtitle">Thông tin giao hàng</h2>
                    <div class="row" id="address-list">
                        <c:if test="${not empty requestScope.address}">
                            <c:forEach items="${requestScope.address}" var="address" varStatus="status">
                                <div class="col-sm-12 mb-3">
                                    <input type="hidden" name="id-address" value="${address.id}"/>
                                    <div class="card">
                                        <div class="card-body focus__address <c:if test="${status.first}">selected</c:if>"
                                             onclick="selectCard(this)" style="cursor: pointer">
                                            <input id="card-address" type="hidden" value="${address.id}"/>
                                            <h5 class="card-title">Địa chỉ giao hàng</h5>
                                            <p class="card-text">${address.exportAddressString()}</p>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </c:if>
                    </div>
                    <p class="other__info">Bạn muốn giao hàng đến địa chỉ khác?
                        <span class="add__delivery" data-bs-toggle="modal"
                              data-bs-target="#modal-address">Thêm thông tin giao hàng mới</span>
                    </p>
                    <div class="popup__bg">
                        <div class="popup__form">
                            <div class="form__header">
                                <h2 class="form__title"></h2>
                                <span class="button__close"><i class="fa-solid fa-xmark"></i></span>
                            </div>
                            <form id="customize__info--form">
                                <input type="hidden" name="deliveryInfoTarget">
                                <div class="customize__item">
                                    <label for="fullName" class="input__text">Họ và tên
                                        <span class="compulsory">*</span></label>
                                    <input type="text" name="fullName" class="input__content field__content"
                                           id="fullName" placeholder="Họ và tên của bạn">
                                    <span id="fullNameError" class="error__notice"></span>
                                </div>
                                <div class="customize__item">
                                    <label for="email" class="input__text">Email
                                        <span class="compulsory">*</span></label>
                                    <input type="text" name="email" class="input__content field__content" id="email"
                                           placeholder="Email của bạn">
                                    <span id="emailError" class="error__notice"></span>
                                </div>
                                <div class="customize__item">
                                    <label for="phone" class="input__text">Số điện thoại
                                        <span class="compulsory">*</span></label>
                                    <input type="text" name="phone" class="input__content field__content" id="phone"
                                           placeholder="Số điện thoại của bạn">
                                    <span id="phoneError" class="error__notice"></span>
                                </div>
                                <div class="customize__item">
                                    <label for="address" class="input__text">Địa chỉ
                                        <span class="compulsory">*</span></label>
                                    <textarea class="textarea__content field__content" name="address" id="address"
                                              rows="6" placeholder="Địa chỉ của bạn"></textarea>
                                    <span id="addressError" class="error__notice"></span>
                                </div>
                                <div class="button__forward">
                                    <button type="button" class="button__cancel">Hủy bỏ</button>
                                    <button type="submit" class="button__custom" name="action"></button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>

                <!-- New update template -->
                <c:set var="freeShip" value="5000000"/> <c:choose>
                <c:when test="${sessionScope[userIdCart].getTotalPrice(false) >= freeShip}">
                    <p class="free__ship"><i class="fa-solid fa-circle-check"></i>Miễn phí vận chuyển cho hóa
                        đơn từ
                        <fmt:setLocale value="vi_VN"/> <fmt:formatNumber value="${freeShip}" type="currency"/>
                    </p>
                </c:when> <c:otherwise>
                <div class="delivery__method--container">
                    <h2 class="checkout__subtitle">Phương thức vận chuyển</h2>
                    <form id="delivery__method--form" class="radio__section">
                        <input type="hidden" name="action" value="choiceDeliveryMethod">
                        <c:forEach items="${applicationScope.listDeliveryMethod}" var="deliveryMethod">
                            <div class="method__content">
                                <div class="method__item section__info--selection">
                                    <input
                                            <c:if test="${deliveryMethod eq sessionScope[userIdCart].deliveryMethod}">checked</c:if>
                                            type="radio" name="delivery__method" class="radio__button"
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
                    </form>
                </div>
            </c:otherwise>
            </c:choose>
                <!-- New update template -->
                <div class="payment__method--container">
                    <h2 class="checkout__subtitle">Phương thức thanh toán</h2>
                    <form id="payment__method--form" class="radio__section">
                        <input type="hidden" name="action" value="choicePaymentMethod">
                        <c:forEach items="${applicationScope.listPaymentMethod}" var="paymentMethod">
                            <div class="method__content">
                                <div class="method__item section__info--selection">
                                    <input
                                            <c:if test="${paymentMethod eq sessionScope[userIdCart].paymentMethod}">checked</c:if>
                                            type="radio" name="payment__method" class="radio__button"
                                            value="${paymentMethod.id}"
                                            id="payment__method${paymentMethod.id}">
                                    <label class="label__selection"
                                           for="payment__method${paymentMethod.id}">${paymentMethod.typePayment}</label>
                                </div>
                                <div class="description__method information__transaction">
                                    <c:choose>
                                        <c:when test="${paymentMethod.id > 1}">
                                            <c:set value="${CheckoutServices.getINSTANCE().getPaymentOwnerByPaymentMethodId(paymentMethod.id)}"
                                                   var="paymentOwner"/>
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
                                                        <img src=""
                                                             alt="https://cdn-icons-png.flaticon.com/512/2830/2830155.png"/>
                                                        <td>Ngân hàng</td>
                                                    </c:if>
                                                    <c:if test="${paymentMethod.id eq 3}">
                                                        <img src="https://cdn-icons-png.flaticon.com/512/1796/1796874.png"
                                                             alt="Ví điện tử"/>
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
                                                <img class="qr__code"
                                                     src="<c:url value="/assets/img/paymentQR/${qrImage}" />">
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
                        <tbody class="order__list">
                        </tbody>
                    </table>
                </div>
                <div class="invoice--final">
                    <div class="invoice__content">
                        <div class="price__item--detail">
                            <div class="temporary__container">
                                <span>Tạm tính ( <span class="count__product"></span> sản phẩm)</span>
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
                            <span class="total__value"></span>
                        </div>
                    </div>
                    <div class="ground__button--forward">
                        <button class="place__order">Đặt hàng</button>
                        <a href="<c:url value="/public/user/shoppingCart.jsp" />">
                            <button class="back--shopping__cart">Quay lại giỏ hàng</button>
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>
<div class="popup__deletion"></div>
<!-- Modal address -->
<div class="modal fade " id="modal-address" tabindex="-1" aria-labelledby="modal-label" aria-hidden="true">
    <div class="modal-dialog modal-dialog-scrollable modal-xl modal-dialog-centered">
        <form id="form-address" class="modal-content needs-validation ">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="modal-label">Thêm địa chỉ</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <div class="container-fluid">
                    <div class="row gx-3 mb-3 mt-2">
                        <div class="col-md-4 col-sm-12">
                            <label class="small  py-1" for="inputProvince">Tỉnh / Thành phố </label>
                            <select name="province" id="inputProvince" class="form-select"
                                    aria-label="Chọn">
                                <option value=""></option>
                            </select>
                            <div class="valid-feedback">

                            </div>
                        </div>

                        <div class="col-md-4 col-sm-12">
                            <label class="small py-1" for="inputDistrict"> Quận / Huyện </label>
                            <select name="district" id="inputDistrict" class="form-select"
                                    aria-label="Chọn">
                                <option value=""></option>
                            </select>
                            <div class="valid-feedback">

                            </div>
                        </div>
                        <div class="col-md-4 col-sm-12">
                            <label class="small py-1" for="inputWard">Phường</label>
                            <select name="ward" id="inputWard" class="form-select"
                                    aria-label="Chọn">
                                <option value=""></option>
                            </select>
                            <div class="valid-feedback">

                            </div>
                        </div>
                    </div>
                    <div class="row gx-3 mb-3 mt-2 ">
                        <div class="col-12">
                            <label class="small py-1" for="inputAddress"> Số nhà, đường </label>
                            <textarea class="form-control" name="detail"
                                      id="inputAddress"></textarea>
                            <div class="valid-feedback">

                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
                <button type="submit" class="btn btn-primary">Lưu</button>
            </div>
        </form>
    </div>
</div>
<c:import url="/public/footer.jsp"/>
</body>
<!--Select 2 jquery-->
<link href="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/css/select2.min.css" rel="stylesheet"/>
<script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/js/select2.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.13.2/jquery-ui.min.js"
        integrity="sha512-57oZ/vW8ANMjR/KQ6Be9v/+/h6bq9/l3f0Oc7vn6qMqyhvPd1cvKBRWWpzu0QoneImqr2SkmO4MSqU+RpHom3Q=="
        crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<!--jQuery validator-->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.20.0/jquery.validate.min.js"
        integrity="sha512-WMEKGZ7L5LWgaPeJtw9MBM4i5w5OSBlSjTjCtSnvFJGSVD26gE5+Td12qN5pvWXhuWaWcVwF++F7aqu9cvqP0A=="
        crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script src="https://ajax.aspnetcdn.com/ajax/jquery.validate/1.15.0/additional-methods.js"></script>
<script type="module" src="<c:url value="/js/checkout.js"/>"></script>
<script type="text/javascript">
    function selectCard(card) {
        // Xóa lớp 'selected' khỏi tất cả các thẻ
        var cards = document.querySelectorAll('.focus__address');
        cards.forEach(function (card) {
            card.classList.remove('selected');
        });

        // Thêm lớp 'selected' vào thẻ đã được click
        card.classList.add('selected');
    }

    function updateCheckout(orders) {
        const con = $('.order__list')
        const conCount = $('.count__product')
        const totalPrice = $('.total__value')
        let totalCost = 0
        conCount.text(orders.order.length + '')
        con.html('')
        orders.order.forEach(order => {
            let content = `
            <tr class="row__content">
                            <td class="td__item">
                                <div class="product__item">
                                    <div class="order__product--info">
                                        <p class="product__name">` + order.name + `</p>
                                        <span>
                                                Màu sắc:
                                                <p class="order__color d-inline">` + order.color + `</p>
                                            </span>
                                        <p class="order__size">` + order.size + `</p>
                                    </div>
                                </div>
                            </td>
                            <td class="td__item">` + order.count + `</td>
                            <td class="td__item">` + order.price.toLocaleString('vi-VN') + `</td>
                        </tr>
            `
            con.append(content)
            totalCost += order.price
        })
        totalPrice.text(totalCost.toLocaleString('vi-VN'))
    }

    var jsonOrder =
        <c:if test="${not empty requestScope.models}">
        <c:set value="${requestScope.models}" var="jsonOrder" />
        updateCheckout(${jsonOrder})

    function handlePlaceOrder() {
        $('#delivery__method--form input[class=radio__button][name=delivery__method]').change(function () {
            $('#payment__method--form input[class=radio__button][name=payment__method]').prop('disabled', false);
        })

        $('.place__order').on('click', function () {
            <c:choose>
            <c:when test="${not empty requestScope.address}">
            $.ajax({
                type: 'POST',
                url: '/public/user/placeOrder',
                data: {},
                dataType: 'json',
                success: function (response) {
                    const popupOrder = `<div class="popup__order">
                                            <div class="bar__loading"></div>
                                            <p class="message__process">Hệ thống đang xử lý, vui lòng quý khách chờ trong vài giây và không đóng tab này. Trong trường hợp tab bị đóng thì quá trình hiện đang được xử lý sẽ thất bại</p>
                                        </div>`
                    $('.place__order').parent().append(popupOrder)
                    $(document).find('.ground__button--forward a').addClass('disabled-link')
                    $(document).find('.place__order').css('cursor', 'not-allowed').prop('disabled', 'false')
                    $(document).find('.radio__button').each(function (index) {
                        $(this).css('cursor', 'not-allowed').prop('disabled', 'false')
                    })
                    $(document).find('.popup__order').css('display', 'block')
                    setTimeout(function () {
                        $(document).find('.popup__order').addClass('active');
                    }, 100);

                    setTimeout(function () {
                        let invoiceNo = response.invoiceNo;
                        let dateOrder = response.dateOrder;
                        listOrder = ${jsonOrder};
                        obj = {
                            delivery: $('input[class=radio__button][name=delivery__method]:checked').val(),
                            payment: $('input[class=radio__button][name=payment__method]:checked').val(),
                            address: $('.focus__address.selected > #card-address').val(),
                            orders: listOrder.order,
                            invoiceNo: invoiceNo
                        }
                        data = [{
                            name: 'order',
                            value: JSON.stringify(obj)
                        }]
                        let formData = $.param(data);
                        console.log("Order >> " + JSON.stringify(obj))
                        $.ajax({
                            url: "/public/user/successOrder",
                            method: 'post',
                            data: data,
                            success: function (res) {
                                $('#main').html(res)
                            },
                            error: function (err) {
                                console.log(err)
                            }
                        })
                    }, 3000);
                }
            })
            </c:when>
            <c:otherwise>
            Swal.fire({
                title: "Chúng tôi không tìm thấy địa chỉ giao hàng của bạn?",
                text: "Vui lòng bổ sung thông tin ở trường bên trái!!!",
                icon: "question"
            });
            </c:otherwise>
            </c:choose>
        })
    }

    handlePlaceOrder();
    </c:if>
    $(document).ready(function () {
        // Thiết lập lựa chọn mặc định
        $('input[class=radio__button][name=delivery__method]')[0].checked = true
        $('input[class=radio__button][name=payment__method]')[0].checked = true
    })

    // function handleChoiceDeliveryMethod() {
    //     $(document).ready(function () {
    //         $('input[name="delivery__method"]').change(function () {
    //             let action = $('#delivery__method--form input[type=hidden][name="action"]').val();
    //             let deliveryMethodId = $(this).val();
    //             $.ajax({
    //                 type: 'POST',
    //                 url: '/Checkout',
    //                 data: {
    //                     action: action,
    //                     deliveryMethodId: deliveryMethodId
    //                 },
    //                 dataType: 'json',
    //                 success: function (response) {
    //                     $(this).prop('checked', true);
    //                     $('.total__price--final .total__value').text(response.newTotalPrice);
    //                     $('.shipping__container span:last-child').text(response.shippingFee);
    //                     $('.amount__pay .amount').text(response.newTotalPrice);
    //                 }
    //             })
    //         })
    //     })
    // }
    //
    // handleChoiceDeliveryMethod();
    //
    // function handleChoicePaymentMethod() {
    //     $(document).ready(function () {
    //         $('input[name="payment__method"]').change(function () {
    //             let action = $('#payment__method--form input[type=hidden][name="action"]').val();
    //             let paymentMethodId = $(this).val();
    //             $.ajax({
    //                 type: 'POST',
    //                 url: '/Checkout',
    //                 data: {
    //                     action: action,
    //                     paymentMethodId: paymentMethodId
    //                 },
    //                 dataType: 'json',
    //                 success: function (response) {
    //                     $('.transaction__content .content').text(response.contentForPay);
    //                 }
    //             })
    //         })
    //     })
    // }
    //
    // handleChoicePaymentMethod();
    //
    // function handleChoiceDeliveryInfo() {
    //     $(document).ready(function () {
    //         $('#delivery__info--form').on('click', '.button__choice', function (event) {
    //             event.preventDefault();
    //             let buttonChoiceClicked = $(this);
    //             if (buttonChoiceClicked.text() === 'Chọn') {
    //                 let deliveryInfo = buttonChoiceClicked.closest('.delivery__info');
    //                 let deliveryInfoKey = deliveryInfo.find('input[type=hidden][name=deliveryInfoKey]').val();
    //                 let typeEdit = buttonChoiceClicked.val();
    //                 $.ajax({
    //                     type: 'POST',
    //                     url: '/Checkout',
    //                     data: {
    //                         typeEdit: typeEdit,
    //                         deliveryInfoKey: deliveryInfoKey
    //                     },
    //                     success: function (response) {
    //                         buttonChoiceClicked.text(response)
    //                         $('.button__choice').not(buttonChoiceClicked).text("Chọn")
    //                     }
    //                 })
    //             }
    //         })
    //     })
    // }
    //
    // handleChoiceDeliveryInfo();
    //
    // function handleRemoveDeliveryInfo() {
    //     $(document).ready(function () {
    //         $('#delivery__info--form').on('click', '.button__remove', function (event) {
    //             event.preventDefault();
    //             let buttonRemoveClicked = $(this);
    //             let deliveryInfo = buttonRemoveClicked.closest('.delivery__info');
    //             let deliveryInfoKey = deliveryInfo.find('input[type=hidden][name=deliveryInfoKey]').val();
    //             let typeEdit = buttonRemoveClicked.val();
    //
    //             let buttonChoice = deliveryInfo.find('.button__choice');
    //             let statusChoice = buttonChoice.text();
    //
    //             const popupDeletion = $(document).find('.popup__deletion');
    //             popupDeletion.html(`<div class="popup__container">
    //
    //                                     <div class="popup__content">
    //                                         <div class="title__header">
    //                                             <span class="title"><i class="fa-solid fa-triangle-exclamation"></i> Xóa thông tin giao hàng</span>
    //                                             <span class="subtitle">Bạn có muốn xóa thông tin giao hàng đang chọn?</span>
    //                                         </div>
    //                                         <div class="button__control">
    //                                             <button class="agree__button">Xác nhận</button>
    //                                             <button class="cancel__button">Hủy</button>
    //                                         </div>
    //                                     </div>
    //                                 </div>`);
    //
    //             $(popupDeletion).find('.cancel__button').on('click', function () {
    //                 $(popupDeletion).find('.popup__container').remove();
    //             })
    //
    //             $(popupDeletion).find('.agree__button').on('click', function () {
    //                 $.ajax({
    //                     type: 'POST',
    //                     url: '/Checkout',
    //                     data: {
    //                         typeEdit: typeEdit,
    //                         deliveryInfoKey: deliveryInfoKey,
    //                         statusChoice: statusChoice
    //                     },
    //                     success: function (response) {
    //                         $(popupDeletion).find('.popup__container').remove();
    //                         deliveryInfo.remove();
    //                         if (statusChoice === "Đã chọn") {
    //                             $('#default__info').find('.button__choice').text("Đã chọn")
    //                         }
    //                     }
    //                 })
    //             })
    //         })
    //     })
    // }
    //
    // handleRemoveDeliveryInfo();
</script>
</html>