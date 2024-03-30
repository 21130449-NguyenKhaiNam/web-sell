$(document).ready(function () {
    var URL = "https://online-gateway.ghn.vn/shiip/public-api/master-data/"
    let provinceId, districtId;
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
                data: data,
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
                data: data,
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
                data: data,
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
                    console.log("Upload successful:", response);
                },
                error: function (xhr, status, error) {
                    // Handle error response
                    console.error("Upload error:", error);
                }
            });
            return false; // Prevent form submission
        }
    })
});