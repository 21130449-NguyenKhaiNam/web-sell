import {addParam, convertFormDataToObject, formatDate} from "../base.js";

$(document).ready(function () {
        $.validator.addMethod("notEqual", function (value, element, param) {
            return value !== param;
        }, "Please select an option.");
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
                    data: "createAt",
                    defaultContent: '',
                    render: function (data, type, row) {
                        if (data == null) {
                            return "Không có hạn sử dụng"
                        } else {
                            return formatDate(data)
                        }
                    }
                }, {
                    data: "expiryDate",
                    defaultContent: '',
                    render: function (data, type, row) {
                        if (data == null) {
                            return "Không có hạn sử dụng"
                        } else {
                            return formatDate(data)
                        }
                    }
                }, {
                    data: "state", render: function (data, type, row) {
                        switch (data) {
                            case "1":
                                return "Đang hoạt động"
                            case "2":
                                return "Ẩn "
                        }
                    }
                }, {
                    data: "state", render: function (data, type, row) {
                        let obj = {
                            icon: "",
                            className: ""
                        }
                        if (data == "2")
                            obj = {
                                icon: `<i class="fa-solid fa-unlock"></i>`,
                                className: "btn btn-primary"
                            }
                        else
                            obj = {
                                icon: `<i class="fa-solid fa-lock"></i>`,
                                className: "btn btn-danger"
                            }
                        return `<button class="${obj.className}">${obj.icon}</button>`;
                    }
                }
            ],
            columnDefs: [
                {
                    targets: 5,
                    createdCell: function (td, cellData, rowData, row, col) {
                        $(td).addClass('text-center');
                        $(td).find("button").attr("data-code", rowData.code);
                        $(td).find("button").attr("data-state", rowData.state);
                        $(td).find("button").attr("data-index", row);
                    },
                    orderable: false
                },
                {
                    targets: 4,
                    createdCell: function (td, cellData, rowData, row, col) {
                        if (rowData.availableTurns == 0) {
                            $(td).text("Hết lượt sử dụng ")
                        }
                    },
                },
            ],
            createdRow: function (row, data, dataIndex) {
                if (data.availableTurns == 0) {
                    $(row).addClass('table-warning');
                    return;
                }
                switch (data.state) {
                    case "1":
                        $(row).addClass('table-success');
                        break;
                    case "2":
                        $(row).addClass('table-danger');
                        break;
                }
            },
            language: {
                url: 'https://cdn.datatables.net/plug-ins/1.11.5/i18n/vi.json'
            },
            select: true,
            initComplete: function (settings, json) {
                handleEventDatatable();
            }
        }
        const configSelect2 = {
            placeholder: "Chọn sản phẩm muốn áp dụng mã giảm giá",
            multiple: true,
            language: {
                "noResults": function () {
                    return "Chọn sản phẩm muốn áp dụng mã giảm giá";
                }
            },
            dropdownParent: $("#modal")
        }
        const table = $("#table");
        const button = $("#button");
        let row = {
            rowDataSelected: undefined,//Lưu giữ đối tượng mà ngừoi dùng đã chọn để thực hiện cập lấy thông tin trong chức năng cập nhập
            rowIndexSelected: undefined //Lưu giữ vị trí dòng mà ngừoi dùng đã chọn để thực hiện cập lấy thông tin trong chức năng cập nhập
        };
        const datatable = new DataTable("#table", configDatatable);

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
                let formData = $(form).serialize();
                // Nếu không có dòng nào được chọn thì thực hiện thêm mới
                if (!row.rowDataSelected) {
                    Swal.fire({
                        title: `Bạn có muốn thêm mã giảm giá này không?`,
                        showDenyButton: true,
                        showCancelButton: true,
                        confirmButtonText: "Có",
                        denyButtonText: `Không`
                    }).then((result) => {
                        if (result.isConfirmed) {
                            handleSave(formData, (response) => {
                                if (response.success) {
                                    Swal.fire({
                                        icon: 'success',
                                        title: 'Thêm thành công',
                                    })
                                    // Thêm dòng mới vào bảng
                                    datatable.row.add(
                                        {
                                            ...convertFormDataToObject(form)
                                        }
                                    ).draw(false);
                                    $("#modal").modal("hide");
                                } else {
                                    Swal.fire({
                                        icon: 'error',
                                        title: 'Thêm thất bại',
                                    })
                                }
                            })
                        }
                    });
                } else {
                    Swal.fire({
                        title: `Bạn có muốn cập nhập mã giảm giá này không?`,
                        showDenyButton: true,
                        showCancelButton: true,
                        confirmButtonText: "Có",
                        denyButtonText: `Không`
                    }).then((result) => {
                        if (result.isConfirmed) {
                            // Nếu có dòng nào được chọn thì thực hiện cập nhập
                            // Thêm id vào form data
                            formData = addParam(form, {
                                key: "id",
                                value: row.rowDataSelected.id
                            })
                            handleUpdate(formData, (response) => {
                                if (response.success) {
                                    Swal.fire({
                                        icon: 'success',
                                        title: 'Cập nhập thành công',
                                    })
                                    // Cập nhập dòng mới vào bảng
                                    datatable.row(row.rowIndexSelected).data(
                                        {
                                            ...row.rowDataSelected,
                                            ...convertFormDataToObject(form)
                                        }
                                    ).draw(false);
                                    $("#modal").modal("hide");
                                } else {
                                    Swal.fire({
                                        icon: 'error',
                                        title: 'Cập nhập thất bại',
                                    })
                                }
                            })
                        }
                    });
                }
                return false;
            }
        };
        const select2Element = $("#productId").select2(configSelect2);
        const form = $("#form")
        form.on("submit", (e) => {
            e.preventDefault()
        })
        const formValidate = form.validate(configValidator);
        configModal();

        function configModal() {
            $("#modal").on("hide.bs.modal", function () {
                form.find("input, textarea, select").val("")
                formValidate.resetForm();
            })
            $("#modal").on("show.bs.modal", function () {
                addDataToSelect();
                if (row.rowDataSelected) {
                    getDetail(row.rowDataSelected.code);
                }
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

        function handleUpdate(formData, callback) {
            $.ajax({
                url: "/api/admin/voucher/update",
                type: "POST",
                data: formData,
                success: function (response) {
                    callback(response);
                }
            })
        }

        function handleEventDatatable() {
            // Xử lý sự kiện khi click vào 1 dòng trong bảng
            datatable.on('select', function (e, dt, type, indexes) {
                row = {
                    rowDataSelected: datatable.row(indexes).data(),
                    rowIndexSelected: datatable.row(indexes).index()
                }
                button.text("Cập nhật mã giảm giá");
            }).on('deselect', function (e, dt, type, indexes) {
                row = {
                    rowDataSelected: undefined,
                    rowIndexSelected: undefined,
                }
                button.text("Thêm mã giảm giá")
            });
            table.find("tbody").on('click', 'button', function (e) {
                e.stopPropagation();
                const code = $(this).data("code");
                const state = $(this).data("state");
                const index = $(this).data("index");
                if (state == "1") {
                    handleEventVisible("hide", code, index);
                } else {
                    handleEventVisible("visible", code, index);
                }
            });
            table.on('draw.dt', function () {
                row = {
                    rowDataSelected: undefined,
                    rowIndexSelected: undefined
                }
            });
        }

        function getDetail(id) {
            $.ajax({
                url: "/api/admin/voucher/detail",
                type: "GET",
                data: {
                    code: row.rowDataSelected.code
                },
                success: function (response) {
                    if (response) {
                        fieldDataVoucher(response);
                    }
                }
            })
        }

        function fieldDataVoucher(data) {
            const voucher = data.voucher;
            const listIdProduct = data.listIdProduct;
            form.find("#code").val(voucher.code);
            form.find("#description").val(voucher.description);
            form.find("#minimumPrice").val(voucher.minimumPrice);
            form.find("#discountPercent").val(voucher.discountPercent);
            form.find("#availableTurns").val(voucher.availableTurns);
            form.find("#expiryDate").val(voucher.expiryDate);
            form.find("#state").val(voucher.state);
            addDataToSelect(listIdProduct);
        }

        function handleEventVisible(type, code, index) {
            Swal.fire({
                title: `Bạn có muốn ${type == "visible" ? "hiện thị" : "ẩn"} mã giảm giá này không?`,
                showDenyButton: true,
                confirmButtonText: "Có",
                denyButtonText: `Không`
            }).then((result) => {
                if (result.isConfirmed) {
                    $.ajax({
                        url: "/api/admin/voucher/visible",
                        data: {
                            code: code,
                            type: type,
                        },
                        type: "POST",
                        success: function (data) {
                            if (data.success) {
                                Swal.fire({
                                    icon: 'success',
                                    title: 'Cập nhập thành công ',
                                })
                                datatable.row(index).data({
                                    ...datatable.row(index).data(),
                                    state: type == "hide" ? "1" : "2"
                                }).draw(false);
                            } else {
                                Swal.fire({
                                    icon: 'error',
                                    title: 'Cập nhập thất bại',
                                })
                            }
                        },
                        error: function () {
                            Swal.fire({
                                icon: 'error',
                                title: 'Có lỗi xảy ra',
                            })
                        }
                    })
                }
            });
        }

        // Tải tất cả sản phẩm vào select2
        function addDataToSelect(selectedValues) {
            // Clear existing options if needed
            select2Element.empty();
            // Lấy danh sách sản phẩm
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
                        });
                        // Loop through the data array and create new options
                        data.forEach(function (item) {
                            const isSelected = selectedValues ? selectedValues.includes(item.id) : false;
                            const newOption = new Option(item.text, item.id, isSelected, isSelected);
                            select2Element.append(newOption);
                        });

                        // Refresh the Select2 UI to reflect the changes
                        select2Element.trigger('change');
                    }
                }
            })
        }
    }
)