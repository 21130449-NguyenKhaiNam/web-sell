import {configSweetAlert2, formatCurrency, formatDate, http} from "./base.js";

$(document).ready(function () {
    var voucherApply = {
        state: undefined,
        voucher: undefined,
        listIdProduct: undefined,
    };

    // Các trạng thái của voucher
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
            className: "info",
            message: "Hết lượt sử dụng mã giảm giá",
        }, {
            state: 4,
            className: "info",
            message: "Mã giảm giá đã hết hạn",
        }, {
            state: 5,
            className: "warning",
            message: "Mã giảm giá không áp dụng cho đơn hàng này",
        },
        {
            state: 6,
            className: "warning",
            message: "Số tiền đơn hàng không đủ để áp dụng mã giảm giá",
        },
    ];

    init();

    function init() {
        // Thực thi việc lấy voucher
        handleGetVouchers();

        handleOpenSidebarVoucher();
        handleCheckBox();
        increaseQuantityCartProduct();
        decreaseQuantityCartProduct();
        deleteCartProduct();
        applyCodeVoucher();

        // Chuyển tiếp đến trang thanh toán
        handlePay();
    }

    function handlePay() {
        $('#continue--directional').on('click', function (event) {
            event.preventDefault()

            let checked = false
            let data = []
            const checks = $('.check__pay')
            checks.each(function () {
                const check = $(this)
                const parent = $(this).parents("tr")
                if (check.prop('checked')) {
                    if (!checked)
                        checked = true
                    let obj = {
                        id: check.val(),
                        ind: parent.data("cartProductIndex"),
                        name: parent.find("a.product__name").text().trim(),
                        color: parent.find("p.order__color").text().trim(),
                        size: parent.find("p.order__size--specification").text().trim(),
                        count: parent.find("input.quality__required").val(),
                        price: parent.find("td.subtotal__item").text().replace('₫', '').replace('.', '').trim(),
                        voucher: voucherApply.voucher
                    }
                    data.push({
                        name: check.attr('name'),
                        value: JSON.stringify(obj),
                    });
                }
            })

            if (checked) {
                // Đã lựa chọn hàng
                let formData = $.param(data);
                let url = this.href
                $.ajax({
                    url: url,
                    data: formData,
                    processData: false,
                    contentType: false,
                    success: (data) => {
                        // Ghi lại toàn bộ nội dung của document
                        document.open();
                        document.write(data);
                        history.pushState(null, null, url);
                        document.close();
                    },
                    error: (err) => {
                        console.log(err)
                    }
                })
            } else {
                Swal.fire({
                    icon: "error",
                    title: "Oops...",
                    text: "Vui lòng lựa chọn món hàng muốn thanh toán",
                });
            }

        })
    }

    function handleCheckBox() {
        // Chọn tất cả
        $("#check__pay-all").on("click", function () {
            $(".check__pay").prop("checked", true);
            updatePrice();
        });

        // Bỏ tất cả
        $("#remove__pay-all").on("click", function () {
            $(".check__pay").prop("checked", false);
            updatePrice();
        })

        // Checkbox từng sản phẩm
        $('.container__check__pay').on('click', function () {
            const checkbox = $(this).find('.check__pay');
            let isClick = checkbox.prop('checked');
            checkbox.prop('checked', !isClick);
            updatePrice();
        });
        $('.check__pay').on('click', function () {
            let isClick = $(this).prop('checked');
            $(this).prop('checked', !isClick);
            updatePrice();
        })

    }

    // Thực thi tăng số lương
    function increaseQuantityCartProduct() {
        $('.plus__quality').on('click', function (event) {
            event.preventDefault();
            let cartItem = $(this).closest('.cart__item');
            let productId = cartItem.data("productId");
            let cartProductIndex = cartItem.data("cartProductIndex");
            let cartForm = $(document).find('.shopping__cart--form');
            let action = $(this).val();
            http({
                url: cartForm.attr('action'),
                type: cartForm.attr('method'),
                data: {
                    action: action,
                    productId: productId,
                    cartProductIndex: cartProductIndex
                },
                dataType: 'json',
            }).then((response => {
                let quantitySwapper = $(cartItem).find('.quality__swapper');
                let quantityRequired = $(quantitySwapper).find('.quality__required');
                quantityRequired.val(response.newQuantity);
                $(cartItem).find(".subtotal__item").text(response.newSubtotalFormat);
                updatePrice()
            }));
        })
    }

    // Thực thi giảm số lương
    function decreaseQuantityCartProduct() {
        $('.minus__quality').on('click', function (event) {
            event.preventDefault();
            const quantityCurrent = $(this).closest('.quality__swapper').find('.quality__required').val();
            if (quantityCurrent == 1) {
                Swal.fire({
                    icon: "warning",
                    title: "Cảnh bảo",
                    text: "Số lượng sản phẩm tối thiểu là 1",
                });
                return;
            }
            let cartItem = $(this).closest('.cart__item');
            let productId = cartItem.data("productId");
            let cartProductIndex = cartItem.data("cartProductIndex");
            let cartForm = $(document).find('.shopping__cart--form');
            let action = $(this).val();
            http({
                url: cartForm.attr('action'),
                type: cartForm.attr('method'),
                data: {
                    action: action,
                    productId: productId,
                    cartProductIndex: cartProductIndex
                },
                dataType: 'json',
            }).then(response => {
                let quantitySwapper = $(cartItem).find('.quality__swapper');
                let quantityRequired = $(quantitySwapper).find('.quality__required');
                console.log(response)
                quantityRequired.val(response.newQuantity)
                $(cartItem).find(".subtotal__item").text(response.newSubtotalFormat);
                updatePrice();
            });
        })
    }

    // Thực thi xóa sản phẩm
    function deleteCartProduct() {
        $('.remove__item').on('click', function (event) {
            event.preventDefault();
            Swal.fire({
                ...configSweetAlert2,
                icon: "info",
                title: "Bạn có muốn xóa sản phẩm này ra khỏi giỏ hàng không?",
                showDenyButton: true,
                confirmButtonText: "Có",
                denyButtonText: `Không`
            }).then(result => {
                if (result.isConfirmed) {
                    let cartItem = $(this).closest('.cart__item');
                    let productId = cartItem.data("productId");
                    let cartProductIndex = cartItem.data("cartProductIndex");
                    let cartForm = $(document).find('.shopping__cart--form');
                    let action = $(this).val();
                    http({
                        url: cartForm.attr('action'),
                        type: cartForm.attr('method'),
                        data: {
                            action: action,
                            productId: productId,
                            cartProductIndex: cartProductIndex
                        },
                        dataType: 'json',
                    }).then(response => {
                        cartItem.remove();
                        if (response.newTotalItems === 0) {
                            emptyCart();
                        } else {
                            updatePrice();
                        }
                    }).catch(err => {
                        console.log(err)
                    })
                }
            })
        })
    }

    // Xử lý giao diện nếu giỏ hàng rỗng
    function emptyCart() {
        const html = `<div class="cart__container--empty">
                                <p>Không có sản phẩm nào trong giỏ hàng của bạn</p>
                                <a href="../product/productBuying.jsp"><button>Tiếp tục mua sắm</button></a>
                                <img src="../../assets/img/continueShopping.svg">
                            </div>`
        $(document).find('.cart__container').html(html);
    }


    function applyCodeVoucher() {
        $('#promotion__form').on('submit', function (event) {
            event.preventDefault();
            // Không có sản phẩm chọn để áp dụng mã giảm giá
            const totalItem = $(".cart__item:has(input.check__pay:checked)").length;
            if (totalItem == 0) {
                Swal.fire({
                    icon: "info",
                    title: "Oops...",
                    text: "Vui lòng chọn sản phẩm để áp dụng",
                });
                return;
            }
            const promotionForm = $(this);
            const promotionCodeInput = $(promotionForm).find('#promotion__code')
            if (promotionCodeInput.val().trim() == "") {
                Swal.fire({
                    icon: "info",
                    title: "Oops...",
                    text: "Vui lòng nhập mã giảm giá",
                });
                return;
            }
            http({
                url: promotionForm.attr('action'),
                type: promotionForm.attr('method'),
                data: {
                    code: promotionCodeInput.val(),
                    id: getProductListId(),
                },
                dataType: 'json',
            }).then((response => {
                if (response.code != 200) {
                    Swal.fire({
                        icon: "error",
                        title: "Có lỗi khi áp dụng mã giảm giá",
                        text: "Vui lòng thử lại sau"
                    })
                    return;
                }
                const state = getVoucherState(response.result.state);
                voucherApply = {
                    state: state,
                    voucher: response.result.voucher,
                    listIdProduct: response.result.listIdProduct,// Lưu lại danh sách id sản phẩm có thể áp dụng voucher
                }
                Swal.fire({
                    icon: state.className,
                    title: "Thông báo",
                    text: state.message,
                });
                updatePrice();
            }));
        })
    }

    // Cập nhập giá khi tăng, giảm, xóa sản phẩm, khi áp dụng voucher
    function updatePrice() {
        console.log("listIdProduct: ", voucherApply.listIdProduct)
        const cartItemElements = document.querySelectorAll(".cart__item:has(input.check__pay:checked)");
        const totalItem = cartItemElements.length;

        // Tổng  tiền áp dung mgg
        const totalPriceCanApplyVoucher = [...cartItemElements].map((item) => {
            const productId = $(item).data("product-id");
            if (!voucherApply.listIdProduct?.includes(productId)) {
                return 0;
            }
            const quantityProduct = $(item).find(".quality__required").val();
            const priceUnit = $(item).find(".unit__price").data("price");
            return quantityProduct * priceUnit;
        }).reduce((acc, cur) => acc + cur, 0);
        if (voucherApply.voucher) {
            console.log(voucherApply.voucher)
            if (totalPriceCanApplyVoucher < voucherApply.voucher.minimumPrice)
                updateVoucherState(getVoucherState(6));
            else
                updateVoucherState(voucherApply.state);
        }
        //Tổng tiền
        const totalPrice = [...cartItemElements].map((item) => {
            const quantityProduct = $(item).find(".quality__required").val();
            const priceUnit = $(item).find(".unit__price").data("price");
            return quantityProduct * priceUnit;
        }).reduce((acc, cur) => acc + cur, 0);
        const priceVoucher = voucherApply.voucher?.discountPercent ? voucherApply.voucher.discountPercent * totalPrice : 0;
        const finalPrice = totalPrice - priceVoucher;
        $("#total__items").text(totalItem);
        $("#price__total").text(formatCurrency(totalPrice));
        $("#price__voucher").text(priceVoucher ? formatCurrency(priceVoucher) : "");
        $("#price__final").text(formatCurrency(finalPrice));
    }

    // xử lý cho sidebar voucher
    function handleOpenSidebarVoucher() {
        const promotionSidebar = document.querySelector(".promotion__sidebar")
        const promotionDisplayAll = document.querySelector(".promotion__all span:last-child");
        const iconBackShoppingCart = document.querySelector(".promotion__header i");
        const buttonBackShoppingCart = document.querySelector(".promotion__footer button")
        const promotionContent = $(".promotion__content");

        promotionDisplayAll.addEventListener("click", () => {
            promotionSidebar.classList.add("visible");
            const listIdProduct = getProductListId();
        })

        iconBackShoppingCart.addEventListener("click", () => {
            promotionSidebar.classList.remove("visible")
        })

        buttonBackShoppingCart.addEventListener("click", () => {
            promotionSidebar.classList.remove("visible")
        })
    }

    function getProductListId() {
        const selectorCartItems = "[data-product-id]:has(input.check__pay:checked)";
        return Array.from(document.querySelectorAll(selectorCartItems)).map(productItem => productItem.getAttribute("data-product-id"));
    }

    // Lấy danh sách voucher
    function handleGetVouchers() {
        http({
            url: "/api/voucher/get",
            type: "GET",
        }).then((data) => {
            if (data && data.length > 0) {
                const promotionContent = $(".promotion__content");
                promotionContent.html(loadVoucher(data));
                handleCopyDiscountCode();
            } else {

            }
        });
    }

    function handleCopyDiscountCode() {
        const copyButtonElements = document.querySelectorAll(".button__copy");
        copyButtonElements.forEach(copyButtonElement => {
            let originalContent = copyButtonElement.innerHTML;
            copyButtonElement.addEventListener('click', () => {
                    copyButtonElement.innerHTML = `Đã sao chép <i class= "fa-solid fa-copy"></i>`;
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
                }
            )
        })

        async function copyToClipboard(text) {
            try {
                await navigator.clipboard.writeText(text);
            } catch (error) {
                throw new Error("Không thể sao chép vào clipboard: ", error);
            }
        }
    }

    function loadVoucher(listVoucher) {
        return listVoucher.map(voucher => {
            return ` <div class="promotion__item">
                        <div class="discount__percent">
                            <i class="fa-solid fa-fire"></i>
                            <span>
                                ${voucher.discountPercent}
                            </span>
                        </div>
                        <div class="item__content">
                            <h1 class="promotion__text">
                                NHẬP MÃ:
                                    ${voucher.code}
                            </h1>
                            <p>HSD: ${formatDate(voucher.expiryDate)}
                            </p>
                            <p class="promotion__description">
                                  ${voucher.description}
                           ${formatCurrency(voucher.minimumPrice)}
                            </p>
                            <button class="button__copy"
                                    data-code="${voucher.code}">Sao
                                chép
                                <i class="fa-solid fa-copy"></i></button>
                        </div>
                    </div>`
        })
    }

    function getVoucherState(state) {
        return voucherState.find(voucher => voucher.state == state);
    }

    function updateVoucherState(voucherState) {
        if (!voucherState) return;
        $("#apply__status").removeClass().text("").addClass("alert alert-" + voucherState.className).text(voucherState.message);
    }
})