$(document).ready(function () {
        $('#continue--directional').on('click', function (event) {
            event.preventDefault()

            let checked = false
            const checks = $('.check__pay')
            checks.each(function () {
                const check = $(this)
                if (check.prop('checked')) {
                    checked = true
                    return;
                }
            })

            if (checked) {
                // Đã lựa chọn hàng
            } else {
                Swal.fire({
                    icon: "error",
                    title: "Oops...",
                    text: "Vui lòng lựa chọn món hàng muốn thanh toán",
                });
            }

        })
        handleEventChangePrice();
        handleOpenSidebarVoucher();

        function checkPayHigh(isAll = false) {
            if (isAll)
                $('.check__pay').attr('checked', true)

            $('#check__pay-all').on('click', function () {
                $('.check__pay').attr('checked', true)
                updatePrice();
            })

            $('#remove__pay-all').on('click', function () {
                $('.check__pay').attr('checked', false)
                updatePrice();
            })
        }

        // checkPayHigh()

        function handlePay() {
            let isClick = false;

            $('.check__pay').on('click', function () {
                isClick = true
                let myCom = $(this)
                let checkPay = myCom.prop('checked')
                let comTotalItem = $('.total__items')[0]
                let totalItem = parseInt(comTotalItem.innerText) || 0
                if (checkPay) {
                    // Thêm sản phẩm
                    comTotalItem.innerText = totalItem + 1
                } else {
                    // Hủy bỏ sản phẩm
                    comTotalItem.innerText = totalItem - 1

                }
                myCom.prop('checked', checkPay);
            })

            $('.container__check__pay').on('click', function () {
                const checkbox = $(this).find('.check__pay');
                if (!isClick) {
                    checkbox.prop('checked', !checkbox.prop('checked'));
                }
                isClick = false
            });
        }

        // handlePay()

        function increaseQuantityCartProduct() {
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
                        // Tăng số lượng sản phẩm
                        let quantitySwapper = $(cartItem).find('.quality__swapper');
                        let quantityRequired = $(quantitySwapper).find('.quality__required');
                        quantityRequired.val(response.newQuantity);

                        let subtotalItem = $(cartItem).find('.subtotal__item');
                        subtotalItem.text(response.newSubtotalFormat);
                        updatePrice();
                        // let temporaryPrice = $(document).find('.price__item:first-child .price__value')
                        // temporaryPrice.text(response.newTemporaryPriceFormat)

                        // let totalPrice = $(document).find('.price__value--final')
                        // totalPrice.text(response.newTotalPriceFormat);

                        // const applyStatus = $(document).find('.apply__status')
                        // if (response.successApplied) {
                        //     $(applyStatus).html(`<span class="apply__success"><i class="fa-solid fa-circle-check"></i><span>` + response.successApplied + `</span></span>`)
                        //     $(document).find('.price__items .price__item:last-child').html(`<p class="price__text">Giảm giá</p><p class="price__value">` + response.discountPriceFormat + `</p>`);
                        //     $(document).find('.price__value--final').text(response.newTotalPriceFormat)
                        // } else if (response.failedApply) {
                        //     $(applyStatus).html(`<span class="apply__failed"><i class="fa-solid fa-circle-exclamation"></i><span>` + response.failedApply + `</span></span>`)
                        // }
                    }
                })
            })
        }

        increaseQuantityCartProduct();

        function decreaseQuantityCartProduct() {
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
                        updatePrice();
                        // let temporaryPrice = $(document).find('.price__item:first-child .price__value')
                        // temporaryPrice.text(response.newTemporaryPriceFormat)
                        //
                        // let totalPrice = $(document).find('.price__value--final')
                        // totalPrice.text(response.newTotalPriceFormat);

                        // if (response.discountPrice !== 0) {
                        //     $(document).find('.price__items .price__item:last-child').html(`<p class="price__text">Giảm giá</p><p class="price__value">` + response.discountPriceFormat + `</p>`);
                        // }
                        //
                        // const applyStatus = $(document).find('.apply__status')
                        // if (response.failedApply) {
                        //     $(applyStatus).html(`<span class="apply__failed"><i class="fa-solid fa-circle-exclamation"></i><span>` + response.failedApply + `</span></span>`)
                        //     $(document).find('.price__items .price__item:last-child').html("");
                        // }
                    }
                })
            })
        }

        decreaseQuantityCartProduct();

        function deleteCartProduct() {
            $('.remove__item').on('click', function (event) {
                event.preventDefault();
                let cartItem = $(this).closest('.cart__item');
                let productId = cartItem.data("productId");
                let cartProductIndex = cartItem.data("cartProductIndex");
                let cartForm = $(document).find('.shopping__cart--form');
                let action = $(this).val();

                Swal.fire({
                    title: "Xóa đơn hàng ",
                    text: "Bạn có muốn xóa sản phẩm này ra khỏi đơn hàng không?",
                    icon: "warning",
                    showCancelButton: true,
                    confirmButtonColor: "#d33",
                    cancelButtonColor: "#ddd",
                    confirmButtonText: "Xóa",
                    cancelButtonText: "Hủy"
                }).then((result) => {
                    if (result.isConfirmed) {
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
                                // hiện thông bó xóa thanh công
                                Swal.fire({
                                    title: "Xóa thành công!",
                                    text: "Sản phẩm đã dược xóa khỏi giỏ hàng.",
                                    icon: "success"
                                });
                                $(cartItem).remove();
                                if (response.newTotalItems === 0) {
                                    $(document).find('.cart__container').html(`<div class="cart__container--empty">
                                                                                <p>Không có sản phẩm nào trong giỏ hàng của bạn</p>
                                                                                <a href="../product/productBuying.jsp"><button>Tiếp tục mua sắm</button></a>
                                                                                <img src="../../assets/img/continueShopping.svg">
                                                                            </div>`);
                                } else {
                                    // let temporaryPrice = $(document).find('.price__item:first-child .price__value')
                                    // temporaryPrice.text(response.newTemporaryPriceFormat)
                                    //
                                    // let totalPrice = $(document).find('.price__value--final')
                                    // totalPrice.text(response.newTotalPriceFormat);

                                    if (response.discountPrice !== 0) {
                                        $(document).find('.price__items .price__item:last-child').html(`<p class="price__text">Giảm giá</p><p class="price__value">` + response.discountPriceFormat + `</p>`);
                                    }

                                    // const applyStatus = $(document).find('.apply__status')
                                    // if (response.failedApply) {
                                    //     $(applyStatus).html(`<span class="apply__failed"><i class="fa-solid fa-circle-exclamation"></i><span>` + response.failedApply + `</span></span>`)
                                    //     $(document).find('.price__items .price__item:last-child').html("");
                                    // }
                                    updatePrice();
                                }
                            }
                        })
                    }
                });
            })
        }

        deleteCartProduct()

        // Voucher
        const promotionContent = $(".promotion__content");
        const selectorCartItems = "[data-product-id]:has(input.check__pay:checked)";
        const formVoucher = $('#promotion__form');
        var voucherApply;

        function handleOpenSidebarVoucher() {
            const promotionSidebar = document.querySelector(".promotion__sidebar")
            const promotionDisplayAll = document.querySelector(".promotion__all span:last-child");
            const iconBackShoppingCart = document.querySelector(".promotion__header i");
            const buttonBackShoppingCart = document.querySelector(".promotion__footer button")


            promotionDisplayAll.addEventListener("click", () => {
                promotionSidebar.classList.add("visible");
                const listIdProduct = getProductListId();
                handleGetVouchers(listIdProduct);
            })

            iconBackShoppingCart.addEventListener("click", () => {
                promotionSidebar.classList.remove("visible")
                promotionContent.html("");
            })

            buttonBackShoppingCart.addEventListener("click", () => {
                promotionSidebar.classList.remove("visible")
                promotionContent.html("");
            })
        }

        function getProductListId() {
            return Array.from(document.querySelectorAll(selectorCartItems)).map(productItem => productItem.getAttribute("data-product-id"));
        }

// Xử lý sự kiện khi người dùng nhấn vào nút Sao chép mã giảm giá
        function handleCopyDiscountCode() {
            const copyButtonElements = document.querySelectorAll(".button__copy");
            copyButtonElements.forEach(copyButtonElement => {
                let originalContent = copyButtonElement.innerHTML;
                copyButtonElement.addEventListener('click', () => {
                    copyButtonElement.innerHTML = `Đã sao chép <i class="fa-solid fa-copy"></i>`;
                    setTimeout(() => {
                        copyButtonElement.innerHTML = originalContent;
                    }, 1000);

                    const codeToCopy = copyButtonElement.getAttribute('data-code');
                    copyToClipboard(codeToCopy)
                        .then(() => {
                            console.log(codeToCopy);
                        })
                        .catch(error => {
                            console.error("Không thể sao chép: ", error);
                        });
                })
            })

            async function copyToClipboard(text) {
                try {
                    await navigator.clipboard.writeText(text);
                } catch (error) {
                    throw new Error("Không thể sao chép vào clipboard: ", error);
                }
            }
        }

        function handleGetVouchers(listIdProduct) {
            $.ajax({
                url: "/api/voucher/getAll",
                type: "GET",
                data: {
                    id: listIdProduct,
                },
                success: function (data) {
                    if (data.success && data.vouchers) {
                        promotionContent.html(loadVoucher(data.vouchers));
                        handleCopyDiscountCode();
                    }
                },
            })
        }

        function loadVoucher(listVoucher) {
            return listVoucher.map(voucher => {
                return ` <div class="promotion__item">
                        <div class="discount__percent">
                            <i class="fa-solid fa-fire"></i>
                            <span>
                                <fmt:formatNumber
                                        type="percent"
                                        value="${voucher.discountPercent}"/>
                            </span>
                        </div>
                        <div class="item__content">
                            <h1 class="promotion__text">
                                NHẬP MÃ:
                                    ${voucher.code}
                            </h1>
                            <p>HSD:  ${voucher.expiryDate}
                            </p>
                            <p class="promotion__description">
                                    ${voucher.description}
                           ${formatCurrencyVND(voucher.minimumPrice)}
                            </p>
                            <button class="button__copy"
                                    data-code="${voucher.code}">Sao
                                chép
                                <i class="fa-solid fa-copy"></i></button>
                        </div>
                    </div>`
            })
        }

        const voucherState = [
            {
                state: 1,
                className: "success",
                message: "Áp dụng mã giảm giá thành công",
            }, {
                state: 2,
                className: "warning",
                message: "Mã giảm giá không tìm thấy",
            }, {
                state: 3,
                className: "warning",
                message: "Hết lượt sử dụng mã giảm giá",
            }, {
                state: 4,
                className: "warning",
                message: "Mã giảm giá đã hết hạn",
            }, {
                state: 5,
                className: "danger",
                message: "Mã giảm giá không áp dụng cho sản phẩm này",
            },
            {
                state: 6,
                className: "danger",
                message: "Vui lòng chọn sản phẩm cần áp dụng mã giảm giá",
            }
        ];

        function getVoucherState(state) {
            return voucherState.find(voucher => voucher.state == state);
        }

        function handleApplyVoucher() {
            formVoucher.on('submit', function (event) {
                event.preventDefault();
                const promotionCodeInput = $(formVoucher).find('#promotion__code')
                let promotionCode = promotionCodeInput.val();
                $.ajax({
                    url: "/api/voucher/apply",
                    type: "POST",
                    data: {
                        code: promotionCode,
                        id: getProductListId(),
                    },
                    success: function (response) {
                        if (response.success) {
                            voucherApply = {
                                state: response.result?.state,
                                voucher: response.result?.voucher,
                                listIdProduct: response.result?.listIdProduct
                            }
                            updatePrice();
                        } else {
                            updateVoucherState(getVoucherState(6));
                            voucherApply = {};
                        }
                    }
                });
            })
        }

        handleApplyVoucher();

// Gắn sự kiện
        function handleEventChangePrice() {
            // Thêm sự kiện check box chọn tất cả
            $('#check__pay-all').on('click', function () {
                $('.check__pay').attr('checked', true)
                updatePrice();
            })

            // Thêm sự kiện check box bỏ chọn tất cả
            $('#remove__pay-all').on('click', function () {
                $('.check__pay').attr('checked', false)
                updatePrice();
            })

            $('.container__check__pay').on('click', function () {
                const checkbox = $(this).find('.check__pay');
                checkbox.prop('checked', !checkbox.prop('checked'));
                updatePrice();
            });

            // Thêm sự kiện check box cart item
            $(".cart__item .container__check__pay:has(input.check__pay)").on("click", () => {
                updatePrice();
            })
            //Thêm sự kiện check box chọn tất cả
            $("#check__pay-all").on("click", () => {
                updatePrice();
            });

            // Thêm sự kiện thay đổi số lượng sản phẩm
            $(".plus__quality ").on("click", () => {
                updatePrice();
            });
            $(".minus__quality ").on("click", () => {
                updatePrice();
            });
        }

        function updateVoucherState({className, message}) {
            if (!className || !message) return;
            $("#apply__status").removeClass();
            $("#apply__status").text("");
            $("#apply__status").addClass("alert alert-" + className).text(message);
        }

// Gọi mỗi khi có thay đổi số sản phẩm trong giỏ hàng (bao gồm cả việc áp dụng mã giảm giá)
        function updatePrice() {
            const totalItem = $(".cart__item:has(input.check__pay:checked)").length;
            const cartItemElements = document.querySelectorAll(".cart__item:has(input.check__pay:checked)");
            const totalPrice = [...cartItemElements].map((item) => {
                const quantityProduct = $(item).find(".quality__required").val();
                const priceUnit = convertToNumber($(item).find(".unit__price").text());
                return quantityProduct * priceUnit;
            }).reduce((acc, cur) => acc + cur, 0);
            const priceVoucher = voucherApply ? voucherApply.discountPercent * totalPrice : 0;
            const finalPrice = totalPrice - priceVoucher;
            console.log(voucherApply)
            $("#total__items").text(totalItem);
            $("#price__total").text(formatCurrencyVND(totalPrice));
            $("#price__voucher").text(priceVoucher ? formatCurrencyVND(priceVoucher) : "");
            $("#price__final").text(formatCurrencyVND(finalPrice));
        }

        function convertToNumber(currency) {
            let withoutCurrencySymbol = currency.replace("₫", "").trim();

            let cleanedString = withoutCurrencySymbol.replace(/\./g, "");

            return Number(cleanedString);
        }

        function formatCurrencyVND(amount) {
            // Create a NumberFormat object with Vietnamese locale and currency style
            const formatter = new Intl.NumberFormat('vi-VN', {
                style: 'currency',
                currency: 'VND'
            });

            // Format the amount using the NumberFormat object
            return formatter.format(amount);
        }

    }
)
