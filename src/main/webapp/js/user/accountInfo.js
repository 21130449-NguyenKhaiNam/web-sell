$(document).ready(function () {
    // Add select 2 for address
    var URL = "https://online-gateway.ghn.vn/shiip/public-api/master-data/"
    let provinceId, districtId, wardId;
    const inputProvince = $("#inputProvince");
    const inputDistrict = $("#inputDistrict");
    const inputWard = $("#inputWard");

    async function callAjax(param) {
        try {
            const response = await fetch(`${URL}${param}`, {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json',
                    'token': '015940b9-e810-11ee-b290-0e922fc774da'
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

    setupSelect2();

    function setupSelect2() {
        inputProvince.select2({
            placeholder: 'Chọn tỉnh/thành phố',
            data: [],
        });

        inputDistrict.select2({
            placeholder: 'Chọn quận/huyện',
            data: [],
            language: {
                "noResults": function () {
                    return "Vui lòng chọn tỉnh/thành phố trước";
                }
            },
        });

        inputWard.select2({
            placeholder: 'Chọn phường/xã',
            data: [],
            language: {
                "noResults": function () {
                    return "Vui lòng chọn quận/huyện trước";
                }
            },
        });
        getProvinces();
        loadData();
        addEvent();
    }

    function addEvent() {
        inputProvince.on('select2:select', (e) => {
            provinceId = e.params.data.id;
            loadData();
        });
        inputDistrict.on('select2:select', (e) => {
            districtId = e.params.data.id;
            loadData()
        })
    }

    function loadData() {
        if (provinceId) {
            getDistricts(provinceId);
        }
        if (districtId) {
            getWards(districtId);
        }
        if (provinceId && districtId) {
            getWards(districtId);
        }
    }

    function getProvinces() {
        callAjax("province").then(res => {
            const data = res.data.map(item => {
                return {
                    id: item.ProvinceID,
                    text: item.ProvinceName
                }
            });
            inputProvince.empty()
            inputProvince.select2({
                placeholder: 'Chọn tỉnh/thành phố',
                data: provinceId ? data : ["", ...data],
            });
        });
    }

    function getDistricts(provinceId) {
        callAjax(`district?province_id=${provinceId}`).then(res => {
            const data = res.data.map(item => {
                return {
                    id: item.DistrictID,
                    text: item.DistrictName
                }
            });
            inputDistrict.empty();
            inputDistrict.select2({
                placeholder: 'Chọn quận/huyện',
                data: districtId ? data : ["", ...data],
                language: {
                    "noResults": function () {
                        return "Vui lòng chọn tỉnh/thành phố trước";
                    }
                },
            });
        });
    }

    function getWards(districtId) {
        callAjax(`ward?district_id=${districtId}`).then(res => {
            const data = res.data.map(item => {
                return {
                    id: item.WardCode,
                    text: item.WardName,
                }
            });
            inputWard.empty();
            inputWard.select2({
                placeholder: 'Chọn phường/xã',
                data: wardId ? data : ["", ...data],
                language: {
                    "noResults": function () {
                        return "Vui lòng chọn quận/huyện trước";
                    }
                },
            });
        });
    }

    $("#inputDate").datepicker({
        dateFormat: 'dd-mm-yy', // Set the date format as needed
        onSelect: function (dateText) {
            // Optionally, you can perform some action when a date is selected
        }
    });

    // ------------------------------------
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
            var formData = new FormData(form);
            $.ajax({
                url: "/upload-avatar",
                type: "POST",
                data: formData,
                processData: false,
                contentType: false,
                xhr: function () {
                    const xhr = new window.XMLHttpRequest();
                    // Upload progress
                    xhr.upload.addEventListener("progress", function (evt) {
                        if (evt.lengthComputable) {
                            const percentComplete = (evt.loaded / evt.total) * 100;
                            // Update progress bar
                            $("#progress").css("width", percentComplete + "%");
                            $("#progress").text(percentComplete.toFixed(2) + "%");
                            $("#progress").delay(5000).fadeOut('slow', function () {
                                $(this).remove();
                            });
                        }
                    }, false);
                    return xhr;
                },
                success: function (response) {
                    // Handle success response

                },
                error: function (xhr, status, error) {
                    // Handle error response
                    console.error("Upload error:", error);
                }
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
//     Validate form update info
    $("#form-info").validate({
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
            },
            phone: {
                required: true,
                minlength: 10,
                maxlength: 11,
                pattern:/(03|05|07|08|09|01[2|6|8|9])+([0-9]{8})\\b/,
            },
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
        }, messages: {
            fullName: {
                required: "Vui lòng nhập họ tên",
                minlength: "Họ tên phải có ít nhất 5 ký tự",
                maxlength: "Họ tên không được quá 50 ký tự",
                pattern:"Vui lòng nhập đúng số điên thoại"
            }
            , gender: {
                required: "Vui lòng chọn giới tính",
            },
            birthDay: {
                required: "Vui lòng chọn ngày sinh",
            },
            phone: {
                required: "Vui lòng nhập số điện thoại",
                minlength: "Số điện thoại phải có ít nhất 10 số",
                maxlength: "Số điện thoại không được quá 11 số",
                number: "Số điện thoại phải là số",
            },
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
            const formData = $(form).serialize();
            $.ajax({

            });
        }
    })
});
