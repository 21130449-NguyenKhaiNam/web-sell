$(document).ready(function () {
    $.validator.addMethod("currencyVND", function (value, element) {
        return /^-?\d+(?:\.\d{1,2})?$/.test(value);
    }, "Please enter a valid currency format");
    $.validator.addMethod("notEqual", function (value, element, param) {
        return value !== param;
    }, "Please select an option.");
    const configDatatable = {
        paging: true,
        processing: true,
        serverSide: true,
        ajax: {
            url: "/api/admin/voucher/datatable",
            dataSrc: "data",
        }, columns: [
            {data: "code"},
            {data: "availableTurns"},
            {
                data: "expiryDate",
                defaultContent: '',
                render: function (data, type, row) {
                    if (data == null) {
                        return "Không có hạn sử dụng"
                    } else {
                        return convertDateFormat(data)
                    }
                }
            }, {
                data: "expiryDate",
                defaultContent: '',
                render: function (data, type, row) {
                    if (data == null) {
                        return "Không có hạn sử dụng"
                    } else {
                        return convertDateFormat(data)
                    }
                }
            }, {
                data: "state", render: function (data, type, row) {
                    if (data == "1") {
                        return "Đang hoạt động"
                    }
                    if (data == "0") {
                        return "Hết hạn"
                    }
                    if (data == "-1") {
                        return "Bị khóa"
                    }
                }
            },], language: {
            url: 'https://cdn.datatables.net/plug-ins/1.11.5/i18n/vi.json'
        },
    }
    const datatable = $("#table").DataTable(configDatatable);
    // $("#expiryDate").datepicker({
    //     dateFormat: 'dd-mm-yy', // Set the date format to "dd-mm-yyyy"
    //     changeMonth: true, // Allow changing of months
    //     changeYear: true, // Allow changing of years
    //     yearRange: '-100:+0', // Allow selection of years from 100 years ago to the current year
    //     strictInputParsing: true
    // });

    const configValidator = {
        rules: {
            code: {
                required: true,
                minlength: 5,
                maxlength: 50,
            },
            description: {
                required: true,
                minlength: 5,
                maxlength: 300,
            },
            minimumPrice: {
                required: true,
                currencyVND: true
            },
            discountPercent: {
                required: true,
                range: [0, 100],
            },
            availableTurns: {
                required: true,
                number: true,
            },
            expiryDate: {
                required: true,
                dateISO: true
            },
            state: {
                required: true,
                notEqual: "-1"
            }
        }, messages: {
            code: {
                required: "Vui lòng nhập mã giảm giá",
                minlength: "Mã giảm giá phải chứa ít nhất 5 ký tự",
                maxlength: "Mã giảm giá chứa tối đa 50 ký tự",
            },
            description: {
                required: "Vui lòng nhập mô tả",
                minlength: "Mô tả phải chứa ít nhất 5 ký tự",
                maxlength: "Mô tả chứa tối đa 300 ký tự",
            },
            minimumPrice: {
                required: "Vui lòng nhập giá tối thiểu",
                currencyVND: "Vui lòng nhập đúng định dạng tiền tệ VND"
            },
            discountPercent: {
                required: "Vui lòng nhập phần trăm giảm giá",
                range: "Phần trăm giảm giá phải nằm trong khoảng từ 0 đến 100",
            },
            availableTurns: {
                required: "Vui lòng nhập số lần sử dụng",
                number: "Vui lòng nhập số nguyên dương",
            },
            expiryDate: {
                required: "Vui lòng nhập ngày hết hạn",
                dateISO: "Vui lòng nhập đúng định dạng ngày"
            },
            state: {
                required: "Vui lòng chọn trạng thái",
                notEqual: "Vui lòng chọn trạng thái"
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
            console.log(error.text(), element);
            console.log($(element).next())
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
            // const formData = new FormData(form);
            // $.ajax({
            //     url: "/api/admin/voucher/create",
            //     type: "POST",
            //     data: formData,
            //     processData: false,
            //     contentType: false,
            //     success: function (response) {
            //         if (response.status === 200) {
            //             $("#modal__create").modal("hide");
            //             form.reset();
            //             datatable.ajax.reload();
            //             toastr.success(response.message);
            //         } else {
            //             toastr.error(response.message);
            //         }
            //     }
            // })
        }
    };

    const formValidate = $("#form__create").validate(configValidator);

    function convertDateFormat(dateString) {
        // Split the input string by the hyphen (-)
        const parts = dateString.split('-');

        // Extract the year, month, and day parts
        const year = parts[0];
        const month = parts[1];
        const day = parts[2];

        // Rearrange the parts to the format DD/MM/YYYY
        return day + '/' + month + '/' + year;
    }

    configModalCreate();

    function configModalCreate() {
        document.querySelector("#modal__create").addEventListener("hide.bs.modal", function () {
            formValidate.resetForm();
        })
    }

})