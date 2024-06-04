<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <jsp:include page="/public/commonLink.jsp"/>
    <link rel="stylesheet" href="<c:url value="/assets/css/admin/admin.css"/>">
    <link rel="stylesheet" href="<c:url value="/assets/css/productBuying.css"/> ">
    <link rel="stylesheet" href="<c:url value="/assets/css/admin/adminProducts.css" />">
    <title>Quản lý sản phẩm</title>
</head>
<body>
<main id="main">
    <section class="content">
        <div class="container-xl">
            <div class="row">
                <div class="col-lg-12">
                    <div>
                        <h1>Danh sách mã giảm giá</h1>

                        <button type="button" id="button-create-product" class="button button__add"
                                data-bs-toggle="modal" data-bs-target="#modal__create">
                            <i class="fa-solid fa-plus"></i>
                            Thêm mã giảm giá
                        </button>
                    </div>
                    <div class="table__wrapper">
                        <table id="table" class="table">
                            <thead>
                            <tr class="table__row">
                                <th class="table__head">Mã giảm giá</th>
                                <th class="table__head">Lượt sử dụng còn lại</th>
                                <th class="table__head">Ngày tạo</th>
                                <th class="table__head">Ngày hết hạn</th>
                                <th class="table__head">Trạng thái</th>
                            </tr>
                            </thead>
                            <tbody>

                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <!-- Modal Create-->
    <div class="modal fade" id="modal__create" tabindex="-1" aria-labelledby="modal__create-label" aria-hidden="true">
        <div class="modal-dialog" style="max-width: 80%">
            <form id="form__create" class="modal-content needs-validation">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="modal__create-label">Thêm mã giảm giá</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-12">
                                <div class="row">
                                    <div class="col-12">
                                        <label for="code" class="form-label">Code</label>
                                        <input type="text" class="form-control" name="code" id="code" value="">
                                        <div class="valid-feedback">

                                        </div>
                                    </div>
                                    <div class="col-12 mt-2">
                                        <label for="description" class="form-label">Mô tả </label>
                                        <textarea class="form-control" name="description" id="description"></textarea>
                                        <div class="valid-feedback">

                                        </div>
                                    </div>
                                    <div class="col-4 mt-2">
                                        <label for="minimumPrice" class="form-label">Giá trị tối thiểu áp dụng</label>
                                        <input type="text" class="form-control" name="minimumPrice" id="minimumPrice"
                                               value="">
                                        <div class="valid-feedback">

                                        </div>
                                    </div>

                                    <div class="col-4 mt-2">
                                        <label for="discountPercent" class="form-label">Phần trăm giảm giá </label>
                                        <input type="text" class="form-control" id="discountPercent" value=""
                                               name="discountPercent">
                                        <div class="valid-feedback">

                                        </div>
                                    </div>
                                    <div class="col-4 mt-2">
                                        <label for="availableTurns" class="form-label text-nowrap">Số lượt sử
                                            dụng</label>
                                        <input type="text" class="form-control" id="availableTurns"
                                               name="availableTurns" value="">
                                        <div class="valid-feedback">

                                        </div>
                                    </div>
                                    <div class="col-6 mt-2">
                                        <label for="expiryDate" class="form-label text-nowrap">Ngày hết hạn</label>
                                        <input type="date" class="form-control" name="expiryDate" id="expiryDate">
                                        <div class="valid-feedback">

                                        </div>
                                    </div>
                                    <div class="col-6 mt-2">
                                        <label for="state" class="form-label text-nowrap">Trạng thái </label>
                                        <select id="state" name="state" class="form-select" aria-label="Chọn">
                                            <option value="-1" id="defaultOption">-- Chọn trạng thái --
                                            </option>
                                            <option value="1">Hoạt động</option>
                                            <option value="2">Khóa</option>
                                        </select>
                                        <div class="valid-feedback">

                                        </div>
                                    </div>
                                    <div class="col-md-12 col-sm-12">
                                        <label for="productId" class="small py-1">Các sản phẩm áp
                                            dụng</label>
                                        <select id="productId" name="productId[]" class="form-select" aria-label="Chọn">
                                            <option value=""></option>
                                        </select>
                                        <div class="valid-feedback">

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
                    <button type="submit" class="btn btn-primary">Lưu</button>
                </div>
            </form>
        </div>
    </div>
</main>
<link href="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/css/select2.min.css" rel="stylesheet"/>
<script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/js/select2.min.js"></script>
<script>
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

        $("#form__create").on("submit", (e) => {
            e.preventDefault()
        })
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
                            theme: 'bootstrap-5',
                            placeholder: "Chọn sản phẩm muốn áp dụng mã giảm giá",
                            multiple: true
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
    })
</script>

</body>
</html>