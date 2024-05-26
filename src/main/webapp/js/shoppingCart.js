$(document).ready(() => {
    const promotionContent = $(".promotion__content");
    const selectorCartItems = "[data-product-id]:has(input.check__pay:checked) "
    const formVoucher = $('#promotion__form');
    const stateApply={

    }

    function handleEventShoppingCart() {
        const promotionCodeElement = document.getElementById("promotion_code");
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
            // const promotionForm = $(this);
            // const buttonApply = $(promotionForm).find('#apply');
            // const promotionCodeInput = $(promotionForm).find('#promotion__code')
            // const temporaryPriceInputHidden = $(promotionForm).find('input[type=hidden][name=temporaryPrice]')
            // const action = buttonApply.val();
            let promotionCode = formVoucher.find("#promotion__code").val();
            // let temporaryPrice = temporaryPriceInputHidden.val();

            event.preventDefault();
            $.ajax({
                url: "/api/voucher/apply",
                type: "POST",
                data: {
                    code: promotionCode,
                    id: getProductListId(),
                },
                success: function (response) {
                    console.log(response);
                    const applyStatus = $(document).find('.apply__status')
                    if (response.success) {
                        let message;
                        switch (response.canApply) {
                            case CAN_APPLY:
                                message = "Áp dụng mã giảm giá thành công";
                                break;
                            case NOT_FOUND:
                                message = "Mã giảm giá không tồn tại";
                                break;
                            case EMPTY_AVAILABLE_TURN:
                                message = "Hết lượt sử dụng mã giảm giá";
                                break;
                        }
                    }
                    if (response.canApply == CAN_APPLY) {
                        $(applyStatus).html(`<span class="apply__success"><i class="fa-solid fa-circle-check"></i><span>` + response.successApplied + `</span></span>`)
                        $(document).find('.price__items .price__item:last-child').html(`<p class="price__text">Giảm giá</p><p class="price__value">` + response.discountPriceFormat + `</p>`);
                        $(document).find('.price__value--final').text(response.newTotalPriceFormat)
                    } else if (response.failedApply) {
                        $(applyStatus).html(`<span class="apply__failed"><i class="fa-solid fa-circle-exclamation"></i><span>` + response.failedApply + `</span></span>`)
                    }
                }
            });
        })
    }

    setupFormVoucher();
});