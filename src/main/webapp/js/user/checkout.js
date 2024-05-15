import {alert} from "../notify.js";

$(document).ready(() => {
    const customer = {
        id: "",
        name: "",
        phone: "",
        address: "",
        email: "",
        cartId: "",
        cartItems: [],
        paymentMethodId: ""
    }
// Xử lý chọn phương thức thanh toán
    function handleChoicePaymentMethod() {
        $(document).ready(function () {
            $('input[name="payment__method"]').change(function () {
                customer.paymentMethodId = parseInt($(this).val());
            })
        })
    }

    // handleChoicePaymentMethod();

//Xử lý chọn địa chỉ trong sổ địa chỉ

    function handleChoiceAddress() {

    }

    function handleChoiceDeliveryInfo() {
        $('#delivery__info--form').on('click', '.button__choice', function (event) {
            event.preventDefault();
            let buttonChoiceClicked = $(this);
            if (buttonChoiceClicked.text() === 'Chọn') {
                let deliveryInfo = buttonChoiceClicked.closest('.delivery__info');
                let deliveryInfoKey = deliveryInfo.find('input[type=hidden][name=deliveryInfoKey]').val();
                let typeEdit = buttonChoiceClicked.val();
                $.ajax({
                    type: 'POST',
                    url: '/Checkout',
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
    }

    // handleChoiceDeliveryInfo();

// Xử lý xóa thông tin giao hàng
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

                $(popupDeletion).find('.cancel__button').on('click', function () {
                    $(popupDeletion).find('.popup__container').remove();
                })

                $(popupDeletion).find('.agree__button').on('click', function () {
                    $.ajax({
                        type: 'POST',
                        url: '/Checkout',
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

    // handleRemoveDeliveryInfo();


//     Validate form
    function validateForm() {
        $('#checkout__form').validate({
            rules: {
                name: {
                    required: true,
                    minlength: 5,
                    maxlength: 50
                },
                phone: {
                    required: true,
                    number: true,
                    minlength: 10,
                    maxlength: 11
                },
                address: {
                    required: true,
                    minlength: 10,
                    maxlength: 100
                },
                email: {
                    required: true,
                    email: true
                }
            },
            messages: {
                name: {
                    required: "Vui lòng nhập họ tên",
                    minlength: "Họ tên phải có ít nhất 5 ký tự",
                    maxlength: "Họ tên không được vượt quá 50 ký tự"
                },
                phone: {
                    required: "Vui lòng nhập số điện thoại",
                    number: "Số điện thoại không hợp lệ",
                    minlength: "Số điện thoại phải có ít nhất 10 ký tự",
                    maxlength: "Số điện thoại không được vượt quá 11 ký tự"
                },
                address: {
                    required: "Vui lòng nhập địa chỉ",
                    minlength: "Địa chỉ phải có ít nhất 10 ký tự",
                    maxlength: "Địa chỉ không được vượt quá 100 ký tự"
                },
                email: {
                    required: "Vui lòng nhập email",
                    email: "Email không hợp lệ"
                }
            }
        })
    }

    const form = $('#form-checkout');
    console.log(form)
    form.validate({
        rules: {
            fullName: {
                required: true,
                minlength: 5,
                maxlength: 50
            },
            email: {
                required: true,
                email: true
            },
            phone: {
                required: true,
                number: true,
                minlength: 10,
                maxlength: 11
            },
            addressId: {
                required: true,
            },
            paymentId: {
                required: true,
            }
        },
        messages: {
            fullName: {
                required: "Vui lòng nhập họ tên",
                minlength: "Họ tên phải có ít nhất 5 ký tự",
            },
            email: {
                required: "Vui lòng nhập email",
                email: "Email không hợp lệ"
            },
            phone: {
                required: "Vui lòng nhập số điện thoại",
                number: "Số điện thoại không hợp lệ",
                minlength: "Số điện thoại phải có ít nhất 10 ký tự",
                maxlength: "Số điện thoại không được vượt quá 11 ký tự"
            },
            addressId: {
                required: "Vui lòng chọn địa chỉ"
            },
            paymentId: {
                required: "Vui lòng chọn phương thức thanh toán"
            }
        },
        onkeyup: function (element) {
            $(element).valid();
        },
        onfocusout: function (element) {
            $(element).valid();
        },
        onblur: function (element) {
            $(element).valid();
        },
        validClass: 'is-valid',
        errorClass: 'is-invalid',
        errorPlacement: function (error, element) {
            $(element).parent(".form__group").find(".form-message").addClass("invalid-feedback").removeClass("valid-feedback").text(error.text());
        },
        highlight: function (element, errorClass, validClass) {
            $(element).addClass(errorClass).removeClass(validClass);
            $(element).parent().removeClass(errorClass).addClass(validClass);
        },
        unhighlight: function (element, errorClass, validClass) {
            $(element).removeClass(errorClass).addClass(validClass);
            $(element).parent().removeClass(errorClass).addClass(validClass);
        },
        submitHandler: function (form) {
            var formData = new FormData(form);
            // Hiển thi dialog xác nhận
            alert(function () {
                $.ajax({
                    type: 'POST',
                    url: '/Checkout',
                    data: formData,
                    contentType: false,
                    processData: false,
                    success: function (response) {
                        if (response.status === 200) {
                            handlePlaceOrder();
                        }
                    }
                })
            }, function () {
                form.reset();
            }, {
                notify: "Bạn có muốn đặt hàng không?",
                success: "Đặt hàng thành công",
            });

        }
    });

    // Xử lý đặt hàng
    function handlePlaceOrder() {
        $('#delivery__method--form input[class=radio__button][name=delivery__method]').change(function () {
            $('#payment__method--form input[class=radio__button][name=payment__method]').prop('disabled', false);
        })

        $('.place__order').on('click', function () {
            $.ajax({
                type: 'POST',
                url: '/PlaceOrder',
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
                        window.location.href = "/SuccessOrder?invoiceNo=" + invoiceNo;
                    }, 3000);
                }
            })
        })
    }
});
