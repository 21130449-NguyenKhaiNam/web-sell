import { alert } from "../notify.js";
import { addSpinner, cancelSpinner } from "../spinner.js";
import { getProvince, getWard, getDistrict, getProvinceId, getDistrictId, getWardCode } from "../shipping.js";

$(document).ready(function () {
    $.fn.select2.defaults.set("theme", "bootstrap-5");
    $.fn.select2.defaults.set("width", "resolve");

    // ------------------------------------
    // Cập nhập avatar
    // Validate form upload avatar
    $("#form-avatar").hide();
    $('#open-form').on("click", () => {
        $("#form-avatar").show();
        $("#open-form").hide();
    })

    if ($.validator) {
        $.validator.addMethod("singleFile", function (value, element) {
            return this.optional(element) || element.files.length === 1;
        }, "Please select only one file.");
    }

    if ($("#form-avatar")[0]) {
        $("#form-avatar").validate({
            rules: {
                avatar: {
                    required: true,
                    extension: "jpg|jpeg|png",
                    singleFile: true,
                },
                messages: {
                    avatar: {
                        required: "Vui lòng chọn ảnh",
                        extension: "Vui lòng chọn file có định dạng jpg, jpeg, png",
                        singleFile: "Vui lòng chọn 1 file duy nhất",
                    },
                }
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
                // Submit form via AJAX
                alert(() => {
                    var formData = new FormData(form);
                    $.ajax({
                        url: "/upload-avatar",
                        type: "POST",
                        data: formData,
                        processData: false,
                        contentType: false,
                        beforeSend: function () {
                            addSpinner();
                        },
                        success: function (response) {
                            Swal.fire({
                                title: "Chúc mừng!",
                                text: "Avatar đã được cập nhập",
                                icon: "success"
                            });
                        },
                        error: function (xhr, status, error) {
                            Swal.fire({
                                title: "Lỗi!",
                                text: "Avatar không cập nhập thành công",
                                icon: "error"
                            });
                        },
                        complete: function () {
                            cancelSpinner();
                        }
                    });
                });
                return false; // Prevent form submission
            }
        })
        $("#avatar").on("change", function () {
            // Check if file input is valid
            if ($("#avatar").valid()) {
                // Read the selected file and display preview
                const file = this.files[0];
                const reader = new FileReader();
                reader.onload = function (e) {
                    $("#preview-avatar").attr("src", e.target.result);
                    $("#preview-avatar").show();
                };
                reader.readAsDataURL(file);
            } else {
                // Hide preview if file input is not valid
                $("#preview").hide();
            }
        });
    }

    //     -------------------------------
    //     Cập nhập thông tin cá nhân

    //     Date picker jquery plug-in
    $("#inputDate").datepicker({
        dateFormat: 'dd-mm-yy', // Set the date format to "dd-mm-yyyy"
        changeMonth: true, // Allow changing of months
        changeYear: true, // Allow changing of years
        yearRange: '-100:+0', // Allow selection of years from 100 years ago to the current year
        strictInputParsing: true
    });
    $.validator.addMethod(
        "customDate",
        function (value, element) {
            // Validate the date format using a regular expression
            return value.match(/^\d{2}-\d{2}-\d{4}$/);
        },
        "Please enter a valid date in the format dd-mm-yyyy"
    );
    //     Validate form personal
    $("#form-personal").validate({
        rules: {
            fullName: {
                required: true,
                minlength: 5,
                maxlength: 50,
            },
            gender: {
                required: true,
            },
            birthDay: {
                required: true,
                customDate: true
            },
            phone: {
                required: true,
                minlength: 10,
                maxlength: 11,
            },

        }, messages: {
            fullName: {
                required: "Vui lòng nhập họ tên",
                minlength: "Họ tên phải có ít nhất 5 ký tự",
                maxlength: "Họ tên không được quá 50 ký tự",
            }
            , gender: {
                required: "Vui lòng chọn giới tính",
            },
            birthDay: {
                required: "Vui lòng chọn ngày sinh",
                customDate: "Ngày sinh không hợp lệ"
            },
            phone: {
                required: "Vui lòng nhập số điện thoại",
                minlength: "Số điện thoại phải có ít nhất 10 số",
                maxlength: "Số điện thoại không được quá 11 số",
            },
        },
        dateFormat: "dd-mm-yy",
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
            $(element).parent().children().last().text(error.text());
        },
        highlight: function (element, errorClass, validClass) {
            $(element).addClass(errorClass).removeClass(validClass).attr('required', 'required');
            $(element).parent().children().last().addClass("invalid-feedback");
        },
        unhighlight: function (element, errorClass, validClass) {
            $(element).removeClass(errorClass).addClass(validClass).removeAttr('required');
            $(element).parent().children().last().text("");
        },
        submitHandler: function (form) {
            alert(() => {
                const formData = $(form).serialize();
                $.ajax({
                    url: "/api/user/info",
                    type: 'POST',
                    data: formData,
                    beforeSend: function () {
                        addSpinner();
                    },
                    success: function (response) {
                        Swal.fire({
                            title: "Chúc mừng!",
                            text: "Thông tin cá nhân đã được cập nhập",
                            icon: "success"
                        });
                    },
                    error: function (xhr, status, error) {
                        Swal.fire({
                            title: "Lỗi!",
                            text: "Thông tin cá nhân không cập nhập thành công",
                            icon: "error"
                        });
                    },
                    complete: function () {
                        cancelSpinner();
                    }
                });
            });
            return false;
        }
    })

    //    -------------------------------
    let provinceId, districtId, wardId;
    const inputProvince = $("#inputProvince");
    const inputDistrict = $("#inputDistrict");
    const inputWard = $("#inputWard");
    let addressCustomer = []
    let currentAction = "";
    // Cấu hình validate form địa chỉ
    const addressValidator = {
        rules: {
            province: {
                required: true,
            },
            district: {
                required: true,
            },
            ward: {
                required: true,
            },
            detail: {
                required: true,
            }
        },
        messages: {
            province: {
                required: "Vui lòng chọn tỉnh/thành phố",
            },
            district: {
                required: "Vui lòng chọn quận/huyện",
            },
            ward: {
                required: "Vui lòng chọn phường/xã",
            },
            detail: {
                required: "Vui lòng nhập địa chỉ chi tiết",
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
            $(element).parent().children().last().text(error.text());
        },
        highlight: function (element, errorClass, validClass) {
            $(element).addClass(errorClass).removeClass(validClass).attr('required', 'required');
            $(element).parent().children().last().addClass("invalid-feedback");
        },
        unhighlight: function (element, errorClass, validClass) {
            $(element).removeClass(errorClass).addClass(validClass).removeAttr('required');
            $(element).parent().children().last().text("");
        },
        submitHandler: function (form) {
            const formDataArray = $(form).serializeArray();
            formDataArray.push(
                {
                    name: "provinceName",
                    value: $("#inputProvince option:selected").text()
                },
                {
                    name: "districtName",
                    value: $("#inputDistrict option:selected").text()
                },
                {
                    name: "wardName",
                    value: $("#inputWard option:selected").text()
                },
                {
                    name: "action",
                    value: currentAction
                }
            );
            const formData = $.param(formDataArray);
            alert(() => {
                // Cập nhập địa chỉ
                $.ajax({
                    url: "/api/user/address",
                    type: 'POST',
                    data: formData,
                    beforeSend: function () {
                        addSpinner();
                    },
                    success: function (response) {
                        if (response.status) {
                            Swal.fire({
                                title: "Chúc mừng!",
                                text: "Địa chỉ mới đã được cập nhập",
                                icon: "success"
                            });
                            if (currentAction == "create") {
                                addressCustomer.push(response.address);
                                loadDataToTable();
                                setInterval(() => {
                                    location.reload();
                                }, 1000)
                            } else {
                                const address = addressCustomer.find(address => {
                                    return address.id == response.address.id;
                                });
                                if (address) {
                                    address.province = response.address.province;
                                    address.district = response.address.district;
                                    address.ward = response.address.ward;
                                    address.detail = response.address.detail;
                                }
                                loadDataToTable();
                            }
                        } else {
                            Swal.fire({
                                title: "Lỗi!",
                                text: "Địa chỉ mới không cập nhập thành công",
                                icon: "error"
                            });
                        }

                    },
                    error: function (xhr, status, error) {
                        Swal.fire({
                            title: "Lỗi!",
                            text: "Địa chỉ mới không cập nhập thành công",
                            icon: "error"
                        });
                    },
                    complete: function () {
                        cancelSpinner();
                    }
                });
            })
        }
    }
    const addressForm = $("#form-address").validate(addressValidator);
    // Gán sự kiện tạo mới địa chỉ
    $(".btn__address-create").on("click", () => {
        provinceId = undefined;
        districtId = undefined;
        wardId = undefined;
        setupSelect2().then(() => {
            addressForm.resetForm();
        });
        currentAction = "create";
    })
    // Lấy danh sách địa chỉ
    $.ajax({
        url: "/api/user/address",
        type: 'GET',
        dataType: 'json',
        success: function (response, xhr) {
            if (response.status == 200) {
                const addressList = response.address;
                if (addressList.length > 0) {
                    addressCustomer = addressList;
                    loadDataToTable();
                }
            }
        },
        error: function (xhr, status, error) {
            // setupSelect2();
        }
    });

    // Load danh sách địa chỉ vào UI
    function loadDataToTable() {
        const table = $('#addressList tbody');
        table.empty();
        const htmls = addressCustomer.map(function (address) {
           if(address) {
               return `<tr>
                        <td>${address.id}</td>
                        <td>${address.province}</td>
                        <td>${address.district}</td>
                        <td>${address.ward}</td>
                        <td>${address.detail}</td>
                        <td>
                            <button class="btn btn-primary btn__address-update" data-id="${address.id}" data-bs-toggle="modal" data-bs-target="#modal">
                                 <i class="fa-solid fa-pen-to-square"></i>
                            </button>
                           <button class="btn btn-danger btn__address-delete" data-id="${address.id}" >
                                <i class="fa-solid fa-trash"></i>
                            </button>
                        </td>
                    </tr>`
           }
           return '';
        })
        table.html(htmls.join(''));
        viewModal();
    }

    //    -------------------------------
    //     Xem địa chỉ chi tiết để cập nhập
    function viewModal() {
        $(".btn__address-create").on("click", function () {
            addressForm.resetForm();
            currentAction = "create";
            setupSelect2();
        })
        $(".btn__address-update").on("click", async function () {
            addressForm.resetForm();
            currentAction = "update";
            const id = $(this).data("id");
            if (id) {
                const address = addressCustomer.find(address => {
                    return address.id == id
                });
                if (address) {
                    provinceId = await getProvinceId(address.province);
                    districtId = await getDistrictId(provinceId, address.district);
                    wardId = await getWardCode(districtId, address.ward);
                    $('#inputAddress').val(address.detail);
                }
            } else {
                provinceId = undefined;
                districtId = undefined;
                wardId = undefined;
                $('#inputAddress').val("");
            }
            setupSelect2();
        });

        $(".btn__address-delete").on("click", function () {
            addressForm.resetForm();
            const id = $(this).data("id");
            const element = $(this).closest("tr");
            if (id) {
                alert(() => {
                        $.ajax({
                            url: "/api/user/address/delete",
                            type: 'POST',
                            data: {
                                id: id,
                            },
                            beforeSend: function () {
                                addSpinner();
                            },
                            success: function (response) {
                                Swal.fire({
                                    title: "Chúc mừng!",
                                    text: "Địa chỉ đã được xóa",
                                    icon: "success"
                                });
                                element.remove();
                                addressCustomer = addressCustomer.filter(address => {
                                    return address.id != id;
                                });
                            },
                            error: function (xhr, status, error) {
                                Swal.fire({
                                    title: "Lỗi!",
                                    text: "Địa chỉ không xóa thành công",
                                    icon: "error"
                                });
                            },
                            complete: function () {
                                cancelSpinner();
                            }
                        });
                    }, () => {
                        addressForm.resetForm();
                    }
                    , {
                        notify: "Bạn có muốn xóa địa chỉ này không?",
                        cancel: "Hủy",
                    });
            }
        })
    }

    // Cấu hình select 2
    async function setupSelect2() {
        inputProvince.select2({
            theme: 'bootstrap-5',
            placeholder: 'Chọn tỉnh/thành phố',
            data: [],
        });

        inputDistrict.select2({
            placeholder: 'Chọn quận/huyện',
            theme: 'bootstrap-5',
            data: [],
            language: {
                "noResults": function () {
                    return "Vui lòng chọn tỉnh/thành phố trước";
                }
            },
        });

        inputWard.select2({
            placeholder: 'Chọn phường/xã',
            theme: 'bootstrap-5',
            data: [],
            language: {
                "noResults": function () {
                    return "Vui lòng chọn quận/huyện trước";
                }
            },
        });
        await getProvinces();
        await loadData();
        await addEvent();
    }

    async function addEvent() {
        inputProvince.on('select2:select', async (e) => {
            provinceId = e.params.data.id;
            await loadData();
        });
        inputDistrict.on('select2:select', async (e) => {
            districtId = e.params.data.id;
            await loadData()
        })
    }

    async function loadData() {
        if (provinceId) {
            await getDistricts(provinceId);
        }
        if (provinceId && districtId) {
            await getWards(districtId);
        }
    }

    async function getProvinces() {
        const data = await getProvince();
        inputProvince.empty()
        inputProvince.select2({
            placeholder: 'Chọn tỉnh/thành phố',
            data: ["", ...data],
            dropdownParent: $("#modal")
        }).val(provinceId).trigger('change.select2');
    }

    async function getDistricts(provinceId) {
        const data = await getDistrict(provinceId);
        inputDistrict.empty();
        inputDistrict.select2({
            placeholder: 'Chọn quận/huyện',
            data: ["", ...data],
            language: {
                "noResults": function () {
                    return "Vui lòng chọn tỉnh/thành phố trước";
                }
            },
            dropdownParent: $("#modal")
        }).val(districtId).trigger('change.select2');
    }

    async function getWards(districtId) {
        const data = await getWard(districtId);
        inputWard.empty();
        inputWard.select2({
            placeholder: 'Chọn phường/xã',
            data: ["", ...data],
            language: {
                "noResults": function () {
                    return "Vui lòng chọn quận/huyện trước";
                }
            },
            dropdownParent: $("#modal")
        }).val(wardId).trigger('change.select2');
    }
});