$(document).ready(function () {
    $.validator.addMethod("currencyVND", function (value, element) {
        return /^-?\d+(?:\.\d{1,2})?$/.test(value);
    }, "Please enter a valid currency format");
    $.validator.addMethod("notEqual", function (value, element, param) {
        return value !== param;
    }, "Please select an option.");
    $.fn.select2.defaults.set("theme", "bootstrap-5");
    $.fn.select2.defaults.set("width", "resolve");
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
                    switch (data) {
                        case "1":
                            return "Đang hoạt động"
                        case "2":
                            return "Hết hạn"
                        case "3":
                            return "Bị khóa"
                    }
                }
            },], language: {
            url: 'https://cdn.datatables.net/plug-ins/1.11.5/i18n/vi.json'
        },
    }
    const datatable = $("#table").DataTable(configDatatable);

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
                min: 0.1,
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
            },
            productId: {
                required: true
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
                min: "Phần trăm giảm giá phải lớn hơn 0.1",
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
            },
            productId: {
                required: "Vui lòng chọn sản phẩm tối thiểu 1 sản phẩm"
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
            $(element).parent().find(".valid-feedback , .invalid-feedback").text(error.text());
        },
        success: function (label) {
            label.remove(); // Remove the error message when input is valid
        },
        highlight: function (element, errorClass, validClass) {
            $(element).addClass(errorClass).removeClass(validClass).attr('required', 'required');
            $(element).parent().find(".valid-feedback").addClass("invalid-feedback");
        },
        unhighlight: function (element, errorClass, validClass) {
            $(element).removeClass(errorClass).addClass(validClass).removeAttr('required');
            $(element).find(".valid-feedback").text("");
        },
        submitHandler: function (form) {
            const formData = $(form).serialize();
            handleSave(formData, (response) => {
                if (response) {
                    // form.reset();
                    // datatable.ajax.reload();
                } else {
                    alert("Có lỗi xảy ra");
                }
            });
            return false;
        }
    };

    const form = $("#form__create")
    form.on("submit", (e) => {
        e.preventDefault()
    })
    const formValidate = $("#form__create").validate(configValidator);

    configModalCreate();

    function configModalCreate() {
        document.querySelector("#modal__create").addEventListener("hide.bs.modal", function () {
            form.find("input, textarea, select").val("")
            formValidate.resetForm();
        })
        document.querySelector("#modal__create").addEventListener("show.bs.modal", function () {
            setupSelect2();
        });
    }

    function handleSave(formData, callback) {
        $.ajax({
            url: "/api/admin/voucher/create",
            type: "POST",
            data: formData,
            success: function (response) {
                callback(response);
            }
        })
    }

    function setupSelect2() {
        $.ajax({
            url: "/api/admin/voucher/get-product",
            type: "GET",
            success: function (response) {
                if (response) {
                    const data = response.map(item => {
                        return {
                            id: item.id,
                            text: item.name
                        }
                    })
                    $("#productId").select2({
                        data: data,
                        // theme: 'bootstrap-5',
                        placeholder: "Chọn sản phẩm muốn áp dụng mã giảm giá",
                        multiple: true,
                        language: {
                            "noResults": function () {
                                return "Chọn sản phẩm muốn áp dụng mã giảm giá";
                            }
                        }
                    });
                    // select2.on("select2:select", function (e) {
                    //     // const data = e.params.data;
                    //     // $("#productId").val(data.id);
                    // });
                    // select2.on("select2:unselect", function (e) {
                    //     // $("#productId").val("");
                    // });
                }
            }
        })
    }

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

})