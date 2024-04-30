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