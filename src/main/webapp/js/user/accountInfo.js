import {alert} from "../notify.js";
import {addSpinner, cancelSpinner} from "../spinner.js";

$(document).ready(function () {
    // Add select 2 for address
    const URL = "https://online-gateway.ghn.vn/shiip/public-api/master-data/";
    const token = '015940b9-e810-11ee-b290-0e922fc774da';
    let provinceId, districtId, wardId;
    const inputProvince = $("#inputProvince");
    const inputDistrict = $("#inputDistrict");
    const inputWard = $("#inputWard");

// ------------------------------------
// Cập nhập avatar
// Validate form upload avatar
    $("#form-avatar").hide();
    $('#open-form').on("click", () => {
        $("#form-avatar").show();
        $("#open-form").hide();
    })


    $.validator.addMethod("singleFile", function (value, element) {
        return this.optional(element) || element.files.length === 1;
    }, "Please select only one file.");

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

//     -------------------------------
//     Cập nhập thông tin cá nhân
//     Validate form personal
    // Date picker jquery plug-in
    $("#inputDate").datepicker({
        dateFormat: 'dd-mm-yy', // Set the date format to "dd-mm-yyyy"
        changeMonth: true, // Allow changing of months
        changeYear: true, // Allow changing of years
        yearRange: '-100:+0', // Allow selection of years from 100 years ago to the current year
        strictInputParsing: true
    });
    $.validator.addMethod(
        "customDate",
        function(value, element) {
            // Validate the date format using a regular expression
            return value.match(/^\d{2}-\d{2}-\d{4}$/);
        },
        "Please enter a valid date in the format dd-mm-yyyy"
    );
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

//     -------------------------------
//     Cập nhập Địa chỉ
    // Call api get address
    $.ajax({
        url: "/api/user/address",
        type: 'GET',
        dataType: 'json',
        success: function (response, xhr) {
            console.log(response);
            if (response.status == 200) {
                const address = response;
                provinceId = address.provinceId;
                districtId = address.districtId;
                wardId = address.wardId;
                $('#inputAddress').val(address.detail);

            }
            setupSelect2();
        },
        error: function (xhr, status, error) {
            setupSelect2();
        }
    });

    // Validate address form
    $("#form-address").validate({
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
                }
            );
            const formData = $.param(formDataArray);
            alert(() => {
                $.ajax({
                    url: "/api/user/address",
                    type: 'POST',
                    data: formData,
                    beforeSend: function () {
                        addSpinner();
                    },
                    success: function (response) {
                        Swal.fire({
                            title: "Chúc mừng!",
                            text: "Địa chỉ mới đã được cập nhập",
                            icon: "success"
                        });
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
    });

    // Sử dụng để gọi api lấy dữ liệu các tỉnh thành
    async function callAjax(param) {
        try {
            const response = await fetch(`${URL}${param}`, {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json',
                    'token': token
                }
            });

            if (!response.ok) {
                throw new Error('Network response was not ok');
            }

            const data = await response.json();
            return data;
        } catch (error) {
            console.error('There was a problem with the fetch operation:', error);
        }
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
        const data = await callAjax("province").then(res => {
            return res.data.map(item => {
                return {
                    id: item.ProvinceID,
                    text: item.ProvinceName
                }
            });
        });
        inputProvince.empty()
        inputProvince.select2({
            theme: 'bootstrap-5',
            placeholder: 'Chọn tỉnh/thành phố',
            data: ["", ...data],
        }).val(provinceId).trigger('change.select2');
    }

    async function getDistricts(provinceId) {
        const data = await callAjax(`district?province_id=${provinceId}`).then(res => {
            return res.data.map(item => {
                return {
                    id: item.DistrictID,
                    text: item.DistrictName
                }
            });
        });
        inputDistrict.empty();
        inputDistrict.select2({
            theme: 'bootstrap-5',
            placeholder: 'Chọn quận/huyện',
            data: ["", ...data],
            language: {
                "noResults": function () {
                    return "Vui lòng chọn tỉnh/thành phố trước";
                }
            },
        }).val(districtId).trigger('change.select2');
    }

    async function getWards(districtId) {
        const data = await callAjax(`ward?district_id=${districtId}`).then(res => {
            return res.data.map(item => {
                return {
                    id: item.WardCode,
                    text: item.WardName,
                }
            });
        });
        inputWard.empty();
        inputWard.select2({
            theme: 'bootstrap-5',
            placeholder: 'Chọn phường/xã',
            data: ["", ...data],
            language: {
                "noResults": function () {
                    return "Vui lòng chọn quận/huyện trước";
                }
            },
        }).val(wardId).trigger('change.select2');
    }
});
