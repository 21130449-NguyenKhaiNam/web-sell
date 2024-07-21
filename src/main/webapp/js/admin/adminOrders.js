import {configSweetAlert2, endLoading, formatCurrency, formatDate, http, startLoading} from "../base.js";
import {getFeeAndLeadTime} from "../shipping.js";

$(document).ready(function () {
    const TYPE_PAYMENT = {
        1: "COD",
        3: "VNPAY"
    }
    const ORDER_STATUS = {
        1: "Chờ xác nhận ",
        2: "Đang sản xuất",
        3: "Đang vận chuyển",
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
            layout: {
                topStart: {
                    buttons: ['copy', 'csv', 'excel', 'pdf', 'print']
                }
            },
            createdRow: function (row, data, dataIndex) {
                if (data.orderStatusId == 5) {
                    $(row).addClass('table-danger');
                }
                if (data.orderStatusId == 4) {
                    $(row).addClass('table-success');
                }
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
            status: data.orderStatusTarget,
            payment: data.paymentMethodTarget.typePayment,
            transaction: data.transactionStatusTarget.typeStatus,
            orderDetails: data.listOrderDetailByOrderId,
            voucher: data?.voucherTarget
        };
        modalView.find(".fullname").text(order.fullName);
        modalView.find(".email").text(order.email);
        modalView.find(".phone").text(order.phone);
        const address = order.detail + ", " + order.ward + ", " + order.district + ", " + order.province;
        modalView.find(".address").text(address);
        modalView.find(".orderId").text(order.id);
        modalView.find(".createAt").text(formatDate(order.dateOrder));
        modalView.find(".voucherApply").text(order?.voucherId || "Không sử dụng mã giảm giá");
        modalView.find(".paymentMethod").text(order.payment);
        modalView.find(".orderStatus").text(order.status.typeStatus);
        modalView.find(".transaction").text(order.transaction);
        loadListOrderDetail(order.orderDetails);
        if (order.status.id == 1 || order.status.id == 2) return;
        startLoading();
        getFeeAndLeadTime({
            province: order.province,
            district: order.district,
            ward: order.ward,
            detail: order.detail
        }).then(data => {
            modalView.find(".payment-fee").text(formatCurrency(data.feeShipping));
            modalView.find(".lead-date").text(formatDate(data.leadDate));
        }).finally(() => {
            endLoading();
        })
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
        modalView.find(".payment-fee").text("");
        modalView.find(".lead-date").text("");
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
        const htmlContent = $("#form-update-status").html();
        Swal.fire({
            ...configSweetAlert2,
            title: 'Cập nhập tình trạng đơn hàng',
            icon: "warning",
            html: htmlContent,
            showCloseButton: true,
            showCancelButton: true,
            focusConfirm: true,
            confirmButtonText: 'Cập nhập!',
            cancelButtonText: 'Đóng',
            didOpen() {
                const modal = $(Swal.getHtmlContainer());
                configModalUpdateStatus(modal, id);
            },
            preConfirm: () => {
                const modal = $(Swal.getHtmlContainer());
                const orderStatusId = modal.find('.orderStatus').val();
                const transactionStatusId = modal.find('.transactionStatus').val();
                return {orderStatusId, transactionStatusId};
            },
        }).then(result => {
            if (result.isConfirmed) {
                const {orderStatusId, transactionStatusId} = result.value;
                updateStatus(id, orderStatusId, transactionStatusId);
            }
        });
    }

    function configModalUpdateStatus(modal, id) {
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
            const orderStatusSelect = modal.find(".orderStatus")
            const transactionStatusSelect = modal.find(".transactionStatus")
            const listAllOrderStatus = [];
            const listAllTransactionStatus = [];

            orderStatusSelect.find("option").each(function () {
                const data = {
                    id: $(this).val(),
                    text: $(this).text(),
                    disabled: $(this).val() <= orderStatusTarget.id
                };
                listAllOrderStatus.push(data);
            });

            transactionStatusSelect.find("option").each(function () {
                const data = {
                    id: $(this).val(),
                    text: $(this).text(),
                    disabled: $(this).val() <= transactionStatusTarget.id
                }
                listAllTransactionStatus.push(data);
            });

            orderStatusSelect.empty();
            transactionStatusSelect.empty();

            orderStatusSelect.select2({
                width: '100%',
                closeOnSelect: true,
                allowClear: true,
                placeholder: 'Chọn tình trạng đơn hàng',
                dropdownParent: $('.swal2-popup'),
                data: listAllOrderStatus,
            }).val(orderStatusTarget.id);

            transactionStatusSelect.select2({
                width: '100%',
                closeOnSelect: true,
                allowClear: true,
                placeholder: 'Chọn tình trạng giao dịch',
                dropdownParent: $('.swal2-popup'),
                data: listAllTransactionStatus,
            }).val(transactionStatusTarget.id);
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
            if (response.code = 200)
                Swal.fire({
                    ...configSweetAlert2,
                    title: 'Cập nhập trạng thái thành công!',
                    icon: 'success',
                    showCloseButton: true,
                }).then(() => {
                    datatable.ajax.reload();
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
                title: 'Cập nhập trạng thất bại!',
                text: "Ràng buộc thay đổi không hợp lệ",
                icon: 'error',
                showCloseButton: true,
            });
        });
    }
});