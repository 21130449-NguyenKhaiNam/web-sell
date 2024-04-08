$(document).ready(function () {
    var form = $('#form');
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
            const formData = $(form).serialize();
            $.ajax({
                url: "/api/user/password",
                type: 'POST',
                data: formData,
                success: function (response) {
                    handleResponse(response);
                },
                error: function (xhr, status, error) {
                    // Handle error response
                    console.error(xhr.responseText);
                }
            });
        }
    });
    $.validator.addMethod("strongPassword", function (value, element) {
        return this.optional(element) || /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*()_+[\]{}|;':"\\<>?,./-]).{8,}$/.test(value);
    }, "Mật khẩu phải chứa ít nhất 1 chữ cái viết thường, 1 chữ cái viết hoa, 1 số và 1 ký tự đặc biệt, tối thiểu 8 ký tự");

    function handleResponse(response) {
        if (response.status === 200) {
            console.log("Đổi mật khẩu thành công");
            Swal.fire({
                title: "Chúc mừng!",
                text: "Đổi mật khẩu thành công",
                icon: "success"
            });
        } else {
            console.log("Đổi mật khẩu ko thành công");
            Swal.fire({
                title: "Lỗi!",
                text: "Đổi mật khẩu ko thành công",
                icon: "error"
            });
        }
    }
});
