import {configSweetAlert2, formatCurrency, formatDate, http} from "../base.js";

$(document).ready(function () {
    const TYPE_PAYMENT = {
        1: "COD",
        3: "VNPAY"
    }
    const ORDER_STATUS = {
        1: "Đang xác nhận ",
        2: "Đang sản xuất ",
        3: "Đang giao hàng ",
        4: "Giao hàng thành công ",
        5: "Giao hàng thất bại ",
    }
    const searchSelect = $("#searchSelect");
    const
        configDatatable = {
            paging: true,
            processing: true,
            serverSide: true,
            page: 1,
            pageLength: 8,
            lengthChange: false,
            searching: false,
            ordering: false,
            ajax: {
                url: "/api/admin/order/search",
                data: function (d) {
                    d.searchSelect = searchSelect.val();
                    return d;
                },
            }, columns: [
                {data: "id"},
                {
                    data: "dateOrder", render: function (data) {
                        return formatDate(data);
                    }
                },
                {data: "fullName"},
                {
                    data: "paymentMethodId", render: function (data) {
                        return `${TYPE_PAYMENT[data]}`
                    }
                },
                {
                    data: "orderStatusId",
                    render: function (data) {
                        return `${ORDER_STATUS[data]}`;
                    }
                },
                {
                    data: "id", render: function (data) {
                        return `<button type="button" class="btn btn-info" data-bs-toggle="modal" data-bs-target="#modal-view" data-id="${data}"><i class="fa-solid fa-eye"></i></button>`
                    }
                },
                {
                    data: "id", render: function (data) {
                        return `<button type="button" class="btn btn-warning btn-update" data-id="${data}"><i class="fa-solid fa-pen"></i></button>`
                    }
                }
            ],
            language: {
                url: 'https://cdn.datatables.net/plug-ins/1.11.5/i18n/vi.json'
            },
            select: {
                style: 'multi'
            },
            initComplete: function (settings, json) {
                initEventDatatable();
                initFormSearch();
                handleFormSearch();
                configModalSearch();
                configModalView();
            }
        }
    const table = $("#table");
    const datatable = table.DataTable(configDatatable);
    const formSearch = $("#form-search");
    const createdAt = $("#createAt");
    const modalFilter = $("#modal-filter");
    const modalView = $("#modal-view")
    const tableOrderDetail = $("#table-order-detail tbody");
    let objFilter;

    function initEventDatatable() {
        table.find("tbody").on('click', 'button', function (e) {
            e.stopPropagation();
        }).on('click', 'button.btn-update', function () {
            const id = $(this).data("id");
            modalUpdate(id);
        });
        datatable.on("select", function (e, dt, type, indexes) {
            console.log("selected")
        })

    }

    function initFormSearch() {
        createdAt.daterangepicker({
            opens: 'right',
            autoUpdateInput: false,
            showDropdowns: true,
            locale: {
                cancelLabel: 'Hủy ',
                applyLabel: 'Chọn',
                daysOfWeek: ['CN', 'T2', 'T3', 'T4', 'T5', 'T6', 'T7'],
                monthNames: ['Tháng 1', 'Tháng 2', 'Tháng 3', 'Tháng 4', 'Tháng 5', 'Tháng 6',
                    'Tháng 7', 'Tháng 8', 'Tháng 9', 'Tháng 10', 'Tháng 11', 'Tháng 12'],
                firstDay: 1,
            }
        });

        createdAt.on('apply.daterangepicker', function (ev, picker) {
            $(this).val(picker.startDate.format('DD/MM/YYYY') + ' - ' + picker.endDate.format('DD/MM/YYYY'));
        });

        createdAt.on('cancel.daterangepicker', function (ev, picker) {
            $(this).val('');
        });
    }

    function handleFormSearch() {
        formSearch.on("submit", function (e) {
            e.preventDefault();
            const formDataArray = $(this).serializeArray();
            const formDataJson = {};
            $.each(formDataArray, function () {
                if (formDataJson[this.name]) {
                    if (!formDataJson[this.name].push) {
                        formDataJson[this.name] = [formDataJson[this.name]];
                    }
                    formDataJson[this.name].push(this.value || '');
                } else {
                    formDataJson[this.name] = this.value || '';
                }
            });
            if (formDataJson.createdAt) {
                const dateRange = formDataJson.createdAt.split(" - ");
                formDataJson.startDate = dateRange[0];
                formDataJson.endDate = dateRange[1];
            }
            delete formDataJson.createdAt;
            objFilter = formDataJson;
            const queryString = $.param(formDataJson);
            datatable.ajax.url(`/api/admin/order/search?${queryString}`).load();
            modalFilter.modal("hide");
            Swal.fire({
                ...configSweetAlert2,
                icon: 'success',
                title: "Áp dụng bộ lọc tìm kiếm thành công",
                showConfirmButton: false,
                timer: 1500
            });
        });
    }

    function configModalSearch() {
        modalFilter.on("show.bs.modal", function () {
            if (!objFilter) return;
            console.log(objFilter)
            formSearch[0].reset();
            formSearch.find("#contentSearch").val(objFilter.contentSearch);
            formSearch.find("#searchSelect").val(objFilter.searchSelect);
            formSearch.find("#createdAt").val(objFilter.startDate + " - " + objFilter.endDate);
            objFilter?.paymentMethod?.forEach(function (item) {
                $('input[name="paymentMethod"][value="' + item + '"]').prop('checked', true);
            });
            objFilter?.orderStatus?.forEach(function (item) {
                $('input[name="orderStatus"][value="' + item + '"]').prop('checked', true);
            });
        }).on("hidden.bs.modal", function () {
        });
    }

    function configModalView() {
        modalView.on("show.bs.modal", function (event) {
            // Button that triggered the modal
            const button = event.relatedTarget
            // Extract info from data-bs-* attributes
            const id = $(button).data("id");
            getDetail(id)
        }).on("hidden.bs.modal", function () {
            clearData();
        });
    }

    function getDetail(id) {
        http({
            url: '/api/admin/order',
            data: {
                orderId: id,
                action: "seeDetail"
            },
        }).then(function (response) {
            fieldData(response);
        })
    }

    function fieldData(data) {
        const order = {
            ...data.orderTarget,
            status: data.orderStatusTarget.typeStatus,
            payment: data.paymentMethodTarget.typePayment,
            transaction: data.transactionStatusTarget.typeStatus,
            orderDetails: data.listOrderDetailByOrderId,
            voucher: data?.voucherTarget
        };
        modalView.find(".fullname").text(order.fullName);
        modalView.find(".email").text(order.email);
        modalView.find(".phone").text(order.phone);
        modalView.find(".address").text(order.address);
        modalView.find(".orderId").text(order.id);
        modalView.find(".createAt").text(formatDate(order.dateOrder));
        modalView.find(".voucherApply").text(order?.voucherId || "Không sử dụng mã giảm giá");
        modalView.find(".paymentMethod").text(order.payment);
        modalView.find(".orderStatus").text(order.status);
        modalView.find(".transaction").text(order.transaction);
        loadListOrderDetail(order.orderDetails);
    }

    function clearData() {
        modalView.find(".fullname").text("");
        modalView.find(".email").text("");
        modalView.find(".phone").text("");
        modalView.find(".address").text("");
        modalView.find(".orderId").text("");
        modalView.find(".createAt").text("");
        modalView.find(".voucherApply").text("");
        modalView.find(".paymentMethod").text("");
        modalView.find(".orderStatus").text("");
        modalView.find(".transaction").text("");
        tableOrderDetail.html("");

    }

    function loadListOrderDetail(listOrderDetail) {
        const htmls = listOrderDetail.map((item) => (`<tr class="table__row">
                                                                <td class="table__data" style="text-align: left">${item.productName}</td>
                                                                <td class="table__data">${item.colorRequired}</td>
                                                                <td class="table__data">${item.sizeRequired}</td>
                                                                <td class="table__data">${item.quantityRequired}</td>
                                                                <td class="table__data">${formatCurrency(item.price)}</td>
                                                            </tr>`))
        tableOrderDetail.html(htmls.join(""));
    }

    function modalUpdate(id) {
        Swal.fire({
            ...configSweetAlert2,
            title: 'Cập nhập tình trạng đơn hàng',
            icon: "warning",
            html: `
                <form id="form-update-status" class="container-fluid">
                    <div class="row">
                        <div class="col-6">
                           <label for="orderStatus" class="form-label">Tình trạng đơn hàng</label>
                           <select id="orderStatus" name="orderStatus"></select>
                        </div>
                        <div class="col-6">
                            <label for="transactionStatus" class="form-label">Tình trạng giao dịch</label>
                           <select id="transactionStatus" name="transactionStatus"></select>
                        </div>
                    </div>
                </form>
            `,
            showCloseButton: true,
            showCancelButton: true,
            focusConfirm: true,
            confirmButtonText: 'Cập nhập!',
            cancelButtonText: 'Đóng',
            willOpen: () => {
                getStatusCanChange(id)
            },
            preConfirm: () => {
                const orderStatusId = $('#orderStatus').val();
                const transactionStatusId = $('#transactionStatus').val();
                return {orderStatusId, transactionStatusId};
            },
        }).then(result => {
            if (result.isConfirmed) {
                const {orderStatusId, transactionStatusId} = result.value;
                updateStatus(id, orderStatusId, transactionStatusId);
            }
        });
    }

    function getStatusCanChange(id) {
        const orderStatus = $("#orderStatus");
        const transactionStatus = $("#transactionStatus");

        http({
            url: "/api/admin/order",
            type: "GET",
            data: {
                action: "showDialogUpdate",
                orderId: id
            }
        }).then(response => {
            const orderStatusTarget = response.orderStatusTarget;
            const transactionStatusTarget = response.transactionStatusTarget;
            const listAllOrderStatus = response.listAllOrderStatus.map(item => {
                const result = {
                    id: item.id,
                    text: item.typeStatus
                }
                if (item.id <= orderStatusTarget.id) {
                    result.disabled = true
                }
                return result;
            });
            const listAllTransactionStatus = response.listAllTransactionStatus.map(item => {
                const result = {
                    id: item.id,
                    text: item.typeStatus
                }
                if (item.id <= transactionStatusTarget.id) {
                    result.disabled = true
                }
                return result;
            });
            orderStatus.select2({
                width: '100%',
                closeOnSelect: true,
                allowClear: true,
                placeholder: 'Chọn tình trạng đơn hàng',
                dropdownParent: $('.swal2-popup'),
                data: listAllOrderStatus,
            }).val(orderStatusTarget.id).trigger("change");
            transactionStatus.select2({
                width: '100%',
                closeOnSelect: true,
                allowClear: true,
                placeholder: 'Chọn tình trạng giao dịch',
                dropdownParent: $('.swal2-popup'),
                data: listAllTransactionStatus,
            }).val(transactionStatusTarget.id).trigger("change");
        });
    }

    function updateStatus(id, orderStatus, transactionStatus) {
        const data = {
            orderId: id,
        }
        if (orderStatus) {
            data.orderStatusId = orderStatus;
        }
        if (transactionStatus) {
            data.transactionStatusId = transactionStatus
        }
        http({
            url: "/api/admin/order",
            type: "POST",
            data: {
                action: "saveUpdateStatus",
                ...data
            }
        }, false).then(response => {
            if (response.status = 200)
                Swal.fire({
                    ...configSweetAlert2,
                    title: 'Cập nhập trạng thái thành công!',
                    icon: 'success',
                    showCloseButton: true,
                });
            else
                Swal.fire({
                    ...configSweetAlert2,
                    title: 'Cập nhập trạng thất bại!',
                    text: "Ràng buộc thay đổi không hợp lệ",
                    icon: 'error',
                    showCloseButton: true,
                });
        }).catch(error => {
            Swal.fire({
                ...configSweetAlert2,
                title: 'Cập nhập trạng thái thất bại!',
                text: "Đã có lỗi xảy ra",
                icon: 'error',
                showCloseButton: true,
            });
        });
    }
});