import {alert} from "../notify.js";
import {addSpinner, cancelSpinner} from "../spinner.js";

$(document).ready(function () {
    // Custom regex kiểm tra mật khẩu mạnh
    $.validator.addMethod("strongPassword", function (value, element) {
        return this.optional(element) || /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*()_+[\]{}|;':"\\<>?,./-]).{8,}$/.test(value);
    }, "Mật khẩu phải chứa ít nhất 1 chữ cái viết thường, 1 chữ cái viết hoa, 1 số và 1 ký tự đặc biệt, tối thiểu 8 ký tự");

    var form = $('#form');
    // Validate from đổi mật khẩu
    form.validate({
        rules: {
            currentPassword: {
                required: true,
            }, newPassword: {
                required: true, strongPassword: true
            }, confirmPassword: {
                required: true, equalTo: "#newPassword"
            }
        }, messages: {
            currentPassword: {
                required: "Vui lòng nhập mật khẩu hiện tại",
            }, newPassword: {
                required: "Vui lòng nhập mật khẩu mới",
                strongPassword: "Mật khẩu phải chứa ít nhất 1 chữ cái viết thường, 1 chữ cái viết hoa, 1 số và 1 ký tự đặc biệt, tối thiểu 8 ký tự"
            }, confirmPassword: {
                required: "Vui lòng xác nhận mật khẩu",
                equalTo: "Mật khẩu xác nhận không khớp"
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
            $(element).next().text(error.text());
        },
        highlight: function (element, errorClass, validClass) {
            $(element).addClass(errorClass).removeClass(validClass).attr('required', 'required');
            $(element).next().addClass("invalid-feedback");
        },
        unhighlight: function (element, errorClass, validClass) {
            $(element).removeClass(errorClass).addClass(validClass).removeAttr('required');
            $(element).next().text("");
        },
        submitHandler: function (form) {
            // Hiển thi dialog xác nhận
            alert(function () {
                const formData = $(form).serialize();
                $.ajax({
                    url: "/api/user/password",
                    type: 'POST',
                    data: formData,
                    beforeSend: function () {
                        addSpinner();
                    },
                    success: function (response) {
                        handleResponse(response);
                        form.reset();
                    },
                    error: function (xhr, status, error) {
                        console.error(xhr.responseText);
                    },
                    complete: function () {
                        cancelSpinner();
                    }
                });
            }, function () {
                form.reset();
            });

        }
    });

    function handleResponse(response) {
        if (response.isValid) {
            Swal.fire({
                title: "Chúc mừng!",
                text: "Đổi mật khẩu thành công",
                icon: "success"
            });
        } else {
            Swal.fire({
                title: "Lỗi!",
                text: "Đổi mật khẩu ko thành công, vui lòng kiểm tra lại mật khẩu hiện tại",
                icon: "error"
            });
        }
    }
});
