$(document).ready(() => {
    const promotionContent = $(".promotion__content");
    const selectorCartItems = "[data-product-id]:has(input.check__pay:checked)"
    const formVoucher = $('#promotion__form');
    const stateApply = {}

    function handleEventShoppingCart() {
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
        console.log(document.querySelectorAll(selectorCartItems))
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

    handleEventShoppingCart();

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

    function formatCurrencyVND(amount) {
        const inputFormat = "dd/MM/yyyy";
        const outputFormat = "yyyy-MM-dd"
        // Create a NumberFormat object with Vietnamese locale and currency style
        const formatter = new Intl.NumberFormat('vi-VN', {
            style: 'currency',
            currency: 'VND'
        });

        // Format the amount using the NumberFormat object
        return formatter.format(amount);
    }

    const CAN_APPLY = "1";
    const NOT_FOUND = "2";
    const EMPTY_AVAILABLE_TURN = "3";


    function setupFormVoucher() {
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
                    const applyStatus = $(document).find('.apply__status')
                    if (response.success) {
                        let message, state;
                        switch (response.canApply) {
                            case CAN_APPLY:
                                message = "Áp dụng mã giảm giá thành công";
                                status = "success";
                                break;
                            case NOT_FOUND:
                                message = "Mã giảm giá không tồn tại";
                                status = "warning";
                                break;
                            case EMPTY_AVAILABLE_TURN:
                                message = "Hết lượt sử dụng mã giảm giá";
                                status = "error";
                                break;
                        }
                    }
                    applyStatus.html(`<p class="btn btn=${state}">${message}</p>`)
                    if (response.canApply == CAN_APPLY) {
                        const voucher = response.voucher;
                        $(document).find('.price__voucher').val(voucher.discountPercent);
                        updatePrice();
                    }
                }
            });
        })
    }

    function updatePrice() {
        const totalItem = $(".cart__item:has(input.check__pay:checked)").length;
        const totalPrice = $(".cart__item:has(input.check__pay:checked)").each((index, item) => {
            const quantityProduct = $(item).find(".quantity__input").val();
            const priceUnit = $(item).find(".price__unit").val();
            return quantityProduct * priceUnit;
        }).reduce((acc, cur) => acc + cur, 0);
        const finalPrice = totalPrice - $(".price__voucher").val();
        $(".total__item").html(totalItem);
        $(".price__total").html(totalPrice);
        $(".price__final").html(finalPrice);
    }

    setupFormVoucher();
});